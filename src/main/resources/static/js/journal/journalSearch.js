document.addEventListener('DOMContentLoaded', () => {
  const total_btn=document.querySelector('.total_area_btn');
  const area_btn = document.querySelectorAll('.tag_area li button');
  const area_btn_seoul = document.querySelectorAll('.tag_area_seoul button');
  const area_btn_geonggi = document.querySelectorAll('.tag_area_geonggi button');
  const area_btn_totalseoul=document.querySelector('.tag_area_totalseoul');
  const area_btn_totalgeonggi=document.querySelector('.tag_area_totalgeonggi');
  
  total_btn.addEventListener('click',()=>{
      area_btn.forEach(button=>{
        button.style.backgroundColor='';
        button.style.border='1px solid #FEF2F2';
      })
      area_btn_totalseoul.style.fontWeight='' 
      area_btn_totalgeonggi.style.fontWeight='' 

  });

  area_btn.forEach(button => {
    button.addEventListener('click', () => {
      const currentColor = button.style.backgroundColor.toLowerCase();
      if(currentColor === 'rgb(251, 181, 181)' || currentColor === '#fbb5b5'){
        button.style.backgroundColor='#fef2f2';

      }else{
        button.style.backgroundColor = '#FBB5B5';
        area_btn_totalseoul.style.fontWeight='' 
      
      }
    })  
   });

  area_btn_totalseoul.addEventListener('click',()=>{
    area_btn_seoul.forEach(button=>{
      button.addEventListener('click',()=>{
        area_btn_totalseoul.style.fontWeight=''; 
      })
      button.style.backgroundColor='';
      button.style.border='1px  solid #FEF2F2';
    });
    if(area_btn_totalseoul.style.fontWeight==='bold'){
      area_btn_totalseoul.style.fontWeight='';
    }else{
      area_btn_totalseoul.style.fontWeight='bold';
    }
    

  });
  

  area_btn_totalgeonggi.addEventListener('click',()=>{
    area_btn_geonggi.forEach(button=>{
      button.addEventListener('click',()=>{
        area_btn_totalgeonggi.style.fontWeight=''; 
      })
          button.style.backgroundColor='';
          button.style.border='1px solid #FEF2F2';
    });
    if(area_btn_totalgeonggi.style.fontWeight==='bold'){
      area_btn_totalgeonggi.style.fontWeight='';
    }else{
      area_btn_totalgeonggi.style.fontWeight='bold';
    }
  });

    // 게시물 및 페이지네이션 처리
    const posts = Array.from({ length: 50 }, (_, i) => `게시물 ${i + 1}`); // 예시 게시물 생성
    const postsPerPage = 12;
    let currentPage = 1;
    let totalPages;

    function displayPosts() {
        const postContainer = document.getElementById('posts');
        postContainer.innerHTML = '';

        const startIndex = (currentPage - 1) * postsPerPage;
        const endIndex = startIndex + postsPerPage;
        const currentPosts = posts.slice(startIndex, endIndex);

        currentPosts.forEach(post => {
            const postDiv = document.createElement('div');
            postDiv.className = 'post-container';

            // 이미지 추가
            const img = document.createElement('img');
            img.className = 'post-image';
            img.src = 'https://via.placeholder.com/300'; // 예시 이미지
            img.alt = post;

            postDiv.appendChild(img);

            // 제목을 post-container 내부에 추가
            const titleDiv = document.createElement('div');
            titleDiv.className = 'post-title'; // 클래스 추가
            titleDiv.textContent = post;

            postDiv.appendChild(titleDiv); // 제목을 이미지 하단에 추가

            // 오른쪽 하단에 png 파일을 추가
            const pngImage = document.createElement('img');
            pngImage.className = 'toggle-image';
            pngImage.src = '../../img/main/unsaved.png'; // 처음에 표시할 PNG 파일
            pngImage.alt = '';
            pngImage.style.position = 'absolute';
            pngImage.style.bottom = '10px';
            pngImage.style.right = '10px';
            pngImage.style.width = '35px'; // 크기 조정
            pngImage.style.cursor = 'pointer';

            // 클릭 시 다른 이미지로 변경
            pngImage.addEventListener('click', function() {
                console.log(pngImage.src)
                console.log(pngImage.src.includes('img/main/saved.png'))
                if (pngImage.src.includes('img/main/saved.png')) {
                    pngImage.src = '../../img/main/unsaved.png'; // 다시 클릭하면 원래 이미지로 복귀
                } else {
                    pngImage.src = '../../img/main/saved.png'; // 클릭 시 변경할 PNG 파일
                }
            });

        


            postDiv.style.position = 'relative'; // 부모 요소를 relative로 설정
            postDiv.appendChild(pngImage); // post-container에 이미지 추가

            postContainer.appendChild(postDiv); // 게시물 컨테이너에 추가
        });

        // 총 게시물 수 표시
        const totalPostsElement = document.getElementById('total-posts');
        totalPostsElement.textContent = posts.length; // 총 게시물 수를 표시
    }

    function setupPagination() {
        const paginationContainer = document.getElementById('pagination');
        paginationContainer.innerHTML = '';

        totalPages = Math.ceil(posts.length / postsPerPage);

        const createButton = (pageNum, text) => {
            const button = document.createElement('button');
            button.textContent = text;
            button.disabled = (currentPage === pageNum);
            button.addEventListener('click', () => {
                currentPage = pageNum;
                displayPosts();
                setupPagination();
            });
            return button;
        };

        if (currentPage > 1) {
            const prevButton = createButton(Math.max(currentPage - 5, 1), '<'); // 1 이하로 내려가지 않도록 수정
            paginationContainer.appendChild(prevButton);
        }

        const startPage = Math.max(1, currentPage - 4);
        const endPage = Math.min(totalPages, currentPage + 4);

        for (let i = startPage; i <= endPage; i++) {
            const button = createButton(i, i);
            paginationContainer.appendChild(button);
        }

        if (currentPage < totalPages) {
            const nextButton = createButton(Math.min(currentPage + 5, totalPages), '>'); // 총 페이지 수 초과 방지
            paginationContainer.appendChild(nextButton);
        }
    }

    // 초기 표시
    displayPosts();
    setupPagination();

    $(function () {
        $("#header").load("../main/header.html");
        });
    
        $(function () {
        $("#footer").load("../main/footer.html");
        });
});

document.addEventListener('DOMContentLoaded', function() {
  // 기본적으로 '최신순' 버튼에 bold 적용
  const latestButton = document.querySelector('.content-latest');
  latestButton.classList.add('bold');

  // 모든 버튼에 대해 클릭 이벤트 리스너 추가
  const buttons = document.querySelectorAll('button');
  buttons.forEach(button => {
      button.addEventListener('click', function() {
          // 모든 버튼에서 bold 클래스 제거
          buttons.forEach(btn => btn.classList.remove('bold'));
          // 클릭된 버튼에 bold 클래스 추가
          this.classList.add('bold');
      });
  });
});

//지역버튼 클릭시 필터링
$(document).ready(function() {
    // 버튼 클릭 시
    $(".area-btn").click(function() {
        var selectedArea = $(this).data("area"); // 클릭한 버튼의 data-area 값 가져오기

        // AJAX 요청 보내기
        $.ajax({
            url: "/journal/search",  // 해당 검색 URL
            type: "GET",
            data: {
                searchKeyword: selectedArea // 검색어로 지역명 전달
            },
            success: function(response) {
                // 결과를 받아서 화면 갱신
                $("#journalResults").html(response); // #journalResults에 결과 출력
            },
            error: function() {
                alert("검색 중 오류가 발생했습니다.");
            }
        });
    });
});