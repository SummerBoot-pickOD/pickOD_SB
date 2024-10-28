//헤더푸터
$(function () {
  $("#header").load("../main/header.html");
  });
  
  $(function () {
  $("#footer").load("../main/footer.html");
  });


  const btnSave = document.querySelector('.fix-btn');
  const btnCancle = document.querySelector('.delete-btn');



  btnCancle.addEventListener('click', function () {

    if (confirm('삭제하시겠습니까?')) {
  
      window.location.href = '../mypage/myPage.html';
    }
  
  });

  btnSave.addEventListener('click', function () {

    if (confirm('편집하시겠습니까?')) {
  
      window.location.href = '../planner/myPlan.html';
    } 
  
  });


 