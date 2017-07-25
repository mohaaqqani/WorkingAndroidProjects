package com.example.omer.smstry2;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)!=  PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new  String[]{Manifest.permission.SEND_SMS},1);
        }

        Button  btnSendSms  = (Button) findViewById(R.id.btn_sms);
        btnSendSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMS("+1234567890","Hi hello");
            }
        });
    }

    private void    sendSMS(String  phoneNumer,String   message){
        SmsManager  smsManager  =   SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNumer,null,message,null,null);
    }
}
