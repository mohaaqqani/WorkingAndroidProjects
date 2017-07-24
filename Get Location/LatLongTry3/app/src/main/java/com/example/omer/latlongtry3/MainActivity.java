package com.example.omer.latlongtry3;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements com.google.android.gms.location.LocationListener {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { android.Manifest.permission.ACCESS_COARSE_LOCATION },1);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.ACCESS_FINE_LOCATION },1);
        }

        if (ContextCompat.checkSelfPermission(this,Manifest.permission.INTERNET)!=  PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new  String[]{Manifest.permission.INTERNET},1);
        }

        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Log.d(TAG, "onCreate: Loc   :   "+location);
        if(location==null){
            location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Log.d(TAG, "onCreate: Location  "+  location );
        }
    }


    @Override
    public void onLocationChanged(Location location) {
        
    }
}
