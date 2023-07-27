package com.wj.train.business.controller;

import com.wj.train.business.req.StationQueryReq;
import com.wj.train.business.req.StationSaveReq;
import com.wj.train.business.resp.StationQueryResp;
import com.wj.train.business.service.StationService;
import com.wj.train.common.resp.CommonResp;
import com.wj.train.common.resp.PageResp;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/station")
@Slf4j
public class StationController {
    @Resource
    private StationService stationService;





    @GetMapping("/query-all")
    public CommonResp<List<StationQueryResp>> queryList(){
        List<StationQueryResp> stationQueryResps = stationService.queryAll();
        return new CommonResp<>(stationQueryResps);
    }
}
