package com.example.adwitiyasingh.permissionlistener;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    PermissionListener pl = new PermissionListener();
    Button btnask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnask= (Button) findViewById(R.id.btnrqst);

       final  String[] str = new String[2];
        str[0] = Manifest.permission.ACCESS_COARSE_LOCATION;
        str[1] = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        btnask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pl.AskForPermission(new PermissionListener.onPermissionResultListenr() {
                    @Override
                    public void onGranted(String Permsission) {
                        Toast.makeText(MainActivity.this, Permsission +" granted", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onDenied(String Permission) {
                        Toast.makeText(MainActivity.this, Permission+ " denied", Toast.LENGTH_SHORT).show();

                    }
                }, str, MainActivity.this);
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        for( int i=0 ; i<grantResults.length;i++) {
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                pl.opl.onGranted(permissions[i]);
            } else {
                pl.opl.onDenied(permissions[i]);
            }
        }


        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
