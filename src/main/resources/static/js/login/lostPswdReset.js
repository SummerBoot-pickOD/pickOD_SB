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
  if(pswd1 != pswd2){
    $("#invalid-pswd").text("두 비밀번호가 다릅니다. 다시 입력해주십시오.");
    return;
  }
  //우리 비밀번호 조건이 뭐였더라...??

  //db와 통신 : 비밀번호 변경
  alert("비밀번호가 성공적으로 변경되었습니다.\n로그인 페이지로 이동합니다.");
  window.location.href = "./login.html";
})