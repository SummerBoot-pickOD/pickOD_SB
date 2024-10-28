document.addEventListener('DOMContentLoaded', function () {
  $(function () {
    $("#header").load("../../html/admin/admHeader.html");
  });

  $(function () {
    $("#footer").load("../../html/main/footer.html");
  });

  //댓글 상세보기 모달 
  const openModal = document.querySelectorAll('.user-detail');
  const usrDetailModal = document.querySelector('.nonmodal-container');
  const closeBtn = document.querySelector('.close-btn');

  //댓글 상세보기 모달 띄우기
  openModal.forEach(btn => {
    btn.addEventListener("click", function () {
      // console.log("clicked")
      usrDetailModal.style.display = "flex";
    })
  });


  //댓글 상세보기 모달 닫기
  closeBtn.addEventListener("click", function () {
    usrDetailModal.style.display = "none";
  });


  //쪽지 보내기 상세보기 모달 
  const openMessage = document.querySelector('.message');
  const msgModal = document.querySelector('.sendmsg-container');
  const closeModal = document.querySelector('.message-close');
  const sendMsg = document.querySelector(".send-msg")

  //쪽지 보내기 모달 띄우기
  openMessage.addEventListener("click", function () {
    msgModal.style.display = "flex";
  })

  //쪽지 보내기 모달 닫기
  closeModal.addEventListener("click", function () {
    msgModal.style.display = "none";
  });

  //쪽지 보내기 버튼 클릭 시 
  sendMsg.addEventListener("click", function () {
    alert("쪽지를 전송하였습니다.");
    msgModal.style.display = "none";
    // 쪽지 내용 전달됨 
  })
});


