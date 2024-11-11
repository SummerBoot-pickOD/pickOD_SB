// $(function () {
//   $("#header").load("../../html/admin/admHeader.html");
// });
//
// $(function () {
//   $("#footer").load("../../html/main/footer.html");
// });

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

let sanc_id = 0
$("#search-sanction").click(function(){
  //해당 이메일 or 닉네임을 가진 사람의 역대 제재 횟수를 가져옴 (입력값을 정확히 작성해야 함)

  data = {
    inqCondition : $("#sanc-srch-condition").val(),
    inqKeyword : $("#sanc-srch-input").val()
  }
  sanc_id = $("#sanc-srch-input").val()

  fetch('/admin/admReport/sncCnt',{
    method : 'POST',
    headers : {'Content-Type':'application/json'},
    body : JSON.stringify(data)
  }).then(res => res.json())
      .then(data =>{
        if(data.success){
          $("#s-count").text(data.count);
        }else{
          alert("해당 회원의 역대 제제 횟수를 가져오지 못했습니다.");
        }
      }).catch(e => {
    console.log(e);
    alert('서버와의 연결에 문제가 발생했습니다.'+e);
    });
  });



$("#update-sanction").click(function(){
  if(confirm("정말 제재를 부과하겠습니까?") == false){
    return;
  }
  //제재 부과
  if($("#s-count").text() == ''){
    alert("해당 사용자의 제재 횟수를 먼저 검색해 주십시오.");
    return;
  }
  if($("#s-count").text() == 'X'){
    alert("제대로 된 이메일/닉네임이 아닙니다. 다시 검색해 주십시오.");
    return;
  }
  if(sanc_id != $("#sanc-srch-input").val()){
    alert("제재 횟수를 검색한 계정과 제재를 부과할 계정이 다릅니다.");
    return;
  }

  data = {
    inqCondition : $("#sanc-srch-condition").val(),
    inqKeyword : $("#sanc-srch-input").val(),
    sanctionCnt : Number($("#s-count").text())+1,
    sanctionReason : $("#sanction-reason").val(),
    sanctionNote: $("#sanc-note").val(),
  }

  fetch('/admin/admReport/impSnc',{
    method : 'POST',
    headers : {'Content-Type':'application/json'},
    body : JSON.stringify(data)
  }).then(res => res.json())
      .then(data =>{
        if(data.success){
          alert("제재가 적용되었습니다.");
          $("#s-count").text('');
          $("#sanc-srch-input").val('');
        }else{
          alert("제재 적용에 실패했습니다.");
        }
      }).catch(e => {
    console.log(e);
    alert('서버와의 연결에 문제가 발생했습니다.');
  });
})

$("#show-write").click(function(){
  //장소,여행일지,템플릿 등의 게시물이라면, 새 탭에서 작성글 링크로 감
  //window.open("게시물 주소");
  
  //쪽지라면 모달 추가
  $("#report-detail-msg").css('display','block');
})