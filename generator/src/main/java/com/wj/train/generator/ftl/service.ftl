package com.wj.train.${module}.service;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wj.train.common.context.LoginMemberContext;
import com.wj.train.common.resp.PageResp;
import com.wj.train.common.util.SnowUtil;
import com.wj.train.${module}.domain.${Domain};
import com.wj.train.${module}.domain.${Domain}Example;
import com.wj.train.${module}.mapper.${Domain}Mapper;
import com.wj.train.${module}.req.${Domain}QueryReq;
import com.wj.train.${module}.req.${Domain}SaveReq;
import com.wj.train.${module}.resp.${Domain}QueryResp;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ${Domain}Service {

    @Resource
    private ${Domain}Mapper ${domain}Mapper;


    public void save(${Domain}SaveReq req){
        DateTime date = DateTime.now();
        ${Domain} ${domain} = BeanUtil.copyProperties(req, ${Domain}.class);
        if(ObjectUtil.isNull(${domain}.getId())){
            ${domain}.setMemberId(LoginMemberContext.getId());
            ${domain}.setId(SnowUtil.getSnowflakeNextId());
            ${domain}.setCreateTime(date);
            ${domain}.setUpdateTime(date);
            ${domain}Mapper.insert(${domain});
        }else{
            ${domain}.setUpdateTime(date);
            ${domain}Mapper.updateByPrimaryKey(${domain});
        }
        return;
    }

    public PageResp<${Domain}QueryResp> queryListByMemberId(${Domain}QueryReq req){
        ${Domain}Example ${domain}Example = new ${Domain}Example();
        ${domain}Example.setOrderByClause("id desc");
        ${Domain}Example.Criteria criteria = ${domain}Example.createCriteria();
        if(ObjectUtil.isNotNull(req.getMemberId())){
            criteria.andMemberIdEqualTo(req.getMemberId());
        }
        log.info("查询的页码:{}", req.getPage());
        log.info("查询的页数:{}", req.getSize());
        PageHelper.startPage(req.getPage(), req.getSize());
        List<${Domain}> ${domain}s = ${domain}Mapper.selectByExample(${domain}Example);
        PageInfo<${Domain}> pageInfo = new PageInfo<>(${domain}s);
        log.info("查询总记录数:{}", pageInfo.getTotal());
        PageResp<${Domain}QueryResp> pageResp = new PageResp<>();
        BeanUtil.copyToList(${domain}s, ${Domain}QueryResp.class);
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(BeanUtil.copyToList(${domain}s, ${Domain}QueryResp.class));
        return pageResp;
    }

    public void delete(Long id) {
        ${domain}Mapper.deleteByPrimaryKey(id);
    }
}
