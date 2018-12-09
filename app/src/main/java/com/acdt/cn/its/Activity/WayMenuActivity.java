package com.acdt.cn.its.Activity;

import android.app.Activity;
import android.app.Instrumentation;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.acdt.cn.its.R;
import com.acdt.cn.its.Utils.ContantsValue;
import com.acdt.cn.its.Utils.HttpUtils;
import com.acdt.cn.its.Utils.ResolveJson;
import com.acdt.cn.its.vo.GetAllSense;
import com.acdt.cn.its.vo.GetLightSenseValue;

import org.json.JSONException;

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
                    break;
                case  GUANG:
                    huanjing2.setText("光照强度"+getLightSenseValue.getDown()+","+getLightSenseValue.getUp());
                    break;
            }
            super.handleMessage(msg);
        }
    };
    private TextView huanjing1;
    private GetLightSenseValue getLightSenseValue;
    private TextView huanjing2;
    private Button guang;
    private Button MyCarblack1;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_way_menu);
        huanjing1 = (TextView) findViewById(R.id.huanjing1);
        huanjing2 = (TextView) findViewById(R.id.huanjing2);
        //空气质量
        intikong();
        //光照强度
        intiguang();
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

    private void intiguang() {
        guang = (Button) findViewById(R.id.guang);
        guang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        String doPost = HttpUtils.doPost(ContantsValue.HTTP + ContantsValue.HTTPGETLIGHTSENSEVALVE, null);
                        try {
                            getLightSenseValue = ResolveJson.ResolveGetLightSenseValue(doPost);
                            Message msg=new Message();
                            msg.what=GUANG;
                            handler.sendMessage(msg);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        super.run();
                    }
                }.start();
            }
        });


    }

    private void intikong() {
        sgay = (Button) findViewById(R.id.sgay);
        sgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });
    }
}
