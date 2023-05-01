package com.wj.train.member.controller;

import com.wj.train.common.resp.CommonResp;
import com.wj.train.member.req.MemberLoginReq;
import com.wj.train.member.req.MemberRegisterReq;
import com.wj.train.member.req.MemberSendCodeReq;
import com.wj.train.member.resp.MemberLoginResp;
import com.wj.train.member.service.MemberService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@Slf4j
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
    public CommonResp<Long> register(@Valid MemberRegisterReq req){
        long register = memberServicel.register(req);
        CommonResp<Long> commonResp = new CommonResp<>();
        commonResp.setContent(register);
        return commonResp;
    }

    @PostMapping("/sendCode")
    public CommonResp<String> sendCode(@Valid MemberSendCodeReq req){
        String code = memberServicel.sendCode(req);
        log.info("短信验证码:"+code);
        return new CommonResp<String>(code);
    }

    @PostMapping("/login")
    public CommonResp<MemberLoginResp> login(@Valid MemberLoginReq req){
        MemberLoginResp resp =  memberServicel.login(req);
        return new CommonResp<>(resp);
    }
}
