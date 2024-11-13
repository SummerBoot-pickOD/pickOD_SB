document.addEventListener('DOMContentLoaded', function () {

  const openCmt = document.querySelectorAll('.to-comments');
  const toJnl = document.querySelectorAll('.to-journal');

  openCmt.forEach(btn => {
    btn.addEventListener("click", function () {
      const jnlNum = this.getAttribute('data-jnl-num');
      window.location.href = "/admin/admReplies/journal?jnlNum=" + jnlNum;
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