package com.smbt.pickod.dto.festival;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Page {
    private int pageCount;    // 한 세트에 보여줄 페이지 수
    private int startPage;    // 페이지 세트 시작 번호
    private int endPage;      // 페이지 세트 끝 번호
    private int realEnd;      // 실제 마지막 페이지 번호
    private boolean prev;     // 이전 버튼 표시 여부
    private boolean next;     // 다음 버튼 표시 여부
    private int total;        // 전체 데이터 개수
    private Criteria criteria; // Criteria 객체 (페이지 정보)
    private List<FestivalDTO> festivals; // 페이징된 데이터

    public Page(Criteria criteria, int total) {
        this(criteria, total, 5);
    }
    public Page(Criteria criteria, int total, int pageCount) {
        this.criteria = criteria;
        this.total = total;
        this.pageCount = pageCount;

        // 실제 마지막 페이지 계산
        this.realEnd = (int) Math.ceil((double) total / criteria.getAmount()); //Math.ceil 소수점있으면 올림

        // 세트의 끝 페이지 번호 계산
        this.endPage = (int) Math.ceil((double) criteria.getPage() / pageCount) * pageCount;

        // 세트의 시작 페이지 번호 계산
        this.startPage = this.endPage - pageCount + 1;

        //실제가 더 크면 end를 real 조정
        if (this.realEnd < this.endPage) {
            this.endPage = this.realEnd;
        }

        // 이전 버튼 표시 여부
        this.prev = this.startPage > 1;

        // 다음 버튼 표시 여부
        this.next = this.endPage < this.realEnd;

        if(this.endPage==0 ){
            this.endPage=1;
        }
    }
}

