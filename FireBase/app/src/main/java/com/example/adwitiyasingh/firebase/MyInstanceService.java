package com.example.adwitiyasingh.firebase;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by adwitiyasingh on 4/27/17.
 */

public class MyInstanceService extends FirebaseInstanceIdService {
    public static final String TAG = "TOKEN";
    @Override
    public void onTokenRefresh() {

        String token = FirebaseInstanceId.getInstance().getToken();

        Log.d(TAG, "onTokenRefresh: Token : " + token);
        super.onTokenRefresh();

    }


}

