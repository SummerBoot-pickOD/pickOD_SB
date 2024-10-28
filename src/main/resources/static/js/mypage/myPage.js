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

// 게시물 및 페이지네이션 처리

// const posts = Array.from(document.querySelectorAll('.check-list'));
// const postsPerPage = 9;
// let currentPage = 1;
// let totalPages;

// function displayMails() {
//   const postContainer = document.getElementById('posts');
//   postContainer.innerHTML = ''; // 현재 표시된 항목 초기화

//   const startIndex = (currentPage - 1) * postsPerPage;
//   const endIndex = Math.min(startIndex + postsPerPage, posts.length); // 실제 항목 수와 비교하여 인덱스 계산

//   // 해당 페이지에 맞는 mailbox-list만 표시
//   for (let i = startIndex; i < endIndex; i++) {
//     postContainer.appendChild(posts[i]);
//   }
// }


// function setupPagination() {
//   const paginationContainer = document.getElementById('pagination');
//   paginationContainer.innerHTML = ''; // 페이지네이션 초기화

//   totalPages = Math.ceil(posts.length / postsPerPage); // 실제 항목 수로 페이지 수 계산

//   const createButton = (pageNum, text) => {
//     const button = document.createElement('button');
//     button.textContent = text;
//     button.disabled = (currentPage === pageNum);
//     button.addEventListener('click', () => {
//       currentPage = pageNum;
//       displayMails(); // 페이지 클릭 시 항목 업데이트
//       setupPagination(); // 페이지네이션 버튼 다시 설정
//     });
//     return button;
//   };

//   // 이전 페이지 버튼
//   if (currentPage > 1) {
//     const prevButton = createButton(currentPage - 1, '<');
//     paginationContainer.appendChild(prevButton);
//   }

//   // 페이지 버튼 생성
//   for (let i = 1; i <= totalPages; i++) {
//     const button = createButton(i, i);
//     paginationContainer.appendChild(button);
//   }

//   // 다음 페이지 버튼
//   if (currentPage < totalPages) {
//     const nextButton = createButton(currentPage + 1, '>');
//     paginationContainer.appendChild(nextButton);
//   }
// };


//   document.addEventListener('DOMContentLoaded', function() {
//     setupPagination();
//     displayMails();
//   });