package com.example.adwitiyasingh.broadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ChargerConnectedListener extends BroadcastReceiver {

    public static final String TAG = "BR";

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)) {
            Log.d(TAG, "onReceive: Power Connected");
        } else if (intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)) {
            Log.d(TAG, "onReceive: Power Disconnnected");


        }
    }
}
