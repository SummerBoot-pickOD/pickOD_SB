document.addEventListener('DOMContentLoaded', function () {
  $(function () {
    $("#header").load("../../html/admin/admHeader.html");
  });

  $(function () {
    $("#footer").load("../../html/main/footer.html");
  });

  $("#login-button").click(function () {
    $("#invalid-login").val("");
    let id = $(".id").val();
    let pw = $(".pw").val();
    console.log(id + ", " + pw);
    if (id == "aaa@gmail.com" && pw == "1234") {
      window.location.href = "../../html/admin/admUserManagement.html"
    } else {
      $("#invalid-login").text("* 아이디 또는 비밀번호가 일치하지 않습니다. 다시 입력해주십시오.");
    }
  })
});