package com.example.adwitiyasingh.weatherwithdrawer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {
    static Boolean drkmode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sPref = getApplicationContext().getSharedPreferences("DARKMODE",MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = sPref.edit();
        if(sPref.getBoolean("drkmode", false)){


        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }
}
