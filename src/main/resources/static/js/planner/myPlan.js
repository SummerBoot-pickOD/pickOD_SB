//헤더푸터
$(function () {
  $("#header").load("../main/header.html");
  });
  
  $(function () {
  $("#footer").load("../main/footer.html");
  });


// 모달 열기
const modal = document.getElementById("dateRangeModal");
const openModalBtn = document.getElementById("openModalBtn");
const closeModalBtn = document.getElementById("closeModalBtn");
const saveDateBtn = document.getElementById("saveDateBtn");
const displayDateRange = document.getElementById("displayDateRange");
const startDateInput = document.getElementById("startDate");
const endDateInput = document.getElementById("endDate");

openModalBtn.onclick = function() {
  modal.style.display = "flex";
}

closeModalBtn.onclick = function() {
  modal.style.display = "none";
}

saveDateBtn.onclick = function() {
  const startDate = startDateInput.value;
  const endDate = endDateInput.value;

  if (startDate && endDate) {
    if (new Date(startDate) <= new Date(endDate)) {
      displayDateRange.textContent = `${startDate} ~ ${endDate}`;
      modal.style.display = "none";
    } else {
      alert("출발일은 도착일보다 이전이어야 합니다.");
    }
  } else {
    alert("출발일과 도착일을 모두 선택해주세요.");
  }
}

// 모달 외부를 클릭하면 닫기
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}


  //장소추가 - with delete button
document.addEventListener('DOMContentLoaded', function () {
  let placeCount = 0; // 장소 순번 관리
  let currentDaySpotContainer = null; // 현재 선택된 dayspot-container를 저장하는 변수

  // 장소 추가 모달 관련 요소
  const addSpotModal = document.querySelector('.addspot-container');
  const closeAddSpotBtn = document.querySelector('.addspot-container .close-btn');
  const savedPlaces = document.querySelectorAll('.place-list button');

  // 이벤트 위임을 사용하여 동적으로 생성된 '장소추가' 버튼에 이벤트 핸들러 적용
  document.addEventListener('click', function (e) {
    // 장소추가 버튼 클릭 시
    if (e.target && e.target.classList.contains('add-spot-btn')) {
      addSpotModal.style.display = 'flex'; // 모달창 표시
      currentDaySpotContainer = e.target.closest('.dayplan-container').querySelector('.dayspot-container'); // 선택된 dayspot-container
    }
  });

  // 모달 닫기 버튼
  closeAddSpotBtn.onclick = function () {
    addSpotModal.style.display = 'none'; // 모달창 숨기기
  };

  // 장소 리스트에서 '추가' 버튼 클릭 시 해당 장소를 day-spot에 추가
  savedPlaces.forEach(button => {
    button.onclick = function () {
      placeCount += 1; // 장소 순번 증가
      const spotName = this.parentElement.textContent.trim(); // 장소 이름 가져오기
      console.log(spotName);
      // 기존 비어있는 day-spot이 있다면 그곳에 추가
      let existingDaySpot = currentDaySpotContainer.querySelector('.day-spot');

      // 만약 기존에 빈 day-spot이 있으면 그곳에 장소를 추가
      if (existingDaySpot && !existingDaySpot.querySelector('.order-spot').textContent) {
        existingDaySpot.querySelector('.order-num').textContent = placeCount; // 순번 추가
        existingDaySpot.querySelector('.order-spot').textContent = spotName; // 장소 이름 추가
        existingDaySpot.style.display = 'flex'; // 장소 추가될 때 display를 'flex'로 설정
        addDeleteButton(existingDaySpot); // 삭제 버튼 추가
      } else {
        // day-spot이 비어 있지 않거나 없는 경우, 새로운 day-spot을 생성
        const daySpotDiv = document.createElement('div');
        daySpotDiv.classList.add('day-spot');
        daySpotDiv.style.display = 'flex'; // 새로운 day-spot의 display를 'flex'로 설정

        // 순번 div 생성
        const orderNumDiv = document.createElement('div');
        orderNumDiv.classList.add('order-num');
        orderNumDiv.textContent = placeCount; // 순번 추가

        // 장소 이름 div 생성
        const orderSpotDiv = document.createElement('div');
        orderSpotDiv.classList.add('order-spot');
        orderSpotDiv.textContent = spotName; // 장소 이름 추가

        // day-spot에 order-num과 order-spot 추가
        daySpotDiv.appendChild(orderNumDiv);
        daySpotDiv.appendChild(orderSpotDiv);

        // 삭제 버튼 추가
        addDeleteButton(daySpotDiv);

        // 선택된 dayspot-container에 새로운 day-spot 추가
        currentDaySpotContainer.appendChild(daySpotDiv);
      }

      // 장소 추가 후 모달창 닫기
      addSpotModal.style.display = 'none';
    };
  });
});

// 메모 추가 - with delete button
document.addEventListener('DOMContentLoaded', function () {
  const memoModal = document.querySelector('.memo-container');
  const memoTextarea = document.getElementById('textarea');
  const saveMemoBtn = document.querySelector('.save');
  let currentDaySpotContainer = null; 

  document.addEventListener('click', function (e) {
    if (e.target && e.target.classList.contains('add-memo-btn')) {
      memoModal.style.display = 'flex'; 
      currentDaySpotContainer = e.target.closest('.dayplan-container').querySelector('.dayspot-container'); 
    }

    if (e.target && e.target.classList.contains('btn-close')) {
      memoModal.style.display = 'none';
      memoTextarea.value = ''; 
    }
  });

  saveMemoBtn.onclick = function () {
    const memoText = memoTextarea.value.trim(); 

    if (memoText && currentDaySpotContainer) {
      const lastDaySpot = currentDaySpotContainer.querySelector('.day-spot:last-child');
      const btnArea = currentDaySpotContainer.querySelector('.btn-area');

      const memoBox = document.createElement('div');
      memoBox.classList.add('memo-space');
      memoBox.style.display = 'flex'; 

      const spaceArea = document.createElement('div');
      spaceArea.classList.add('space-area');
      memoBox.appendChild(spaceArea); 

      const memoArea = document.createElement('div');
      memoArea.classList.add('memo-area');
      memoArea.textContent = memoText; 
      memoBox.appendChild(memoArea);

      addDeleteButton(memoBox);  // 삭제 버튼 추가

      if (lastDaySpot) {
        lastDaySpot.insertAdjacentElement('afterend', memoBox); 
      } else if (btnArea) {
        btnArea.insertAdjacentElement('beforebegin', memoBox);
      }

      memoModal.style.display = 'none';
      memoTextarea.value = ''; 
    } else {
      alert('메모를 입력하세요.');
    }
  };
});

// Add a delete button to the element
function addDeleteButton(element) {
  const deleteBtn = document.createElement('button');
  deleteBtn.classList.add('btn-delete');
  deleteBtn.textContent = '삭제';  // "삭제" means delete
  deleteBtn.style.marginLeft = 'auto';  // Align the delete button to the right
  deleteBtn.style.cursor = 'pointer';

  deleteBtn.addEventListener('click', function () {
    element.remove();  // Remove the entire element (either day-spot or memo-space)
  });

  element.appendChild(deleteBtn);  // Append the delete button to the element
}



//일정계산해서 컨테이너추가하기추가하기
document.addEventListener('DOMContentLoaded', function () {
  const startDateInput = document.getElementById('startDate');
  const endDateInput = document.getElementById('endDate');
  const displayDateRange = document.getElementById('displayDateRange');
  const saveDateBtn = document.getElementById('saveDateBtn');
  const main = document.querySelector('main'); // 메인 컨테이너에 dayplan-container 추가

  // 날짜 차이 계산 함수
  function calculateDaysBetween(startDate, endDate) {
    const start = new Date(startDate);
    const end = new Date(endDate);
    const diffTime = Math.abs(end - start);
    const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24)) + 1; // 양쪽 날짜 포함
    return diffDays;
  }

  // 날짜 형식 변환 함수 (YYYY-MM-DD -> MM/DD 형식)
  function formatDateToMD(dateString) {
    const date = new Date(dateString);
    const month = date.getMonth() + 1; // 월은 0부터 시작하므로 +1
    const day = date.getDate();
    return `${month}/${day}`;
  }

  // 저장 버튼 클릭 시 실행
  saveDateBtn.addEventListener('click', function () {
    const startDate = startDateInput.value;
    const endDate = endDateInput.value;

    if (startDate && endDate) {
      // 날짜 범위 표시
      displayDateRange.innerHTML = `<div>여행 날짜: ${startDate} ~ ${endDate}</div>`;

      // 기존 dayplan-container 삭제 (있다면)
      document.querySelectorAll('.dayplan-container').forEach(container => container.remove());

      // 날짜 차이 계산
      const numDays = calculateDaysBetween(startDate, endDate);

      // 날짜만큼 dayplan-container 추가
      for (let i = 0; i < numDays; i++) {
        const currentDate = new Date(startDate);
        currentDate.setDate(currentDate.getDate() + i); // 날짜 증가

        const dayPlanContainer = document.createElement('div');
        dayPlanContainer.classList.add('dayplan-container');

        // 각 daybox 생성 및 날짜 표시
        const dayBox = document.createElement('div');
        dayBox.classList.add('day-box');
        const dayNumber = i + 1;
        const formattedDate = formatDateToMD(currentDate.toISOString().split('T')[0]);

        dayBox.innerHTML = `<span class="daynum">Day ${dayNumber}</span> <span class="datenum">${formattedDate}</span>`;
        dayPlanContainer.appendChild(dayBox);

        // dayspot-container 추가
        const dayspotContainer = document.createElement('div');
        dayspotContainer.classList.add('dayspot-container');

        // 기본 day-spot을 dayspot-container에 추가
        const daySpot = document.createElement('div');
        daySpot.classList.add('day-spot');
        daySpot.innerHTML = `
          <div class="order-num"></div>
          <div class="order-spot"></div>
        `;
        dayspotContainer.appendChild(daySpot);

        // 메모 박스 추가
        const memoBox = document.createElement('div');
        memoBox.classList.add('memo-space');

        // space-area 추가
        const spaceArea = document.createElement('div');
        spaceArea.classList.add('space-area');
        memoBox.appendChild(spaceArea); // memo-space 추가

        // memo-area 추가
        const memoArea = document.createElement('div');
        memoArea.classList.add('memo-area');
        memoBox.appendChild(memoArea); // memo-space 추가

        dayspotContainer.appendChild(memoBox); // dayspot-container에 memo-space 추가

        dayPlanContainer.appendChild(dayspotContainer);

        // 버튼 영역 추가
        const btnArea = document.createElement('div');
        btnArea.classList.add('btn-area');
        btnArea.innerHTML = `
          <button type="button" class="add-spot-btn">장소추가</button>
          <button type="button" class="add-memo-btn">메모추가</button>
        `;
        dayPlanContainer.appendChild(btnArea);

        // dayplan-container를 main에 추가
        main.appendChild(dayPlanContainer);
      }
    } else {
      alert('날짜를 선택해주세요.');
    }
  });
});
const btnSave = document.querySelector('.save-btn');
const btnCancle = document.querySelector('.cancel-btn');


btnSave.addEventListener('click', function () {
    // Confirm 창을 띄우고, 확인 시 true, 취소 시 false 반환

  if (confirm('저장하시겠습니까?')) {
    // 확인 버튼을 누르면 mypage.html로 이동
    window.location.href = '../mypage/mypage.html';
  } 
  // 취소 버튼을 누르면 아무 일도 일어나지 않음 (아무 코드도 실행되지 않음)
});

console.log(window.location.href);
btnCancle.addEventListener('click', function () {

  if (confirm('정말 취소하시겠습니까?')) {
    // 확인 버튼을 누르면 main.html로 이동
    window.location.href = '../main/main.html';
    // 현재 페이지의 URL 정보를 관리하는 객체로 접근해서 mainhtml로접근
  } 
  // 취소 버튼을 누르면 아무 일도 일어나지 않음 (아무 코드도 실행되지 않음)
});






/*20241021 이전

//헤더푸터
$(function () {
  $("#header").load("../main/header.html");
  });
  
  $(function () {
  $("#footer").load("../main/footer.html");
  });


// 모달 열기
const modal = document.getElementById("dateRangeModal");
const openModalBtn = document.getElementById("openModalBtn");
const closeModalBtn = document.getElementById("closeModalBtn");
const saveDateBtn = document.getElementById("saveDateBtn");
const displayDateRange = document.getElementById("displayDateRange");
const startDateInput = document.getElementById("startDate");
const endDateInput = document.getElementById("endDate");

openModalBtn.onclick = function() {
  modal.style.display = "flex";
}

closeModalBtn.onclick = function() {
  modal.style.display = "none";
}

saveDateBtn.onclick = function() {
  const startDate = startDateInput.value;
  const endDate = endDateInput.value;

  if (startDate && endDate) {
    if (new Date(startDate) <= new Date(endDate)) {
      displayDateRange.textContent = `${startDate} ~ ${endDate}`;
      modal.style.display = "none";
    } else {
      alert("출발일은 도착일보다 이전이어야 합니다.");
    }
  } else {
    alert("출발일과 도착일을 모두 선택해주세요.");
  }
}

// 모달 외부를 클릭하면 닫기
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}


//장소추가

document.addEventListener('DOMContentLoaded', function () {
  let placeCount = 0; // 장소 순번 관리
  let currentDaySpotContainer = null; // 현재 선택된 dayspot-container를 저장하는 변수

  // 장소 추가 모달 관련 요소
  const addSpotModal = document.querySelector('.addspot-container');
  const closeAddSpotBtn = document.querySelector('.addspot-container .close-btn');
  const savedPlaces = document.querySelectorAll('.place-list button');

  // 이벤트 위임을 사용하여 동적으로 생성된 '장소추가' 버튼에 이벤트 핸들러 적용
  document.addEventListener('click', function (e) {
    // 장소추가 버튼 클릭 시
    if (e.target && e.target.classList.contains('add-spot-btn')) {
      addSpotModal.style.display = 'flex'; // 모달창 표시
      currentDaySpotContainer = e.target.closest('.dayplan-container').querySelector('.dayspot-container'); // 선택된 dayspot-container
    }
  });

  // 모달 닫기 버튼
  closeAddSpotBtn.onclick = function () {
    addSpotModal.style.display = 'none'; // 모달창 숨기기
  };

  // 장소 리스트에서 '추가' 버튼 클릭 시 해당 장소를 day-spot에 추가
  savedPlaces.forEach(button => {
    button.onclick = function () {
      placeCount += 1; // 장소 순번 증가
      const spotName = this.parentElement.textContent.trim(); // 장소 이름 가져오기
      console.log(spotName);
      // 기존 비어있는 day-spot이 있다면 그곳에 추가
      let existingDaySpot = currentDaySpotContainer.querySelector('.day-spot');

      // 만약 기존에 빈 day-spot이 있으면 그곳에 장소를 추가
      if (existingDaySpot && !existingDaySpot.querySelector('.order-spot').textContent) {
        existingDaySpot.querySelector('.order-num').textContent = placeCount; // 순번 추가
        existingDaySpot.querySelector('.order-spot').textContent = spotName; // 장소 이름 추가
        existingDaySpot.style.display = 'flex'; // 장소 추가될 때 display를 'flex'로 설정
      } else {
        // day-spot이 비어 있지 않거나 없는 경우, 새로운 day-spot을 생성
        const daySpotDiv = document.createElement('div');
        daySpotDiv.classList.add('day-spot'); // day-spot 클래스 추가
        daySpotDiv.style.display = 'flex'; // 새로운 day-spot의 display를 'flex'로 설정

        // 순번 div 생성
        const orderNumDiv = document.createElement('div');
        orderNumDiv.classList.add('order-num');
        orderNumDiv.textContent = placeCount; // 순번 추가

        // 장소 이름 div 생성
        const orderSpotDiv = document.createElement('div');
        orderSpotDiv.classList.add('order-spot');
        orderSpotDiv.textContent = spotName; // 장소 이름 추가

        // day-spot에 order-num과 order-spot 추가
        daySpotDiv.appendChild(orderNumDiv);
        daySpotDiv.appendChild(orderSpotDiv);

        // 선택된 dayspot-container에 새로운 day-spot 추가
        currentDaySpotContainer.appendChild(daySpotDiv);
      }

      // 장소 추가 후 모달창 닫기
      addSpotModal.style.display = 'none';
    };
  });
});

//메모추가하는 부분
document.addEventListener('DOMContentLoaded', function () {
  // 메모 추가 모달 관련 요소
  const memoModal = document.querySelector('.memo-container');
  const memoTextarea = document.getElementById('textarea');
  const saveMemoBtn = document.querySelector('.save');
  let currentDaySpotContainer = null; // 현재 선택된 dayspot-container를 저장하는 변수

  // 이벤트 위임을 사용하여 동적으로 생성된 '메모추가' 버튼에 이벤트 핸들러 적용
  document.addEventListener('click', function (e) {
    // '메모추가' 버튼 클릭 시 모달 열기
    if (e.target && e.target.classList.contains('add-memo-btn')) {
      memoModal.style.display = 'flex'; // 메모 모달 표시
      currentDaySpotContainer = e.target.closest('.dayplan-container').querySelector('.dayspot-container'); // 선택된 dayspot-container 저장
    }

    // '닫기' 버튼 클릭 시 모달 닫기
    if (e.target && e.target.classList.contains('btn-close')) {
      memoModal.style.display = 'none';
      memoTextarea.value = ''; // 메모창 닫힐 때 텍스트 비우기
    }
  });

  
  // '확인' 버튼 클릭 시 메모를 추가하는 기능
  saveMemoBtn.onclick = function () {
    const memoText = memoTextarea.value.trim(); // 입력된 메모 내용

    if (memoText && currentDaySpotContainer) {
      // 마지막으로 추가된 day-spot 찾기
      const lastDaySpot = currentDaySpotContainer.querySelector('.day-spot:last-child');
      const btnArea = currentDaySpotContainer.querySelector('.btn-area'); // btn-area 위치 찾기

      // 새로운 .memo-space 생성
      const memoBox = document.createElement('div');
      memoBox.classList.add('memo-space');
      memoBox.style.display = 'flex'; // .memo-space의 display를 'flex'로 설정
      console.log(memoBox.style.display); //이부분이 안되네

      // Create space-area inside memo-space
      const spaceArea = document.createElement('div');
      spaceArea.classList.add('space-area');
      memoBox.appendChild(spaceArea); // Add space-area to memo-space

      // Create memo-area inside memo-space
      const memoArea = document.createElement('div');
      memoArea.classList.add('memo-area');
      memoArea.textContent = memoText; // 메모 내용을 추가
      memoBox.appendChild(memoArea); // Add memo-area to memo-space

      // 마지막 day-spot 뒤에 memo-space 삽입
      if (lastDaySpot) {
        lastDaySpot.insertAdjacentElement('afterend', memoBox); // 마지막 day-spot 뒤에 memo-space 삽입
      } else if (btnArea) {
        // 만약 day-spot이 없다면 btn-area 앞에 삽입
        btnArea.insertAdjacentElement('beforebegin', memoBox);
      }

      // Close the modal and clear the textarea
      memoModal.style.display = 'none'; // 메모 모달 닫기
      memoTextarea.value = ''; // 메모창 닫힐 때 텍스트 비우기
    } else {
      alert('메모를 입력하세요.');
    }
  };
});


//일정계산해서 컨테이너추가하기추가하기
document.addEventListener('DOMContentLoaded', function () {
  const startDateInput = document.getElementById('startDate');
  const endDateInput = document.getElementById('endDate');
  const displayDateRange = document.getElementById('displayDateRange');
  const saveDateBtn = document.getElementById('saveDateBtn');
  const main = document.querySelector('main'); // 메인 컨테이너에 dayplan-container 추가

  // 날짜 차이 계산 함수
  function calculateDaysBetween(startDate, endDate) {
    const start = new Date(startDate);
    const end = new Date(endDate);
    const diffTime = Math.abs(end - start);
    const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24)) + 1; // 양쪽 날짜 포함
    return diffDays;
  }

  // 날짜 형식 변환 함수 (YYYY-MM-DD -> MM/DD 형식)
  function formatDateToMD(dateString) {
    const date = new Date(dateString);
    const month = date.getMonth() + 1; // 월은 0부터 시작하므로 +1
    const day = date.getDate();
    return `${month}/${day}`;
  }

  // 저장 버튼 클릭 시 실행
  saveDateBtn.addEventListener('click', function () {
    const startDate = startDateInput.value;
    const endDate = endDateInput.value;

    if (startDate && endDate) {
      // 날짜 범위 표시
      displayDateRange.innerHTML = `<div>여행 날짜: ${startDate} ~ ${endDate}</div>`;

      // 기존 dayplan-container 삭제 (있다면)
      document.querySelectorAll('.dayplan-container').forEach(container => container.remove());

      // 날짜 차이 계산
      const numDays = calculateDaysBetween(startDate, endDate);

      // 날짜만큼 dayplan-container 추가
      for (let i = 0; i < numDays; i++) {
        const currentDate = new Date(startDate);
        currentDate.setDate(currentDate.getDate() + i); // 날짜 증가

        const dayPlanContainer = document.createElement('div');
        dayPlanContainer.classList.add('dayplan-container');

        // 각 daybox 생성 및 날짜 표시
        const dayBox = document.createElement('div');
        dayBox.classList.add('day-box');
        const dayNumber = i + 1;
        const formattedDate = formatDateToMD(currentDate.toISOString().split('T')[0]);

        dayBox.innerHTML = `<span class="daynum">Day ${dayNumber}</span> <span class="datenum">${formattedDate}</span>`;
        dayPlanContainer.appendChild(dayBox);

        // dayspot-container 추가
        const dayspotContainer = document.createElement('div');
        dayspotContainer.classList.add('dayspot-container');

        // 기본 day-spot을 dayspot-container에 추가
        const daySpot = document.createElement('div');
        daySpot.classList.add('day-spot');
        daySpot.innerHTML = `
          <div class="order-num"></div>
          <div class="order-spot"></div>
        `;
        dayspotContainer.appendChild(daySpot);

        // 메모 박스 추가
        const memoBox = document.createElement('div');
        memoBox.classList.add('memo-space');

        // space-area 추가
        const spaceArea = document.createElement('div');
        spaceArea.classList.add('space-area');
        memoBox.appendChild(spaceArea); // memo-space 추가

        // memo-area 추가
        const memoArea = document.createElement('div');
        memoArea.classList.add('memo-area');
        memoBox.appendChild(memoArea); // memo-space 추가

        dayspotContainer.appendChild(memoBox); // dayspot-container에 memo-space 추가

        dayPlanContainer.appendChild(dayspotContainer);

        // 버튼 영역 추가
        const btnArea = document.createElement('div');
        btnArea.classList.add('btn-area');
        btnArea.innerHTML = `
          <button type="button" class="add-spot-btn">장소추가</button>
          <button type="button" class="add-memo-btn">메모추가</button>
        `;
        dayPlanContainer.appendChild(btnArea);

        // dayplan-container를 main에 추가
        main.appendChild(dayPlanContainer);
      }
    } else {
      alert('날짜를 선택해주세요.');
    }
  });
});
const btnSave = document.querySelector('.save-btn');
const btnCancle = document.querySelector('.cancel-btn');


btnSave.addEventListener('click', function () {
    // Confirm 창을 띄우고, 확인 시 true, 취소 시 false 반환

  if (confirm('저장하시겠습니까?')) {
    // 확인 버튼을 누르면 mypage.html로 이동
    window.location.href = '../mypage/mypage.html';
  } 
  // 취소 버튼을 누르면 아무 일도 일어나지 않음 (아무 코드도 실행되지 않음)
});

console.log(window.location.href);
btnCancle.addEventListener('click', function () {

  if (confirm('정말 취소하시겠습니까?')) {
    // 확인 버튼을 누르면 main.html로 이동
    window.location.href = '../main/main.html';
    // 현재 페이지의 URL 정보를 관리하는 객체로 접근해서 mainhtml로접근
  } 
  // 취소 버튼을 누르면 아무 일도 일어나지 않음 (아무 코드도 실행되지 않음)
}); */