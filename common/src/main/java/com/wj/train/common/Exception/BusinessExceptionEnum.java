package com.wj.train.common.Exception;


public enum BusinessExceptionEnum {
    MEMBER_MOBILE_EXIST("手机号已经注册");

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
