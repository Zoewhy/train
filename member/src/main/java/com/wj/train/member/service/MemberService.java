package com.wj.train.member.service;

import com.wj.train.member.mapper.MemberMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


@Service
public class MemberService {
    @Resource
    private MemberMapper mapper;

    public int count(){
        return mapper.count();
    }

}
