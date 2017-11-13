package com.example.adwitiyasingh.hardwaresensors;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorEventListener2;
import android.hardware.SensorManager;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "Sensors";
    ConstraintLayout constr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        constr = (ConstraintLayout) findViewById(R.id.constr);

        SensorManager manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> mySensors = manager.getSensorList(Sensor.TYPE_ALL);

        Sensor accSensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        for (Sensor sensor : mySensors) {
            Log.d(TAG, "NAME: " + sensor.getName());
            Log.d(TAG, "VENDOR: " + sensor.getVendor());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
                Log.d(TAG, "TYPE: " + sensor.getStringType());
            }

        }
        SensorEventListener sel = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
               Float vals[]  = new Float[3];
                for (int i = 0; i < event.values.length; i++) {
//                    Log.d(TAG, "onSensorChanged: a" + i + " " + event.values[i]);
                    vals[i] = event.values[i];
                }
                constr.setBackgroundColor(
                        Color.rgb(
                                accel2color(vals[0]),
                                accel2color(vals[1]),
                                accel2color(vals[2])));

//                Log.d(TAG, "onSensorChanged: **********************");


            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        manager.registerListener(sel, accSensor, manager.SENSOR_DELAY_NORMAL);


    }

    int accel2color(float acccel) {
        return (int) (((acccel + 12) / 24) * 255);
    }
}

