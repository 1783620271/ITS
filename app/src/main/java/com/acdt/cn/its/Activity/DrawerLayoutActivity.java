package com.acdt.cn.its.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.acdt.cn.its.R;

public class DrawerLayoutActivity extends Activity {
    DrawerLayout dl;
    Button btnShow;
    RelativeLayout rlRight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        initView();
        initData();
    }
    private void initView() {
        btnShow = (Button) findViewById(R.id.btn_show);
        dl = (DrawerLayout) findViewById(R.id.drawerlayout);
        rlRight = (RelativeLayout) findViewById(R.id.left11);
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