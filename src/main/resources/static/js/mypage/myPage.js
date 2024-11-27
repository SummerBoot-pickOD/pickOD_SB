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




//내 베스트게시물 리스트
// 페이지 로드 시 myBestList 데이터를 사용해 렌더링
document.addEventListener('DOMContentLoaded', () => {
  myBest(myBestList);
});

function myBest (data){
  const container = document.querySelector(".mybest-list");

// 데이터에 따라 요소 반복 생성
  data.forEach(item => {
    // nth-list 요소 생성
    const nthList = document.createElement("div");
    nthList.classList.add("nth-list");

    // 제목 요소 생성
    const title = document.createElement("div");
    title.classList.add("nth-title");
    title.textContent = item.jnlTitle;

    // 상세 정보 컨테이너 생성
    const detail = document.createElement("div");
    detail.classList.add("nth-detail");

    // 좋아요 횟수 요소 생성
    const check = document.createElement("div");
    check.classList.add("nth-check");
    check.textContent = item.numPick;

    // 조회수 요소 생성
    const view = document.createElement("div");
    view.classList.add("nth-view");
    view.textContent = item.jnlViews;

    // 댓글 수 요소 생성
    const review = document.createElement("div");
    review.classList.add("nth-review");
    review.textContent = item.numComments;

    // 상세 정보에 각 요소 추가
    detail.appendChild(check);
    detail.appendChild(view);
    detail.appendChild(review);

    // nth-list에 제목과 상세 정보 추가
    nthList.appendChild(title);
    nthList.appendChild(detail);

    // 컨테이너에 nth-list 추가
    container.appendChild(nthList);
  });
}

// let pickIdField;
//내 체크리스트
// 페이지 로드 시 checkedList 데이터를 사용해 렌더링
document.addEventListener('DOMContentLoaded', () => {
  renderCheckList(checkedList);
  // search-fp 클래스의 버튼 클릭 이벤트 리스너 추가
  const journalButton = document.querySelector('.search-fp');
  journalButton.addEventListener('click', () => {
    // checkedJournalList로 렌더링
    renderCheckList(checkedJournalList);
  });
  // search-sp 클래스의 버튼 클릭 이벤트 리스너 추가
  const placeButton = document.querySelector('.search-sp');
  placeButton.addEventListener('click', () => {
    // checkedPlaceList로 렌더링
    renderCheckList(CheckedPlaceList);
  });

  const tempButton = document.querySelector('.search-tpl');
  tempButton.addEventListener('click', () => {
    // checkedTemplateList로 렌더링
    renderCheckList(checkedTempList);
  });

  const defaultButton = document.querySelector('.btn-checklist');
  defaultButton.addEventListener('click', () => {
    // checkedList 렌더링
    renderCheckList(checkedList);
  });

});

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
    img.src = item.thumbnailUrl ? item.thumbnailUrl : '../../img/mypage/더미사진.png';
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

    //찜한 게시물 번호 추가
    const checkId = document.createElement('div');


    if (item.pickType === 'TEMPLATE') {
      checkId.textContent = item.pickId;
      checkId.classList.add('temp-id');

    } else if (item.pickType === 'JOURNAL') {
      checkId.textContent = item.pickId;
      checkId.classList.add('jnl-id');

    } else if (item.pickType === 'PLACE') {
      checkId.textContent = item.pickId;
      checkId.classList.add('place-id');

    }
    checkId.hidden = true;



    // 생성된 요소를 check-list div에 추가
    checkListDiv.appendChild(imgBoxDiv);
    checkListDiv.appendChild(boxDiv);
    checkListDiv.appendChild(nameDiv);
    checkListDiv.appendChild(checkId);

    // check-list div를 checklist-container에 추가
    container.appendChild(checkListDiv);
  });
}

// 찜하기 해제하면 리스트삭제
document.addEventListener('DOMContentLoaded', function () {
  let checkBtnAll = document.querySelectorAll('.checklist-check')
  console.log(checkBtnAll);
  checkBtnAll.forEach(function (deleteBtn) {
    deleteBtn.addEventListener('click', function () {
      console.log(deleteBtn);
      console.log(deleteBtn.parentElement.parentElement);

      const tempIdElement = deleteBtn.parentElement.parentElement.querySelector('.temp-id')?.innerText;
      console.log(tempIdElement);
      const placeIdElement = deleteBtn.parentElement.parentElement.querySelector('.place-id')?.innerText;
      console.log(placeIdElement);
      const jnlNumElement = deleteBtn.parentElement.parentElement.querySelector('.jnl-id')?.innerText;
      console.log(jnlNumElement);

      data = {

      };
      console.log(data);
      if (tempIdElement) data.tempId = Number(tempIdElement);
      if (placeIdElement) data.placeId = Number(placeIdElement);
      if (jnlNumElement) data.jnlNum = Number(jnlNumElement);


      fetch('/mypage/deleteCheck', {
        method: 'DELETE',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
      }).then(response => {
        if (!response.ok) throw new Error("Fail to fetch message.");
        return response.text();
      })
          .then(data => {
            console.log(data);
            location.reload();
          })
          .catch(error => {
            console.log("Error:", error);
          });
    });
  });
});

document.addEventListener('DOMContentLoaded', function () {
  //클릭한게 저널이면 해당 저널 게시물 이동
  let clickToBoard = document.querySelectorAll('.check-list');
  clickToBoard.forEach(function (eachBoard){
    eachBoard.addEventListener('click', function(){

      if (event.target.className === 'checklist-check') {
        return; // 체크박스를 클릭하면 함수 실행을 멈춤
      }

      const tempIdElement = eachBoard.querySelector('.temp-id')?.innerText;
      console.log(tempIdElement);
      const placeIdElement = eachBoard.querySelector('.place-id')?.innerText;
      console.log(placeIdElement);
      const jnlNumElement = eachBoard.querySelector('.jnl-id')?.innerText;
      console.log(jnlNumElement);


      if (jnlNumElement) {
        console.log("jnlNum이 들어옴");
        const data = {
          jnlNum: Number(jnlNumElement),
        };

        window.location.href = `/journal/detail/${data.jnlNum}`;
      }else if(placeIdElement){
        console.log("placeId 들어옴");
        const data = {
          placeId: Number(placeIdElement),
        };
        window.location.href = `/place/${data.placeId}`;
      }else if(tempIdElement){
        console.log("tempId 들어옴");
        const data = {
          tempId: Number(tempIdElement),
        };

        window.location.href = `/template/detail/${data.tempId}`;
      }
    })
  })
});




// 페이지 로드 시 checkedJournalList 데이터를 사용해 렌더링
document.addEventListener('DOMContentLoaded', () => {
  renderTripList(myJournalList);
});

function renderTripList(data) {
  // tripList-container를 가져옵니다.
  const container = document.querySelector('.mytrip-container');
  container.innerHTML = ''; // 초기화하여 기존 항목 삭제

  // 주어진 데이터 배열을 반복하여 각 항목에 대해 HTML 요소를 생성합니다.
  data.forEach(item => {
    // 새로운 trip-list div 생성
    const tripListDiv = document.createElement('div');
    tripListDiv.classList.add('mytrip-list');

    // 이미지 박스 생성
    const imgBoxDiv = document.createElement('div');
    imgBoxDiv.classList.add('triplist-imgbox');
    const img = document.createElement('img');
    img.classList.add('triplist-img');
    img.src = item.thumbnailUrl ? item.thumbnailUrl : '../../img/mypage/더미사진.png';
    img.alt = '썸네일 이미지';
    imgBoxDiv.appendChild(img);

    // 트립리스트 박스 생성
    const boxDiv = document.createElement('div');
    boxDiv.classList.add('triplist-box');

    // 제목 div 생성
    const nameDiv = document.createElement('div');
    nameDiv.classList.add('triplist-name');
    nameDiv.textContent = item.jnlTitle;

    //내 저널 번호 추가
    const jnlId = document.createElement('div');
    jnlId.textContent = item.jnlNum;
    jnlId.classList.add('jnl-num');
    jnlId.hidden = true;

    // 생성된 요소를 trip-list div에 추가
    tripListDiv.appendChild(imgBoxDiv);
    tripListDiv.appendChild(boxDiv);
    tripListDiv.appendChild(nameDiv);
    tripListDiv.appendChild(jnlId);

    // trip-list div를 mytrip-container에 추가
    container.appendChild(tripListDiv);
  });

  //클릭한게 저널이면 해당 저널 게시물 이동
  let clickToMyJournal = document.querySelectorAll('.mytrip-list');
  clickToMyJournal.forEach(function (eachJournal){
    eachJournal.addEventListener('click', function(){

      const jnlNum = eachJournal.querySelector('.jnl-num').innerText;
      console.log(jnlNum);

      const data ={
        jnlNum: Number(jnlNum),
      }
      window.location.href = `/journal/detail/${data.jnlNum}`;

    })
  })
}




//내 계획리스트
// 페이지 로드 시 myPlanList 데이터를 사용해 렌더링
document.addEventListener('DOMContentLoaded', () => {
  renderMyPlanList(myPlanList);
});

function renderMyPlanList(data){
  // 부모 컨테이너를 선택
  const container = document.querySelector(".myplan-container");

// 데이터에 따라 요소 반복 생성
  data.forEach(plan => {
    // myplan-list 요소 생성
    const myPlanList = document.createElement("div");
    myPlanList.classList.add("myplan-list");

    // 이미지 박스 요소 생성
    const imgBox = document.createElement("div");
    imgBox.classList.add("myplan-imgbox");
    const link = document.createElement("a");
    link.href = "../planner/myplanDetail.html";
    const img = document.createElement("img");
    // img.src = plan.imgSrc;
    img.src = '../../img/mypage/나무사진.png';
    img.classList.add("myplan-img");
    img.alt = "내여행계획";
    link.appendChild(img);
    imgBox.appendChild(link);

    // 내용 컨테이너 요소 생성
    const content = document.createElement("div");
    content.classList.add("myplan-content");

    // 기간 요소 생성
    const period = document.createElement("div");
    period.classList.add("myplan-period");
    period.textContent = (plan.period-1)+"박"+ (plan.period+"일");

    // 제목 요소 생성
    const title = document.createElement("div");
    title.classList.add("myplan-title");
    title.textContent = plan.planTitle;

    // 상세 날짜 요소 생성
    const detailDate = document.createElement("div");
    detailDate.classList.add("myplan-detaildate");
    detailDate.textContent = "계획일정:"

    const span = document.createElement("span");
    span.textContent = plan.planStartDate +"~"+ plan.planEndDate;
    detailDate.appendChild(span);

    // 컨텐츠에 모든 요소 추가
    content.appendChild(period);
    content.appendChild(title);
    content.appendChild(detailDate);
    // content.appendChild(location);
    // content.appendChild(locNum);

    // myplan-list에 이미지 박스와 컨텐츠 추가
    myPlanList.appendChild(imgBox);
    myPlanList.appendChild(content);

    // 컨테이너에 myplan-list 추가
    container.appendChild(myPlanList);
  });
}




