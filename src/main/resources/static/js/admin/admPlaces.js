document.addEventListener('DOMContentLoaded', function () {
  const openCmt = document.querySelectorAll('.to-comments');
  const toPlace = document.querySelectorAll('.to-place');

  openCmt.forEach(btn => {
    btn.addEventListener("click", function () {
      window.location.href = "../../html/admin/admReplies.html";
    });
  });

  toPlace.forEach(btn => {
    btn.addEventListener("click", function () {
      const placeId = this.getAttribute('data-place-id');
      window.location.href = "/admin/admPlaceMgmt/details/" + placeId;
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