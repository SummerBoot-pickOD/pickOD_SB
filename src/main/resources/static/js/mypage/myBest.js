$(function () {
$("#header").load("../main/header.html");
});

$(function () {
$("#footer").load("../main/footer.html");
});

document.addEventListener('DOMContentLoaded', () => {
    myBestTrip(myTripList);
});

function myBestTrip(data){
    // 상위 컨테이너 선택
    const container = document.querySelector(".mybest-container");

// 데이터에 따라 요소 반복 생성
    data.forEach(item => {
        // mybest-list 요소 생성
        const myBestList = document.createElement("div");
        myBestList.classList.add("mybest-list");

        // 이미지 박스 요소 생성
        const imgBox = document.createElement("div");
        imgBox.classList.add("mybest-imgbox");
        const img = document.createElement("img");
        // img.src = item.imgSrc;
        img.src = '../../img/mypage/더미사진.png';
        img.alt = "썸네일이미지";
        img.classList.add("mybest-img");
        imgBox.appendChild(img);

        // 콘텐츠 박스 요소 생성
        const contentBox = document.createElement("div");
        contentBox.classList.add("mybest-box");
        const content = document.createElement("div");
        content.classList.add("mybest-content");
        content.textContent = '댓글: ' + item.numComments + '개';
        contentBox.appendChild(content);

        // 이름 요소 생성
        const name = document.createElement("div");
        name.classList.add("mybest-name");
        name.textContent = item.jnlTitle;

        // mybest-list에 이미지, 콘텐츠, 이름 요소 추가
        myBestList.appendChild(imgBox);
        myBestList.appendChild(contentBox);
        myBestList.appendChild(name);

        // 상위 컨테이너에 mybest-list 추가
        container.appendChild(myBestList);
    });
}