package com.acdt.cn.its.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.acdt.cn.its.R;
import com.acdt.cn.its.Utils.ContantsValue;
import com.acdt.cn.its.Utils.HttpUtils;
import com.acdt.cn.its.Utils.ResolveJson;
import com.acdt.cn.its.vo.GetAllSense;

import static com.acdt.cn.its.Utils.ContantsValue.HTTP;

public class DrawerLayoutActivity extends Activity {
    DrawerLayout dl;
    Button btnShow;
    LinearLayout rlRight;
    private Button set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        initView();
        initData();
        //菜单设置
        intiSet();
        //座驾设置
        intiMenuCar();
        //传感器数据显示
        intiSense();
    }

    private void intiSense() {
        TextView textright2 = (TextView) findViewById(R.id.textright2);
        TextView textright3 = (TextView) findViewById(R.id.textright3);
        //获取环境数据
        String textPM=HttpUtils.doPost(HTTP+ContantsValue.HTTPGETALLSENSE,null);
        try {
            GetAllSense getAllSense=ResolveJson.ResolveGetAllSense(textPM);
            textright2.setText("PM2.5:"+getAllSense.getPm2_5()+"ug/m^3,温度:"+getAllSense.getTemp()+"摄氏度");
            textright3.setText("湿度:"+getAllSense.getHumidity()+",CO2:"+getAllSense.getHumidity()+"ug/m^31");

        } catch (Exception e) {
            e.printStackTrace();
        }
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
                    dl.openDrawer(rlRight);
            }
        });
    }
}