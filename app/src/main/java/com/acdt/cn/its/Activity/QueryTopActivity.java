package com.acdt.cn.its.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Instrumentation;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.acdt.cn.its.R;
import com.acdt.cn.its.Utils.GenerateJsonUtil;
import com.acdt.cn.its.Utils.HttpUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.acdt.cn.its.Utils.ContantsValue;
import com.acdt.cn.its.Utils.ResolveJson;
import com.acdt.cn.its.vo.GetCarAccountBalance;

import org.json.JSONException;

import static android.content.ContentValues.TAG;

public class QueryTopActivity extends Activity {

    private static final int NUMBER = 101;
    private static final int MONEY = 102;
    private EditText MyCarQuerySerial;
    private Button MyCarQery;
    private EditText MyCarTopUpSerial;
    private Button MyCarTopUp;
    private Button MyCarblack1;
    private EditText MyCarTopUpMoney;
Handler handler=new Handler(){
    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case NUMBER:
                Toast.makeText(QueryTopActivity.this, "你的账户余额为"+getCarAccountBalance.getBanlance()+"元", Toast.LENGTH_SHORT).show();
                break;
            case MONEY:
                if(simple.equals("ok")){
                    Toast.makeText(QueryTopActivity.this, "充值成功", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(QueryTopActivity.this, "充值失败", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        super.handleMessage(msg);
    }
};
    private GetCarAccountBalance getCarAccountBalance;
    private String simple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_querytop);
        //初始化数据
        intiUI();
    }

    private void intiUI() {
        MyCarQuerySerial = (EditText) findViewById(R.id.MyCarQuerySerial);
        MyCarQery = (Button) findViewById(R.id.MyCarQery);
        MyCarTopUpSerial = (EditText) findViewById(R.id.MyCarTopUpSerial);
        MyCarTopUpMoney = (EditText) findViewById(R.id.MyCarTopUpMoney);
        MyCarTopUp = (Button) findViewById(R.id.MyCarTopUp);

        MyCarblack1 = (Button) findViewById(R.id.MyCarblack1);
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
        MyCarQery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //根据小车id查询小车账户信息

                intiAccount();
            }
        });
        MyCarTopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //根据小车id进行充值
                dialog(Integer.parseInt(MyCarTopUpSerial.getText().toString()),MyCarTopUpMoney.getText().toString());

            }
        });
    }

    //三个按钮的对话框
    private void dialog(int number,String money){
        AlertDialog.Builder builder = new AlertDialog.Builder(QueryTopActivity.this);
        builder.setIcon(R.drawable.rmb);
        builder.setTitle("小车账户充值");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss //获取当前时间
        Date date = new Date(System.currentTimeMillis());
       //time1.setText("Date获取当前日期时间"+simpleDateFormat.format(date));
        builder.setMessage("在"+simpleDateFormat.format(date)+"将要给"+number+"号充值"+money+"元");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                intiTop();
            }
        });
        builder.setNeutralButton("忽略", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }
    private void intiTop() {
        new Thread(){
            @Override
            public void run() {
                //发送请求
                int number=Integer.parseInt(MyCarTopUpSerial.getText().toString());
                String money=GenerateJsonUtil.GenerateSetCarAccountRecharge(number,Integer.parseInt(MyCarTopUpMoney.getText().toString()));
                String doPost = HttpUtils.doPost(ContantsValue.HTTP + ContantsValue.HTTPSETCARACCOUNTRECHARGE, money);
              //  Log.i(TAG, "money: "+money);
                try {
                    simple = ResolveJson.ResolveSimple(doPost);
                    Message msg=new Message();
                    msg.what=MONEY;
                    handler.sendMessage(msg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                super.run();
            }
        }.start();
    }

    private void intiAccount() {
        new Thread(){
            @Override
            public void run() {
                //发送请求

                int number=Integer.parseInt(MyCarQuerySerial.getText().toString());

                String simple = GenerateJsonUtil.GenerateSimple(number);
                String doPost = HttpUtils.doPost(ContantsValue.HTTP + ContantsValue.HTTPGETCARACCOUNTBALANCE, simple);
               // Log.i(TAG, "run: "+doPost);

                try {
                    getCarAccountBalance = ResolveJson.ResolveGetCarAccountBalance(doPost);
                   // Log.i(TAG, "run: "+getCarAccountBalance);
                    Message msg=new Message();
                    msg.what=NUMBER;
                    handler.sendMessage(msg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                super.run();
            }
        }.start();
    }

}
