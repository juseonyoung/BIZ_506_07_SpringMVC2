$(function () {
  // 화면에 scroll이 이동하면
  $(window).scroll(function () {
    let headerHeight = $("header").height(); // header tag의 높이값
    // 화면을 세로 방향으로 스크롤 할 떄 윈도우 화면의 최 상단 좌표 가져오기
    let windowTop = $(window).scrollTop();
    //  nav가 header부분을 넘겨버리면(화면 아래로 넘어가면)
    if (windowTop > headerHeight) {
      //  네비 부분의 포지션을 고정시키고 가장 위쪽으로 달라붙에 만든다
      $("#main-nav").css("position", "fixed");
      $("#main-nav").css("top", "0");
    } else {
      $("#main-nav").css("position", "relative");
    }
    console.log(headerHeight, windowTop);
  });

  $("li#menu-home").click(function () {});
  $("li#menu-home").click(function () {});
  $("li#menu-home").click(function () {});
  $("li#menu-home").click(function () {});
  //이렇게 일일이 하면 번거로움

  // nav 의 li tag 클릭했을 때
  $("#main-nav li").click(function () {
    let menu_text = $(this).text();
    let menu_id = $(this).attr("id"); //아이디 값 추출해서

    if (menu_text === "도서정보") {
      document.location.href = `${rootPath}/books`;
    } else if (menu_id === "menu-home") {
      document.location.href = `${rootPath}/`;
    } else if (menu_id === "menu-read-book") {
      document.location.href = `${rootPath}/read/`;
    } else if (menu_id === "menu-join") {
      document.location.href = `${rootPath}/member/join`;
    }
  });
});
