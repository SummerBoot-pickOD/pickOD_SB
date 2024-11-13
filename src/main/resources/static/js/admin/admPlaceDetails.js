
document.addEventListener('DOMContentLoaded', () => {
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
  let reported = document.getElementById('is-reported').getAttribute('data-isreported');
  console.log(reported);
  const reportIcon = document.getElementById('report-icon');
  if (String(reported) === 'Y') {
    reportIcon.src = reportIcon.getAttribute('data-img1');
    reportIcon.style.cursor = 'pointer';
    reportIcon.addEventListener('click', () => {
      // 이때, 이 장소이름이 검색된?? 그니까 이 장소만 조회된 페이지로 넘기기
      const postId = reportIcon.getAttribute('data-place-id');
      window.location.href = '/admin/admPlaceMgmt/toReport?postId=' + postId;
    });
  } else if (String(reported) === 'N') {
    reportIcon.src = reportIcon.getAttribute('data-img2');
  }


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


  //nav 발자국 톡 > 댓글 관리페이지로 이동
  const toComments = document.getElementById('to-comments');

  toComments.addEventListener('click', function (event) {
    event.preventDefault();
    window.location.href = '../../html/admin/admReplies.html';
  });

  //장소 삭제 버튼
  const deleteBtn = document.getElementById('delete-btn');
  deleteBtn.addEventListener('click', function(){
    const placeName = this.getAttribute('data-place-name');
    const placeId = this.getAttribute('data-place-id');
    let result = confirm(`"${placeName}" 을 장소에서 삭제하시겠습니까?`);
    console.log(placeName);
    console.log(placeId);
    if (result){
      window.location.href = '/admin/admPlaceMgmt/details/deletePlace?placeId=' + placeId;
    } else {
      window.location.href='/admin/admPlaceMgmt/details/' + placeId;
    }
  })
});
