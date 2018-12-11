package com.acdt.cn.its.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.constraint.Constraints;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.acdt.cn.its.R;
import com.acdt.cn.its.Utils.ContantsValue;
import com.acdt.cn.its.Utils.GenerateJsonUtil;
import com.acdt.cn.its.Utils.HttpUtils;
import com.acdt.cn.its.Utils.NetworkUtil;
import com.acdt.cn.its.Utils.ResolveJson;
import com.acdt.cn.its.Utils.SpUtils;
import com.acdt.cn.its.vo.BusStation;
import com.acdt.cn.its.vo.GetAllSense;
import com.acdt.cn.its.vo.GetBusStation;
import com.acdt.cn.its.vo.GetLightSenseValue;

import org.json.JSONException;

import java.util.Timer;
import java.util.TimerTask;

import static android.content.ContentValues.TAG;
import static com.acdt.cn.its.Utils.ContantsValue.HTTP;
import static com.acdt.cn.its.Utils.ContantsValue.HTTPGETALLSENSE;

public class DrawerLayoutActivity extends Activity {
    private static final int NUMBER1 = 101;
    private static final int DICTANCE = 102;
    private static final int DICTANCE1 = 103;
    DrawerLayout dl;
    Button btnShow;
    LinearLayout rlRight;
    private Button set;
    private GetAllSense getAllSense;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case NUMBER1:
                   // Log.i(TAG, "handleMessage: "+333333);
                    textright2.setText("PM2.5:"+ getAllSense.getPm2_5()+"ug/m^3,温度:"+ getAllSense.getTemp()+"摄氏度");
                    textright3.setText("湿度:"+ getAllSense.getHumidity()+",CO2:"+ getAllSense.getHumidity()+"ug/m^31");
                    break;
                case  DICTANCE:
                    BusStation busStation=busTionList.getBusTionList().get(0);
                    BusStation busStation1=busTionList.getBusTionList().get(1);
                    textleft2.setText("1号公交："+busStation.getDistance()+"2号公交："+busStation1.getDistance());
                    break;
                case  DICTANCE1:
                    BusStation busStatio= busTionList1.getBusTionList().get(0);
                    BusStation busStati=busTionList1.getBusTionList().get(1);
                    textleft4.setText("1号公交："+busStatio.getDistance()+"2号公交："+busStati.getDistance());
                    break;
            }
            super.handleMessage(msg);
        }
    };
    private TextView textright2;
    private TextView textright3;
    int number=1;
    private Timer timer;
    private TimerTask task;
    private LinearLayout menu_park;
    private LinearLayout menu_bus;
    private LinearLayout menu_way;
    private LinearLayout menu_road;
    private GetLightSenseValue getLightSenseValue;
    private TextView textleft2;
    private TextView textleft4;
    private GetBusStation busTionList;
    private TextView textleft3;
    private GetBusStation busTionList1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        ContantsValue.setIP(SpUtils.getString(getApplicationContext(),ContantsValue.IPADDRESS,""));
        //Log.i(TAG, "onCreate: "+ContantsValue.IP);
        initView();
        //定时刷新
        intiTime();
        initData();
        //菜单设置
        intiSet();
        //座驾设置
        intiMenuCar();
        //传感器数据显示
        //停车查询
        intiPark();
        //我的路况
        intilukuang();
        //公交查询
        intibus();
        //道路环境
        intimenuway();
    }

    private void intiTime() {
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                intiSense();
            }
        };
        timer.schedule(task,0,5000);
    }

    private void intilukuang() {
        menu_road = (LinearLayout) findViewById(R.id.menu_road);
        menu_road.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DrawerLayoutActivity.this,LuKuangActivity.class));

            }
        });

    }

    private void intimenuway() {
        menu_way = (LinearLayout) findViewById(R.id.menu_way);
        menu_way.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DrawerLayoutActivity.this,WayMenuActivity.class));
            }
        });
    }

    private void intibus() {
        menu_bus = (LinearLayout) findViewById(R.id.menu_bus);
        menu_bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DrawerLayoutActivity.this,QuertBusActivity.class));
            }
        });
    }

    private void intiPark() {
        menu_park = (LinearLayout) findViewById(R.id.menu_park);
        menu_park.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(new Intent(DrawerLayoutActivity.this,ParkQeryActivity.class)));
            }
        });
    }


    /**
     * 获取数据
     */
    private void intiSense() {
                //获取环境数据
                try {
                    String textPM=HttpUtils.doPost(HTTP+HTTPGETALLSENSE,null);
                   // Log.i(TAG, "run: "+textPM);
                    getAllSense = ResolveJson.ResolveGetAllSense(textPM);
                   // Log.i(TAG, "run: "+ getAllSense.toString());
                    Message msg=new Message();
                    msg.what=NUMBER1;
                    handler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
                String stationinfo1 = GenerateJsonUtil.GenerateGetBusStationinfo("1");
                String doPost2 = HttpUtils.doPost(ContantsValue.HTTP + ContantsValue.HTTPGETBUSSTATIONINFO, stationinfo1);
                // Log.i(Constraints.TAG, "run: "+doPost);
                try {
                    busTionList = ResolveJson.ResolveGetBusStation(doPost2);
                    Message msg=new Message();
                    msg.what=DICTANCE;
                    handler.sendMessage(msg);
                    // Log.i(TAG, "run: "+getBusStation.getBusTionList().get(0));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
        //l号站台距离
    }

    private void intiMenuCar() {
        LinearLayout menu_car = (LinearLayout) findViewById(R.id.menu_car);
        menu_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DrawerLayoutActivity.this,MyCarActivity.class));
            }
        });
    }

    private void intiSet() {
        set = (Button) findViewById(R.id.set);
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DrawerLayoutActivity.this,SetActivity.class));
            }
        });
    }

    private void initView() {
        btnShow = (Button) findViewById(R.id.btn_show);
        dl = (DrawerLayout) findViewById(R.id.drawerlayout);
        rlRight = (LinearLayout) findViewById(R.id.lefList);
        textright2 = (TextView) findViewById(R.id.textright2);
        textright3 = (TextView) findViewById(R.id.textright3);
        textleft2 = (TextView) findViewById(R.id.textleft2);
        textleft4 = (TextView) findViewById(R.id.textleft4);
        /*
        // 关闭手势滑动
        dl.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        dl.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) { }
            @Override
            public void onDrawerOpened(View drawerView)
            { // 打开手势滑动
                 dl.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                // 关闭手势滑动
                dl.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            } @Override
            public void onDrawerStateChanged(int newState) {
            }
        });
         */
    }
    private void initData() {
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO 点击按钮打开侧滑菜单
                dl.openDrawer(rlRight);
            }

        });
    }
}