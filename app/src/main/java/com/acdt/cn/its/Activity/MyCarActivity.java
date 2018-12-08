package com.acdt.cn.its.Activity;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.acdt.cn.its.R;
import com.acdt.cn.its.Utils.GenerateJsonUtil;
import com.acdt.cn.its.Utils.HttpUtils;
import com.acdt.cn.its.Utils.ResolveJson;
import com.acdt.cn.its.vo.GetCarSpeed;

import org.json.JSONException;

import static android.content.ContentValues.TAG;
import static com.acdt.cn.its.Utils.ContantsValue.HTTP;
import static com.acdt.cn.its.Utils.ContantsValue.HTTPGETCARSPEED;
import static com.acdt.cn.its.Utils.ContantsValue.HTTPGETLIGHTSENSEVALVE;
import static com.acdt.cn.its.Utils.ContantsValue.HTTPSETCARMOVE;

public class MyCarActivity extends Activity {

    private static final int ACTION1= 202;
    private static final int ACTION2= 203;
    private static final int ACTION3= 204;
    private static final int ACTION4= 205;
    private static final int SPEED1 = 101;
    private static final int SPEED2 = 103;
    private static final int SPEED4 = 105;
    private static final int SPEED3 = 104;
    private static final int ACTIONstart1 = 110;
    private static final int ACTIONstart2 = 111;
    private static final int ACTIONstart3 = 112;
    private static final int ACTIONstart4 = 113;
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

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case  SPEED1:
                    myCarState1.setText(getCarSpeed.getCarSpeed()+"kM/h");
                    break;
                case  SPEED2:
                    myCarState2.setText(getCarSpeed.getCarSpeed()+"kM/h");
                    break;
                case  SPEED3:
                    myCarState3.setText(getCarSpeed.getCarSpeed()+"kM/h");
                    break;
                case  SPEED4:
                    myCarState4.setText(getCarSpeed.getCarSpeed()+"kM/h");
                    break;
                case  ACTION1:
                    Toast.makeText(MyCarActivity.this, "小车已停止", Toast.LENGTH_SHORT).show();
                    break;
                case  ACTION2:
                    Toast.makeText(MyCarActivity.this, "小车已停止", Toast.LENGTH_SHORT).show();
                    break;
                case  ACTION3:
                    Toast.makeText(MyCarActivity.this, "小车已停止", Toast.LENGTH_SHORT).show();
                    break;
                case  ACTION4:
                    Toast.makeText(MyCarActivity.this, "小车已停止", Toast.LENGTH_SHORT).show();
                    break;
                case  ACTIONstart1:
                    Toast.makeText(MyCarActivity.this, "小车已开启", Toast.LENGTH_SHORT).show();
                    break;
                case  ACTIONstart2:
                    Toast.makeText(MyCarActivity.this, "小车已开启", Toast.LENGTH_SHORT).show();
                    break;
                case  ACTIONstart3:
                    Toast.makeText(MyCarActivity.this, "小车已开启", Toast.LENGTH_SHORT).show();
                    break;
                case  ACTIONstart4:
                    Toast.makeText(MyCarActivity.this, "小车已开启", Toast.LENGTH_SHORT).show();
                    break;

            }
            super.handleMessage(msg);
        }
    };
    private GetCarSpeed getCarSpeed;
    private String simplestart;
    private String simplestop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycar);
        intiUtil();
    }

    private void intiUtil() {
        myCarBitton1 = (Button) findViewById(R.id.MyCarBitton1);
        new Thread(){
            @Override
            public void run() {
                //发送请求
                String speed = GenerateJsonUtil.GenerateSimple(1);
                String Carspeed = HttpUtils.doPost(HTTP + HTTPGETCARSPEED, speed);
                try {
                    getCarSpeed = ResolveJson.ResolveGetCatSpeed(Carspeed);
                    Message msg=new Message();
                    msg.what=SPEED1;
                    handler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                super.run();
            }
        }.start();
        //2
        new Thread(){
            @Override
            public void run() {
                //发送请求
                String speed = GenerateJsonUtil.GenerateSimple(2);
                String Carspeed = HttpUtils.doPost(HTTP + HTTPGETCARSPEED, speed);
                try {
                    getCarSpeed = ResolveJson.ResolveGetCatSpeed(Carspeed);
                    Message msg=new Message();
                    msg.what=SPEED2;
                    handler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                super.run();
            }
        }.start();
        //3
        new Thread(){
            @Override
            public void run() {
                //发送请求
                String speed = GenerateJsonUtil.GenerateSimple(1);
                String Carspeed = HttpUtils.doPost(HTTP + HTTPGETCARSPEED, speed);
                try {
                    getCarSpeed = ResolveJson.ResolveGetCatSpeed(Carspeed);
                    Message msg=new Message();
                    msg.what=SPEED3;
                    handler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                super.run();
            }
        }.start();
        //4
        new Thread(){
            @Override
            public void run() {
                //发送请求
                String speed = GenerateJsonUtil.GenerateSimple(1);
                String Carspeed = HttpUtils.doPost(HTTP + HTTPGETCARSPEED, speed);
                try {
                    getCarSpeed = ResolveJson.ResolveGetCatSpeed(Carspeed);
                    Message msg=new Message();
                    msg.what=SPEED4;
                    handler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                super.run();
            }
        }.start();
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
                if(myCarState1.getText().equals("20kM/h")){
                    myCarBitton1.setBackgroundResource(R.drawable.endmycar);
                    myCarState1.setText("停止");
                    new Thread(){
                        @Override
                        public void run() {
                            //发送请求
                            // String Caractionstart = GenerateJsonUtil.GenerateSetCarMove(1, "start");
                            String Caractionstop = GenerateJsonUtil.GenerateSetCarMove(1, "stop");
                            //  String actionstart = HttpUtils.doPost(HTTP + HTTPSETCARMOVE, Caractionstart);

                            String actionstop = HttpUtils.doPost(HTTP + HTTPSETCARMOVE, Caractionstop);
                            try {
                                //simplestart = ResolveJson.ResolveSimple(actionstart);
                                simplestop = ResolveJson.ResolveSimple(actionstop);
                                Message msg=new Message();
                                msg.what=ACTION1;
                                handler.sendMessage(msg);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            super.run();
                        }
                    }.start();
                }else{
                    myCarBitton1.setBackgroundResource(R.drawable.startmycar);
                    Message msg=new Message();
                    msg.what=SPEED1;
                    handler.sendMessage(msg);
                    new Thread(){
                        @Override
                        public void run() {
                            //发送请求
                            String Caractionstart = GenerateJsonUtil.GenerateSetCarMove(1, "start");
                            String actionstart = HttpUtils.doPost(HTTP + HTTPSETCARMOVE, Caractionstart);
                            try {
                                simplestart = ResolveJson.ResolveSimple(actionstart);
                                Message msg=new Message();
                                msg.what=ACTIONstart1;
                                handler.sendMessage(msg);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            super.run();
                        }
                    }.start();
                }
            }
        });
        myCarBitton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(myCarState2.getText().equals("20kM/h")){
                    myCarBitton2.setBackgroundResource(R.drawable.endmycar);
                    myCarState2.setText("停止");
                    new Thread(){
                        @Override
                        public void run() {
                            //发送请求
                            // String Caractionstart = GenerateJsonUtil.GenerateSetCarMove(1, "start");
                            String Caractionstop = GenerateJsonUtil.GenerateSetCarMove(2, "stop");
                            //  String actionstart = HttpUtils.doPost(HTTP + HTTPSETCARMOVE, Caractionstart);

                            String actionstop = HttpUtils.doPost(HTTP + HTTPSETCARMOVE, Caractionstop);
                            try {
                                //simplestart = ResolveJson.ResolveSimple(actionstart);
                                simplestop = ResolveJson.ResolveSimple(actionstop);
                                Message msg=new Message();
                                msg.what=ACTION2;
                                handler.sendMessage(msg);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            super.run();
                        }
                    }.start();
                }else{
                    myCarBitton2.setBackgroundResource(R.drawable.startmycar);
                    Message msg=new Message();
                    msg.what=SPEED2;
                    handler.sendMessage(msg);
                    new Thread(){
                        @Override
                        public void run() {
                            //发送请求
                            String Caractionstart = GenerateJsonUtil.GenerateSetCarMove(2, "start");
                            String actionstart = HttpUtils.doPost(HTTP + HTTPSETCARMOVE, Caractionstart);
                            try {
                                simplestart = ResolveJson.ResolveSimple(actionstart);
                                Message msg=new Message();
                                msg.what=ACTIONstart2;
                                handler.sendMessage(msg);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            super.run();
                        }
                    }.start();
                }
            }
        });
        myCarBitton3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(myCarState3.getText().equals("20kM/h")){
                    myCarBitton3.setBackgroundResource(R.drawable.endmycar);
                    myCarState3.setText("停止");
                    new Thread(){
                        @Override
                        public void run() {
                            //发送请求
                            // String Caractionstart = GenerateJsonUtil.GenerateSetCarMove(1, "start");
                            String Caractionstop = GenerateJsonUtil.GenerateSetCarMove(3, "stop");
                            //  String actionstart = HttpUtils.doPost(HTTP + HTTPSETCARMOVE, Caractionstart);

                            String actionstop = HttpUtils.doPost(HTTP + HTTPSETCARMOVE, Caractionstop);
                            try {
                                //simplestart = ResolveJson.ResolveSimple(actionstart);
                                simplestop = ResolveJson.ResolveSimple(actionstop);
                                Message msg=new Message();
                                msg.what=ACTION3;
                                handler.sendMessage(msg);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            super.run();
                        }
                    }.start();
                }else{
                    myCarBitton3.setBackgroundResource(R.drawable.startmycar);
                    Message msg=new Message();
                    msg.what=SPEED3;
                    handler.sendMessage(msg);
                    new Thread(){
                        @Override
                        public void run() {
                            //发送请求
                            String Caractionstart = GenerateJsonUtil.GenerateSetCarMove(3, "start");
                            String actionstart = HttpUtils.doPost(HTTP + HTTPSETCARMOVE, Caractionstart);
                            try {
                                simplestart = ResolveJson.ResolveSimple(actionstart);
                                Message msg=new Message();
                                msg.what=ACTIONstart3;
                                handler.sendMessage(msg);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            super.run();
                        }
                    }.start();
                }
            }
        });
        myCarBitton4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(myCarState4.getText().equals("20kM/h")){
                    myCarBitton4.setBackgroundResource(R.drawable.endmycar);
                    myCarState4.setText("停止");
                    new Thread(){
                        @Override
                        public void run() {
                            //发送请求
                            // String Caractionstart = GenerateJsonUtil.GenerateSetCarMove(1, "start");
                            String Caractionstop = GenerateJsonUtil.GenerateSetCarMove(4, "stop");
                            //  String actionstart = HttpUtils.doPost(HTTP + HTTPSETCARMOVE, Caractionstart);

                            String actionstop = HttpUtils.doPost(HTTP + HTTPSETCARMOVE, Caractionstop);
                            try {
                                //simplestart = ResolveJson.ResolveSimple(actionstart);
                                simplestop = ResolveJson.ResolveSimple(actionstop);
                                Message msg=new Message();
                                msg.what=ACTION4;
                                handler.sendMessage(msg);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            super.run();
                        }
                    }.start();
                }else{
                    myCarBitton4.setBackgroundResource(R.drawable.startmycar);
                    Message msg=new Message();
                    msg.what=SPEED4;
                    handler.sendMessage(msg);
                    new Thread(){
                        @Override
                        public void run() {
                            //发送请求
                            String Caractionstart = GenerateJsonUtil.GenerateSetCarMove(4, "start");
                            String actionstart = HttpUtils.doPost(HTTP + HTTPSETCARMOVE, Caractionstart);
                            try {
                                simplestart = ResolveJson.ResolveSimple(actionstart);
                                Message msg=new Message();
                                msg.what=ACTIONstart4;
                                handler.sendMessage(msg);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            super.run();
                        }
                    }.start();
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
