// //헤더푸터
// $(function () {
// $("#header").load("../main/header.html");
// });
//
// $(function () {
// $("#footer").load("../main/footer.html");
// });
//
// // 보낸편지모달기능
// $(function () {
//   $("#sentmailModal").load("../message/sentmailModal.html");
// });



// // 페이지가 로드될 때 mailList 데이터를 기반으로 메일 항목을 렌더링
document.addEventListener('DOMContentLoaded', function() {
  if(mailList.length != 0) {
    renderSentMailList(mailList);
  }
});

// 메일 항목을 동적으로 생성
function renderSentMailList(data) {
  // 메일 컨테이너를 가져옵니다.
  const mailboxContainer = document.getElementById('mailbox-container');
  mailboxContainer.innerHTML = ''; // 초기화하여 기존 항목 삭제
  // mailList 배열의 각 항목을 순회합니다.
  data.forEach(mailList => {
    // 메일 항목을 감싸는 div 요소 생성
    const mailboxList = document.createElement('div');
    mailboxList.className = 'mailbox-list';

    // 체크박스 항목
    const checkItem = document.createElement('div');
    checkItem.className = 'check-item';
    const checkbox = document.createElement('input');
    checkbox.type = 'checkbox';
    checkbox.name = 'item';
    checkbox.className = 'item';
    checkItem.appendChild(checkbox);

    // real or not
    const mailOpen = document.createElement('div');
    mailOpen.className = 'mail-open';
    const mailIcon = document.createElement('img');
    mailIcon.src = '../../img/message/쪽지함.png';
    mailIcon.alt = '';
    mailOpen.appendChild(mailIcon);

    // 발신자 닉네임
    const mailTo = document.createElement('div');
    mailTo.className = 'mail-to';
    mailTo.textContent = mailList.memberNickname;

    // 메일 내용
    const mailContent = document.createElement('div');
    mailContent.className = 'mail-content';
    mailContent.textContent = mailList.msgContent;

    // 메일 날짜
    const mailDate = document.createElement('div');
    mailDate.className = 'mail-date';
    mailDate.textContent = mailList.msgSentTime;

    const mailId = document.createElement('div');
    mailId.className = 'msg-id';
    mailId.textContent = mailList.msgId;
    mailId.hidden = true;

    // 각 요소를 mailboxList에 추가
    mailboxList.appendChild(checkItem);
    mailboxList.appendChild(mailOpen);
    mailboxList.appendChild(mailTo);
    mailboxList.appendChild(mailContent);
    mailboxList.appendChild(mailDate);
    mailboxList.appendChild(mailId);

    // 최종적으로 메일 컨테이너에 mailboxList 추가
    mailboxContainer.appendChild(mailboxList);
  });
}


// 쪽지띄우기
document.addEventListener('DOMContentLoaded', function() {
  // 모든 mailbox-list 요소들을 가져오기
  let mailboxLists = document.querySelectorAll('.mailbox-list');

  // 각 mailbox-list 요소에 클릭 이벤트 추가
  mailboxLists.forEach(function(mailbox) {

    mailbox.addEventListener('click', function() {

      const hiddenMsgId = mailbox.querySelector('.msg-id');

      data = {
        msgId : Number(hiddenMsgId.innerText)
      };
      console.log(data);
      //체크박스부분은제외
      if (event.target.tagName === 'INPUT' && event.target.type === 'checkbox') {
        return; // 체크박스를 클릭하면 함수 실행을 멈춤
      }

      // 읽으면 편지 읽음표시기능
      let readMail =this.querySelector('.mail-open img');
      readMail.src = '../../img/message/받은편지.png';

      fetch(`/message/sentmailModal/${data.msgId}`,{
        method: "GET",
        headers: {'Content-Type': 'application/json'},
      }).then(response=>{
        if(!response.ok) throw new Error("Failed to fetch message");
        return response.json();
      }).then(view =>{
        document.querySelector('.ppl-to').innerText = view.memberNickname;
        document.querySelector('.nonmodal-textarea').innerText = view.msgContent;
        //  모달 보이기
        document.querySelector('.sentmsg-container').style.display = 'block';
      }).catch(error=>{
        console.error("Error:", error);
      })

      //삭제하기
      const btnBin = document.querySelector('.delete-msg');
      btnBin.addEventListener('click',function (){

        fetch(`/message/sentMail`,{
          method: 'POST',
          headers: {'Content-Type': 'application/json'},
          body: JSON.stringify({ msgId: data.msgId })
        })
            .then(response => {
              if(!response.ok) throw new Error("Fail to fetch message.");
              return response.text();
            })
            .then(data=>{
              console.log(data);
            })
            .catch(error => {
              console.log("Error:", error);
            });
      })
    });
  });
});

// 전체 체크 박스 선택
const checkAll = document.querySelector('.all');
const mailboxContainer = document.getElementById('mailbox-container');

// "전체 선택" 체크박스 클릭 이벤트
checkAll.addEventListener('click', function () {
  const checkItems = mailboxContainer.querySelectorAll('.item');
  checkItems.forEach(function (checkbox) {
    checkbox.checked = checkAll.checked;
  });
});

// 개별 체크박스 클릭 이벤트
mailboxContainer.addEventListener('click', function (event) {
  if (event.target.classList.contains('item') && event.target.type === 'checkbox') {
    const checkItems = mailboxContainer.querySelectorAll('.item');
    const allChecked = Array.from(checkItems).every(function (checkbox) {
      return checkbox.checked;
    });
    checkAll.checked = allChecked;
  }
});

// 체크된 msgId를 가져오는 함수
function getCheckedMsgIds() {
  const checkedItems = document.querySelectorAll('.item:checked');
  console.log('Checked Items:', checkedItems);

  const msgIds = Array.from(checkedItems).map((checkbox) => {
    const mailbox = checkbox.closest('.mailbox-list');
    console.log('Mailbox:', mailbox);

    const msgIdElement = mailbox.querySelector('.msg-id');
    console.log('msgIdElement:', msgIdElement);

    return msgIdElement ? msgIdElement.textContent : null;

  });
  console.log('Extracted Msg IDs:', msgIds);
  return msgIds.filter(Boolean);
}

getCheckedMsgIds();


// 체크된 msgId를 컨트롤러로 보내는 함수
function sendCheckedMsgIds() {
  // 체크된 msgId 가져오기
  const checkedMsgIds = getCheckedMsgIds();

  if (checkedMsgIds.length === 0) {
    console.warn('선택된 항목이 없습니다.');
    return;
  }

  // data 생성
  const data = {
    msgIds: checkedMsgIds
  };


  // Fetch API로 POST 요청 보내기
  fetch('/message/deleteCheckedSentMsgs', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(data)
  })
      .then(response => {
        if (!response.ok) throw new Error("Fail to fetch message.");
        return response.text();
      })
      .then(data => {
        console.log('성공:', data);
        alert('메세지 휴지통이동');
      })
      .catch(error => {
        console.error('에러 발생:', error);
        alert('메세지 휴지통이동 실패');
      });
}

// btn-delete 클래스의 버튼 클릭 이벤트 설정
document.addEventListener('DOMContentLoaded', () => {
  const deleteButton = document.querySelector('#btn-delete');
  console.log(deleteButton);
  deleteButton.addEventListener('click', sendCheckedMsgIds);
});

// // 게시물 및 페이지네이션 처리

// const mailboxLists = Array.from(document.querySelectorAll('.mailbox-list'));
// const MailsPerPage = 10;
// let currentPage = 1;
// let totalPages;
//
// function displayMails() {
//   const postContainer = document.getElementById('mails');
//   postContainer.innerHTML = ''; // 현재 표시된 항목 초기화
//
//   const startIndex = (currentPage - 1) * MailsPerPage;
//   const endIndex = Math.min(startIndex + MailsPerPage, mailboxLists.length); // 실제 항목 수와 비교하여 인덱스 계산
//
//   // 해당 페이지에 맞는 mailbox-list만 표시
//   for (let i = startIndex; i < endIndex; i++) {
//     postContainer.appendChild(mailboxLists[i]);
//   }
// }
//
//
// function setupPagination() {
//   const paginationContainer = document.getElementById('pagination');
//   paginationContainer.innerHTML = ''; // 페이지네이션 초기화
//
//   totalPages = Math.ceil(mailboxLists.length / MailsPerPage); // 실제 항목 수로 페이지 수 계산
//
//   const createButton = (pageNum, text) => {
//     const button = document.createElement('button');
//     button.textContent = text;
//     button.disabled = (currentPage === pageNum);
//     button.addEventListener('click', () => {
//       currentPage = pageNum;
//       displayMails(); // 페이지 클릭 시 항목 업데이트
//       setupPagination(); // 페이지네이션 버튼 다시 설정
//     });
//     return button;
//   };
//
//   // 이전 페이지 버튼
//   if (currentPage > 1) {
//     const prevButton = createButton(currentPage - 1, '<');
//     paginationContainer.appendChild(prevButton);
//   }
//
//   // 페이지 버튼 생성
//   for (let i = 1; i <= totalPages; i++) {
//     const button = createButton(i, i);
//     paginationContainer.appendChild(button);
//   }
//
//   // 다음 페이지 버튼
//   if (currentPage < totalPages) {
//     const nextButton = createButton(currentPage + 1, '>');
//     paginationContainer.appendChild(nextButton);
//   }
// }
//
//
//   document.addEventListener('DOMContentLoaded', function() {
//     setupPagination();
//     displayMails();
//   });
//
//




