// 쪽지닫기기능
let replybtnClose = document.querySelector('.btn-close-replymail');

replybtnClose.addEventListener("click", function() {
  let replymsgContainer = this.closest('.replymsg-container');
  
  if(replymsgContainer){
    replymsgContainer.style.display='none';
  }
});

let sendBtn = document.querySelector('.send-msg');
sendBtn.addEventListener("click", function() {
  let replymsgContainer = this.closest('.replymsg-container');

  if(replymsgContainer){
    replymsgContainer.style.display='none';
  }
});
