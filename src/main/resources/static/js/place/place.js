document.addEventListener('DOMContentLoaded', () => {
  const total_btn=document.querySelector('.total_area_btn');
  const area_btn = document.querySelectorAll('.tag_area li button');
  const area_btn_seoul = document.querySelectorAll('.tag_area_seoul button');
  const area_btn_geonggi = document.querySelectorAll('.tag_area_geonggi button');
  const area_btn_totalseoul=document.querySelector('.tag_area_totalseoul');
  const area_btn_totalgeonggi=document.querySelector('.tag_area_totalgeonggi');
  
  const saveimg_btn=document.querySelectorAll('.saveimg img');

  const listItems = document.querySelectorAll('.place-list li');

 

//검색 필터
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
        button.style.backgroundColor='';
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
          button.style.border='1px  solid #FEF2F2';
        });
        if(area_btn_totalgeonggi.style.fontWeight==='bold'){
      area_btn_totalgeonggi.style.fontWeight='';
    }else{
      area_btn_totalgeonggi.style.fontWeight='bold';
    }
  });
  
//찜하기 버튼
  saveimg_btn.forEach(button=>{
    button.addEventListener('click',()=>{
      if(button.getAttribute('src')==="../../img/main/unsaved.png"){
       button.setAttribute('src',"../../img/main/saved.png");
      }else{
        button.setAttribute('src',"../../img/main/unsaved.png");
      } 
    })
  });

//총 장소 개수
  const itemCount= listItems.length;
  document.getElementById('itemCount').textContent = '총 ' + itemCount+'건';
  document.getElementById('itemCount').fontWeight='bold';



});

