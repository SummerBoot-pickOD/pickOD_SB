$(function () {
  $("#header").load("../../html/main/header.html");
});

$(function () {
  $("#footer").load("../../html/main/footer.html");
});

//비밀번호체크
document.addEventListener("DOMContentLoaded", function() {
  const saveButton = document.getElementById("save");
  const pswdFirst = document.getElementById("pswd-first");
  const pswdAgain = document.getElementById("pswd-again");
  const invalidPswdDiv = document.getElementById("invalid-pswd");
  const quitButton = document.getElementById("quit");

  saveButton.addEventListener("click", function(event) {
    // 비밀번호가 일치하는지 확인
    if (pswdFirst.value !== pswdAgain.value) {
      // 일치하지 않으면 에러 메시지 출력
      invalidPswdDiv.innerText = "비밀번호가 일치하지 않습니다";
      invalidPswdDiv.style.color = "red"; // 메시지를 빨간색으로 표시
      // 폼 제출 방지
      event.preventDefault();
      // 알림창 띄우기
      alert("비밀번호가 일치하지 않습니다");
      return;
    }
    if(confirm("저장하시겠습니까?")==false){
      return;
    }
    
  });

  quitButton.addEventListener("click", function(event) {
    // 비밀번호가 일치하는지 확인
    if (pswdFirst.value !== pswdAgain.value) {
      // 일치하지 않으면 에러 메시지 출력
      invalidPswdDiv.innerText = "비밀번호가 일치하지 않습니다";
      invalidPswdDiv.style.color = "red"; // 메시지를 빨간색으로 표시
      // 폼 제출 방지
      event.preventDefault();
      // 알림창 띄우기
    }else{
        // prompt 창 띄우기
        const userInput = prompt("탈퇴를 원하시면 '지금탈퇴'를 입력하세요.");
        
        if (userInput !== "지금탈퇴") {
          // 입력 값이 '지금탈퇴'가 아니면 폼 제출을 막음
          event.preventDefault();
          alert("탈퇴를 원하시면 '지금탈퇴'를 정확히 입력해주세요.");
        }

    }
  });
});

//닉네임 중복 확인
$("#send-nick-dup").click(function(){
  //입력값만 회원정보 테이블에 가서 중복 여부 확인
  nick = $("#nick-container input").val();

  data = {
    nickname : nick
  };

  fetch('/signup/isNickUnique',{
    method : 'POST',
    headers :{
      'Content-Type':'application/json'
    },
    body : JSON.stringify(data)
  }).then(res => res.json())
      .then(data => {
        if (data.success){

          if(data.isUnique){
            $("#invalid-nick").text("사용 가능한 닉네임입니다.");
            $("#invalid-nick").css("color","blue");
          }else{
            $("#invalid-nick").text("이미 사용중인 닉네임입니다. 다른 닉네임을 기입해주세요.");
            $("#invalid-nick").css("color","red");
          }
        }else{
          alert("닉네임 중복확인 실패.");
        }
      }).catch(e => {
    console.log(e);
    alert('서버와의 연결에 문제가 발생했습니다.')
  })
});
//저장하겠습니까 컨펌창 띄우기
// document.addEventListener("DOMContentLoaded", function() {
//   const saveButton = document.getElementById("save");

//   saveButton.addEventListener("click", function(event) {

//     // 컨펌창띄우기
//     const userConfirmed = confirm("저장하시겠습니까?");
    
//     if (!userConfirmed) {
//       // 사용자가 취소를 누르면 
//       event.preventDefault();
//       // 폼 제출을 막음
//     }
//   });
// });


// // 탈퇴
// document.addEventListener("DOMContentLoaded", function() {
//   const quitButton = document.getElementById("quit");

//   quitButton.addEventListener("click", function(event) {
//     // prompt 창 띄우기
//     const userInput = prompt("탈퇴를 원하시면 '지금탈퇴'를 입력하세요.");
    
//     if (userInput !== "지금탈퇴") {
//       // 입력 값이 '지금탈퇴'가 아니면 폼 제출을 막음
//       event.preventDefault();
//       alert("탈퇴를 원하시면 '지금탈퇴'를 정확히 입력해주세요.");
//     }
//   });
// });