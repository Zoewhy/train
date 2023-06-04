package com.wj.train.business.req;

import com.wj.train.common.req.PageReq;

public class TrainStationQueryReq extends PageReq {
    String trainCode;



    public String getTrainCode() {
        return trainCode;
    }

    public void setTrainCode(String trainCode) {
        this.trainCode = trainCode;
    }

    @Override
    public String toString() {
        return "TrainStationQueryReq{" +
                "trainCode='" + trainCode + '\'' +
                "} " + super.toString();
    }

}