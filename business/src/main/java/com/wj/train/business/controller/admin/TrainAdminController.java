package com.wj.train.business.controller.admin;

import com.wj.train.business.service.TrainSeatService;
import com.wj.train.common.resp.CommonResp;
import com.wj.train.common.resp.PageResp;
import com.wj.train.business.req.*;
import com.wj.train.business.resp.TrainQueryResp;
import com.wj.train.business.service.TrainService;
import com.wj.train.business.service.TrainService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/train")
@Slf4j
public class TrainAdminController {
    @Resource
    private TrainService trainService;

    @Resource
    private TrainSeatService trainSeatService;

    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody TrainSaveReq req){
        trainService.save(req);
        return new CommonResp<>();
    }


    @GetMapping("/query-list")
    public CommonResp<PageResp<TrainQueryResp>> queryList(@Valid TrainQueryReq req){
        PageResp<TrainQueryResp> trainQueryResps = trainService.queryList(req);
        return new CommonResp<>(trainQueryResps);
    }
    @GetMapping("/query-all")
    public CommonResp<List<TrainQueryResp>> queryAll(){
        List<TrainQueryResp> trainQueryResps = trainService.queryAl();
        return new CommonResp<>(trainQueryResps);
    }
    @DeleteMapping("/delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id){
        trainService.delete(id);
        return new CommonResp<>();
    }

    @GetMapping("/gen-seat/{trainCode}")
    public CommonResp<Object> genSeat(@PathVariable String trainCode){
        trainSeatService.genSeat(trainCode);
        return new CommonResp<>();
    }
}
