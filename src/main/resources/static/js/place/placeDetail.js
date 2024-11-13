$(document).ready(function () {
  let placeId = $('#placeId').val(); // 현재 페이지의 placeId
  $.ajax({
    url: '/place/status',
    type: 'GET',
    data: { placeId: placeId },
    success: function (response) {
      if (response==="yes") {
        $('.saveimg').attr('src', "../../img/main/saved.png");
      } else {
        $('.saveimg').attr('src', "../../img/main/unsaved.png");
      }
    },
    error: function () {
      console.error("찜 상태를 확인하는 중 오류 발생");
    }
  });
});

document.addEventListener('DOMContentLoaded',()=>{
  const slider = document.getElementById('slider');
  const prevBtn = document.getElementById('prevBtn');
  const nextBtn = document.getElementById('nextBtn');

  // const saveimg_btn=document.querySelector('.saveimg');
  
  
  const images = [
    "../../img/place/placeDetail/samplePlace.jpg",
    "../../img/place/placeDetail/samplePlace2.jpg",
    "../../img/place/placeDetail/samplePlace3.jpg"
  ];

  let currentIndex = 0;

  nextBtn.addEventListener('click', () => {
    currentIndex = (currentIndex + 1) % images.length;
    slider.setAttribute('src', images[currentIndex]);
  });

  prevBtn.addEventListener('click', () => {
    currentIndex =(currentIndex - 1+images.length) % images.length;
    slider.setAttribute('src', images[currentIndex]);
  });


  // 찜하기
  $('.saveimg').click(function() {
    let clickedElement = $(this);
    let placeId = clickedElement.data('place-id');  // 클릭한 버튼의 placeId 가져오기



    $.ajax({
      url: '/place/toggle',  // 서버로 요청 보낼 URL
      type: 'POST',
      data: {
        placeId: placeId

      },
      success: function(response) {
        if(response==="찜이 추가되었습니다"){
          clickedElement.attr('src', "../../img/main/saved.png");
        }else if(response==="찜이 삭제되었습니다"){
          clickedElement.attr('src', "../../img/main/unsaved.png");
        }
        alert(response);

      },
      error: function() {
        alert('에러가 발생했습니다.');
      }
    });
  });








 

 //nav 버튼
  document.querySelectorAll('.placeDetail-nav a').forEach(anchor => {
    anchor.addEventListener('click', function(e) {
      e.preventDefault(); 
  
      const targetId = this.getAttribute('href'); 
      const targetElement = document.querySelector(targetId).offsetTop; 

      window.scrollTo({
        top:targetElement-111,
        behavior: 'smooth' // 부드러운 스크롤
      });
    });
  });
 

// 신고
  $(".reportimg").click(function(){
    const postType ='P';
    const postId = 1;
    const writerId = 3;
    //
    // // Set the hidden input values
    $('#reportForm input[name="reportPostType"]').val(postType);
    $('#reportForm input[name="reportPostId"]').val(postId);
    $('#reportForm input[name="writerId"]').val(writerId);

    $(".modal-container").css("display","block");
  });


  // 발자국 톡 리뷰
  
    //작성자가 본인이 아니라면 휴지통 아이콘 지우는 버튼 없애야하고
  //$(".reply-buttons>img[alt='삭제']").css('display','none');


  //등록버튼 누르면 아래에 댓글 추가
  $("#submit-reply").click(function(){
    text = $("#reply-write>input").val();
    if (text.length == 0){
      alert("댓글을 작성하고 등록 버튼을 눌러주세요.");
      return;
    }
    //작성자의 정보를 끌고 온 다음 적절히 넣어야겠지
    newReply = `<li class = "written">
          <div class="reply-image"><img src="../../img/place/sample_place.png" alt="프사"></div>
          <div class="reply-content">
            <div class = "content">`+text+`</div>
            <div class = "user"><span id = "nick">김카디</span>&nbsp;&nbsp;<span id = "date">24.10.16</span></div>
            <div class = "likes"><img src="../../img/journal/footprint.png" alt="좋아요"><span>35</span></div>
          </div>
          <div class="reply-buttons">
            <img src="../../img/message/쪽지함휴지통.png" alt="삭제">
            <img src="../../img/message/report.png" alt="신고">
          </div>
        </li>`

    $("#reply").prepend(newReply);
    //맨 앞에 넣게 할거면 append 대신 prepend도 가능
  })


  // 좋아요 누르면 등록 + 숫자 하나 올라감
  picked = "../../img/journal/footprint_pick.png"
  Npicked = "../../img/journal/footprint.png"
  $(".likes img[alt='좋아요']").click(function(){
    //좋아요가 여러개이기 때문에, $(this)로 클릭당한 자신을 기준으로 해야 됨
    now = $(this).attr('src');
    cnt = Number($(this).siblings('span').text())
    if (now == picked){
      $(this).attr('src',Npicked);
      $(this).siblings('span').text(String(cnt-1));
    }else{
      $(this).attr('src',picked);
      $(this).siblings('span').text(String(cnt+1));
    }
    return;
  })

  // 더보기 누르면 스크롤 형태로 다 보여줌
  $("#reply-more").click(function(){
    $("#reply").css('height','600px');
    $("#reply").css('overflow-y','scroll');
    $("#reply-more").css('display','none');
  })

    //삭제 누르면 삭제되고
  $(".reply-buttons>img[alt='삭제']").click(function(){
    if(confirm("정말 삭제하시겠습니까?") == false){
      return;
    }
      //db처리
    $(this).closest(".written").remove();
    alert("삭제가 완료되었습니다.")
  })
  //근데 추가한 댓글을 바로 삭제하는 법을 모르겠네;;    



});


