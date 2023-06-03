package com.wj.train.member.service;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wj.train.common.context.LoginMemberContext;
import com.wj.train.common.resp.PageResp;
import com.wj.train.common.util.SnowUtil;
import com.wj.train.member.domain.Passenger;
import com.wj.train.member.domain.PassengerExample;
import com.wj.train.member.mapper.PassengerMapper;
import com.wj.train.member.req.PassengerQueryReq;
import com.wj.train.member.req.PassengerSaveReq;
import com.wj.train.member.resp.PassengerQueryResp;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PassengerService {

    @Resource
    private PassengerMapper passengerMapper;


    public void save(PassengerSaveReq req){
        DateTime date = DateTime.now();
        Passenger passenger = BeanUtil.copyProperties(req, Passenger.class);
        if(ObjectUtil.isNull(passenger.getId())){
            passenger.setMemberId(LoginMemberContext.getId());
            passenger.setId(SnowUtil.getSnowflakeNextId());
            passenger.setCreateTime(date);
            passenger.setUpdateTime(date);
            passengerMapper.insert(passenger);
        }else{
            passenger.setUpdateTime(date);
            passengerMapper.updateByPrimaryKey(passenger);
        }
        return;
    }

    public PageResp<PassengerQueryResp> queryListByMemberId(PassengerQueryReq req){
        PassengerExample passengerExample = new PassengerExample();
        passengerExample.setOrderByClause("id desc");
        PassengerExample.Criteria criteria = passengerExample.createCriteria();
        if(ObjectUtil.isNotNull(req.getMemberId())){
            criteria.andMemberIdEqualTo(req.getMemberId());
        }
        log.info("查询的页码:{}", req.getPage());
        log.info("查询的页数:{}", req.getSize());
        PageHelper.startPage(req.getPage(), req.getSize());
        List<Passenger> passengers = passengerMapper.selectByExample(passengerExample);
        PageInfo<Passenger> pageInfo = new PageInfo<>(passengers);
        log.info("查询总记录数:{}", pageInfo.getTotal());
        PageResp<PassengerQueryResp> pageResp = new PageResp<>();
        BeanUtil.copyToList(passengers, PassengerQueryResp.class);
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(BeanUtil.copyToList(passengers, PassengerQueryResp.class));
        return pageResp;
    }

    public void delete(Long id) {
        passengerMapper.deleteByPrimaryKey(id);
    }
}
