package com.example.adwitiyasingh.alarmmanager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static android.app.PendingIntent.FLAG_ONE_SHOT;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);

        PendingIntent pi = PendingIntent.getActivity(
                this,
                111,
                new Intent(this, MainActivity.class),
                FLAG_ONE_SHOT

        );

        manager.setExact(
                AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime() +5000,
                pi
        );
    }
}
