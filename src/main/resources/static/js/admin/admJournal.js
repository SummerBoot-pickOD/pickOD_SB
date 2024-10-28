document.addEventListener('DOMContentLoaded', function () {
  $(function () {
    $("#header").load("../../html/admin/admHeader.html");
  });

  $(function () {
    $("#footer").load("../../html/main/footer.html");
  });


  const openCmt = document.querySelectorAll('.to-comments');
  const toJnl = document.querySelectorAll('.to-journal');

  openCmt.forEach(btn => {
    btn.addEventListener("click", function () {
      window.location.href = "../../html/admin/admReplies.html";
    });
  });

  toJnl.forEach(btn => {
    btn.addEventListener("click", function () {
      window.location.href = "../../html/admin/admJournalDetails.html";
    });
  });

  document.querySelectorAll(".jnl-title").forEach(cell => {
    cell.addEventListener('click', function (evt) {
      console.log("클릭됨")
      this.classList.toggle('expanded');
    })
  })



});