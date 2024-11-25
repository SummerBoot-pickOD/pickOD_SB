$(function () {
    $("#header").load("../main/header.html");
    $("#footer").load("../main/footer.html");
});


// DAY1..., 장소1... 태그
let currDay = 1;
let currLocation = 1;
let days = {
    1: { locations: { 1: '' } } // Default DAY 1 with one empty location
};

// Save current content from Summernote
function saveCurrentContent() {
    const currentContent = $('#summernote').summernote('code'); // 현재 서머노트 내용
    days[currDay].locations[currLocation] = currentContent; // 현재 Day/Location에 저장

    // JSON 변환 및 숨겨진 필드 업데이트
    const jnlDayList = [];
    Object.keys(days).forEach((day) => {
        Object.keys(days[day].locations).forEach((location) => {
            jnlDayList.push({
                jnlDay: parseInt(day),
                jnlPlaceOrder: parseInt(location),
                jnlContents: days[day].locations[location] || '',
                placeId: 1 // Place ID는 다른 방식으로 추가할 수 있음
            });
        });
    });

    // hidden input 업데이트
    document.getElementById('jnlDayListJson').value = JSON.stringify(jnlDayList);
}

// Load content into Summernote
function loadContent() {
    const content = days[currDay].locations[currLocation] || ''; // 저장된 내용 또는 빈 문자열
    $('#summernote').summernote('code', content); // 서머노트에 로드
}

// Switch to a specific day
function switchDay(day) {
    saveCurrentContent(); // 기존 내용 저장
    currDay = day;
    currLocation = 1; // 항상 첫 장소로 초기화
    updateDayTabs();
    updateLocationTabs();
    loadContent(); // 새로운 Day의 내용 로드
}

// Switch to a specific location
function switchLocation(day, location) {
    saveCurrentContent(); // 기존 내용 저장
    currLocation = location;
    loadContent(); // 새로운 Location의 내용 로드
    updateLocationTabs();
}

// Add a new day
function addDay() {
    saveCurrentContent(); // 현재 상태 저장
    const newDay = Object.keys(days).length + 1;
    days[newDay] = { locations: { 1: '' } }; // New day with one empty location

    const dayTab = document.createElement('button');
    dayTab.className = 'days';
    dayTab.type = 'button';
    dayTab.innerText = `DAY ${newDay}`;
    dayTab.onclick = () => switchDay(newDay);

    document.querySelector('.add-day').before(dayTab);
    switchDay(newDay); // 새롭게 생성된 Day로 이동
}

// Add a new location for the current day
function addLocation(day) {
    saveCurrentContent(); // 현재 상태 저장
    const locationCount = Object.keys(days[day].locations).length + 1;
    days[day].locations[locationCount] = ''; // New empty location

    const locationTab = document.createElement('button');
    locationTab.className = 'places';
    locationTab.type = 'button';
    locationTab.innerText = `장소${locationCount}`;
    locationTab.onclick = () => switchLocation(day, locationCount);

    document.querySelector('.add-place').before(locationTab);
    switchLocation(day, locationCount); // 새롭게 생성된 Location으로 이동
}

// Update Day Tabs
function updateDayTabs() {
    document.querySelectorAll('.days').forEach((tab, index) => {
        tab.classList.toggle('active', index + 1 === currDay);
    });
    updateLocationTabs();
}

// Update Location Tabs
function updateLocationTabs() {
    const locationsTabsContainer = document.getElementById('place-tabs');
    locationsTabsContainer.innerHTML = '';

    const locations = days[currDay].locations;
    Object.keys(locations).forEach((locationNum) => {
        const locationTab = document.createElement('button');
        locationTab.className = 'places';
        locationTab.type = 'button';
        locationTab.innerText = `장소${locationNum}`;
        locationTab.classList.toggle('active', locationNum == currLocation);
        locationTab.onclick = () => switchLocation(currDay, locationNum);
        locationsTabsContainer.appendChild(locationTab);
    });

    const addPlaceBtn = document.createElement('button');
    addPlaceBtn.className = 'add-place';
    addPlaceBtn.type = 'button';
    addPlaceBtn.innerText = '+';
    addPlaceBtn.onclick = () => addLocation(currDay);
    locationsTabsContainer.appendChild(addPlaceBtn);
}

// Save and clear all other days except DAY 1 and 장소 1
function saveAndClearOtherDays() {
    saveCurrentContent(); // 현재 상태 저장
    Object.keys(days).forEach((day) => {
        if (day != 1) {
            delete days[day];
        } else {
            Object.keys(days[day].locations).forEach((location) => {
                if (location != 1) {
                    delete days[day].locations[location];
                }
            });
        }
    });

    currDay = 1;
    currLocation = 1;
    updateDayTabs();
    updateLocationTabs();
    loadContent(); // DAY 1 및 장소 1의 내용 로드

    console.log("Saved and cleared:", days); // Debugging
}

// Initialize Summernote and Tabs
$(document).ready(function () {
    $('#summernote').summernote({
        height: 300,
        focus: true,
        lang: "ko-KR",
        placeholder: '최대 2048자까지 쓸 수 있습니다',
        toolbar: [
            ['style', ['bold', 'italic', 'underline', 'clear']],
            ['para', ['ul', 'ol', 'paragraph']],
            ['insert', ['link', 'picture', 'video']],
            ['view', ['fullscreen', 'codeview', 'help']]
        ]
    });

    $('form').on('submit', function() {
        saveCurrentContent(); // 폼 제출 전에 내용 저장
    });

    document.querySelector('.delete-button').addEventListener('click', function(event) {
        if (!confirmDelete()) {
            event.preventDefault();  // 삭제를 취소하면 기본 동작을 막습니다.
        }
    });

    function confirmDelete() {
        // 경고창 확인을 누르면 이동하도록 설정
        if (confirm("저장되지 않은 내용은 삭제됩니다. 정말로 삭제하시겠습니까?")) {
            window.location.href = "/journal/list";  // 확인을 누르면 해당 페이지로 이동
            return true;  // 경고창을 확인했을 때 true를 반환
        } else {
            return false;  // 취소를 눌렀을 때 false를 반환
        }
    }

    updateDayTabs(); // 초기 탭 설정
});