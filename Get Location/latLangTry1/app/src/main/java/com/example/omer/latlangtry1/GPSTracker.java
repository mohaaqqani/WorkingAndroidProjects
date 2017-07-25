package com.example.omer.latlangtry1;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;

/**
 * Created by OMER on 7/24/2017.
 */

public class GPSTracker extends Service implements LocationListener {

    private final Context   mContext;

//    Flag  for GPS status
    boolean isGPSEnabled    =   false;
//    Flag  for network Status
    boolean isNetworkEnabled    =   false;
//    Flag for  location    Status
    boolean canGetLocation  =   false;

    Location    location;
    double  longitude;
    double  latitude;

//    The minimum distance to change    updates in  meters  ie 10 meters
    private static final long   MIN_DISTANCE_CHANGE_FOR_UPDATES =   10;
//    The minimus time between updates in milliseconds
    private static final long   MIN_TIME_BW_UPDATES =   1000    *   60  *   1;
//    Declaring a location  manager
    protected LocationManager   locationManager;

    public GPSTracker(Context mContext) {
        this.mContext = mContext;
        getLocation();
    }

    public Location getLocation() {
        try{
            locationManager =   (LocationManager)   mContext.getSystemService(LOCATION_SERVICE);

//        Getting   GPS Status
            isGPSEnabled    =   locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
//        Getting   network Status
            isNetworkEnabled    =   locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isGPSEnabled   &&  !isNetworkEnabled){
//            no    network provider    is enabled
            }   else {
                this.canGetLocation =   true;
                if (isNetworkEnabled){
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,MIN_TIME_BW_UPDATES,MIN_DISTANCE_CHANGE_FOR_UPDATES,this);

                    if (locationManager !=  null){
                        location    =   locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location    !=  null){
                            latitude    =   location.getLatitude();
                            longitude   =   location.getLongitude();
                        }
                    }

//                if    GPS Enabled get lat/long    using   GPS services
                    if (isGPSEnabled){
                        if (location    ==  null){
                            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,MIN_TIME_BW_UPDATES,MIN_DISTANCE_CHANGE_FOR_UPDATES,this);

                            if (locationManager !=  null){
                                location    =   locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                                if (location    !=  null){
                                    latitude    =   location.getLatitude();
                                    longitude   =   location.getLongitude();
                                }
                            }
                        }
                    }
                }
            }
        }   catch (Exception    e){
            e.printStackTrace();
        }

        return location;
    }

    /**
     * Stop using GPS listener
     * Calling this function will stop using GPS in your app
     */
    public void stopUsingGPS(){
        if (locationManager !=  null){
            locationManager.removeUpdates(GPSTracker.this);
        }
    }

    /**
     * Function to get latitude
     */
    public double getLatitude() {
        if (location    !=  null){
            latitude    =   location.getLatitude();
        }
        return latitude;
    }

    /**
     * Function to get longitude
     */
    public double   getLongitude(){
        if (location    !=  null){
            longitude    =   location.getLongitude();
        }

        return longitude;
    }


    /**
     * Function to check GPS/wifi enabled
     *
     * @return boolean
     */
    public boolean  canGetLocation(){
        return  this.canGetLocation;
    }

    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
        // Setting Dialog Title
        alertDialog.setTitle("GPS is settings");
        // Setting Dialog Message
        alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?");
        // On pressing Settings button
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                mContext.startActivity(intent);
            }
        });
        // on pressing cancel button
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        // Showing Alert Message
        alertDialog.show();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
