package com.example.adwitiyasingh.firebase;

import android.app.Notification;
import android.app.NotificationManager;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by adwitiyasingh on 4/27/17.
 */

public class MyMessagingService extends FirebaseMessagingService {
    public static final String TAG = "FireBase";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d(TAG, "onMessageReceived: messageID : " + remoteMessage.getMessageId());
        Log.d(TAG, "onMessageReceived: data  : " + remoteMessage.getData());
        Log.d(TAG, "onMessageReceived: notification : "+ remoteMessage.getNotification());

         Notification.Builder notifbldr = new Notification.Builder(this);
        notifbldr.setContentTitle(remoteMessage.getNotification().getTitle());
        notifbldr.setContentTitle(remoteMessage.getNotification().getBody());
        notifbldr.setSmallIcon(R.mipmap.ic_launcher);
        notifbldr.setPriority(Notification.PRIORITY_HIGH);
        if (Build.VERSION.SDK_INT >= 21) notifbldr.setVibrate(new long[]{1000,1000,1000,1000});

        NotificationManager notMan = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notMan.notify(69,notifbldr.build());
          }
}
