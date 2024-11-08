//헤더푸터
$(function () {
  $("#header").load("../main/header.html");
  });
  
  $(function () {
  $("#footer").load("../main/footer.html");
  });


let btnCheckList = document.querySelector('.btn-checklist');
let checkListContainer = document.querySelector('.checklist-container');

let btnTripList = document.querySelector('.btn-triplist');
let myTripContainer = document.querySelector('.mytrip-container');

let btnPlanList = document.querySelector('.btn-planlist');
let myPlanContainer = document.querySelector('.myplan-container');

let ResultDetail = document.querySelector('.result-detail');
let ResultDetail2 = document.querySelector('.result-detail2');
let ResultDetail3 = document.querySelector('.result-detail3');

btnCheckList.addEventListener("click", function() {
  checkListContainer.style.display = 'grid';
  myTripContainer.style.display = 'none';
  myPlanContainer.style.display = 'none';
  btnCheckList.style.color = 'black';
  btnTripList.style.color = '#a1a1a1';
  btnPlanList.style.color = '#a1a1a1';
  ResultDetail.style.display = 'flex';
  ResultDetail2.style.display = 'none';
  ResultDetail3.style.display = 'none';
});

btnTripList.addEventListener("click", function() {
  myTripContainer.style.display = 'grid';
  checkListContainer.style.display = 'none';
  myPlanContainer.style.display = 'none';
  btnTripList.style.color = 'black';
  btnCheckList.style.color = '#a1a1a1';
  btnPlanList.style.color = '#a1a1a1';
  ResultDetail2.style.display = 'flex';
  ResultDetail.style.display = 'none';
  ResultDetail3.style.display = 'none';
});

btnPlanList.addEventListener("click", function(){
  myPlanContainer.style.display = 'grid';
  myTripContainer.style.display = 'none';
  checkListContainer.style.display = 'none';
  btnPlanList.style.color = 'black';
  btnTripList.style.color = '#a1a1a1';
  btnCheckList.style.color = '#a1a1a1';
  ResultDetail.style.display = 'none';
  ResultDetail2.style.display = 'none';
  ResultDetail3.style.display = 'flex';
});

let searchFp = document.querySelector('.search-fp');
let searchSp = document.querySelector('.search-sp');
let searchTpl = document.querySelector('.search-tpl');

searchFp.addEventListener('click', ()=>{
  searchFp.style.fontWeight = 'bold';
  searchSp.style.fontWeight = 'normal';
  searchTpl.style.fontWeight = 'normal';

  // db연결하면 색인기능 들어감
});

searchSp.addEventListener('click', ()=>{
  searchSp.style.fontWeight = 'bold';
  searchFp.style.fontWeight = 'normal';
  searchTpl.style.fontWeight = 'normal';
  
  // db연결하면 색인기능 들어감
});

searchTpl.addEventListener('click', ()=>{
  searchTpl.style.fontWeight = 'bold';
  searchSp.style.fontWeight = 'normal';
  searchFp.style.fontWeight = 'normal';
  
  // db연결하면 검색기능 들어감
});


let searchRecent = document.querySelector('.search-recent');
let searchDate = document.querySelector('.search-date');

searchRecent.addEventListener('click', ()=>{
  searchRecent.style.fontWeight = 'bold';
  searchDate.style.fontWeight = 'normal';
  
  // db연결하면 색인기능 들어감
});

searchDate.addEventListener('click', ()=>{
  searchDate.style.fontWeight = 'bold';
  searchRecent.style.fontWeight = 'normal';
  
  // db연결하면 검색기능 들어감
});

// 찜하기 해제하면 리스트삭제

document.querySelectorAll('.checklist-check').forEach(function(checkElement) {
  checkElement.addEventListener('click', function() {
    // 클릭된 요소의 부모의 부모(.check-list)를 찾고 제거
    const checkListElement = this.closest('.check-list');
    if (checkListElement) {
      checkListElement.remove();
    }
  });
});


// `checkedList`를 서버에서 받아와 JSON 형식으로 변환하여 JavaScript에 할당
// const checkedList = /*[[${checkedList}]]*/ [];

// 임시 데이터 추가 (테스트용)
// if (checkedList.length === 0) {
//   checkedList.push(
//       { title: "테스트 아이템 1", thumbnailUrl: "https://example.com/image1.jpg" },
//       { title: "테스트 아이템 2", thumbnailUrl: "https://example.com/image2.jpg" },
//       { title: "테스트 아이템 3", thumbnailUrl: "https://example.com/image3.jpg" }
//   );
// }

function renderCheckList(data) {
  // checklist-container를 가져옵니다.
  const container = document.querySelector('.checklist-container');
  container.innerHTML = ''; // 초기화하여 기존 항목 삭제

  // 주어진 데이터 배열을 반복하여 각 항목에 대해 HTML 요소를 생성합니다.
  data.forEach(item => {
    // 새로운 check-list div 생성
    const checkListDiv = document.createElement('div');
    checkListDiv.classList.add('check-list');

    // 이미지 박스 생성
    const imgBoxDiv = document.createElement('div');
    imgBoxDiv.classList.add('checklist-imgbox');
    const img = document.createElement('img');
    img.classList.add('checklist-img');
    img.src = item.thumbnailUrl ? item.thumbnailUrl : '../../img/mypage.png';
    img.alt = '썸네일 이미지';
    imgBoxDiv.appendChild(img);

    // 체크리스트 박스 생성
    const boxDiv = document.createElement('div');
    boxDiv.classList.add('checklist-box');

    const contentDiv = document.createElement('div');
    contentDiv.classList.add('checklist-content');
    boxDiv.appendChild(contentDiv);

    const checkDiv = document.createElement('div');
    checkDiv.classList.add('checklist-check');
    const checkImg = document.createElement('img');
    checkImg.src = '../../img/mypage/saved.png'; // 임시 이미지
    checkImg.alt = '찜한 아이템';
    checkDiv.appendChild(checkImg);
    boxDiv.appendChild(checkDiv);

    // 제목 div 생성
    const nameDiv = document.createElement('div');
    nameDiv.classList.add('checklist-name');
    nameDiv.textContent = item.title || '제목';

    // 생성된 요소를 check-list div에 추가
    checkListDiv.appendChild(imgBoxDiv);
    checkListDiv.appendChild(boxDiv);
    checkListDiv.appendChild(nameDiv);

    // check-list div를 checklist-container에 추가
    container.appendChild(checkListDiv);
  });
}

// 페이지 로드 시 checkedList 데이터를 사용해 렌더링
document.addEventListener('DOMContentLoaded', () => {
  renderCheckList(checkedList);
});