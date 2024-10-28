$(function () {
    $("#header").load("../main/header.html");
    $("#footer").load("../main/footer.html");
});

$(function() {
    // 시작일과 종료일에 달력 기능 적용
    $("#start-date").datepicker({
        dateFormat: "yy-mm-dd",
        changeMonth: true,
        changeYear: true
    });
    $("#end-date").datepicker({
        dateFormat: "yy-mm-dd",
        changeMonth: true,
        changeYear: true
    });
});

document.addEventListener('DOMContentLoaded', function() {
    const saveImage = document.querySelector('.saveimg');

    if (saveImage) {
        saveImage.addEventListener('click', function() {
            if (saveImage.src.includes('unsaved.png')) {
                saveImage.src = '../../img/main/saved.png'; // 이미지 변경
            } else {
                saveImage.src = '../../img/main/unsaved.png'; // 원래 이미지로 복원
            }
        });
    } else {
        console.warn('saveimg 요소를 찾을 수 없습니다.');
    }

    let dayCount = 1; // 총 DAY 버튼 카운트를 추적
    let currentDays = 1; // 현재 화면에 보이는 DAY 버튼의 개수를 추적

    // DAY 버튼 라벨을 업데이트하고 마지막 DAY에만 'x' 버튼을 추가하는 함수
    function updateDayButtons() {
        const dayButtons = document.querySelectorAll('.Day-container .Day-button');
        
        dayButtons.forEach((btn, index) => {
            btn.textContent = `DAY ${index + 1}`;
            const closeButton = btn.querySelector('.close-button');
            if (closeButton) closeButton.remove();

            if (index === dayButtons.length - 1 && currentDays > 1) {
                const closeButton = document.createElement('button');
                closeButton.classList.add('close-button');
                closeButton.textContent = 'x';
                closeButton.addEventListener('click', function() {
                    btn.parentElement.remove(); // 해당 DAY 버튼 삭제
                    currentDays--; // 현재 DAY 개수 감소
                    updateDayButtons(); // DAY 버튼 번호 재정렬
                });
                btn.appendChild(closeButton);
            }
        });
    }

    // DAY 버튼 추가 기능
    document.getElementById('addDayButton').addEventListener('click', function() {
        dayCount++; // 총 DAY 개수 증가
        currentDays++; // 현재 보이는 DAY 개수 증가

        const newDayContainer = document.createElement('div');
        newDayContainer.classList.add('Day-container');

        const newDayButton = document.createElement('button');
        newDayButton.classList.add('Day-button');
        newDayButton.textContent = `DAY ${currentDays}`;

        newDayContainer.appendChild(newDayButton);
        document.getElementById('dayContainer').insertBefore(newDayContainer, this);
        updateDayButtons();
    });

    // 텍스트 박스 업데이트 함수
    function updateLocationBoxes() {
        const locationInputs = document.querySelectorAll('.add-location-text');

        // 모든 텍스트 박스의 '+' 및 'x' 버튼을 제거
        const locationWrapper = document.getElementById('locationWrapper');
        const existingPlusButton = locationWrapper.querySelector('.add-location-button');
        if (existingPlusButton) existingPlusButton.remove();

        const existingCloseButtons = locationWrapper.querySelectorAll('.close-button');
        existingCloseButtons.forEach(button => button.remove());

        locationInputs.forEach((input, index) => {
            // 입력 박스 클릭 시 placeholder 제거
            input.addEventListener('focus', function() {
                this.placeholder = ''; // placeholder 비우기
            });

            if (index === locationInputs.length - 1) {
                // 마지막 텍스트 박스에 '+' 버튼 추가
                const plusButton = document.createElement('button');
                plusButton.classList.add('add-location-button');
                plusButton.textContent = '+';
                plusButton.addEventListener('click', function() {
                    const newLocationInput = document.createElement('input');
                    newLocationInput.classList.add('add-location-text');
                    newLocationInput.setAttribute('type', 'text');
                    newLocationInput.setAttribute('placeholder', '장소를 입력해주세요.');
                    locationWrapper.appendChild(newLocationInput);
                    updateLocationBoxes(); // 추가 후 박스 업데이트
                });
                input.insertAdjacentElement('afterend', plusButton);
            }

            // 현재 입력 박스가 1개 이상일 때만 'x' 버튼 추가
            if (locationInputs.length > 1 && index === locationInputs.length - 1) {
                const closeButton = document.createElement('button');
                closeButton.classList.add('close-button');
                closeButton.textContent = 'x';
                closeButton.addEventListener('click', function() {
                    input.remove(); // 텍스트 박스 삭제
                    updateLocationBoxes(); // 업데이트
                });
                input.insertAdjacentElement('afterend', closeButton);
            }
        });
    }

    // 텍스트 박스 추가 기능
    document.getElementById('addLocationButton').addEventListener('click', function() {
        const newLocationInput = document.createElement('input');
        newLocationInput.classList.add('add-location-text');
        newLocationInput.setAttribute('type', 'text');
        newLocationInput.setAttribute('placeholder', '장소를 입력해주세요.');
        document.getElementById('locationWrapper').appendChild(newLocationInput);
        updateLocationBoxes(); // 추가 후 박스 업데이트
    });
});