package com.acdt.cn.its.Activity;

import android.app.Activity;
import android.app.Instrumentation;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.acdt.cn.its.R;
import com.acdt.cn.its.Utils.ContantsValue;
import com.acdt.cn.its.Utils.GenerateJsonUtil;
import com.acdt.cn.its.Utils.HttpUtils;
import com.acdt.cn.its.Utils.ResolveJson;
import com.acdt.cn.its.vo.GetParkFree;
import com.acdt.cn.its.vo.GetParkRate;
import com.acdt.cn.its.vo.ParkFreeId;

import org.json.JSONException;

import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class ParkQeryActivity extends Activity {

    private static final int CARRATE = 101;
    private static final int SETRATE = 102;
    private static final int CARPARKING = 103;
    private GetParkRate getParkRate;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case  CARRATE:
                    Toast.makeText(ParkQeryActivity.this, "当前费率"+getParkRate.getMoney()+"元/次", Toast.LENGTH_SHORT).show();
                    count.setText(getParkRate.getMoney()+"元/次");
                    break;
                case  SETRATE:
                    if(simple.equals("ok")){
                        Toast.makeText(ParkQeryActivity.this, "费率修改成功", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(ParkQeryActivity.this, "费率修改失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case  CARPARKING:
                    for(int i=0;i<getParkFree.getParkFreeIdLit().size();i++){
                        parkFreeId = getParkFree.getParkFreeIdLit().get(i);
                        if(parkFreeId.getParkFreeId()==2){
                            car2.setBackgroundResource(R.drawable.parknotfree);

                        }else{
                            car2.setBackgroundResource(R.drawable.parkfree);
                        }
                        if(parkFreeId.getParkFreeId()==1){
                            car1.setBackgroundResource(R.drawable.parknotfree);
                            continue;
                        } else {
                            car1.setBackgroundResource(R.drawable.parkfree);
                        }
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    };
    private Button park_rate;
    private TextView count;
    private EditText park_startrate;
    private EditText park_unit;
    private Button park_update;
    private String simple;
    private Button park_query_carid;
    private GetParkFree getParkFree;
    private ParkFreeId parkFreeId;
    private ImageView car1;
    private ImageView car2;
    private Button myCarblack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parkquery);
        park_rate = (Button) findViewById(R.id.park_rate);
        count = (TextView) findViewById(R.id.count);
        park_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查询费率
                intrate();
            }
        });
        //修改费率
        park_startrate = (EditText) findViewById(R.id.park_startrate);
        park_unit = (EditText) findViewById(R.id.park_unit);
        park_update = (Button) findViewById(R.id.park_update);
        park_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取数据
                intiUpdate();
            }
        });
        park_query_carid = (Button) findViewById(R.id.park_query_carid);
        car1 = (ImageView) findViewById(R.id.car1);
        car2 = (ImageView) findViewById(R.id.car2);
        park_query_carid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取空闲车位
                intiparkCarId();
            }
        });
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
    }

    private void intiparkCarId() {
        new Thread(){
            @Override
            public void run() {
                String doPost = HttpUtils.doPost(ContantsValue.HTTP + ContantsValue.HTTPGETPARKFREE, null);
                //Log.i(TAG, "run: 11"+doPost);
                try {
                    getParkFree = ResolveJson.ResolveGetParkFree(doPost);

                    Message msg=new Message();
                    msg.what=CARPARKING;
                    handler.sendMessage(msg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                super.run();
            }
        }.start();
    }

    private void intiUpdate() {
        new Thread(){
            @Override
            public void run() {
                int money=Integer.parseInt(park_startrate.getText().toString());
                String type=park_unit.getText().toString();
                String parkRate = GenerateJsonUtil.GenerateSetParkRate(type, money);
                String doPost = HttpUtils.doPost(ContantsValue.HTTP + ContantsValue.HTTPSETPARKRATE, parkRate);
                try {
                    simple = ResolveJson.ResolveSimple(doPost);
                    Message msg=new Message();
                    msg.what=SETRATE;
                    handler.sendMessage(msg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                super.run();
            }
        }.start();
    }

    private void intrate() {
        new Thread(){
            @Override
            public void run() {
                String doPost = HttpUtils.doPost(ContantsValue.HTTP + ContantsValue.HTTPGETPARKRATE, null);

                try {
                    getParkRate = ResolveJson.ResolveGetParkRate(doPost);
                    Log.i(TAG, "getParkRate"+getParkRate.toString());
                    Message msg=new Message();
                    msg.what=CARRATE;
                    handler.sendMessage(msg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                super.run();
            }
        }.start();
    }
}
