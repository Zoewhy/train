package com.wj.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.wj.train.common.Exception.BusinessException;
import com.wj.train.common.Exception.BusinessExceptionEnum;
import com.wj.train.common.util.SnowUtil;
import com.wj.train.member.domain.Member;
import com.wj.train.member.domain.MemberExample;
import com.wj.train.member.mapper.MemberMapper;
import com.wj.train.member.req.MemberLoginReq;
import com.wj.train.member.req.MemberRegisterReq;
import com.wj.train.member.req.MemberSendCodeReq;
import com.wj.train.member.resp.MemberLoginResp;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.cert.ocsp.Req;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class MemberService {
    @Resource
    private MemberMapper memberMapper;

    public Long countByExample(){
        return memberMapper.countByExample(null);
    }

    public long register(MemberRegisterReq req){
        Member member = selectMemberByMobile(req.getMobile());
        if(member != null){
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_EXIST);
        }

        member = new Member();
        member.setId(SnowUtil.getSnowflakeNextId());
        member.setMobile(req.getMobile());
        memberMapper.insert(member);
        return member.getId();
    }
    public String sendCode(MemberSendCodeReq req){
        Member member = selectMemberByMobile(req.getMobile());
        //如果手机号不存在则插入记录
        if(member == null){
            log.info("手机不存在插入一条记录");
            member = new Member();
            member.setId(SnowUtil.getSnowflakeNextId());
            member.setMobile(req.getMobile());
            memberMapper.insert(member);
        }

        //生成验证码,开发流程省略此部直接返回固定数字
//        String code = RandomUtil.randomString(4);
        String code = "8888";
        //保存短信验证表：手机号，短信验证码，有效期，是否已经使用，业务类型，发送时间，使用时间
        log.info("保存短信验证表");
        //对接短信通道，发送验证码
        log.info("发送短信验证码");
        return code;
    }

    public MemberLoginResp login(MemberLoginReq req){
        String mobile= req.getMobile();
        Member member = selectMemberByMobile(mobile);
        //如果手机号不存在则插入记录
        if(ObjectUtil.isNull(member)){
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_NOT_EXIST);
        }

        //为了方便直接写死，后续需要改
        if(!"8888".equals(req.getCode())){
            throw new BusinessException(BusinessExceptionEnum.MOBILE_CODE_ERROR);
        }

        return BeanUtil.copyProperties(member, MemberLoginResp.class);
    }

    private Member selectMemberByMobile(String mobile) {
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> members = memberMapper.selectByExample(memberExample);
        if(CollUtil.isEmpty(members)){
            return null;
        }else{
            return members.get(0);
        }
    }
}
