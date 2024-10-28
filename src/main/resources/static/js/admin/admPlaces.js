document.addEventListener('DOMContentLoaded', function () {
  $(function () {
    $("#header").load("../../html/admin/admHeader.html");
  });

  $(function () {
    $("#footer").load("../../html/main/footer.html");
  });


  const openCmt = document.querySelectorAll('.to-comments');
  const toPlace = document.querySelectorAll('.to-place');

  openCmt.forEach(btn => {
    btn.addEventListener("click", function () {
      window.location.href = "../../html/admin/admReplies.html";
    });
  });

  toPlace.forEach(btn => {
    btn.addEventListener("click", function () {
      window.location.href = "../../html/admin/admPlaceDetails.html";
    });
  });

  const showAdd = document.querySelectorAll(".address");
  showAdd.forEach(cell => {
    cell.addEventListener('click', function () {
      console.log("클릭됨")
      this.classList.toggle("expanded");
    })
  })
});