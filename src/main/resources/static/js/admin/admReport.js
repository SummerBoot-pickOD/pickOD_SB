// $(function () {
//   $("#header").load("../../html/admin/admHeader.html");
// });
//
// $(function () {
//   $("#footer").load("../../html/main/footer.html");
// });

function decodeType(code){
  switch (code){
    case 'T' : return "템플릿";
    case 'J' : return "여행일지";
    case 'C' : return "댓글";
    case 'M' : return "쪽지";
    case 'P' : return "장소";
    default : return "불명";
  }
}

function hideModal(){
  if ($("#report-detail-msg").css('display') == 'block'){
    $("#report-detail-msg").css('display','none');
  }
  $("#report-detail-modal").css('display','none');
  $(".modal-container").css('display','none');
}

let current_list=undefined;

//검색하기
$(".searchList").click(function(){
  Condition = $(this).siblings('#condition-keyword').val();
  Keyword = $(this).siblings('input[type="text"]').val();
  PostType = $(this).siblings('#condition-post-type').val();
  Solved = Number($('input[type="radio"][name="solved"]:checked').val());

  if (Condition == null || PostType == null || Keyword == ''){
    alert("검색 조건이 비어있습니다. 다시 입력해주세요.");
    return;
  }
  data = {
    'inqCondition' : Condition,
    'inqKeyword' : Keyword,
    'inqPostType' : PostType,
    'inqSolved' : Solved
  };

  console.log(data);

  fetch('/admin/admReport/inqTable', {
    method : 'POST',
    headers : {'Content-Type':'application/json'},
    body : JSON.stringify(data)
  }).then(res => res.json())
      .then(data => {
        console.log(data);

        //페이지 세팅
        let html = `<tr class="table-head">
          <th>글 종류</th>
          <th>글 ID</th>
          <th>신고 사유</th>
          <th>일시</th>
          <th>글 작성자</th>
          <th>신고자</th>
          <th>상세 내용</th>
          <th>신고 처리</th>
          <th>상세보기</th>
          <th hidden>reportId</th>
        </tr>` ;

        for(let i =0; i<data.length; i++){
          let li = data[i];
          let type = decodeType(li.reportPostType);
          html += `
          <tr>
          <td>${type}</td>
          <td>${li.reportPostId}</td>
          <td>${li.reportType}</td>
          <td>${li.reportDate}</td>
          <td>${li.writerId}</td>
          <td>${li.reporterId}</td>
          <td>${li.reportDetail}</td>
          <td>${li.reportSolved}</td>
          <td class="last-col"><button type="button">상세보기</button></td>
          <td hidden>${li.reportId}</td>
        </tr>
          `;
        }
        $('table#report-list').html(html)
        current_list = data;
      })
      .catch(e => {
        console.log(e);
        alert("반환 에러");
      });

})

$('#reports').on('click', 'td.last-col', function(){
  row = $(this).closest('tr').find('td');
  ptype = row.eq(0).text();
  pid = row.eq(1).text();
  rtype = row.eq(2).text()
  rdate = row.eq(3).text()
  writer = row.eq(4).text();
  reporter = row.eq(5).text();
  content = row.eq(6).text();
  rId = row.eq(9).text();

  console.log(ptype + writer + reporter + rtype + rId);
  //데이터 받아와서 내용 넣고


  $('#report-detail-modal td.post-type').text(ptype);
  $('#report-detail-modal #modal-postType').val(ptype);
  $('#report-detail-modal td.post-writer-id').text(writer);
  $('#report-detail-modal td.report-id').text(reporter);
  $('#report-detail-modal td.report-type').text(rtype);
  $('#report-detail-modal td.report-date').text(rdate);
  $('#report-detail-modal textarea#report-detail-text').text(content);
  $('#report-detail-modal #modal-reportId').val(rId);
  $('#report-detail-modal #modal-postId').val(pid);

  if ($(".modal-container").css('display') == 'flex') {
    hideModal();
  }
  setTimeout(() => {
    $(".modal-container").css('display','flex');
    $("#report-detail-modal").css('display','block');
  }, 200);
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
  ptype = $('#report-detail-modal #modal-postType').val();
  pId = $('#report-detail-modal #modal-postId').val();
  if(ptype === '쪽지'){
    console.log('모달에 값 넣어오고 보이기');
    fetch(`/admin/admReport/getMessage?postId=${pId}`)
        .then(res => res.text())
        .then(data => {
          $('textarea#report-msg-text').text(data);
        }).catch(e=>{
      console.log(e);
      alert("에러 발생.")
    })
    //쪽지라면 모달 추가
    $("#report-detail-msg").css('display','block');

  }else{
    console.log('댓글만 원본 작성물 찾고, 나머진 그 아이디 그대로 리다이렉트');
    $(this).closest('form').submit();
  }



})

$("#solved").click(function(){
  if(confirm("신고를 처리하시겠습니까?") == false){
    return;
  }
  reportId = $('#report-detail-modal #modal-reportId').val();
  fetch(`/admin/admReport/solve/${reportId}`,{
    method : 'POST'
  })
      .then(res => res.json())
      .then(data => {
        if(data.success){
          alert("신고 처리 완료.");
          hideModal();
        }else{
          alert("신고 처리 실패했습니다.")
        }
      }).catch(e=>{
        console.log(e);
        alert("서버 처리 실패.")
  })
});