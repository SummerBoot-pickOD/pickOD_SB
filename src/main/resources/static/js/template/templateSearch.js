// 게시물 및 페이지네이션 처리
const posts = templateList;
const postsPerPage = 12;
let currentPage = 1;
let totalPages;

function displayPosts() {
    console.log("templateSearch.js 확인 =========");
    const postContainer = document.getElementById('posts');
    postContainer.innerHTML = '';
    console.log("templateSearch.js 확인 =========");

    const startIndex = (currentPage - 1) * postsPerPage;
    const endIndex = startIndex + postsPerPage;
    const currentPosts = templateList.slice(startIndex, endIndex);

    currentPosts.forEach(post => {
        const postDiv = document.createElement('div');
        postDiv.className = 'post-container';


        const img = document.createElement('img');
        img.className = 'post-image';
        img.src = (post.uploadpath && post.fileName) ? `${post.uploadpath}/${post.fileName}` : '/img/journal/하단 큰 이미지.png';
        img.alt = post.tempTitle;
        postDiv.appendChild(img);

        const titleDiv = document.createElement('div');
        titleDiv.className = 'post-title';
        titleDiv.textContent = post.tempTitle;
        postDiv.appendChild(titleDiv);

        // 클릭 시 해당 여행일지 상세 페이지로 이동
        postDiv.addEventListener('click', function() {
            // post.jnlNum 값을 URL에 포함시켜 상세 페이지로 이동
            window.location.href = `/template/detail/${post.tempId}`;  // post.jnlNum이 정확히 전달되는지 확인
        });

        const pngImage = document.createElement('img');
        pngImage.className = 'toggle-image';
        pngImage.src = '../../img/main/unsaved.png';
        pngImage.alt = '';
        pngImage.style.position = 'absolute';
        pngImage.style.bottom = '10px';
        pngImage.style.right = '10px';
        pngImage.style.width = '35px';
        pngImage.style.cursor = 'pointer';

        pngImage.addEventListener('click', function() {
            pngImage.src = pngImage.src.includes('img/main/saved.png') ? '../../img/main/unsaved.png' : '../../img/main/saved.png';
        });

        postDiv.style.position = 'relative';
        postDiv.appendChild(pngImage);
        postContainer.appendChild(postDiv);
    });
}

const totalPostsElement = document.getElementById('total-posts');
totalPostsElement.textContent = posts.length;

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
            console.log("TemplateList:", templateList);
        });
        return button;
    };

    if (currentPage > 1) {
        const prevButton = createButton(Math.max(currentPage - 5, 1), '<');
        paginationContainer.appendChild(prevButton);
    }

    const startPage = Math.max(1, currentPage - 4);
    const endPage = Math.min(totalPages, currentPage + 4);

    for (let i = startPage; i <= endPage; i++) {
        const button = createButton(i, i);
        paginationContainer.appendChild(button);
    }

    if (currentPage < totalPages) {
        const nextButton = createButton(Math.min(currentPage + 5, totalPages), '>');
        paginationContainer.appendChild(nextButton);
    }
}

displayPosts();
setupPagination();

    $(function () {
        $("#header").load("../main/header.html");
        });
    
        $(function () {
        $("#footer").load("../main/footer.html");
        });





