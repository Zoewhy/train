package com.wj.train.business.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wj.train.common.req.PageReq;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class DailyTrainQueryReq extends PageReq {
    // 对于封装get请求不能使用JsonFormat，需要使用spring自带的DateTimeFormat注解
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String code;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "DailyTrainQueryReq{" +
                "date=" + date +
                ", code='" + code + '\'' +
                "} " + super.toString();
    }
}