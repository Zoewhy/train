package com.wj.train.member.req;

import com.wj.train.common.req.PageReq;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;



public class PassengerQueryReq extends PageReq {


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