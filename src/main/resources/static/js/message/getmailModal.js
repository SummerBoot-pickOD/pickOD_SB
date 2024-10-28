// 쪽지닫기기능
let getbtnClose = document.querySelector('.btn-close-getmail');

getbtnClose.addEventListener("click", function() {
  let getmsgContainer = this.closest('.getmsg-container');
  
  if(getmsgContainer){
    getmsgContainer.style.display='none';
  }
});

// 신고기능

$(function () {
  $("#report").load("../report/reportSend.html");
});

$(".reportimg").click(function(){
  $(".modal-container").css("display","block");
});
