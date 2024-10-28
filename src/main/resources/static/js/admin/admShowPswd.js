$(function () {
  $("#header").load("../../html/admin/admHeader.html");
});

$(function () {
  $("#footer").load("../../html/main/footer.html");
});

//뒤로가기 버튼 쓸거야?


//있다면 저 방식을 따르면 되고,
//만약 없다면?
// $("#container>h2").html("해당 이메일로는 계정이 없습니다.");
// $(".email>span").html("이메일 계정 없음");

//이메일이 없다면?
//비밀번호 대신에 회원가입으로 버튼을 바꾸면 될듯?

//비밀번호 보여지는거 
// .getElementByID?? 부분에 디비에 저장된 데이터 보여지게

$("#login").click(function () {
  window.location.href = "./admLogin.html"
})