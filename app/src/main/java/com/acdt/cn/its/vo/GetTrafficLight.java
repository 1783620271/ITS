package com.acdt.cn.its.vo;

public class GetTrafficLight {
    private  Integer YellowTime;
    private  Integer GreenTime;
    private  Integer RedTime;

    public Integer getYellowTime() {
        return YellowTime;
    }

    public void setYellowTime(Integer yellowTime) {
        YellowTime = yellowTime;
    }

    public Integer getGreenTime() {
        return GreenTime;
    }

    public void setGreenTime(Integer greenTime) {
        GreenTime = greenTime;
    }

    public Integer getRedTime() {
        return RedTime;
    }

    public void setRedTime(Integer redTime) {
        RedTime = redTime;
    }

    @Override
    public String toString() {
        return "GetTrafficLight{" +
                "YellowTime=" + YellowTime +
                ", GreenTime=" + GreenTime +
                ", RedTime=" + RedTime +
                '}';
    }
}
