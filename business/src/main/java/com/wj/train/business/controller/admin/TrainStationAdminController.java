package com.wj.train.business.controller.admin;

import com.wj.train.common.resp.CommonResp;
import com.wj.train.common.resp.PageResp;
import com.wj.train.business.req.*;
import com.wj.train.business.resp.TrainStationQueryResp;
import com.wj.train.business.service.TrainStationService;
import com.wj.train.business.service.TrainStationService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/train-station")
@Slf4j
public class TrainStationAdminController {
    @Resource
    private TrainStationService trainStationService;

    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody TrainStationSaveReq req){
        trainStationService.save(req);
        return new CommonResp<>();
    }


    @GetMapping("/query-list")
    public CommonResp<PageResp<TrainStationQueryResp>> queryList(@Valid TrainStationQueryReq req){
        PageResp<TrainStationQueryResp> trainStationQueryResps = trainStationService.queryList(req);
        return new CommonResp<>(trainStationQueryResps);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id){
        trainStationService.delete(id);
        return new CommonResp<>();
    }
}
