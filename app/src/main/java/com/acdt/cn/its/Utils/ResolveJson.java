package com.acdt.cn.its.Utils;

import android.util.Log;

import com.acdt.cn.its.vo.BusStation;
import com.acdt.cn.its.vo.GetAllSense;
import com.acdt.cn.its.vo.GetBusStation;
import com.acdt.cn.its.vo.GetCarAccountBalance;
import com.acdt.cn.its.vo.GetCarSpeed;
import com.acdt.cn.its.vo.GetLightSenseValue;
import com.acdt.cn.its.vo.GetParkFree;
import com.acdt.cn.its.vo.GetParkRate;
import com.acdt.cn.its.vo.GetRoadStatus;
import com.acdt.cn.its.vo.ParkFreeId;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

/**
 * Json数据解析工具类
 */
public  class ResolveJson {
    /**
     * 查询“所有传感器”的当前值
     * @param in
     * @return
     * @throws JSONException
     */
    //json数据转换编码
    public static String JSONTokener(String in) {
        // consume an optional byte order mark (BOM) if it exists
        if (in != null && in.startsWith("\ufeff")) {
            in = in.substring(1);
        }
        return in;
    }

    public static GetAllSense ResolveGetAllSense(String jsonStr) throws JSONException {

        //json数据转换编码
        String tokener = JSONTokener(jsonStr);
        JSONObject jsonObject;
        GetAllSense getAllSense =null;
        jsonObject = new JSONObject(tokener);
        String serverinfo = jsonObject.getString("serverinfo");
        JSONObject object = new JSONObject(serverinfo);
        getAllSense = new GetAllSense();
        getAllSense.setPm2_5(object.getInt("pm2.5"));
        getAllSense.setCo2(object.getInt("co2"));
        getAllSense.setTemp(object.getInt("temperature"));
        getAllSense.setLightIntensity(object.getInt("LightIntensity"));
        getAllSense.setHumidity(object.getInt("humidity"));
        return getAllSense;
    }

    /**
     * 查询光照传感器阈值
     * @param jsonStr
     * @return
     * @throws JSONException
     */
    public static GetLightSenseValue ResolveGetLightSenseValue(String jsonStr) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonStr);
        String serverinfo = jsonObject.getString("serverinfo");
        JSONObject object = new JSONObject(serverinfo);
        GetLightSenseValue getLightSenseValue = new GetLightSenseValue();
        getLightSenseValue.setDown(object.getString("Down"));
        getLightSenseValue.setUp(object.getString("Up"));
        return getLightSenseValue;
    }

    /**
     * 查询小车当前速度
     * @param jsonStr
     * @return
     * @throws JSONException
     */
    public static GetCarSpeed ResolveGetCatSpeed(String jsonStr) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonStr);
        String serverinfo = jsonObject.getString("serverinfo");
        JSONObject object = new JSONObject(serverinfo);
        GetCarSpeed getCarSpeed = new GetCarSpeed();
        getCarSpeed.setCarSpeed(object.getInt("CarSpeed"));
        return getCarSpeed;
    }

    /**
     *   设置小车动作  /  小车账户充值  /  费率设置（返回ok/failed）
     * @param jsonStr
     * @return
     * @throws JSONException
     */
    public static String ResolveSimple(String jsonStr) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonStr);
        String serverinfo = jsonObject.getString("serverinfo");
        JSONObject object = new JSONObject(serverinfo);
        String result = object.getString("result");
        return result;
    }

    /**
     * 查询小车账户余额
     * @param jsonStr
     * @return
     * @throws JSONException
     */
    public static GetCarAccountBalance ResolveGetCarAccountBalance(String jsonStr) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonStr);
        String serverinfo = jsonObject.getString("serverinfo");
        JSONObject object = new JSONObject(serverinfo);
        GetCarAccountBalance getCarAccountBalance = new GetCarAccountBalance();
        getCarAccountBalance.setBanlance(object.getInt("Balance"));
        return getCarAccountBalance;
    }

    /**
     * 查询道路状态
     * @param jsonStr
     * @return
     * @throws JSONException
     */
    public static GetRoadStatus ResolveGetRoadStatus(String jsonStr) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonStr);
        String serverinfo = jsonObject.getString("serverinfo");
        JSONObject object = new JSONObject(serverinfo);
        GetRoadStatus getRoadStatus = new GetRoadStatus();
        getRoadStatus.setStatus(object.getInt("Status"));
        return getRoadStatus;
    }

    /**
     * 查询当前费率信息
     * @param jsonStr
     * @return
     * @throws JSONException
     */
    public static GetParkRate ResolveGetParkRate(String jsonStr) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonStr);
        String serverinfo = jsonObject.getString("serverinfo");
        JSONObject object = new JSONObject(serverinfo);
        GetParkRate getParkRate = new GetParkRate();
        getParkRate.setRateType(object.getString("RateType"));
        getParkRate.setMoney(object.getInt("Money"));
        return getParkRate;
    }

    /**
     * 查询当前空闲车位
     * @param jsonStr
     * @return
     * @throws JSONException
     */
    public static GetParkFree ResolveGetParkFree(String jsonStr) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonStr);
        String serverinfo = jsonObject.getString("serverinfo");
        JSONArray jsonArray = new JSONArray(serverinfo);
        GetParkFree getParkFree = new GetParkFree();
        List<ParkFreeId> parkFreeIdList=new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            ParkFreeId parkFreeId = new ParkFreeId();
            parkFreeId.setParkFreeId(jsonArray.getJSONObject(i).getInt("ParkFreeId"));
            parkFreeIdList.add(parkFreeId);
        }
        getParkFree.setParkFreeIdLit(parkFreeIdList);
        return getParkFree;
    }
    /**
     * 获取到站台的距离
     */
    public static GetBusStation ResolveGetBusStation(String jsonStr) throws JSONException {
        //Log.i(TAG, "ResolveGetBusStation: "+11111111);
        JSONObject jsonObject = new JSONObject(jsonStr);
        String serverinfo = jsonObject.getString("serverinfo");
        JSONArray jsonArray = new JSONArray(serverinfo);
        GetBusStation getBusStation=new GetBusStation();
        List<BusStation> BusTionList=new ArrayList<BusStation>();
        for (int i = 0; i < jsonArray.length(); i++) {
           // Log.i(TAG, "ResolveGetBusStation: "+2222222);
            BusStation busStation = new BusStation();
            busStation.setBusId(jsonArray.getJSONObject(i).getInt("BusId"));
           // Log.i(TAG, "ResolveGetBusStation: "+jsonArray.getJSONObject(i).getInt("BusId"));
           busStation.setDistance(jsonArray.getJSONObject(i).getInt("Distance"));
            //Log.i(TAG, "ResolveGetBusStation: "+jsonArray.getJSONObject(i).getInt("Distance"));
            BusTionList.add(busStation);
        }
        getBusStation.setBusTionList(BusTionList);
      //  Log.i(TAG, "ResolveGetBusStation: 111"+getBusStation.getBusTionList().get(0).toString());
        return getBusStation;
    }
}
