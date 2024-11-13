package com.smbt.pickod.dto.festival;

import lombok.Getter;

import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {
    private int page;     // 현재 페이지
    private int amount;   // 한 페이지당 표시할 항목 수

    //생성자 오버로딩
    public Criteria(){
    this(1, 9);
    }

    public Criteria(int page, int amount) {
        this.page = page;
        this.amount = amount;
    }

}
