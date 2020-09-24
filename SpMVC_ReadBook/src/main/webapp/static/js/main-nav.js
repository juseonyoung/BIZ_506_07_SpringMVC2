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

  $("#main-nav li").click(function () {
    let menu_text = $(this).text();
    if (menu_text === "도서정보") {
      document.location.href = `${rootPath}/books`;
    }
  });
});
