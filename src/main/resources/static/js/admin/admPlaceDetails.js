
document.addEventListener('DOMContentLoaded', () => {
  $(function () {
    $("#header").load("../../html/admin/admHeader.html");
  });

  $(function () {
    $("#footer").load("../../html/main/footer.html");
  });

  //이미지 영역 < > 에 따라 다른 이미지 로딩
  const slider = document.getElementById('slider');
  const prevBtn = document.getElementById('prev-btn');
  const nextBtn = document.getElementById('next-btn');
  const images = [
    "../../img/place/placeDetail/samplePlace.jpg",
    "../../img/place/placeDetail/samplePlace2.jpg",
    "../../img/place/placeDetail/samplePlace3.jpg"
  ];
  let currentIndex = 0;

  nextBtn.addEventListener('click', () => {
    currentIndex = (currentIndex + 1) % images.length;
    slider.setAttribute('src', images[currentIndex]);
  });

  prevBtn.addEventListener('click', () => {
    currentIndex = (currentIndex - 1 + images.length) % images.length;
    slider.setAttribute('src', images[currentIndex]);
  });

  //신고하기 아이콘 신고 여부에 따라 다르게 표시됨 
  let reported = 'Y';  // 실제로는 서버에서 불러와야 함

  const reportIcon = document.getElementById('report-icon');
  if (reported === 'Y') {
    reportIcon.src = '../../img/admin/reported.png';
    reportIcon.style.cursor = 'pointer';
    reportIcon.addEventListener('click', () => {
      // 이때, 이 장소이름이 검색된?? 그니까 이 장소만 조회된 페이지로 넘기기
      window.location.href = '../../html/admin/admReport.html';
    });
  } else if (reported === 'N') {
    reportIcon.src = '../../img/message/report.png';
  };


  //nav 버튼
  document.querySelectorAll('.anchor').forEach(anchor => {
    anchor.addEventListener('click', function (e) {
      e.preventDefault();

      const targetId = this.getAttribute('href');
      const targetElement = document.querySelector(targetId).offsetTop;

      window.scrollTo({
        top: targetElement - 111,
        behavior: 'smooth' // 부드러운 스크롤
      });
    });
  });


  //nav 발자국 톡 
  const toComments = document.getElementById('to-comments');

  toComments.addEventListener('click', function (event) {
    event.preventDefault();
    window.location.href = '../../html/admin/admReplies.html';
  });
});
