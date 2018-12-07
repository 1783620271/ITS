package com.acdt.cn.its.Activity;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.acdt.cn.its.R;

public class MyCarActivity extends Activity {

    private Button myCarBitton1;
    private Button myCarBitton2;
    private Button myCarBitton3;
    private Button myCarBitton4;
    private TextView myCarState1;
    private TextView myCarState2;
    private TextView myCarState3;
    private TextView myCarState4;
    private Button MyCar_queryTop;
    private Button myCarblack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycar);
        intiUtil();
    }

    private void intiUtil() {
        myCarBitton1 = (Button) findViewById(R.id.MyCarBitton1);
        myCarBitton2 = (Button) findViewById(R.id.MyCarBitton2);
        myCarBitton3 = (Button) findViewById(R.id.MyCarBitton3);
        myCarBitton4 = (Button) findViewById(R.id.MyCarBitton4);
        myCarState1 = (TextView) findViewById(R.id.myCarState1);
        myCarState2 = (TextView) findViewById(R.id.myCarState2);
        myCarState3 = (TextView) findViewById(R.id.myCarState3);
        myCarState4 = (TextView) findViewById(R.id.myCarState4);
        myCarblack = (Button) findViewById(R.id.MyCarblack);
        //点击按钮返回事件（物理返回）
        myCarblack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new Thread() {
                    public void run() {
                        try {
                            Instrumentation inst = new Instrumentation();
                            inst.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
                        } catch (Exception e) {

                        }
                    }
                }.start();
            }

        });
        MyCar_queryTop = (Button) findViewById(R.id.MyCar_queryTop);
        myCarBitton1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               if(myCarState1.getText().equals("停止")){
                   myCarBitton1.setBackgroundResource(R.drawable.startmycar);
                   myCarState1.setText("车速:60 kM/h");
               }else {
                   myCarBitton1.setBackgroundResource(R.drawable.endmycar);
                   myCarState1.setText("停止");
               }
            }
        });
        myCarBitton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(myCarState2.getText().equals("停止")){
                    myCarBitton2.setBackgroundResource(R.drawable.startmycar);
                    myCarState2.setText("车速:60 kM/h");
                }else {
                    myCarBitton2.setBackgroundResource(R.drawable.endmycar);
                    myCarState2.setText("停止");
                }
            }
        });
        myCarBitton3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(myCarState3.getText().equals("停止")){
                    myCarState3.setText("车速:60 kM/h");
                    myCarBitton3.setBackgroundResource(R.drawable.startmycar);
                }else {
                    myCarBitton3.setBackgroundResource(R.drawable.endmycar);
                    myCarState3.setText("停止");
                }
            }
        });
        myCarBitton4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(myCarState4.getText().equals("停止")){
                    myCarState4.setText("车速:60 kM/h");
                    myCarBitton4.setBackgroundResource(R.drawable.startmycar);
                }else {
                    myCarBitton4.setBackgroundResource(R.drawable.endmycar);
                    myCarState4.setText("停止");
                }
            }
        });
        MyCar_queryTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyCarActivity.this,QueryTopActivity.class));
            }
        });
    }
}
