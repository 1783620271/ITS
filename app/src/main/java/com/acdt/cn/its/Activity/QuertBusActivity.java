package com.acdt.cn.its.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.acdt.cn.its.R;
import com.acdt.cn.its.Utils.ContantsValue;
import com.acdt.cn.its.Utils.GenerateJsonUtil;
import com.acdt.cn.its.Utils.HttpUtils;
import com.acdt.cn.its.Utils.ResolveJson;
import com.acdt.cn.its.vo.BusStation;
import com.acdt.cn.its.vo.GetBusStation;

import org.json.JSONException;

import static android.support.constraint.Constraints.TAG;

public class QuertBusActivity extends Activity {

    private static final int DICTANCE = 101;
    private static final int DICTANCE1 = 102;
    private Button busButton1;

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case  DICTANCE:
                    BusStation busStation=busTionList.getBusTionList().get(0);
                    Distancebus1.setText("1号公交："+busStation.getDistance());
                    BusStation busStation1=busTionList.getBusTionList().get(1);
                    Distancebus2.setText("2号公交："+busStation1.getDistance());
                    break;
                case  DICTANCE1:
                    BusStation busStatio=busTionList1.getBusTionList().get(0);
                    Distancebus1.setText("1号公交："+busStatio.getDistance());
                    BusStation busStati=busTionList.getBusTionList().get(1);
                    Distancebus2.setText("2号公交："+busStati.getDistance());
                    break;
            }
            super.handleMessage(msg);
        }
    };
    private TextView actionName;
    private TextView busDistance;
    private TextView Distancebus1;
    private TextView Distancebus2;
    private GetBusStation busTionList;
    private Button busButton2;
    private GetBusStation busTionList1;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_querybus);
        actionName = (TextView) findViewById(R.id.actionName);
        busDistance = (TextView) findViewById(R.id.busDistance);
        Distancebus1 = (TextView) findViewById(R.id.Distancebus1);
        Distancebus2 = (TextView) findViewById(R.id.Distancebus2);
        busButton1 = (Button) findViewById(R.id.busButton1);
        busButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionName.setText("1号站台");
                busDistance.setText("距一号站台距离：");
                //获取数据
                intiDistance();
            }
        });
        busButton2 = (Button) findViewById(R.id.busButton2);
        busButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionName.setText("2号站台");
                busDistance.setText("距二号站台距离：");
                intiDistance1();
            }
        });
    }

    private void intiDistance1() {
        new Thread(){
            @Override
            public void run() {
                String stationinfo = GenerateJsonUtil.GenerateGetBusStationinfo("2");
                String doPost = HttpUtils.doPost(ContantsValue.HTTP + ContantsValue.HTTPGETBUSSTATIONINFO, stationinfo);
               // Log.i(TAG, "run: "+doPost);
                try {
                    busTionList1 = ResolveJson.ResolveGetBusStation(doPost);
                    Message msg=new Message();
                    msg.what=DICTANCE1;
                    handler.sendMessage(msg);
                    // Log.i(TAG, "run: "+getBusStation.getBusTionList().get(0));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                super.run();
            }
        }.start();
    }

    private void intiDistance() {
        new Thread(){
            @Override
            public void run() {
                String stationinfo = GenerateJsonUtil.GenerateGetBusStationinfo("1");
                String doPost = HttpUtils.doPost(ContantsValue.HTTP + ContantsValue.HTTPGETBUSSTATIONINFO, stationinfo);
                Log.i(TAG, "run: "+doPost);
                try {
                    busTionList = ResolveJson.ResolveGetBusStation(doPost);
                    Message msg=new Message();
                    msg.what=DICTANCE;
                    handler.sendMessage(msg);
                   // Log.i(TAG, "run: "+getBusStation.getBusTionList().get(0));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                super.run();
            }
        }.start();
    }
}
