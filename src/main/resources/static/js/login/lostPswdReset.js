$(function () {
  $("#header").load("../../html/main/header.html");
});

$(function () {
  $("#footer").load("../../html/main/footer.html");
});

//뒤로가기 버튼 쓸거야?

$("#reset-pswd").click(function(){
  pswd1 = $("#pswd-first").val();
  pswd2 = $("#pswd-again").val();

  const regex_total = /^[A-Za-z0-9]+$/;
  const regex_least = /[A-Z]/;
  if(pswd1 != pswd2){
    $("#invalid-pswd").text("두 비밀번호가 다릅니다. 다시 입력해주십시오.");
    return;
  }

  if(pswd1.length < 8){
    $("#invalid-pswd").text("길이가 8자 이하입니다. 다시 입력해주십시오.");
    return;
  }

  if(!regex_total.test(pswd1)){
    $("#invalid-pswd").text("영문 대/소문자, 숫자만 사용 가능합니다. 다시 입력해주십시오.");
  }

  if(!regex_least.test(pswd1)){
    $("#invalid-pswd").text("영문 대문자는 최소 1개 필요합니다. 다시 입력해주십시오.");
  }

  //db와 통신 : 비밀번호 변경
  $(this).closest('form').submit();
  alert("비밀번호가 성공적으로 변경되었습니다.\n로그인 페이지로 이동합니다.");
  //window.location.href = "./login.html";
})