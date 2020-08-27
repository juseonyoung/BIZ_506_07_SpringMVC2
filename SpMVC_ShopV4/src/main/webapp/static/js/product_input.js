
$(function () {
  /*
    js, jq에서 사용하는 evert 핸들러
    이벤트 핸들러란? 사용자가 화면에 보이는 어떤 대상을 클릭했을 때 또는 어떤 행위를 했을 때 까지 기다리고 있다가
    사용자가 클릭을 하면 function()으로 지정된 코드를 실행하도록 만든 코드블럭
    제일많이사용하는게 클릭하는 이벤트
    js의 콜백 함수라고 한다. 
    */

  $("#p_code_gen").click(function () {
    alert("key");
    $.ajax({
      type: "GET",
      url: `${rootPath}/api/product/keyinput}`,
      success: function (result) {
        alert(result);
      },
    });
  });

  $("#p_code_gen").on("click", function () {
    console.log("on 핸들러");
  });

  function test() {
    alert("안녕하세요");
  }
});
