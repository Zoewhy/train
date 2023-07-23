package com.wj.train.business.controller.admin;

import com.wj.train.common.resp.CommonResp;
import com.wj.train.common.resp.PageResp;
import com.wj.train.business.req.*;
import com.wj.train.business.resp.DailyTrainSeatQueryResp;
import com.wj.train.business.service.DailyTrainSeatService;
import com.wj.train.business.service.DailyTrainSeatService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/daily-train-seat")
@Slf4j
public class DailyTrainSeatAdminController {
    @Resource
    private DailyTrainSeatService dailyTrainSeatService;

    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody DailyTrainSeatSaveReq req){
        dailyTrainSeatService.save(req);
        return new CommonResp<>();
    }


    @GetMapping("/query-list")
    public CommonResp<PageResp<DailyTrainSeatQueryResp>> queryList(@Valid DailyTrainSeatQueryReq req){
        PageResp<DailyTrainSeatQueryResp> dailyTrainSeatQueryResps = dailyTrainSeatService.queryList(req);
        return new CommonResp<>(dailyTrainSeatQueryResps);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id){
        dailyTrainSeatService.delete(id);
        return new CommonResp<>();
    }
}
