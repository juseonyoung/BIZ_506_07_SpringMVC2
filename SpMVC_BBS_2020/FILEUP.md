# 사진이미지를 업로드하여 이미지 갤러리 BBS로 변환

* spring framework에서는 기본적으로 text 위주의 project만 지원한다
* 파일의 종류에 관계없이 업로드하기위해 apache에서 지원하는 dependency 도움을 받아야 한다.
* commons-io, commons-fileupload


## file upload 할 수 있도록 서버 context를 설정해 주어야 한다. 
* file-context.xml 파일을 설정하여 file upload 설정하는 데 
  context-param에서 핸들링할 수 있도록 root-context.xml과 같은 위치에 작성한다. 
* file 선택 하기 위해 input type="file" tag를 추가하고
* 폼 태그에 enctype="multipart/form-data" 를 추가한다.
* 업로드하는 파일의 타입을 제한하고 싶을 때 accept="image/*" 설정해주면 된다.
* 파일의 확장자를 검사하여 제한하고 싶을 때 accept= .git, .jpg, .jpeg, .png 등
* 미디어타입과 확장자를 검사하여 제한하고 싶을 때 accept=video/mp4, audio/mp3, image/png

## 첨부파일이 있는 게시판에서 CRUD 구현
1. insert
* 파일을 업로드할 때 파일이름 UUI 부착하기
* 파일을 UUID를 부착하여 업로드를 실행하고, UUID가 부착된 파일이름을 게시글의 file명 필드에 추가한 후 insert를 수행

2. delete
* 업로드된 첨부파일을 삭제작업 수행 우선
* seq값으로 게시글을 가져오고 file명 필드에서 첨부파일 이름을 getter하여 upload폴더의 파일 삭제
* 그리고 나서 해당하는 게시글을 삭제
* 이 작업을 생략하게 되면 게시글은 없는데 필요없는 파일들이 upload 폴더에 남아있게 된다. 

3. update
* seq 값 게시글을 가져와서 write.jsp에 보이고 변경할 데이터 입력받기
* 파일이 첨부가 되면, 기존의 파일을 삭제하고, 새로운 파일을 upload하고, 새로 업로드된 파일이름을 다시 
* file 명 필드에 저장한 후 update를 수행해야 한다. 
* 파일이 첨부가 안되었으면 기존의 file명 필드에 저장된 값이 변경되지 않도록 하면서 update 수행










