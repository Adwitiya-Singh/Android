package com.example.adwitiyasingh.location;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "LOCATION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int coarseperm = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        int fineperm = ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION);

        if (fineperm != PackageManager.PERMISSION_GRANTED || coarseperm != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {
                   Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
            },911);
        }else{
            getLocationUpdates();
        }

    }
    public void getLocationUpdates(){
        LocationManager locman = (LocationManager) getSystemService(LOCATION_SERVICE);
        LocationListener loclist = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.d(TAG, "onLocationChanged: alt "+ location.getAltitude());
                Log.d(TAG, "onLocationChanged: lat "+ location.getLatitude());
                Log.d(TAG, "onLocationChanged: lon "+ location.getLongitude());
                Log.d(TAG, "onLocationChanged: brn "+ location.getBearing());
                Log.d(TAG, "onLocationChanged: spd "+ location.getSpeed());
                Log.d(TAG, "onLocationChanged: acc "+ location.getAccuracy());
                Log.d(TAG, "onLocationChanged: prv "+ location.getProvider());

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
        };
        //noinspection MissingPermission
        locman.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 20000,100,loclist);
        //noinspection MissingPermission
        locman.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,10,loclist);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode==911){
            for(int result : grantResults){
                if (result==PackageManager.PERMISSION_DENIED){
                    Toast.makeText(MainActivity.this,"Permission denied.",Toast.LENGTH_SHORT).show();
                }
            }
        }
        else{
            getLocationUpdates();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
