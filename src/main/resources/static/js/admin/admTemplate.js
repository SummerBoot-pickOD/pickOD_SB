document.addEventListener('DOMContentLoaded', function () {
  $(function () {
    $("#header").load("../../html/admin/admHeader.html");
  });

  $(function () {
    $("#footer").load("../../html/main/footer.html");
  });

  //템플릿 상세보기 > 템플릿 상세 페이지로 이동
  const toTemplate = document.querySelectorAll('.to-template');

  toTemplate.forEach(btn => {
    btn.addEventListener("click", function () {
      window.location.href = "../../html/admin/admTemplateDetails.html";
    })
  });

  //삭제 버튼 
  const deleteBtn = document.querySelectorAll(".delete");
  deleteBtn.forEach(btn => {
    btn.addEventListener("click", function () {
      const confirmed = confirm("정말 해당 템플릿을 삭제하시겠습니까?");
      if (confirmed) {
        // 실제 삭제 로직 추가 필요
        alert("템플릿이 삭제되었습니다.")
      }
    })
  });
});


