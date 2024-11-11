document.addEventListener('DOMContentLoaded', function () {
  $(function () {
    $("#header").load("../../html/admin/admHeader.html");
  });

  $(function () {
    $("#footer").load("../../html/main/footer.html");
  });

  //회원 상세보기 모달
  const openModal = document.querySelectorAll('.user-detail-btn');
  const usrDetailModal = document.querySelector('.nonmodal-container');
  const closeBtn = document.querySelector('.close-btn');
  //쪽지 보내기 상세보기 모달
    const openMessage = document.querySelector('.message');
    const msgModal = document.querySelector('.sendmsg-container');
    const closeModal = document.querySelector('.message-close');
    const sendMsg = document.querySelector(".send-msg")

  //회원 상세 보기
  openModal.forEach(btn => {
    btn.addEventListener("click", function () {
      // const memberId = this.getAttribute('data-member-id');
        const memberNum = this.getAttribute('data-member-num');
      fetch(`/admin/admMemberMgmt/details?memberNum=${memberNum}`)
          .then(response => response.json())
          .then(data => {
            console.log(data);
            document.getElementById('member-id').textContent = data.memberId;
            document.getElementById('member-nickname').textContent = data.memberNickname;
            document.getElementById('member-address').textContent = data.memberAddress;
            document.getElementById('member-bdate').textContent = data.memberBdate;
            document.getElementById('member-gender').textContent = data.memberGender;
            document.getElementById('sanction-cnt').textContent = data.sanctionCnt;
            document.getElementById('is-banned').textContent = data.isBanned;
              document.getElementById('sanction-end-date').textContent = data.sanctionEndDate;
          })
          .catch(error => console.error('Error:', error));

      // 쪽지 보내기 버튼에 memberNum 전달
        openMessage.setAttribute('data-member-num', memberNum);

      usrDetailModal.style.display = "flex";
    })
  });


  //회원 상세보기 모달 닫기
  closeBtn.addEventListener("click", function () {
    usrDetailModal.style.display = "none";
  });


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


