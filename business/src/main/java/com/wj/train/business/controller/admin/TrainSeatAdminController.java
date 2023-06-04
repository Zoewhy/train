package com.wj.train.business.controller.admin;

import com.wj.train.common.resp.CommonResp;
import com.wj.train.common.resp.PageResp;
import com.wj.train.business.req.*;
import com.wj.train.business.resp.TrainSeatQueryResp;
import com.wj.train.business.service.TrainSeatService;
import com.wj.train.business.service.TrainSeatService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/train-seat")
@Slf4j
public class TrainSeatAdminController {
    @Resource
    private TrainSeatService trainSeatService;

    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody TrainSeatSaveReq req){
        trainSeatService.save(req);
        return new CommonResp<>();
    }


    @GetMapping("/query-list")
    public CommonResp<PageResp<TrainSeatQueryResp>> queryList(@Valid TrainSeatQueryReq req){
        PageResp<TrainSeatQueryResp> trainSeatQueryResps = trainSeatService.queryList(req);
        return new CommonResp<>(trainSeatQueryResps);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id){
        trainSeatService.delete(id);
        return new CommonResp<>();
    }
}
