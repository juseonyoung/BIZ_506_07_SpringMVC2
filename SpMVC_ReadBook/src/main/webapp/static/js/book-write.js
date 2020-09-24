$(function () {
  $("section#book-modal").css("display", "none");

  $("#btn-save").click(function () {
    // 네이버로부터 조회한 도서정보를 저장하는 까닭에 유효성 검사는 일단 생략한다.
    $("form").submit();
  });

  $("#naver-search").click(function () {
    let title = $("#title").val();
    if (title === "") {
      alert("도서명을 입력한 후 검색하세요");
      $("#title").focus();
      return false;
    }
    // ajax 사용하여 서버에 네이버 검색 요청
    $.ajax({
      // ajax로 서버의 /naver/search URL에 POST로 요청을 하면서
      // search_text 변수에 title 변수에 담긴 값을 담아 전달하고

      url: `${rootPath}/naver/search`,
      method: "POST",
      data: { search_text: title },
      // 서버가 데이터 조회를 수행한 후 view(HTML코드) 코드를 return 하면 그 view코드를
      // 결과를 #search-result div box에 채워서 보여달라
      success: function (result) {
        $("#search-result").html(result); //전체를 다 보이지말고 박스 안에 보여라
        // 뷰파일을 result 박스에 html형식으로 overflow:auto 박스 넘치면 스크롤 생기게 해라
      },
      error: function (error) {
        alert("서버 통신 오류!");
      },
    });

    $("section#book-modal").css("display", "block");
  });

  // x 표시 클릭했을 때 모달 창 닫기
  $("div#modal-header span").click(function () {
    $("#book-modal").css("display", "none");
  });

  //동적으로 구현된 HTML에 이벤트 핸들링 설정하기
  // 현재 dectment가 생성되는 동안에 없던 태그를 JS코드에서 동적으로 생성햇을 경우
  // 화면이 그려지는 것은 아무 문제가 없으나

  //  JS에서 이벤트핸들러를 설정할때 아직화면에 없는 ㄴ태그를 연결하면 무시해버리고
  // 사후에(html 문서완성후) JS  코드로 생성할 태그(is, class)이벤트를 설정하려면 자체에 설정하지 않고
  // 가장 상위 객체인 document에 on 함수를 사용하여 이벤트를 설정한다.
  // $(document)on("event","대상",function(){} 이런 형식으로...

  //주의사항
  //$(selector).click(function(){
  //만약 기존에 selector에 click event가 설정되어있으면 기존의 이벤트를 덮어쓰기 한다

  //$(document).on("event","selector")
  // 만약 기존에 selector에 대한 click event가 설정되어 있더라도 중복정의 된다.
  // 동적으로 여는곳에서는 $(document).on() 사용하여 event핸들러 설정
  // 동적으로 열리는 곳에서 절대 사용하면 안된다.
  // 동적으로 열리는 곳에서는 $(selector).click() 를 사용하자

  $(document).on("click", "div.book-select", function () {
    //문서 전체에 event 걸어버림
    let isbn = $(this).data("isbn");

    $.ajax({
      url: `${rootPath}/api/isbn`,
      method: "POST",
      data: { search_text: isbn },
    })
      .done(function (bookVO) {
        //alert(JSON.stringify(bookVO)) //가져온 데이터를 input에 쏟아부어
        $("#seq").val(bookVO.seq);
        $("#title").val(bookVO.title);
        $("#link").val(bookVO.link);
        $("#image").val(bookVO.image);
        $("#author").val(bookVO.author);
        $("#price").val(bookVO.price);
        $("#discount").val(bookVO.discount);
        $("#publisher").val(bookVO.publisher);

        let isbn = bookVO.isbn;
        // isbn 변수에 들어있는 문자열 중에서 html tag 구조를 가진 단어가 있으면 무조건 제거하라
        isbn = isbn.replace(/(<([^>]+)>)/gi, "");
        //isbn = isbn.substring(isbn.length - 15, 13);
        isbn = isbn.substring(isbn.length - 13);
        $("#isbn").val(isbn);

        $("#description").val(bookVO.description);
        $("#pubdate").val(bookVO.pubdate);
        $("#buydate").val(bookVO.buydate);
        $("#buyprice").val(bookVO.buyprice);
        $("#buystore").val(bookVO.buystore);
        $("#section$book-modal").css("display", "none");
      })
      .fail(function (xhr, textStatus, error) {
        alert("서버와 통신오류");
      });
  });
});
