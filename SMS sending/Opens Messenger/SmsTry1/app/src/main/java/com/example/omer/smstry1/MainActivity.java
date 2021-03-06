package com.example.omer.smstry1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static  String sPhoneNumber =  "+123456789" ;
    private static String   mSmsBody    =   "Hello World";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button  button_send_sms =   (Button)    findViewById(R.id.btn_sms);

        button_send_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + sPhoneNumber));
                intent.putExtra("sms_body", mSmsBody);
                startActivity(intent);
            }
        });
    }
}
