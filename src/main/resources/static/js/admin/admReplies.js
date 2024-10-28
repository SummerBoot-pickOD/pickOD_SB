$(function () {
  $("#header").load("../../html/admin/admHeader.html");
});


$(function () {
  $("#footer").load("../../html/main/footer.html");
});


//상세보기 누르면 댓글 상세보기 모달 띄우기
const openModal = document.querySelectorAll('.detail-button');
const cmtDetailModal = document.querySelector('.nonmodal-container');
const closeBtn = document.querySelector('.close-btn');


openModal.forEach(btn => {
  btn.addEventListener("click", function () {
    // console.log("clicked")
    cmtDetailModal.style.display = "flex";
  })
});


// x 누르면 모달 닫기 -> 되다가 다시 안되기 시작. 콘솔 출력은 됨. 수정필요
closeBtn.addEventListener("click", function () {
  console.log("모달 닫기 버튼 ");
  cmtDetailModal.style.display = "none";
});
