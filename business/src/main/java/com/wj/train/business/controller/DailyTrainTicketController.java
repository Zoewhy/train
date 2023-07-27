package com.wj.train.business.controller;

import com.wj.train.business.req.DailyTrainTicketQueryReq;
import com.wj.train.business.req.DailyTrainTicketSaveReq;
import com.wj.train.business.resp.DailyTrainTicketQueryResp;
import com.wj.train.business.service.DailyTrainTicketService;
import com.wj.train.common.resp.CommonResp;
import com.wj.train.common.resp.PageResp;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/daily-train-ticket")
@Slf4j
public class DailyTrainTicketController {
    @Resource
    private DailyTrainTicketService dailyTrainTicketService;


    @GetMapping("/query-list")
    public CommonResp<PageResp<DailyTrainTicketQueryResp>> queryList(@Valid DailyTrainTicketQueryReq req){
        PageResp<DailyTrainTicketQueryResp> dailyTrainTicketQueryResps = dailyTrainTicketService.queryList(req);
        return new CommonResp<>(dailyTrainTicketQueryResps);
    }


}
