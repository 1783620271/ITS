package com.acdt.cn.its.Activity;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.acdt.cn.its.R;
import com.acdt.cn.its.Utils.ContantsValue;
import com.acdt.cn.its.Utils.SpUtils;

import static android.content.ContentValues.TAG;

public class SetActivity extends Activity {

    private  EditText IPone;
    private EditText IPtwo;
    private EditText IPthree;
    private EditText IPfour;
    private Button SetIP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        IPone = (EditText)findViewById(R.id.IPone);
        IPtwo = (EditText) findViewById(R.id.IPtwo);
        IPthree = (EditText) findViewById(R.id.IPthree);
        IPfour = (EditText) findViewById(R.id.IPfour);
        //获取IP地址进行拆分
        String[] strings=ContantsValue.IP.split("\\.");
            IPone.setText(strings[0]);
            IPtwo.setText(strings[1]);
            IPthree.setText(strings[2]);
            IPfour.setText(strings[3]);

        //获取当前ip地址

        SetIP = (Button) findViewById(R.id.SetIP);
        SetIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String IP1=IPone.getText()+"."+IPtwo.getText()+"."+IPthree.getText()+"."+
                        IPfour.getText();

                if(IP1.matches("((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)")){
                    Toast.makeText(SetActivity.this, "IP地址更改成功", Toast.LENGTH_SHORT).show();
                    SpUtils.putString(SetActivity.this,ContantsValue.IPADDRESS,IP1);

                }else{
                    Toast.makeText(SetActivity.this, "IP地址更改失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
