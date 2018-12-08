package com.acdt.cn.its.vo;

public class BusStation {
    private Integer Distance;
    private  Integer BusId;

    public Integer getDistance() {
        return Distance;
    }

    public void setDistance(Integer distance) {
        Distance = distance;
    }

    public Integer getBusId() {
        return BusId;
    }

    public void setBusId(Integer busId) {
        BusId = busId;
    }

    @Override
    public String toString() {
        return "BusStation{" +
                "Distance=" + Distance +
                ", BusId=" + BusId +
                '}';
    }
}
