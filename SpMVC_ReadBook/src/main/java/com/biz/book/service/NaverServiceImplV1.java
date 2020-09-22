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

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("naverServiceV1")
public class NaverServiceImplV1 implements NaverService<BookVO>{

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
		
		String queryURL =NaverSecret.NAVER_BOOK_XML; //기본값은 book
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
	private String getNaverBook(String queryURL) {
		// queryURL 문자열을 http 프로토콜을 통해서 전송하기 위한 형식으로 만드는 클래스
		URL url= null;
		
		// http 프로토콜을 사용하여 데이터 주고받는 도우미 클래스 (헬퍼 클래스)
		HttpURLConnection httpConn = null;
		
		try {
			url = new URL(queryURL);
			httpConn = (HttpURLConnection) url.openConnection(); //add cast로 형변환
			
			httpConn.setRequestMethod("GET"); //http를 get방식으로 요청하겟다
			
			// http 프로토콜에 X-Naver-Client라는 변수로 클라이언트 id값 저장(심는다)
			httpConn.setRequestProperty("X-Naver-Client-Id", NaverSecret.NAVER_CLIENT_ID); //정상적으로 인증 되었냐? 대소문자 구별 중요 
			
			httpConn.setRequestProperty("X-Naver-Client-Secret", NaverSecret.NAVER_CLIENT_SECRET);
			
			// 내가 URL을 만들고 , get방식으로 요청하면서 property에 클라이언트 아이디와 client secret를 저장하여 보냈는데
			// 나에게 응답을 해줄래??? 라고 묻는 것
			int resCode =httpConn.getResponseCode(); 
			//response 코드
			// 
			InputStreamReader is =null;
			BufferedReader buffer = null;
			
			if(resCode == 200) {
				// naver가 정상적으로 응답을 할 것이다
				// 200코드가 오면 네이버로부터 데이터 수신할 준비를 해야 한다. 
				// bufferedReader 통해 받음 혼자서 못받기 때문에 InputStreamReader가 도와줌
				// 200 정상
				// 3xx 요청은 정상 수신했으나 보낼 데이터가 없거나 이미 보냈기 때문에 다시 보내지 않겠다
				// 3xx redirect 했다
				// 3xx 직전에 보낸 데이터가 변화가 없으니 그대로 사용해라 
				// 400 요청정보가 문제 있음 
				// 404 오류 : url, uri 요청주소가 서버에서 처리할 end point 가 없다
				// 405 : 요청주소는 있느ㅏ method(get,post)가 지정되지 않앗다 
						// 브라우저 창에 http://localhost:8080/book/input 라고 request했는데 서버에 @RequestMapping(value="/input", method=POST)만
						// 있을 때! get로 요청했는데 post만 있는 경우
				
				// form 의 action="/input" method=POST 로 지정된 데이터를 submit했는데 서버에 
				// @RequestMapping(value="/input", method=GET만 있을 때
				// 403 : 요청권한이 없다. 인증이 잘못 되었거나, Roll(역할)이 잘못 되었다.
				// 400 : form에 데이터를 입력하고 서버로 전송했을 때 controller의 매개변수 차원에서 문제가 발생했을 때
				// 		<input name="age">라는 input박스에 값을 입력하지 않고 submit했는데 public String input(int age) 라고
				// controller method의 매개변수로 설정해 두었을 때 
				
				// spring Dispatcher는 age 변수에 담긴 값을 int 형으로 형변환을 시도한다. 
				// 이때 input box에 아무런 값이 없으면 age = Integer.valueOf("") 코드가 실행되는 것과 같다. 
				// 이럴 때 내부적으로 exception이 발생하고 res code로 400번을 응답한다. 
				
				//500 : Server Internal Error
				//		사용자의 요청을 처리하는 과정에서 Controller, Service, Dao 등등 코드를 실행하는 도중 
				// 		어떠한 원인으로 exception이 발생했을 때 보내는 코드 
				//		오류메시지를 제일 하단부터 거꾸로 검토해 나가자! Error stack 메시지는 발생된 순서가 역순으로 나타나기 때문이다 
				
				is = new InputStreamReader(httpConn.getInputStream());
			} else {
				is= new InputStreamReader(httpConn.getErrorStream());
			}
				
				// InputStereamReader와 bufferredreader를 파ㅓ이프로 연결
				
				buffer= new BufferedReader(is);
				StringBuffer sBuffer = new StringBuffer();
				
				// 네이버가 보낸 payload(response data)를 한개의 문자열로 통합하여 수신한다. 
				//String sBuffer="";
				String reader = new String();
				while((reader=buffer.readLine()) != null){
					sBuffer.append(reader);
					//sBuffer += reader;
				}
				/*while(true) {
					reader = buffer.readLine();
					if(reader==null) { //naver가 buffer를 다 보낼때까지 sBuffer에 차곡차곡 담아라
						break;
					}
					sBuffer.append(reader);
				}
				*/
				buffer.close();
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
	private List<BookVO> getJsonObject(String jsonString ) {
		
		List<BookVO> bookList = new ArrayList<>();
		
		/*
		 * json클래스를 사용하여 json문자열을 객체로 변환하기
		 * 1. jsonparser를 사용하여 JSONObject로 변환
		 * 2. JSONObject에서 "items"기준으로 잘라서 JSONArray로 변환
		 * 3. JSONArray를 for문으로 반복하면서 각 요소를 다시 JSONObject로 변환
		 * 4. 요소로 변환된 JSONObject에서 각각의 항목을 추출하여 VO에 담기 
		 * 5. VO를 리스트에 add() 하기
		 * 
		 */
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
				
				
				/*
				 * 빌드패턴 사용하여 bookVO 객체 생성
				 * 일반적인 VO 객체를 생성하고 데이터를 setting 하는 방법
				 * 1. 비어있는 VO객체를 생성하고 vo= new VO(); 생성자에 아무것도 넣지않고
				 *   setter method를 사용하여 데이터를 입력하는 방법
				 *   
				 * 2. 생성자에 값을 설정하고 VO객체를 생성 vo = new VO(값1, 값2, 값3...)
				 * 		가.	생성자에 값을 설정(주입)하고 VO객체를 생성하는 방법은 데이터의 순서가 완전히 개발자 책임이다. 
				 * 			혹여 순서가 바뀐채로  VO객체가 생성되면 이후에 발생하는 모든 책임을 개발자가 져야 한다. 
				 * 		나. 일부 데이터만 사용하여 객체를 생성하려면 생성자를 필요한 매개변수만 있는 상태로 또다시 만들어야 한다. 
				 * 			vo = new VO(값1, 값2) : VO(String 값1, String 값2)
				 * 			vo = new VO(값1, 값2, 값3) : VO(String 값1, String값2, String 값3)
				 * 			이렇게 하면 생성자의 중복 선언 발생
				 * 	
				 * 3. 빌더 패턴을 사용한 객체 생성!!!!!!!!!!!!!!!!!!!
				 * 		VO = VO.builder()
				 * 				.변수1(값1) //jo통해 값 가져와 
				 * 				.변수2(값2)
				 * 				.변수3(값3)
				 * 				.build();
				 * 		가. 생성자를 통해서 객체 필요할 때 즉시 생성한다. 
				 * 		나. 생성자에 매개변수를 주입하는 방식인데 여기서는 필요한 데이터만 매개변수로 주입가능
				 * 			모든 변수를 생성자에 주입할 필요가 없다. 변수가 10개 라도 2~3개만 추가 가능
				 * 		다. 매개변수 주입시 set*()와 같은 메서드 사용하지 않고 매개변수의 이름을 통해 직접 설정이 가능하다.
				 * 		라. 객체를 생성할 때 객체 chaining 코딩을 사용할 수 있다. 
				 * 			객체.변수1().변수2().변수3().변수4().변수5() 이런식으로... 
				 */
				BookVO bookVO = BookVO.builder()
						.title(jo.get("title").toString()) //"toString" 메서드는 객체가 가지고 있는 정보나 값들을 문자열로 만들어 리턴하는 메소드 입니다.

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

	@Override
	public String getNaverSearch(String queryURL) {
		// TODO Auto-generated method stub
		return this.getNaverBook(queryURL);
	}

	@Override
	public List<BookVO> getNaverList(String jsonString) {
		// TODO Auto-generated method stub
		return this.getJsonObject(jsonString);
	}
	
	
	
	
	
	
	
}
