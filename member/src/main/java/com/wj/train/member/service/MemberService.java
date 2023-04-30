package com.wj.train.member.service;

import com.wj.train.member.mapper.MemberMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


@Service
public class MemberService {
    @Resource
    private MemberMapper memberMapper;

    public Long countByExample(){
        return memberMapper.countByExample(null);
    }

}
