$(function () {
  $("#header").load("../../html/main/header.html");
});

$(function () {
  $("#footer").load("../../html/main/footer.html");
});

let madecert = 0

$(".send-email").click(function(){
  put = $(".input-email input").val()
  email_regex = /^[0-9a-zA-Z]*@[0-9a-zA-Z]*.[a-zA-Z]{2,3}$/i;
  if(!email_regex.test(put)){
    alert('유효하지 않은 이메일입니다.');
    return;
  }
  //(이메일 양식인지 아닌지 확인 기능 필요)
  const data = {
      email : $(this).siblings('input').val()
  };

  fetch('/login/sendCert',{
      method : 'POST',
      headers :{
          'Content-Type':'application/json'
      },
      body : JSON.stringify(data)
  }).then(res => res.json())
      .then(data => {
          if (data.success){
              alert("인증번호가 전송되었습니다.");
              madecert = data.cert;
          }else{
              alert("이메일 전송에 실패했습니다. 다시 시도해 주세요.");
          }
      }).catch(e => {
          console.log(e);
          alert('서버와의 연결에 문제가 발생했습니다.')
  })
  $(".input-cert").css("display","block");
  $("#certed").val(data.email);
  let time = 120000;
  showTime(time)
});

$("#send-cert").click(function(){
  //인증번호 틀리면 다시
    cert = $(".input-cert input").val();
    console.log(cert)
    if(cert != madecert){
      $("#invalid-cert").text("잘못된 인증번호입니다. 다시 입력해주세요.");
      return;
    }
    if($("#left-time").text() == "만료"){
      $("#invalid-cert").text("시간이 초과되었습니다. 인증번호를 다시 발급받으세요.");
      return;
    }
    alert("인증 성공했습니다.");
    $(".input-cert>form").submit();
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
    $("#left-time").text("만료")
  },time)
}