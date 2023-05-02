package com.wj.train.member.req;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;



public class PassengerQueryReq {


    private Long memberId;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "PassengerQueryReq{" +
                "memberId=" + memberId +
                '}';
    }
}