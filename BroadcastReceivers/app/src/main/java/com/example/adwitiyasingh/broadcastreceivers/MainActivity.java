package com.example.adwitiyasingh.broadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout layout;
    BroadcastReceiver myReceiver;
    IntentFilter ifil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = (ConstraintLayout) findViewById(R.id.constl);
         myReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)) {
                    layout.setBackgroundColor(Color.BLACK);
                } else if (intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)) {
                    layout.setBackgroundColor(Color.GREEN);


                }
            }
        };
         ifil = new IntentFilter();
        ifil.addAction(Intent.ACTION_POWER_CONNECTED);
        ifil.addAction(Intent.ACTION_POWER_DISCONNECTED);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(myReceiver,ifil);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(myReceiver);
        super.onPause();
    }
}
