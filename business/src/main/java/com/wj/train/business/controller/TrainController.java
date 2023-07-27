package com.wj.train.business.controller;

import com.wj.train.business.req.TrainQueryReq;
import com.wj.train.business.req.TrainSaveReq;
import com.wj.train.business.resp.TrainQueryResp;
import com.wj.train.business.service.TrainSeatService;
import com.wj.train.business.service.TrainService;
import com.wj.train.common.resp.CommonResp;
import com.wj.train.common.resp.PageResp;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/train")
@Slf4j
public class TrainController {
    @Resource
    private TrainService trainService;

    @Resource
    private TrainSeatService trainSeatService;



    @GetMapping("/query-all")
    public CommonResp<List<TrainQueryResp>> queryAll(){
        List<TrainQueryResp> trainQueryResps = trainService.queryAl();
        return new CommonResp<>(trainQueryResps);
    }

}
