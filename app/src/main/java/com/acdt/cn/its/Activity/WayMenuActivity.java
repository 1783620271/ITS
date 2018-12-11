package com.acdt.cn.its.Activity;

import android.app.Activity;
import android.app.Instrumentation;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import com.acdt.cn.its.R;
import com.acdt.cn.its.Utils.ContantsValue;
import com.acdt.cn.its.Utils.HttpUtils;
import com.acdt.cn.its.Utils.ResolveJson;
import com.acdt.cn.its.vo.GetAllSense;
import com.acdt.cn.its.vo.GetLightSenseValue;

import org.json.JSONException;

import java.util.Timer;
import java.util.TimerTask;

import static android.support.constraint.Constraints.TAG;
import static com.acdt.cn.its.Utils.ContantsValue.HTTP;
import static com.acdt.cn.its.Utils.ContantsValue.HTTPGETALLSENSE;

public class WayMenuActivity extends Activity {

    private static final int NUMBER1 = 101;
    private static final int GUANG = 102;
    private Button sgay;
    private GetAllSense getAllSense;

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case   NUMBER1:
                    huanjing1.setText("PM2.5:"+ getAllSense.getPm2_5()+"ug/m^3,温度:"+
                            getAllSense.getTemp()+"摄氏度"+"湿度:"+
                            getAllSense.getHumidity()+",CO2:"+ getAllSense.getHumidity()+
                            "ug/m^31");
                    if(getAllSense.getPm2_5()>200){
                        huanjing3.setText("pm2,5污染太严重了，请全服武装哦！\n");
                        viewvideo.setVisibility(View.VISIBLE);
                        viewvideo.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/raw/alarming"));
                        viewvideo.start();
                        viewvideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mp) {
                                mp.start();
                                mp.setLooping(true);
                            }
                        });
                    }else if(getAllSense.getTemp()>40||getAllSense.getTemp()<10){
                        huanjing3.setText("室外高温，请注意出行\n");
                        viewvideo.setVisibility(View.INVISIBLE);
                    }else if(getAllSense.getHumidity()>50||getAllSense.getHumidity()<0){
                        huanjing3.setText("湿度过高，请全服武装哦！\n");
                        viewvideo.setVisibility(View.INVISIBLE);
                    }
                    huanjing2.setText("光照强度"+getAllSense.getLightIntensity());
                    if(getAllSense.getLightIntensity()>2500||getAllSense.getLightIntensity()<1000) {
                        huanjing4.setText("太刺眼了");
                    }else {
                        huanjing4.setText("");
                    }

                    break;
            }
            super.handleMessage(msg);
        }
    };
    private TextView huanjing1;
    private TextView huanjing2;
    private Button guang;
    private Button MyCarblack1;
    private TextView huanjing3;
    private TextView huanjing4;
    private GetLightSenseValue getLightSenseValue1;
    private Integer a;
    private Integer b;
    private VideoView viewvideo;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_way_menu);
        viewvideo = (VideoView) findViewById(R.id.viewvideo);
        huanjing1 = (TextView) findViewById(R.id.huanjing1);
        huanjing2 = (TextView) findViewById(R.id.huanjing2);
        huanjing3 = (TextView) findViewById(R.id.huanjing3);
        huanjing4 = (TextView) findViewById(R.id.huanjing4);
        //定时刷新
        intiTime();
        //空气质量
        intikong();
        MyCarblack1 = (Button) findViewById(R.id.MyCarblack3);
        //点击按钮返回事件（物理返回）
        MyCarblack1.setOnClickListener(new View.OnClickListener() {
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
    }

    private void intiTime() {
        TimerTask task= new TimerTask() {

            @Override
            public void run()  {
                //空气质量
                try {
                    String textPM=HttpUtils.doPost(HTTP+HTTPGETALLSENSE,null);
                    // Log.i(TAG, "run: "+textPM);
                    getAllSense = ResolveJson.ResolveGetAllSense(textPM);
                    Log.i(TAG, "run: "+ getAllSense.toString());
                    Message msg=new Message();
                    msg.what=NUMBER1;
                    handler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        new Timer().schedule(task,0,1000);
    }
    private void intikong() {
        sgay = (Button) findViewById(R.id.sgay);
        sgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            String textPM=HttpUtils.doPost(HTTP+HTTPGETALLSENSE,null);
                            // Log.i(TAG, "run: "+textPM);
                            getAllSense = ResolveJson.ResolveGetAllSense(textPM);
                            Log.i(TAG, "run: "+ getAllSense.toString());
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
        });
    }
}
