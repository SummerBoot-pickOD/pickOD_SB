document.addEventListener('DOMContentLoaded', function () {
  $(function () {
    $("#header").load("../../html/admin/admHeader.html");
  });

  $(function () {
    $("#footer").load("../../html/main/footer.html");
  });

  //템플릿 수정하기 버튼 
  const toTemplate = document.querySelector(".to-template");
  toTemplate.addEventListener("click", function () {
    window.location.href = "../../html/admCreateTemplate.html";
  })

  //댓글보기 버튼 
  const toComment = document.querySelector(".to-comment");
  toComment.addEventListener("click", function () {
    window.location.href = "../../html/admin/admReplies.html";
  })
});
