package com.wj.train.common.Exception;


public enum BusinessExceptionEnum {
    MEMBER_MOBILE_EXIST("手机号已经注册"),
    MEMBER_MOBILE_NOT_EXIST("手机号不存在"),
    MOBILE_CODE_ERROR("验证码错误"),

    BUSINESS_STATION_NAME__UNIQUE_ERROR("车站已经存在");
    ;

    private String desc;

    BusinessExceptionEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
