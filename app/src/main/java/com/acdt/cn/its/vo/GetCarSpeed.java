package com.acdt.cn.its.vo;

public class GetCarSpeed {
    private Integer CarSpeed;

    public Integer getCarSpeed() {
        return CarSpeed;
    }

    public void setCarSpeed(Integer carSpeed) {
        CarSpeed = carSpeed;
    }

    @Override
    public String toString() {
        return "GetCarSpeed{" +
                "CarSpeed=" + CarSpeed +
                '}';
    }
}
