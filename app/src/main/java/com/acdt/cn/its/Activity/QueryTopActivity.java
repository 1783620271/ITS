package com.acdt.cn.its.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Instrumentation;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.acdt.cn.its.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class QueryTopActivity extends Activity {

    private EditText MyCarQuerySerial;
    private Button MyCarQery;
    private EditText MyCarTopUpSerial;
    private Button MyCarTopUp;
    private Button MyCarblack1;
    private EditText MyCarTopUpMoney;

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

                intiTop();
            }
        });
    }

    //三个按钮的对话框
    private void dialog(int number,int money){
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
        Toast.makeText(QueryTopActivity.this, "1111111111", Toast.LENGTH_SHORT).show();
        dialog(1,40);
    }

    private void intiAccount() {

    }

}
