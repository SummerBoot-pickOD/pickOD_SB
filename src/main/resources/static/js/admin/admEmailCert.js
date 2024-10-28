document.addEventListener('DOMContentLoaded', function () {
  $(function () {
    $("#header").load("../../html/admin/admHeader.html");
  });

  $(function () {
    $("#footer").load("../../html/main/footer.html");
  });

  $(".back").click(function () {
    window.location.href = "./admSelectCert.html";
  });

  $(".send-email").click(function () {
    put = $(".email").val()
    email_regex = /^[0-9a-zA-Z]*@[0-9a-zA-Z]*.[a-zA-Z]{2,3}$/i;
    if (!email_regex.test(put)) {
      alert('유효하지 않은 이메일입니다.');
      return;
    }
    //(이메일 양식인지 아닌지 확인 기능 필요)
    alert("인증번호가 전송되었습니다.");

    $(".verify-code").css("display", "block");

    let time = 100000;
    showTime(time)
    return;
  });

  $("#verify").click(function () {
    //인증번호 틀리면 다시
    vCode = $(".verification-code").val();
    console.log(vCode)
    if (vCode !== "1234") {
      $("#invalid-code").text("인증번호가 일치하지 않습니다. 다시 입력해주세요.");
      return;
    }
    if ($("#left-time").text() == "만료") {
      $("#invalid-code").text("시간이 초과되었습니다. 인증번호를 다시 발급받으세요.");
      return;
    }
    alert("인증되었습니다.");
    window.location.href = "./admShowPswd.html";
  })


  // (인증번호 확인 및 인증 제한시간 타이머 보여주는 기능 필요)
  function timer(limit) {
    clock = setInterval(() => {
      limit = limit - 1000;
      min = Math.floor(limit / (60 * 1000))
      sec = Math.floor(limit / 1000) % 60

      $("#left-time").text(min + ":" + sec);
    }, 1000);
  }
  function showTime(time) {
    $("#left-time").html("03:00")
    timer(time);
    setTimeout(function () {
      clearInterval(clock)
      $("#left-time").text("만료")
    }, time)
  }

});