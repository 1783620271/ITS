package com.acdt.cn.its.Utils;

/**
 * 生成Json字符串用作请求参数的工具类
 */
public class GenerateJsonUtil {
    /**
     *  查询小车当前速度 \ 查询小车账户余额 \
     * @param CarId
     * @return
     */
    public String GenerateSimple(Integer CarId){
        String jsonStr="{\"CarId\":"+CarId+"}";
        return jsonStr;
    }

    /**
     * 设置小车动作
     * @param CarId
     * @param CarAction
     * @return
     */
    public String GenerateSetCarMove(Integer CarId,String CarAction){
        String jsonStr="{\"CarId\":"+CarId+",\"CarAction\":\""+CarAction+"\"}";
        return jsonStr;
    }

    /**
     * 小车账户充值
     * @param CarId
     * @param Money
     * @return
     */
    public String GenerateSetCarAccountRecharge(Integer CarId,Integer Money){
        String jsonStr="{\"CarId\":"+CarId+",\"CarAction\":"+Money+"}";
        return jsonStr;
    }

    /**
     * 查询道路状态
     * @param RoadId
     * @return
     */
    public String GenerateGetRoadStatus(Integer RoadId){
        String jsonStr="{\"RoadId\":"+RoadId+"}";
        return jsonStr;
    }

    /**
     * 费率设置
     * @param RateType
     * @param Money
     * @return
     */
    public String GenerateSetParkRate(String RateType,Integer Money){
        String jsonStr="{\"RateType\":"+RateType+",\"Money\":"+Money+"}";
        return jsonStr;
    }
}