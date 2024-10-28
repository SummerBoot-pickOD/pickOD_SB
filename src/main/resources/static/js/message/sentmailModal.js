// 쪽지닫기기능
let sentbtnClose = document.querySelector('.btn-close-sentmail');

sentbtnClose.addEventListener("click", function() {
  let sentmsgContainer = this.closest('.sentmsg-container');
  
  if(sentmsgContainer){
    sentmsgContainer.style.display='none';
  }
});