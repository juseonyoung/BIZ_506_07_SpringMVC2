$(function () {
  $("td.book-title").click(function () {
    let seq = $(this).data("seq");

    // query String 방식
    //document.location.href = `${rootPath}/books/detail?seq=${seq}`;

    // path variable 방식(최근)
    document.location.href = `${rootPath}/books/detail/${seq}`;
  });
});
