package com.example.adwitiyasingh.camera;


import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "CAMERA";

    SurfaceView svCamera;
    SurfaceHolder surfaceHolder;
    Camera camera = null;
    Button btnstart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        svCamera = (SurfaceView) findViewById(R.id.svcamera);
        btnstart = (Button) findViewById(R.id.btncam);

//btnstart.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View v) {
        int camPerm = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA);

        if (camPerm != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, 696);
        } else {
            getCameraStats();
        }

//    }
//});


    }

    public void getCameraStats() {
        camera = android.hardware.Camera.open();

        android.hardware.Camera.Parameters camparams = camera.getParameters();

        for (Camera.Size picsize : camparams.getSupportedPictureSizes()) {
            Log.d(TAG, "picsize: " + picsize.width + " ," + picsize.height);
        }
        for (Camera.Size prevsize : camparams.getSupportedPreviewSizes()) {
            Log.d(TAG, "prevsize: " + prevsize.width + " ," + prevsize.height);
        }
        for (Camera.Size vidsize : camparams.getSupportedVideoSizes()) {
            Log.d(TAG, "vidsize: " + vidsize.width + " ," + vidsize.height);
        }

        surfaceHolder = svCamera.getHolder();
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    camera.setPreviewDisplay(holder);
                    camera.setDisplayOrientation(90);
                    camera.startPreview();
                } catch (IOException e) {
                    Log.d(TAG, "surfaceCreated: Could not start preview");
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

                WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
                Display display = manager.getDefaultDisplay();

                if (holder.getSurface() == null) {
                    return;
                }
                try {
                    camera.stopPreview();
                } catch (Exception e) {
                    Log.e(TAG, "surfaceChanged: ", e);
                }
                if (display.getRotation() == Surface.ROTATION_0) {
                    camera.setDisplayOrientation(90);
                } else if (display.getRotation() == Surface.ROTATION_90) {
                    camera.setDisplayOrientation(0);
                } else if (display.getRotation() == Surface.ROTATION_180) {
                    camera.setDisplayOrientation(270);
                } else {
                    camera.setDisplayOrientation(180);
                }
                try {
                    camera.setPreviewDisplay(holder);
                    camera.startPreview();
                } catch (IOException e) {
                    Log.e(TAG, "surfaceChanged: ", e);
                }
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 696) {
            if (permissions[0].equals(Manifest.permission.CAMERA) && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCameraStats();
            } else {
                Toast.makeText(MainActivity.this, "You denied camera permission.", Toast.LENGTH_SHORT).show();

            }
        }
    }

    @Override
    protected void onDestroy() {
        if (camera != null) {
            camera.release();
        }
        super.onDestroy();
    }
}
