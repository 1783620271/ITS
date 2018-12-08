package com.acdt.cn.its.vo;

public class GetAllSense {

    private Integer pm2_5;
    private  Integer co2;
    private Integer temp;
    private  Integer LightIntensity;
    private  Integer humidity;

    public Integer getPm2_5() {
        return pm2_5;
    }

    public void setPm2_5(Integer pm2_5) {
        this.pm2_5 = pm2_5;
    }

    public Integer getCo2() {
        return co2;
    }

    public void setCo2(Integer co2) {
        this.co2 = co2;
    }

    public Integer getTemp() {
        return temp;
    }

    public void setTemp(Integer temp) {
        this.temp = temp;
    }

    public Integer getLightIntensity() {
        return LightIntensity;
    }

    public void setLightIntensity(Integer lightIntensity) {
        LightIntensity = lightIntensity;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        return "GetAllSense{" +
                "pm2_5=" + pm2_5 +
                ", co2=" + co2 +
                ", temp=" + temp +
                ", LightIntensity=" + LightIntensity +
                ", humidity=" + humidity +
                '}';
    }
}
