package com.wj.train.common.Exception;

public class BusinessException extends RuntimeException{
    private BusinessExceptionEnum e;

    public BusinessException(BusinessExceptionEnum e) {
        this.e = e;
    }


    public BusinessExceptionEnum getE() {
        return e;
    }

    public void setE(BusinessExceptionEnum e) {
        this.e = e;
    }

    /**
     * 不写入堆栈信息，提高性能
     * @return
     */
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
