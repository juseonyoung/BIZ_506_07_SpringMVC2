package com.biz.book.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.biz.book.config.NaverSecret;
import com.biz.book.model.BookVO;


@Service
public class NaverService {

	/*
	 * naver API를 통하여 도서명을 보내고 그 결과를 json형태로 수신하여 
	 * parsing 처리를 수행하는 서비스 클래스이다
	 * naver가 server가 되고 ReadBook 프로젝트가 client가 된다.
	 * 
	 * 1. naver API 에 보낼 쿼리 문자열이 포함된 URL 생성
	 * 2. naver API 에서 보내온 문자열을 JSON 객체로 변환
	 * 3. 파싱 수행하고 
	 * 4. BookVO에 담고
	 * 5. List<BookVO>에 담기
	 * 
	 */
	
	// 도서명을 매개변수로 받아서 queryURL 생성
	public String queryURL(String category, String bookName) {
		
		String queryURL =NaverSecret.NAVER_BOOK_JSON; //기본값은 book
		if(category.equalsIgnoreCase("NEWS")) {
			
			queryURL = NaverSecret.NAVER_NEWS_JSON;
		}else if (category.equalsIgnoreCase("MOVIE")) {
			queryURL = NaverSecret.NAVER_MOVIE_JSON;
		}
		
		String encodeText =null;
		try {
			// 한글 검색을 위해서 검색어를 UTF-8로 엔코딩 처리해주어야 한다. 
			encodeText = URLEncoder.encode(bookName.trim(),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 // 여기까지 필수문자 url?query=자바
		queryURL += String.format("?query=%s",encodeText); 
		
		// 한번에 조회할 테이터 개수를 지정할 수 있다
		queryURL += "&display=+10"; //한번에 10개
		
		return queryURL;
	}
	
	// queryURL을 naverAPI에게 보내고 데이터를 수신하는 method가 된다
	public String getNaverBook(String queryURL) {
		// queryURL 문자열을 http 프로토콜을 통해서 전송하기 위한 형식으로 만드는 클래스
		URL url= null;
		
		// http 프로토콜을 사용하여 데이터 주고받는 도우미 클래스 (헬퍼 클래스)
		HttpURLConnection httpConn = null;
		
		try {
			url = new URL(queryURL);
			httpConn = (HttpURLConnection) url.openConnection(); //add cast로 형변환
			
			httpConn.setRequestMethod("GET"); //http를 get방식으로 요청하겟다
			
			// http 프로토콜에 X-Naver-Client라는 변수로 클라이언트 id값 저장(심는다)
			httpConn.setRequestProperty("X-Naver-Client-Id", NaverSecret.NAVER_CLIENT_ID);
			
			httpConn.setRequestProperty("X-Naver-Client-Secret", NaverSecret.NAVER_CLIENT_SECRET);
			
			// 내가 URL을 만들고 , get방식으로 요청하면서 property에 클라이언트 아이디와 client secret를 저장하여 보냈는데
			// 나에게 응답을 해줄래??? 라고 묻는 것
			int resCode =httpConn.getResponseCode();
			InputStreamReader is =null;
			BufferedReader buffer = null;
			
			if(resCode == 200) {
				// naver가 정상적으로 응답을 할 것이다
				// 200코드가 오면 네이버로부터 데이터 수신할 준비를 해야 한다. 
				// bufferedReader 통해 받음 혼자서 못받기 때문에 InputStreamReader가 도와줌
				
				is = new InputStreamReader(httpConn.getInputStream());
			} else {
				is= new InputStreamReader(httpConn.getErrorStream());
			}
				
				// InputStereamReader와 bufferredreader를 파ㅓ이프로 연결
				
				buffer= new BufferedReader(is);
				//StringBuffer sBuffer = new StringBuffer();
				String sBuffer="";
				String reader = new String();
				while((reader=buffer.readLine()) != null){
					//sBuffer.append(reader);
					sBuffer += reader;
				}
				/*while(true) {
					reader = buffer.readLine();
					if(reader==null) { //naver가 buffer를 다 보낼때까지 sBuffer에 차곡차곡 담아라
						break;
					}
					sBuffer.append(reader);
				}
				*/
				return sBuffer.toString();
				
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	// jsonString을 파싱하여 object(VO)로 바꾸는 기능
	public List<BookVO> getJsonObject(String jsonString ) {
		
		List<BookVO> bookList = new ArrayList<>();
		JSONParser jParser = new JSONParser();
		try {
			// jsonParser 도구 사용하여 JSON형태의 문자열을 jsonobject(객체)로 변환하기 
			JSONObject jObject = (JSONObject) jParser.parse(jsonString);
			JSONArray jArray = (JSONArray) jObject.get("items");
			
			int size = jArray.size();
			for(int i=0; i< size ; i++) {
				JSONObject jo = (JSONObject) jArray.get(i);
				/*
				 * BookVO bookVO= new BookVO(); // bookVO = new
				 * BookVO(title,link,image,author...);
				 * bookVO.setTitle(jo.get("title").toString());
				 * bookVO.setImage(jo.get("image").toString());
				 * bookVO.setLink(jo.get("link").toString());
				 */
				
				// VO의 @Builder 설정함으로써 VO객체를 생성할 때 builder 패턴을 사용할 수 있다.
				// Gof 패턴 중 생성자 패턴 중 1가지 
				
				
				BookVO bookVO = BookVO.builder()
						.title(jo.get("title").toString())
						.image(jo.get("image")== null? "noImage":jo.get("image").toString()) //null이면 공백으로 
						.link(jo.get("link").toString())
						.description(jo.get("description")== null? "noImage":jo.get("description").toString())
						.build();
						
				bookList.add(bookVO);
			}
			
			return bookList;
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	
	
	
}
