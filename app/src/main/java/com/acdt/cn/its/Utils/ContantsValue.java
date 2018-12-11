package com.acdt.cn.its.Utils;

public  class ContantsValue {

    private static  String IP;

    public static String getIP() {
        return IP;
    }

    public static void setIP(String IP) {
        ContantsValue.IP = IP;
    }
    public static  String HTTP = "http://192.168.1.101:8080/transportservice/type/jason/action/";
    public static final String HTTPGETALLSENSE = "GetAllSense.do";
    public static final String HTTPGETLIGHTSENSEVALVE = "GetLightSenseValve.do";
    public static final String HTTPGETCARSPEED = "GetCarSpeed.do";
    public static final String HTTPSETCARMOVE = "SetCarMove.do";
    public static final String HTTPGETCARACCOUNTBALANCE = "GetCarAccountBalance.do";
    public static final String HTTPSETCARACCOUNTRECHARGE = "SetCarAccountRecharge.do";
    public static final String HTTPGETROADSTATUS = "GetRoadStatus.do";
    public static final String HTTPSETPARKRATE = "SetParkRate.do";
    public static final String HTTPGETPARKRATE = "GetParkRate.do";
    public static final String HTTPGETPARKFREE = "GetParkFree.do";
    public static final String HTTPGETBUSSTATIONINFO = "GetBusStationInfo.do";
    public static final String HTTPGETTRAFFICLIGHTCONFIGACTION = "GetTrafficLightConfigAction.do";

    public static final String IPADDRESS = "ipaddress";
}
