package com.example.adwitiyasingh.filestorage;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "FILES";
    public static final String MY_FILE_NAME = "MY_FILE";
    public static final int PERM_REQ_CODE = 1234;
    TextView tvfile;
    EditText etdata;
    Button btnadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        Log.d(TAG, "FilesDir: " + getFilesDir().getPath());
//        Log.d(TAG, "CacheDir: " + getCacheDir().getPath());
//        try{
//            Log.d(TAG, "ExtFilesDir: " + getExternalFilesDir(null).getPath());
//            Log.d(TAG, "ExtCacheDir: " + getExternalCacheDir().getPath());
//
//        }catch(Exception ex){
//            Log.e(TAG, "onCreate: Could not find external directory", ex);


        tvfile = (TextView) findViewById(R.id.tvfile);
        etdata = (EditText) findViewById(R.id.etadd);
        btnadd = (Button) findViewById(R.id.btnadd);
        tvfile.setMovementMethod(new ScrollingMovementMethod());


        tvfile.setText(readFromFile(MY_FILE_NAME));

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int permission = ContextCompat.checkSelfPermission(
                        MainActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE);

                if (permission == PackageManager.PERMISSION_GRANTED) {
                } else {
                    ActivityCompat.requestPermissions(
                            MainActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            PERM_REQ_CODE);
                }
                writeToFile(MY_FILE_NAME, etdata.getText().toString());
                tvfile.setText(readFromFile(MY_FILE_NAME));
            }
        });
        btnadd.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


                File f = new File(Environment.getExternalStorageDirectory(), MY_FILE_NAME);
                if (f.exists()) {
                    f.delete();
                    tvfile.setText("");
                } else {
                    Log.w(TAG, "readFromFile: " + f.getPath() + " Does not exist");
                }
                return true;
            }
        });

    }

    void writeToFile(String myFileName, String data) {
        File f = new File(Environment.getExternalStorageDirectory(), myFileName);
        if (f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                Log.e(TAG, "writeToFile: Could not create file.");
            }
        }
        FileOutputStream ofstream = null;
        try {
            ofstream = new FileOutputStream(f, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        try {
            ofstream.write(data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    String readFromFile(String myFileName) {
        File f = new File(Environment.getExternalStorageDirectory(), myFileName);
        if (f.exists()) {
            String data = "";
            try {
                FileInputStream ifstream = new FileInputStream(f);
                InputStreamReader streamReader = new InputStreamReader(ifstream);
                BufferedReader br = new BufferedReader(streamReader);

                StringBuilder builder = new StringBuilder();
                String buf = "";
                while ((buf = br.readLine()) != null) {
                    builder.append(buf);
                }
                data = builder.toString() + "\n";
            } catch (IOException e) {
                e.printStackTrace();
            }


            return data;
        } else {
            Log.w(TAG, "readFromFile: " + f.getPath() + " Does not exist");
            return "";
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        if (requestCode == PERM_REQ_CODE) {
            for (int i = 0; i < permissions.length; i++) {
                if (permissions[i].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        writeToFile(MY_FILE_NAME,etdata.getText().toString());
                    } else {
                        Toast.makeText(this, "user deined permission", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


}

