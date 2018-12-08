package com.acdt.cn.its.vo;

public class GetLightSenseValue {
    private  String  Down;
    private String Up;

    public String getDown() {
        return Down;
    }

    public void setDown(String down) {
        Down = down;
    }

    public String getUp() {
        return Up;
    }

    public void setUp(String up) {
        Up = up;
    }

    @Override
    public String toString() {
        return "GetLightSenseValue{" +
                "Down='" + Down + '\'' +
                ", Up='" + Up + '\'' +
                '}';
    }
}
