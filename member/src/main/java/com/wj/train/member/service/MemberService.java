package com.wj.train.member.service;

import cn.hutool.core.collection.CollUtil;
import com.wj.train.member.domain.Member;
import com.wj.train.member.domain.MemberExample;
import com.wj.train.member.mapper.MemberMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MemberService {
    @Resource
    private MemberMapper memberMapper;

    public Long countByExample(){
        return memberMapper.countByExample(null);
    }

    public long register(String mobile){
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> members = memberMapper.selectByExample(memberExample);
        if(CollUtil.isNotEmpty(members)){
            throw new RuntimeException("该电话号码已经存在");
        }

        Member member = new Member();
        member.setId(System.currentTimeMillis());
        member.setMobile(mobile);
        memberMapper.insert(member);
        return member.getId();


    }

}
