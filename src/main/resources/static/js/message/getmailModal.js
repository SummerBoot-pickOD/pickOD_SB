// 쪽지닫기기능
let getbtnClose = document.querySelector('.btn-close-getmail');
let btnRemove = document.querySelector('.delete-msg');

getbtnClose.addEventListener("click", function() {
  let getmsgContainer = this.closest('.getmsg-container');
  
  if(getmsgContainer){
    getmsgContainer.style.display='none';
  }
});

btnRemove.addEventListener("click", function(){
  let result = confirm("메세지를 삭제하시겠습니까");
  if (result) {
    let getmsgContainer = this.closest('.getmsg-container');
    getmsgContainer.style.display='none';
    console.log("메세지가삭제되었습니다.");
  } else {
    console.log("삭제실패");
  }
})

// 신고기능

$(function () {
  $("#report").load("../report/reportSend.html");
});

$(".reportimg").click(function(){
  $(".modal-container").css("display","block");
});
