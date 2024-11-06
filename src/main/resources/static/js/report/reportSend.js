$("#report-modal-tail>button").click(function(){
  if($("#reason").val() == null){
    //disabled와 selected가 같이 있는 상태면,
    //선택할 수 있는 옵션이 없다고 생각해 그 칸은 value가 null로 고정
    alert("신고 분류를 선택해주세요.");
    return;
  }
  $(this).closest('form').submit();
  alert("신고가 정상적으로 등록되었습니다.");
  $(".modal-container").css('display','none');
});

$("#modal-exit").click(function(){
  $(".modal-container").css('display','none');
});