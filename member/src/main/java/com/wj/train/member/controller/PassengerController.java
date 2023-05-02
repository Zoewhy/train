package com.wj.train.member.controller;

import com.wj.train.common.context.LoginMemberContext;
import com.wj.train.common.resp.CommonResp;
import com.wj.train.common.resp.PageResp;
import com.wj.train.member.req.*;
import com.wj.train.member.resp.MemberLoginResp;
import com.wj.train.member.resp.PassengerQueryResp;
import com.wj.train.member.service.MemberService;
import com.wj.train.member.service.PassengerService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @GetMapping("/query-list")
    public CommonResp<PageResp<PassengerQueryResp>> queryListByMemberId(@Valid PassengerQueryReq req){
        req.setMemberId(LoginMemberContext.getId());
        PageResp<PassengerQueryResp> passengerQueryResps = passengerService.queryListByMemberId(req);
        return new CommonResp<>(passengerQueryResps);
    }
}
