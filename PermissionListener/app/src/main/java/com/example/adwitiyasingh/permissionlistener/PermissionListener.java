package com.example.adwitiyasingh.permissionlistener;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

/**
 * Created by adwitiyasingh on 4/5/17.
 */

public class PermissionListener {
    public static final int PERM_REQ_CODE = 69;
onPermissionResultListenr opl;

    interface onPermissionResultListenr {
        void onGranted(String Permsission);

        void onDenied(String Permission);

    }

    void AskForPermission(onPermissionResultListenr opl,
                          String[] permissions, Activity activity) {
        this.opl=opl;
//        int i=0;
//        while (i!=permissions.length-1) {
//                int permission = ContextCompat.checkSelfPermission(
//                        activity,
//                        permissions[i]);
//
//                if (permission == PackageManager.PERMISSION_GRANTED) {
//                    opl.onGranted(permissions[i]);
//                } else {
//                    String[] permnow = new String[1];
//                    permnow[0] = permissions[i];
                    ActivityCompat.requestPermissions(activity, permissions, PERM_REQ_CODE);
                }

//            i++;

//        }
//    }


}
