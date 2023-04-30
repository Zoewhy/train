package com.wj.train.member.controller;

import com.wj.train.member.service.MemberService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Resource
    private MemberService memberServicel;

    @GetMapping("/count")
    public Integer count(){
        return Math.toIntExact(memberServicel.countByExample());
    }
}
