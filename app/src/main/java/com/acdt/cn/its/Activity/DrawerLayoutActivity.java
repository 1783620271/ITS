package com.acdt.cn.its.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.acdt.cn.its.R;
import com.acdt.cn.its.Utils.ContantsValue;
import com.acdt.cn.its.Utils.HttpUtils;
import com.acdt.cn.its.Utils.NetworkUtil;
import com.acdt.cn.its.Utils.ResolveJson;
import com.acdt.cn.its.Utils.SpUtils;
import com.acdt.cn.its.vo.GetAllSense;

import java.util.Timer;
import java.util.TimerTask;

import static android.content.ContentValues.TAG;
import static com.acdt.cn.its.Utils.ContantsValue.HTTP;
import static com.acdt.cn.its.Utils.ContantsValue.HTTPGETALLSENSE;

public class DrawerLayoutActivity extends Activity {
    private static final int NUMBER1 = 101;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        ContantsValue.IP=SpUtils.getString(DrawerLayoutActivity.this,ContantsValue.IPADDRESS,"");
        //Log.i(TAG, "onCreate: "+ContantsValue.IP);
        initView();
        initData();
        //菜单设置
        intiSet();
        //座驾设置
        intiMenuCar();
        //传感器数据显示
        //停车查询
        intiPark();
        //公交查询
        intibus();

        intiSense();
        //定时刷新
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(DrawerLayoutActivity.this, DrawerLayoutActivity.class));
            }
        };
        timer.schedule(task,5000);
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


    private void intiSense() {
        textright2 = (TextView) findViewById(R.id.textright2);
        textright3 = (TextView) findViewById(R.id.textright3);
        new Thread(){
            @Override
            public void run() {
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
                super.run();
            }
        }.start();

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
                timer.cancel();
                timer = null;
                task.cancel();
                task = null;
                dl.openDrawer(rlRight);

            }

        });

    }
}