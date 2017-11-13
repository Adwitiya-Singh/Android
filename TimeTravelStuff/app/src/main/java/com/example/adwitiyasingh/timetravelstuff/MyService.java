package com.example.adwitiyasingh.timetravelstuff;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
    public MyService() {
    }
public final String TAG = "Service"
;
    @Override
    public void onCreate() {
        super.onCreate();
Log.d(TAG,"OnCreate");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        long timenow = System.currentTimeMillis();
        Log.d(TAG, "onStartCommand: ");
        long timething = timenow;
        while (System.currentTimeMillis() != timenow + 10000) {

            if (System.currentTimeMillis() == timething + 1000) {
                Log.d(TAG, "Second Passed");
                timething =System.currentTimeMillis();
            }
        }
        Toast.makeText(this,"Done.",Toast.LENGTH_SHORT).show();
        return START_STICKY;

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
