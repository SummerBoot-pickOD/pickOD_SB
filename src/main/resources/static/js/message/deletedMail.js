//헤더푸터
// $(function () {
// $("#header").load("../main/header.html");
// });
//
// $(function () {
// $("#footer").load("../main/footer.html");
// });
// //신고하기
// $(function () {
//   $("#report").load("../report/reportSend.html");
// });
//
// $(".reportimg").click(function(){
//   $(".modal-container").css("display","block");
// });

document.addEventListener('DOMContentLoaded', function() {
    if (mailList.length != 0) {
        renderDeletedMailList(mailList);
    }

// 메일 항목을 동적으로 생성하는 함수
    function renderDeletedMailList(data) {
        // 메일 항목을 추가할 컨테이너를 가져옵니다.
        const mailboxContainer = document.getElementById('mailbox-container');
        mailboxContainer.innerHTML = ''; // 기존 항목 초기화

        // 메일 리스트 데이터를 순회하여 각 항목을 동적으로 생성
        data.forEach(mailList => {
            // 메일 항목을 감싸는 div 요소 생성
            const mailboxList = document.createElement('div');
            mailboxList.className = 'mailbox-list';

            // 체크박스 항목 생성
            const checkItem = document.createElement('div');
            checkItem.className = 'check-item';
            const checkbox = document.createElement('input');
            checkbox.type = 'checkbox';
            checkbox.name = 'item';
            checkbox.className = 'item';
            checkItem.appendChild(checkbox);

            // 메일 아이콘 (읽음 여부) 생성
            const mailOpen = document.createElement('div');
            mailOpen.className = 'mail-open';
            const mailIcon = document.createElement('img');
            mailIcon.src = '../../img/message/쪽지함.png'; // 이미지 경로 설정
            mailIcon.alt = '';
            mailOpen.appendChild(mailIcon);

            // 발신자 정보 (메일 From) 생성
            const mailFrom = document.createElement('div');
            mailFrom.className = 'mail-from';
            mailFrom.textContent = mailList.msgSender;

            // 수신자 정보 (메일 To) 생성
            const mailTo = document.createElement('div');
            mailTo.className = 'mail-to mail-who';
            mailTo.textContent = mailList.msgRecipient; // 데이터에서 수신자 정보 설정

            // 메일 내용 생성
            const mailContent = document.createElement('div');
            mailContent.className = 'mail-content';
            mailContent.textContent = mailList.msgContent; // 데이터에서 메일 내용 설정

            // 메일 날짜 생성
            const mailDate = document.createElement('div');
            mailDate.className = 'mail-date';
            mailDate.textContent = mailList.msgSentTime; // 데이터에서 날짜 정보 설정

            const mailId = document.createElement('div');
            mailId.className = 'msg-id';
            mailId.textContent = mailList.msgId;
            mailId.hidden = true;

            // 각 요소를 mailboxList에 추가
            mailboxList.appendChild(checkItem);
            mailboxList.appendChild(mailOpen);
            mailboxList.appendChild(mailFrom);
            mailboxList.appendChild(mailTo);
            mailboxList.appendChild(mailContent);
            mailboxList.appendChild(mailDate);
            mailboxList.appendChild(mailId);

            // 최종적으로 메일 컨테이너에 mailboxList 추가
            mailboxContainer.appendChild(mailboxList);
        });
    }
})

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


    fetch('/message/removeCheckedMsgs', {
        method: 'DELETE',
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
            alert('영구삭제 성공');
            location.reload();
        })
        .catch(error => {
            console.error('에러 발생:', error);
            alert('영구삭제 실패');
        });
}

//체크된거 복원컨트롤러로 보내기
function sendCheckedMsgIdsForReturn() {
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
    fetch('/message/returnCheckedMsgs', {
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
            alert('쪽지가 복원되었습니다');
            location.reload();
        })
        .catch(error => {
            console.error('에러 발생:', error);
            alert('쪽지 복원 실패');
        });
}

// btn-delete 클래스의 버튼 클릭 이벤트 설정
document.addEventListener('DOMContentLoaded', () => {
    const deleteButton = document.querySelector('.btn-delete-all');
    console.log(deleteButton);
    deleteButton.addEventListener('click', sendCheckedMsgIds);
});

document.addEventListener('DOMContentLoaded', () => {
    const deleteButton = document.querySelector('.btn-return');
    console.log(deleteButton);
    deleteButton.addEventListener('click', sendCheckedMsgIdsForReturn);
});



// 쪽지띄우기
document.addEventListener('DOMContentLoaded', function() {
  // 모든 mailbox-list 요소들을 가져오기
  let mailboxLists = document.querySelectorAll('.mailbox-list');

  // 각 mailbox-list 요소에 클릭 이벤트 추가
  mailboxLists.forEach(function(mailbox) {
    mailbox.addEventListener('click', function() {

    let hiddenMsgId = mailbox.querySelector('.msg-id');
    console.log(hiddenMsgId);
    data = {
        msgId : Number(hiddenMsgId.innerText)
    };

      //체크박스부분은제외
      if (event.target.tagName === 'INPUT' && event.target.type === 'checkbox') {
        return; // 체크박스를 클릭하면 함수 실행을 멈춤
      }

      // 읽으면 편지 읽음표시기능
      let readMail =this.querySelector('.mail-open img');
      readMail.src = '../../img/message/받은편지.png';


      fetch(`/message/binBox/${data.msgId}`,{
          method: "GET",
          headers: {'Content-Type': 'application/json'},
      }).then(response=>{
          if(!response.ok) throw new Error("Failed to fetch message");
          return response.json();
      }).then(view =>{
          document.querySelector('.ppl-from').innerText = view.memberNickname;
          document.querySelector('.nonmodal-textarea').innerText = view.msgContent;
          //  모달 보이기
          document.querySelector('.getmsg-container').style.display = 'block';
      })

      //복원하기
      const btnReturn = document.querySelector('.btn-msg-return');
      btnReturn.addEventListener('click' ,function(){
          let result = confirm("복원하시겠습니까?");
        if (result) {
            let getmsgContainer = this.closest('.getmsg-container');
            getmsgContainer.style.display='none';
            // 받은 쪽지 중 체크된 항목을 찾아서 복원
         fetch(`/message/deletedMail`,{
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(data)
            })
            .then(response => {
                if(!response.ok) throw new Error("Fail to fetch message.");
                return response.text();
            })
            .then(data=>{
                console.log(data);
                location.reload();
            })
            .catch(error => {
                console.log("Error:", error);
            });
        } else {
            alert("취소되었습니다");
            }
      });

      //영구삭제하기
      const btnDelete = document.querySelector('.btn-msg-delete');
        btnDelete.addEventListener('click' ,function(){
          let checkDelete = confirm("영구삭제하시겠습니까?");
          if (checkDelete) {
              let getmsgContainer = this.closest('.getmsg-container');
              getmsgContainer.style.display='none';
              // 받은 쪽지 중 체크된 항목을 찾아서 복원
              fetch(`/message/deletedPermanently`,{
                  method: 'DELETE',
                  headers: {'Content-Type': 'application/json'},
                  body: JSON.stringify(data)
              })
                  .then(response => {
                      if(!response.ok) throw new Error("Fail to fetch message.");
                      return response.text();
                  })
                  .then(data=>{
                      console.log(data);
                      location.reload();
                  })
                  .catch(error => {
                      console.log("Error:", error);
                  });
          } else {
              alert("취소되었습니다");
          }
      });
    });
  });

  // 모달 닫기 버튼 클릭 시 모달 숨기기
    document.querySelector('.btn-close').addEventListener('click', function() {
        document.querySelector('.nonmodal-container').style.display = 'none';
  });
});

// // 게시물 및 페이지네이션 처리
//
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



