/* 헤더푸터 */
$(function () {
  $("#header").load("../main/header.html");
  });

  $(function () {
  $("#footer").load("../main/footer.html");
  });


  /* 로컬에서 프로필사진넣기 */

let uploadLocal = document.querySelector('.upload-local');

uploadLocal.addEventListener('click', function() {
  uploadLocal.style.fontWeight='bold';
  uploadLocal.style.color='black';
  uploadBasic.style.fontWeight='normal';
  uploadBasic.style.color='#777777';
  deleteImg.style.fontWeight = 'normal';
  deleteImg.style.color='#777777';

  document.getElementById('fileInput').click();
});

document.getElementById('fileInput').addEventListener('change', function(event) {
  const file = event.target.files[0];
  if (file) {
      const reader = new FileReader();
      reader.onload = function(e) {
          const img = document.getElementById('preview');
          img.src = e.target.result;
          img.style.display = 'block';
      };
      reader.readAsDataURL(file);
  }
});

// 현재사진 삭제하기
let deleteImg = document.querySelector('.delete-img');

deleteImg.addEventListener('click', function(){
  uploadLocal.style.fontWeight = 'normal';
  uploadLocal.style.color='#777777';
  uploadBasic.style.fontWeight='normal';
  uploadBasic.style.color='#777777';
  deleteImg.style.fontWeight='bold';
  deleteImg.style.color='black';

  let imgDefault = document.getElementById('preview');
  imgDefault.src = '../../img/mypage/기본사람사진.png';
});


//기본제공사진 불러오기
let uploadBasic = document.querySelector('.upload-basic');
uploadBasic.addEventListener('click', function(){
  uploadLocal.style.fontWeight = 'normal';
  uploadLocal.style.color = '#777777';
  deleteImg.style.fontWeight = 'normal';
  deleteImg.style.color = '#777777';
  uploadBasic.style.fontWeight = 'bold';
  uploadBasic.style.color = 'black';

  let basicImgContainerbox = document.querySelector('.basic-img-containerbox');
  basicImgContainerbox.style.display = 'block';


  let basicImgBoxImg = document.querySelectorAll('.basic-img-box img');

  basicImgBoxImg.forEach(function(img, index) {
    img.addEventListener('click', function() {
      const selectedImage = document.getElementById('preview');
      //제공한사진을 올렸을때 삭제하기를 누르면 삭제하기에 볼드처리가됨.
      // 다시한번 제공한 사진을 누르면 볼드처리가 없어지게함
      uploadLocal.style.fontWeight = 'normal';
      uploadLocal.style.color = '#777777';
      deleteImg.style.fontWeight = 'normal';
      deleteImg.style.color = '#777777';
      uploadBasic.style.fontWeight = 'bold';
      uploadBasic.style.color = 'black';

      // 선택한 이미지를 미리보기로 설정
      selectedImage.src = img.src;
      selectedImage.style.display = 'block';

        //첫번째위치한 그림과 클릭한 그림의 순서바꾸기
      let firstImage = document.querySelector('.basic-img-box > div:nth-child(1) img');
      if (img !== firstImage) {
        const tempSrc = firstImage.src;
        firstImage.src = img.src;
        img.src = tempSrc;
      }
    });
  });
});
