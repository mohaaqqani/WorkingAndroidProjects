package com.example.omer.smstry3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button  smsBtn  =   (Button)    findViewById(R.id.btn_send_SMS);
        smsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SMSUtils.sendSMS(getBaseContext(),"+923422909081","hello");
            }
        });
    }
}
