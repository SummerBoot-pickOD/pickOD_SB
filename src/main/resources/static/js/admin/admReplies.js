document.addEventListener('DOMContentLoaded', function () {

//상세보기 누르면 댓글 상세보기 모달 띄우기
  const openModal = document.querySelectorAll('.detail-button');
  const cmtDetailModal = document.querySelector('.nonmodal-container');
  const closeBtn = document.querySelector('.close-btn');

  // 상세보기 모달 내부 버튼
  const toPost = document.querySelector('.to-post');
  const toSanction = document.querySelector('.suspension')
  const deleteBtn = document.querySelector('.delete');

  function fetchCmtPath(cmtId, cmtPostType) {
    let url = '';
    switch (cmtPostType) {
      case '여행 발자국':
        url = `/admin/admReplies/journalCmt/details?cmtId=${cmtId}`;
        break;
      case '템플릿':
        url = `/admin/admReplies/templateCmt/details?cmtId=${cmtId}`;
        break;
      case '장소':
        url = `/admin/admReplies/placeCmt/details?cmtId=${cmtId}`;
        break;
      default:
        console.error('Unknown cmt_post_type:', cmtPostType);
        return null;
    }
    console.log(url);
    return url;
  }
    //상세모달 열기
    openModal.forEach(btn => {
      btn.addEventListener("click", function () {
      // console.log("clicked")
      const cmtId = this.getAttribute('data-cmt-id');
      console.log(cmtId);
      const postType = this.getAttribute('data-post-type')

      fetch(fetchCmtPath(cmtId, postType))
          .then(response => response.json())
          .then(data => {
            console.log(data);
            document.getElementById('post-type').textContent = postType;
            document.getElementById('post-name').textContent = data.postTitle;
            document.getElementById('member-id').textContent = data.memberId;
            // 회원 제재 부여에 멤버 아이디 전달
            toSanction.setAttribute('data-commenter', data.memberNum);
            document.getElementById('member-nickname').textContent = data.memberNickName;
            // 댓글 삭제에 댓글 작성자 닉네임 전달
            deleteBtn.setAttribute('data-commenter-nickname', data.memberNickName);
            document.getElementById('cmt-date').textContent = data.cmtDate;
            document.getElementById('is-reported').textContent = data.isReported;
            document.getElementById('cmt-contents').textContent = data.cmtContents;
          })
          .catch(error => console.error('Error:', error));

        // 바로가기에 post type 전달
        toPost.setAttribute('data-post-type', postType);

        // 댓글 삭제에 comment id 전달
        deleteBtn.setAttribute('data-cmt-id', cmtId);

        cmtDetailModal.style.display = "flex";
    })
  });

// 게시물 바로가기 경로 설정
function toDetailPath(postId, postType) {
  let url = '';
  switch (postType) {
    case '여행 발자국':
      url = `/admin/admJnlMgmt/details?jnlNum=` + postId;
      break;
    case '템플릿':
      url = `/admin/admTemplateMgmt/details?tempId=` + postId;
      break;
    case '장소':
      url = `/admin/admPlaceMgmt/details/` + postId;
      break;
    default:
      console.error('Unknown post type:', postType);
      return null;
  }
  return url;
}
  // 게시물 바로가기 클릭 > 게시물 상세 페이지로 이동
  toPost.addEventListener("click", function(){
    const postType = this.getAttribute('data-post-type');
    const postId = this.getAttribute('data-post-id');

  window.location.href = toDetailPath(postId, postType);

  })

  //댓글 작성자 제재 부여
  toSanction.addEventListener('click', function(){
    const memNum = this.getAttribute('data-commenter');
    window.location.href = '/admin/admMemberMgmt/toSanction?memNum=' + memNum;
  })

  //댓글 삭제
  deleteBtn.addEventListener("click", function(){
    const nickname = this.getAttribute('data-commenter-nickname');
    const cmtId = this.getAttribute('data-cmt-id');
    let result=confirm(` "${nickname}" 님이 작성한 댓글을 삭제하시겠습니까?`);
    const currentUrl = window.location.href;
    if(result){
      fetch( `/admin/admReplies/deleteCmt/${cmtId}`, {
        method: 'POST'
      })
          .then(response => {
            if(response.ok){
              alert(`${nickname}님의 댓글이 삭제되었습니다.`);
              window.location.href = currentUrl;
            } else {
              alert(`댓글 삭제에 실패하였습니다.`);
            }
          }).catch(error => {
        console.error("에러: ", error)
      });
    }
  })

// x 누르면 모달 닫기
  closeBtn.addEventListener("click", function () {
    console.log("모달 닫기 버튼 ");
    cmtDetailModal.style.display = "none";
  });
});
