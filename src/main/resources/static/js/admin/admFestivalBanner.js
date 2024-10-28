document.addEventListener('DOMContentLoaded', () => {
  $(function () {
    $("#header").load("../../html/admin/admHeader.html");
  });

  $(function () {
    $("#footer").load("../../html/main/footer.html");
  });



  const banner = document.getElementById('banner');
  const prevBtn = document.getElementById('prev-btn');
  const nextBtn = document.getElementById('next-btn');
  const counter = document.getElementById('counter');
  const images = [
    "../../img/main/sampleFestivalImg.png",
    "../../img/festival/festivalImg.png",
    "../../img/place/placeDetail/samplePlace2.jpg",
    "../../img/place/placeDetail/samplePlace3.jpg"
  ];
  let currIndex = 0;

  function updatePhotoNum() {
    // 사진 카운터 업데이트
    counter.textContent = `${currIndex + 1} / ${images.length}`;
  }
  //처음에 페이지 로드될 때 
  window.onload = function () {
    const banner = document.getElementById('banner');
    const counter = document.getElementById('counter');

    // 첫 번째 이미지 설정
    banner.src = images[0];
    const currIndex = 1; // 첫 번째 이미지

    // 1/총 이미지 수 표시
    counter.textContent = `${currIndex} / ${images.length}`;
  };

  nextBtn.addEventListener('click', () => {
    currIndex = (currIndex + 1) % images.length;
    banner.setAttribute('src', images[currIndex]);
    updatePhotoNum();
  });

  prevBtn.addEventListener('click', () => {
    currIndex = (currIndex - 1 + images.length) % images.length;
    banner.setAttribute('src', images[currIndex]);
    updatePhotoNum();
  });




});