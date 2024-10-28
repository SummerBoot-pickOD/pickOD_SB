$(function () {
  $("#header").load("../../html/main/header.html");
});

$(function () {
  $("#footer").load("../../html/main/footer.html");
});

//이메일 입력 및 인증

$(".send-email").click(function(){
  put = $("#email-container input").val()
  email_regex = /^[0-9a-zA-Z]*@[0-9a-zA-Z]*.[a-zA-Z]{2,3}$/i;
  if(!email_regex.test(put)){
    alert('유효하지 않은 이메일입니다.');
    return;
  }
  //(이메일 양식인지 아닌지 확인 기능 필요)
  alert("인증번호가 전송되었습니다.");

  $("#cert").css("display","flex");

  let time = 10000;
  showTime(time)
  return;
});

$("#send-cert").click(function(){
  //인증번호 틀리면 다시
    cert = $("#cert input").val();
    console.log(cert)
    if(cert !== "1234"){
      $("#invalid-cert").text("잘못된 인증번호입니다. 다시 입력해주세요.");
      $("#invalid-cert").css("color","red");
      return;
    }
    if($("#left-time").text() == "만료"){
      $("#invalid-cert").text("시간이 초과되었습니다. 인증번호를 다시 발급받으세요.");
      $("#invalid-cert").css("color","red");
      return;
    }
    $("#invalid-cert").text("인증 성공했습니다.");
    $("#invalid-cert").css("color","blue");
    return;
})


// (인증번호 확인 및 인증 제한시간 타이머 보여주는 기능 필요)
function timer(limit){
  clock = setInterval(()=>{
    limit = limit - 1000;
    min = Math.floor(limit / (60*1000))
    sec =  Math.floor(limit / 1000) % 60
    
    $("#left-time").text(min+":"+sec);
  },1000);
}
function showTime(time){
  $("#left-time").html("03:00")
  timer(time);
  setTimeout(function(){
    clearInterval(clock)
    if($("#invalid-cert").text() == "인증 성공했습니다."){
      $("#left-time").text("인증됨");
    }else{
      $("#left-time").text("만료");
    }
  },time)
}

//닉네임 중복 확인
$("#send-nick-dup").click(function(){
  //입력값만 회원정보 테이블에 가서 중복 여부 확인
  nick = $("#nick-container input").val();

  if(nick == "gabin"){
    $("#invalid-nick").text("이미 사용중인 닉네임입니다. 다른 닉네임을 기입해주세요.");
    $("#invalid-nick").css("color","red");
    return;
  }
  $("#invalid-nick").text("사용 가능한 닉네임입니다.");
  $("#invalid-nick").css("color","blue");
  return;
});

//회원가입
$("#signin").click(function(){
  
  //유효성 검사
  if($("#invalid-cert").text() != "인증 성공했습니다."){
    alert("이메일을 인증해주세요.");
    return;
  }
  if($("#pswd-first").val() != $("#pswd-again").val()){
    alert("비밀번호가 다르니 다시 입력해 주세요.");
    return;
  }
  if($("#invalid-nick").text() != "사용 가능한 닉네임입니다."){
    alert("닉네임 중복확인을 통과해주세요.");
    return;
  }
  if($("#year option:selected").val() == "N"){
    alert("출생연도를 선택해주세요.");
    return;
  }
  
  // 데이터 취합해서 보내기
  alert("회원가입이 완료되었습니다.\n자신의 여행을 마음껏 풀고, 꾸려보세요!!");
  window.location.href="../login/login.html";
});