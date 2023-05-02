package com.wj.train.member.service;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.wj.train.common.context.LoginMemberContext;
import com.wj.train.common.util.SnowUtil;
import com.wj.train.member.domain.Passenger;
import com.wj.train.member.domain.PassengerExample;
import com.wj.train.member.mapper.PassengerMapper;
import com.wj.train.member.req.PassengerQueryReq;
import com.wj.train.member.req.PassengerSaveReq;
import com.wj.train.member.resp.PassengerQueryResp;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService {

    @Resource
    private PassengerMapper passengerMapper;


    public void save(PassengerSaveReq req){
        DateTime date = DateTime.now();
        Passenger passenger = BeanUtil.copyProperties(req, Passenger.class);
        passenger.setMemberId(LoginMemberContext.getId());
        passenger.setId(SnowUtil.getSnowflakeNextId());
        passenger.setCreateTime(date);
        passenger.setUpdateTime(date);
        passengerMapper.insert(passenger);
        return;
    }

    public List<PassengerQueryResp> queryListByMemberId(PassengerQueryReq req){
        PassengerExample passengerExample = new PassengerExample();
        PassengerExample.Criteria criteria = passengerExample.createCriteria();
        if(ObjectUtil.isNotNull(req.getMemberId())){
            criteria.andMemberIdEqualTo(req.getMemberId());
        }
        List<Passenger> passengers = passengerMapper.selectByExample(passengerExample);
        return BeanUtil.copyToList(passengers, PassengerQueryResp.class);
    }
}
