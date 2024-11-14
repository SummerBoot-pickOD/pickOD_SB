document.addEventListener('DOMContentLoaded', function () {
  //회원 상세보기 모달
  const openModal = document.querySelectorAll('.user-detail-btn');
  const usrDetailModal = document.querySelector('.nonmodal-container');
  const closeBtn = document.querySelector('.close-btn');

  //쪽지 보내기 상세보기 모달
  const openMessage = document.querySelector('.message');
  const msgModal = document.querySelector('.sendmsg-container');
  const closeModal = document.querySelector('.message-close');
  const sendMsg = document.querySelector(".send-msg")

  //제재 부여 버튼
  const sanctionBtn = document.querySelector('#suspend');

  //회원 탈퇴 버튼
  const deleteMemBtn = document.querySelector('.withdrawal');

    function escapeHTML(str) {
        var element = document.createElement('div');
        if (str) {
            element.innerText = str;
            element.textContent = str;
        }
        return element.innerHTML;
    }

  //회원 상세 보기
  openModal.forEach(btn => {
    btn.addEventListener("click", function () {
        const nickName = this.getAttribute('data-mem-nickname');
        const memberNum = this.getAttribute('data-member-num');
      fetch(`/admin/admMemberMgmt/details?memberNum=${memberNum}`)
          .then(response => response.json())
          .then(data => {
            console.log(data);
            document.getElementById('member-id').textContent = escapeHTML(data.memberId);
            document.getElementById('member-nickname').textContent = escapeHTML(data.memberNickName);
            document.getElementById('member-address').textContent = escapeHTML(data.memberAddress);
            document.getElementById('member-bday').textContent = data.memberBdate;
            document.getElementById('member-gender').textContent = escapeHTML(data.memberGender) || "선택안함";
            document.getElementById('tot-journals').textContent = data.totJournals;
            document.getElementById('tot-plans').textContent = data.totPlans;
            document.getElementById('sanction-cnt').textContent = data.sanctionCnt;
            document.getElementById('is-banned').textContent = data.isBanned;
              document.getElementById('sanction-end-date').textContent = data.sanctionEndDate;
          })
          .catch(error => console.error('Error:', error));

      // 쪽지 보내기 버튼에 memberNum 전달
        openMessage.setAttribute('data-member-num', memberNum);
        openMessage.setAttribute('data-mem-nickname', nickName);

     // 제재 부여 버튼에 memberNum 전달
        sanctionBtn.setAttribute('data-member-num', memberNum);

      //회원 탈퇴 버튼에 memberNum, memberNickName 전달
        deleteMemBtn.setAttribute('data-member-num', memberNum);
        deleteMemBtn.setAttribute('data-mem-nickname', nickName);
      //회원 상세 모달 보이게 하기
      usrDetailModal.style.display = "flex";
    })
  });


  //회원 상세보기 모달 닫기
  closeBtn.addEventListener("click", function () {
    usrDetailModal.style.display = "none";
  });

  //제재 부여 페이지로 이동
  sanctionBtn.addEventListener('click', function(){
      const memNum = this.getAttribute('data-member-num');
      window.location.href = '/admin/admMemberMgmt/toSanction?memNum=' + memNum;
  })

  //쪽지 보내기 모달 띄우기
  openMessage.addEventListener("click", function () {
      const memNum = this.getAttribute('data-member-num');
      const nickName = this.getAttribute('data-mem-nickname');
      document.getElementById("recipient").textContent = nickName;
      // window.location.href='/admin/admMemberMgmt/writeMsg?memNum=' + memNum + '&memNickName=' + nickName;
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

  //회원 삭제
  deleteMemBtn.addEventListener("click", function(){
      const nickname = this.getAttribute('data-mem-nickname')
      const memNum = this.getAttribute('data-member-num')
      let result=confirm(`회원 "${nickname}" 을 삭제하시겠습니까?`);
      console.log(nickname);
      if (result) {
          fetch(`/admin/admMemberMgmt/deleteMember/${memNum}`, {
              method: 'POST'
          })
              .then(response => {
                  if(response.ok){
                      alert(`${nickname}님이 삭제되었습니다.`);
                      window.location.href = '/admin/admMemberMgmt/list';
                  } else {
                      alert(`${nickname}님 삭제에 실패하였습니다.`);
                  }
              }).catch(error => {
                  console.error("에러: ", error)
          });
      } else {
          window.location.href = '/admin/admMemberMgmt/list';
      }
  })

});


