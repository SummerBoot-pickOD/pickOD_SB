// //헤더푸터
// $(function () {
//   $("#header").load("../main/header.html");
//   });
//
//   $(function () {
//   $("#footer").load("../main/footer.html");
//   });

// 신고기능

// $(function () {
//   $("#report").load("../report/reportSend.html");
// });
//
// $(".reportimg").click(function(){
//   $(".modal-container").css("display","block");
// });

// 받은편지모달기능
// $(function () {
//   $("#getmailModal").load("../message/getmailModal.html");
// });

// 답장편지모달기능
// $(function () {
//   $("#replymailModal").load("../message/replymailModal.html");
// });


document.addEventListener('DOMContentLoaded', function() {
  // 페이지네이션 설정 전에 currentPage를 1로 설정
  currentPage = 1;
  
  setupPagination(); // 페이지네이션 버튼 생성
  displayMails(); // 초기 메일 목록 10개만 표시
});

// 전체클릭 기능
let checkAll = document.querySelector('.all');
let checkItem = document.querySelectorAll('.item');

checkAll.addEventListener('click', function() {
  checkItem.forEach(function(e) {
    e.checked = checkAll.checked;
  });
});

checkItem.forEach(function(e) {
  e.addEventListener('click', function() {
      if (!e.checked) {
        checkAll.checked = false;
      } else {
          const allChecked = Array.from(checkItem).every(function(checkItem) {
              return checkItem.checked;
          });
          checkAll.checked = allChecked;
      }
  });
});

//휴지통 이동
const btnDelete = document.querySelector('.btn-delete');
console.log(btnDelete);
const mailboxList = document.querySelectorAll('.mailbox-list');
console.log(mailboxList);

btnDelete.addEventListener('click', function() {

  
  // 받은 쪽지 중 체크된 항목을 찾아서 휴지통으로 이동
  const checkboxes = document.querySelectorAll('.item');
  console.log(checkboxes);
  checkboxes.forEach((checkbox) => {
      if (checkbox.checked) {

          const messageItem = checkbox.closest('.mailbox-list');
          console.log(messageItem);
          messageItem.remove();
           // 받은 쪽지함에서 삭제
          checkbox.checked = false; // 체크 상태 초기화
      }
  });
});


// 쪽지띄우기
document.addEventListener('DOMContentLoaded', function() {
  // 모든 mailbox-list 요소들을 가져오기
  var mailboxLists = document.querySelectorAll('.mailbox-list');

  // 각 mailbox-list 요소에 클릭 이벤트 추가
  mailboxLists.forEach(function(mailbox) {
    mailbox.addEventListener('click', function() {
      //체크박스부분은제외
      if (event.target.tagName === 'INPUT' && event.target.type === 'checkbox') {
        return; // 체크박스를 클릭하면 함수 실행을 멈춤
      }

      // 읽으면 편지 읽음표시기능
      var readMail =this.querySelector('.mail-open img');
      readMail.src = '../../img/message/받은편지.png';
      // 클릭한 mailbox-list 안의 mail-from 텍스트 가져오기
      var senderText = this.querySelector('.mail-from').innerText;
      // 클릭한 mailbox-list 안의 mail-content 텍스트 가져오기
      var contentText = this.querySelector('.mail-content').innerText;

      // 모달의 ppl-from 요소에 발신자 텍스트 설정
      document.querySelector('.ppl-from').innerText = senderText;
      // 모달의 nonmodal-textarea에 쪽지 내용 텍스트 설정
      document.querySelector('.nonmodal-textarea').innerText = contentText;

      // 모달 보이기
      document.querySelector('.getmsg-container').style.display = 'block';

      // 답장기능
      let replyMsg = document.querySelector('.reply-msg');
      replyMsg.addEventListener("click", function(){
      let sendMsgContainer = document.querySelector('.replymsg-container');
      sendMsgContainer.style.display="block";

      document.querySelector('.ppl-to').innerText = senderText;
    
      });
    });
  });
  // // 모달 닫기 버튼 클릭 시 모달 숨기기 -> 따로 빼서만듦
  // document.querySelector('.btn-close').addEventListener('click', function() {
  //   document.querySelector('.getmsg-container').style.display = 'none';
  // });
});

// 쪽지닫기기능
// let btnClose = document.querySelectorAll('.btn-close');
// btnClose.forEach(btn => {
//   btn.addEventListener("click", function() {
//     let nonmodalContainers = this.closest('.nonmodal-container');
    
//     if(nonmodalContainers){
//       nonmodalContainers.style.display='none';
//     }
//   });
// });

// 샘플데이터
// const mailList = [
//   {
//     memberNickName: "닉네임1",
//     msgContent: "메일 내용1",
//     msgSentTime: "2023-10-01"
//   },
//   {
//     memberNickName: "닉네임2",
//     msgContent: "메일 내용2",
//     msgSentTime: "2023-10-02"
//   }
//
// ];


// 메일 항목을 동적으로 생성
function renderMailList(data) {
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
    const mailFrom = document.createElement('div');
    mailFrom.className = 'mail-from';
    mailFrom.textContent = mailList.memberNickName;

    // 메일 내용
    const mailContent = document.createElement('div');
    mailContent.className = 'mail-content';
    mailContent.textContent = mailList.msgContent;

    // 메일 날짜
    const mailDate = document.createElement('div');
    mailDate.className = 'mail-date';
    mailDate.textContent = mailList.msgSentTime;

    // 각 요소를 mailboxList에 추가
    mailboxList.appendChild(checkItem);
    mailboxList.appendChild(mailOpen);
    mailboxList.appendChild(mailFrom);
    mailboxList.appendChild(mailContent);
    mailboxList.appendChild(mailDate);

    // 최종적으로 메일 컨테이너에 mailboxList 추가
    mailboxContainer.appendChild(mailboxList);
  });
}

// 페이지가 로드될 때 mailList 데이터를 기반으로 메일 항목을 렌더링
document.addEventListener('DOMContentLoaded', function() {
  renderMailList(mailList);
});


// 게시물 및 페이지네이션 처리

const mailboxLists = Array.from(document.querySelectorAll('.mailbox-list'));
const MailsPerPage = 10;
let currentPage = 1;
let totalPages;

function displayMails() {
  const mailContainer = document.getElementById('mails');
  mailContainer.innerHTML = ''; // 현재 표시된 항목 초기화

  const startIndex = (currentPage - 1) * MailsPerPage;
  const endIndex = Math.min(startIndex + MailsPerPage, mailboxLists.length); // 실제 항목 수와 비교하여 인덱스 계산

  // 해당 페이지에 맞는 mailbox-list만 표시
  for (let i = startIndex; i < endIndex; i++) {
    mailContainer.appendChild(mailboxLists[i]);
  }
}


function setupPagination() {
  const paginationContainer = document.getElementById('pagination');
  paginationContainer.innerHTML = ''; // 페이지네이션 초기화

  totalPages = Math.ceil(mailboxLists.length / MailsPerPage); // 실제 항목 수로 페이지 수 계산

  const createButton = (pageNum, text) => {
    const button = document.createElement('button');
    button.textContent = text;
    button.disabled = (currentPage === pageNum);
    button.addEventListener('click', () => {
      currentPage = pageNum;
      displayMails(); // 페이지 클릭 시 항목 업데이트
      setupPagination(); // 페이지네이션 버튼 다시 설정
    });
    return button;
  };

  // 이전 페이지 버튼
  if (currentPage > 1) {
    const prevButton = createButton(currentPage - 1, '<');
    paginationContainer.appendChild(prevButton);
  }

  // 페이지 버튼 생성
  for (let i = 1; i <= totalPages; i++) {
    const button = createButton(i, i);
    paginationContainer.appendChild(button);
  }

  // 다음 페이지 버튼
  if (currentPage < totalPages) {
    const nextButton = createButton(currentPage + 1, '>');
    paginationContainer.appendChild(nextButton);
  }
}


