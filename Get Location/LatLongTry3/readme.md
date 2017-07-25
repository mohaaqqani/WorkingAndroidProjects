In maven file add
compile 'com.google.android.gms:play-services-location:11.0.2'


implement com.google.android.gms.location.LocationListener in activity that is intended to impelent getlastlocation function

public class MainActivity extends AppCompatActivity implements com.google.android.gms.location.LocationListener {

}


But ask for permission before calling this function

LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);



Code for permission

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
