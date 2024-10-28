document.addEventListener('DOMContentLoaded', function () {
  $(function () {
    $("#header").load("../../html/admin/admHeader.html");
  });

  $(function () {
    $("#footer").load("../../html/main/footer.html");
  });

  //신고하기 아이콘 신고 여부에 따라 다르게 표시됨 
  let reported = 'Y';  // 실제로는 서버에서 불러와야 함

  const reportIcon = document.getElementById('report-icon');
  if (reported === 'Y') {
    reportIcon.src = '../../img/admin/reported.png';
    reportIcon.style.cursor = 'pointer';
    reportIcon.addEventListener('click', () => {
      // 이때, 이 장소이름이 검색된?? 그니까 이 장소만 조회된 페이지로 넘기기
      window.location.href = '../../html/admin/admReport.html';
    });
  } else if (reported === 'N') {
    reportIcon.src = '../../img/message/report.png';
  };

  //삭제 버튼 
  const deleteBtn = document.querySelector(".delete");

  deleteBtn.addEventListener("click", function () {
    const confirmed = confirm("정말 해당 여행 발자국을 삭제하시겠습니까?");
    if (confirmed) {
      // 실제 삭제 로직 추가 필요
      alert("여행 발자국이 삭제되었습니다. \n작성자에게 삭제 여부를 알리기 위해 자동으로 쪽지가 발송됩니다.")
    }
  });

  //발자국 톡 
  const toComment = document.querySelector('.to-comment');

  toComment.addEventListener('click', function () {
    window.location.href = "../../html/admin/admReplies.html";
  });

  //쪽지 보내기 상세보기 모달 
  const openMessage = document.querySelector('.message-button');
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


