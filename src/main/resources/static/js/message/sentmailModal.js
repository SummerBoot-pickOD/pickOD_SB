// 쪽지닫기기능
let sentbtnClose = document.querySelector('.btn-close-sentmail');
//삭제버튼
let btnRemove = document.querySelector('.delete-msg');


sentbtnClose.addEventListener("click", function() {
  let sentmsgContainer = this.closest('.sentmsg-container');
  
  if(sentmsgContainer){
    sentmsgContainer.style.display='none';
  }
});

btnRemove.addEventListener("click", function(){
  let result = confirm("메세지를 삭제하시겠습니까");
  if (result) {
    let sentmsgContainer = this.closest('.sentmsg-container');
    sentmsgContainer.style.display='none';
    console.log("메세지가삭제되었습니다.");
  } else {
    console.log("삭제실패");
  }
})