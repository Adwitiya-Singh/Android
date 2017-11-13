package com.example.adwitiyasingh.timetravelstuff;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by adwitiyasingh on 3/19/17.
 */
public class ThreadService extends IntentService {
    public static final String TAG = "intentService";
    public ThreadService() {
        super("name");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG, "onHandleIntent: ");
        long timenow = System.currentTimeMillis();
        long timething = timenow;
        while (System.currentTimeMillis() != timenow + 10000) {

            if (System.currentTimeMillis() == timething + 1000) {
                Log.d(TAG, "Second Passed");
                timething =System.currentTimeMillis();
            }
        }
        Toast.makeText(this,"Done.",Toast.LENGTH_SHORT).show();
    }
}
