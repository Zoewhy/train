package com.wj.train.member.controller;

import com.wj.train.common.resp.CommonResp;
import com.wj.train.member.req.MemberLoginReq;
import com.wj.train.member.req.MemberRegisterReq;
import com.wj.train.member.req.MemberSendCodeReq;
import com.wj.train.member.req.PassengerSaveReq;
import com.wj.train.member.resp.MemberLoginResp;
import com.wj.train.member.service.MemberService;
import com.wj.train.member.service.PassengerService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/passenger")
@Slf4j
public class PassengerController {
    @Resource
    private PassengerService passengerService;

    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody PassengerSaveReq req){
        passengerService.save(req);
        return new CommonResp<>();
    }
}
