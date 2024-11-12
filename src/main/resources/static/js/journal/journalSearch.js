document.addEventListener('DOMContentLoaded', () => {
    const total_btn = document.querySelector('.total_area_btn');
    const area_btn = document.querySelectorAll('.tag_area li button');
    const area_btn_seoul = document.querySelectorAll('.tag_area_seoul button');
    const area_btn_geonggi = document.querySelectorAll('.tag_area_geonggi button');
    const area_btn_totalseoul = document.querySelector('.tag_area_totalseoul');
    const area_btn_totalgeonggi = document.querySelector('.tag_area_totalgeonggi');

    total_btn.addEventListener('click', () => {
        area_btn.forEach(button => {
            button.style.backgroundColor = '';
            button.style.border = '1px solid #FEF2F2';
        });
        area_btn_totalseoul.style.fontWeight = '';
        area_btn_totalgeonggi.style.fontWeight = '';
    });

    area_btn.forEach(button => {
        button.addEventListener('click', () => {
            const currentColor = button.style.backgroundColor.toLowerCase();
            if (currentColor === 'rgb(251, 181, 181)' || currentColor === '#fbb5b5') {
                button.style.backgroundColor = '#fef2f2';
            } else {
                button.style.backgroundColor = '#FBB5B5';
                area_btn_totalseoul.style.fontWeight = '';
            }
        });
    });

    area_btn_totalseoul.addEventListener('click', () => {
        area_btn_seoul.forEach(button => {
            button.addEventListener('click', () => {
                area_btn_totalseoul.style.fontWeight = '';
            });
            button.style.backgroundColor = '';
            button.style.border = '1px solid #FEF2F2';
        });
        area_btn_totalseoul.style.fontWeight = (area_btn_totalseoul.style.fontWeight === 'bold') ? '' : 'bold';
    });

    area_btn_totalgeonggi.addEventListener('click', () => {
        area_btn_geonggi.forEach(button => {
            button.addEventListener('click', () => {
                area_btn_totalgeonggi.style.fontWeight = '';
            });
            button.style.backgroundColor = '';
            button.style.border = '1px solid #FEF2F2';
        });
        area_btn_totalgeonggi.style.fontWeight = (area_btn_totalgeonggi.style.fontWeight === 'bold') ? '' : 'bold';
    });

    // 게시물 및 페이지네이션 처리
    const posts = journalList;
    const postsPerPage = 12;
    let currentPage = 1;
    let totalPages;

    function displayPosts() {
        const postContainer = document.getElementById('posts');
        postContainer.innerHTML = '';

        const startIndex = (currentPage - 1) * postsPerPage;
        const endIndex = startIndex + postsPerPage;
        const currentPosts = journalList.slice(startIndex, endIndex);

        currentPosts.forEach(post => {
            const postDiv = document.createElement('div');
            postDiv.className = 'post-container';

            const img = document.createElement('img');
            img.className = 'post-image';
            img.src = post.uploadPath + '/' + post.fileName;
            img.alt = post.jnlTitle;
            postDiv.appendChild(img);

            const titleDiv = document.createElement('div');
            titleDiv.className = 'post-title';
            titleDiv.textContent = post.jnlTitle;
            postDiv.appendChild(titleDiv);

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

    $("#header").load("../main/header.html");
    $("#footer").load("../main/footer.html");

    $(".area-btn").click(function() {
        const selectedArea = $(this).data("area");

        $.ajax({
            url: "/journal/list/area",
            type: "GET",
            data: { area: selectedArea },
            success: function(response) {
                $("#journalResults").html(response);
            },
            error: function() {
                alert("검색 중 오류가 발생했습니다.");
            }
        });
    });

    $(".total_area_btn").click(function() {
        $.ajax({
            url: "/journal/list/area",
            type: "GET",
            data: { area: "" },
            success: function(response) {
                $("#journalResults").html(response);
            },
            error: function() {
                alert("검색 중 오류가 발생했습니다.");
            }
        });
    });
});
