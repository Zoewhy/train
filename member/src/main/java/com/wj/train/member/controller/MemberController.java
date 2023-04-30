package com.wj.train.member.controller;

import com.wj.train.common.resp.CommonResp;
import com.wj.train.member.req.MemberRegisterReq;
import com.wj.train.member.service.MemberService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Resource
    private MemberService memberServicel;

    @GetMapping("/count")
    public CommonResp<Integer> count(){
        int  count = Math.toIntExact(memberServicel.countByExample());
        CommonResp<Integer> commonResp = new CommonResp<>();
        commonResp.setContent(count);
        return commonResp;

    }

    @PostMapping("/register")
    public CommonResp<Long> register(MemberRegisterReq req){
        long register = memberServicel.register(req);
        CommonResp<Long> commonResp = new CommonResp<>();
        commonResp.setContent(register);
        return commonResp;

    }
}
