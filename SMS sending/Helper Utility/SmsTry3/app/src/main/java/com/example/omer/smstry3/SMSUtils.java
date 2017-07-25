package com.example.omer.smstry3;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.telephony.SmsManager;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by OMER on 7/25/2017.
 */

public class SMSUtils extends BroadcastReceiver{
    
    public static final String  SENT_SMS_ACTION_NAME    =   "SMS_SENT";
    public static final String  DELIVERED_SMS_ACTION_NAME   =   "SMS_DELIVERED";
    
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(SENT_SMS_ACTION_NAME)){
            switch (getResultCode()){
                case Activity.RESULT_OK:    //  SMS Sent
                    Toast.makeText(context, "SMS SENT", Toast.LENGTH_LONG).show();
                    break;
                case SmsManager.RESULT_ERROR_GENERIC_FAILURE:   //  Generic failure
                    Toast.makeText(context, "SMS NOT SENT", Toast.LENGTH_LONG).show();
                    break;
                case SmsManager.RESULT_ERROR_NO_SERVICE:        //  No Service
                    Toast.makeText(context, "SMS NOT SENT DUE TO NO SERVICE", Toast.LENGTH_LONG).show();
                    break;
                case SmsManager.RESULT_ERROR_NULL_PDU:          //  Null PDU
                    Toast.makeText(context, "SMS NOT SENT DUE TO NULL PDU", Toast.LENGTH_LONG).show();
                    break;
                case SmsManager.RESULT_ERROR_RADIO_OFF:     //  Radio off
                    Toast.makeText(context, "SMS NOT SENT DUE TO NO RADIO", Toast.LENGTH_LONG).show();
                    break;

            }
        }   else if (intent.getAction().equals(DELIVERED_SMS_ACTION_NAME)){
            switch (getResultCode()){
                case Activity.RESULT_OK:
                    Toast.makeText(context, "SMS RECEIVED", Toast.LENGTH_LONG).show();
                    break;
                case Activity.RESULT_CANCELED:
                    Toast.makeText(context, "SMS Not Received", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }

    public static boolean   canSendSMS(Context  context){
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_TELEPHONY);
    }

    public static void  sendSMS(final Context   context,String  phoneNumber,    String  message){
        if (!canSendSMS(context)){
            Toast.makeText(context, "Can not send message", Toast.LENGTH_LONG).show();
            return;
        }

        PendingIntent   sendPI  =   PendingIntent.getBroadcast(context,0,new Intent(SENT_SMS_ACTION_NAME),0);
        PendingIntent   deliveredPI =   PendingIntent.getBroadcast(context,0,new Intent(DELIVERED_SMS_ACTION_NAME),0);

        final SMSUtils  smsUtils    =   new SMSUtils();

//        Register  for Sending and Delivery
        context.registerReceiver(smsUtils,new IntentFilter(SMSUtils.SENT_SMS_ACTION_NAME));
        context.registerReceiver(smsUtils,new IntentFilter(DELIVERED_SMS_ACTION_NAME));

        SmsManager  sms =   SmsManager.getDefault();
        ArrayList<String>   parts   =   sms.divideMessage(message);

        ArrayList<PendingIntent>    sendList    =   new ArrayList<>();
        sendList.add(sendPI);

        ArrayList<PendingIntent>    deliverList =   new ArrayList<>();
        deliverList.add(deliveredPI);

        sms.sendMultipartTextMessage(phoneNumber,null,parts,sendList,deliverList);

//        we unsubscribed   in  10  seconds
        /*new Handler() {
            @Override
            public void publish(LogRecord record) {

            }

            @Override
            public void flush() {

            }

            @Override
            public void close() throws SecurityException {

            }
        }.postDelayed(new Runnable(){
            @Override
            public void run() {
                context.unregisterReceiver(smsUtils);
            }
        },1000);*/
    }
}
