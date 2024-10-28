$(function () {
  $("#header").load("../../html/admin/admHeader.html");
});

$(function () {
  $("#footer").load("../../html/main/footer.html");
});

$(".last-col").click(function(){
  if($(".modal-container").css('display') == 'flex'){
    if($("#report-detail-msg").css('display') == 'block'){
      $("#report-detail-msg").css('display','none');
    }
    $("#report-detail-modal").css('display','none');
    $(".modal-container").css('display','none');
  }

  //데이터 받아와서 내용 넣고
  setTimeout(() => {
    $(".modal-container").css('display','flex');
    $("#report-detail-modal").css('display','block');
  }, 200);
  return;
});

$(".modal-container #modal-exit").click(function(){
  if($("#report-detail-msg").css('display') == 'block'){
    $("#report-detail-msg").css('display','none');
  }
  $(".modal-container").css('display','none');
});


$("#search-sanction").click(function(){
  //해당 이메일 or 닉네임을 가진 사람의 역대 제재 횟수를 가져옴 (입력값을 정확히 작성해야 함)
  $("#s-count").text("3");
})

$("#update-sanction").click(function(){
  if(confirm("정말 제재를 부과하겠습니까?") == false){
    return;
  }
  //제재 부과
  if($("#s-count").text() == ''){
    alert("해당 사용자의 제재 횟수를 먼저 검색해 주십시오");
    return;
  }
  //db에 연결해서 제재 기록 추가
  alert("제재가 적용되었습니다.");
    $("#s-count").text('')
  return;
})

$("#show-write").click(function(){
  //장소,여행일지,템플릿 등의 게시물이라면, 새 탭에서 작성글 링크로 감
  //window.open("게시물 주소");
  
  //쪽지라면 모달 추가
  $("#report-detail-msg").css('display','block');
})