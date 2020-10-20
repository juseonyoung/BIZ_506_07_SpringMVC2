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

