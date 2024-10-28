document.addEventListener('DOMContentLoaded',()=>{
  const saveimg_btn2=document.querySelectorAll(".checklist-check img");
  const saveimg_btn1=document.querySelectorAll(".tp_saveimg img");
  
//찜버튼
  saveimg_btn2.forEach(button=>{
    button.addEventListener('click',()=>{
      if(button.getAttribute('src')==="../../img/main/unsaved.png"){
      button.setAttribute('src',"../../img/main/saved.png");
      }else{
        button.setAttribute('src',"../../img/main/unsaved.png");
      } 
    })
  });

  saveimg_btn1.forEach(button=>{
    button.addEventListener('click',()=>{
      if(button.getAttribute('src')==="../../img/main/unsaved.png"){
      button.setAttribute('src',"../../img/main/saved.png");
      }else{
        button.setAttribute('src',"../../img/main/unsaved.png");
      } 
    })
  });

//행사 배열
  const festivals = [
    {
      img: '../../img/main/sampleFestivalImg1.png',
      title: '서로 엮는 북촌의 날',
      link: 'http://recollection.kr/',
      date: '2024.10.02~2024.11.30',
      location: '서울특별시 종로구 계동길 37 (계동) 북촌문화센터'
    },
    {
      img: '../../img/main/sampleFestivalImg2.png',
      title: '서울 불빛 축제',
      link: 'http://lightfestival.kr/',
      date: '2024.12.01~2025.01.05',
      location: '서울특별시 중구 서울광장'
    },
    {
      img: '../../img/main/sampleFestivalImg3.png',
      title: '한강 음악 축제',
      link: 'http://hangangmusic.kr/',
      date: '2024.09.15~2024.10.15',
      location: '서울특별시 영등포구 여의도 한강공원'
    },
    {
      img: '../../img/main/sampleFestivalImg1.png',
      title: '한강 음악 축제',
      link: 'http://hangangmusic.kr/',
      date: '2024.09.15~2024.10.15',
      location: '서울특별시 영등포구 여의도 한강공원'
    },
    {
      img: '../../img/main/sampleFestivalImg1.png',
      title: '한강 음악 축제',
      link: 'http://hangangmusic.kr/',
      date: '2024.09.15~2024.10.15',
      location: '서울특별시 영등포구 여의도 한강공원'
    },
    {
      img: '../../img/main/sampleFestivalImg1.png',
      title: '한강 음악 축제',
      link: 'http://hangangmusic.kr/',
      date: '2024.09.15~2024.10.15',
      location: '서울특별시 영등포구 여의도 한강공원'
    }
  ];
  
  let currentFestivalIndex = 0; // 현재 페스티벌 인덱스
  
  // DOM 요소
  const festivalImg = document.getElementById('festivalImg');
  const festivalTitle = document.getElementById('festivalTitle');
  const festivalLink = document.getElementById('festivalLink');
  const festivalDate = document.getElementById('festivalDate');
  const festivalLocation = document.getElementById('festivalLocation');
  const festivalCounter = document.getElementById('festivalCounter');
  
  // 페스티벌 정보 업데이트 함수
  function updateFestivalInfo(index) {
    const festival = festivals[index];
    festivalImg.src = festival.img;
    festivalTitle.textContent = festival.title;
    festivalLink.href = festival.link;
    festivalDate.textContent = festival.date;
    festivalLocation.textContent = festival.location;
    festivalCounter.textContent = `${index + 1}/${festivals.length}`;
  }
  
  // 이전 버튼 클릭 이벤트
  document.getElementById('prevBtn').addEventListener('click', function() {
    currentFestivalIndex = (currentFestivalIndex === 0) ? festivals.length - 1 : currentFestivalIndex - 1;
    updateFestivalInfo(currentFestivalIndex);
  });
  
  // 다음 버튼 클릭 이벤트
  document.getElementById('nextBtn').addEventListener('click', function() {
    currentFestivalIndex = (currentFestivalIndex === festivals.length - 1) ? 0 : currentFestivalIndex + 1;
    updateFestivalInfo(currentFestivalIndex);
  });
  
  // 초기 상태로 첫 번째 페스티벌 정보 표시
  updateFestivalInfo(currentFestivalIndex);









  $(function () {
    $("#header").load("../../html/main/header.html");
  });
  
  $(function () {
    $("#footer").load("../../html/main/footer.html");
  });
});
