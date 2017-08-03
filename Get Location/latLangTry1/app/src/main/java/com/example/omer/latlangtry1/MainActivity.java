package com.example.omer.latlangtry1;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    GPSTracker  gpsTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

        if (ContextCompat.checkSelfPermission(this,Manifest.permission.INTERNET)
                !=  PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.INTERNET}, 1);
        }

//        Log.d(TAG, "onCreate: Location  "   +  gpsTracker.getLocation() );
        gpsTracker  =   new GPSTracker(MainActivity.this);
//        Log.d(TAG, "onCreate: Latitude  :   "+gpsTracker.getLatitude());
//        Log.d(TAG, "onCreate: Longitude :   "+gpsTracker.getLongitude());
        Log.d(TAG, "onCreate: Location  :   "+gpsTracker.getLocation().toString());
    }
}
