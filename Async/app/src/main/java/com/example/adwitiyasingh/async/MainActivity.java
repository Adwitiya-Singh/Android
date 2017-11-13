package com.example.adwitiyasingh.async;

import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    MyTask mtask;
    Button btnstart, btnstop;
    TextView tvprogress;
    ProgressBar pb;
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnstart = (Button) findViewById(R.id.btnstrt);
        btnstop = (Button) findViewById(R.id.btnstop);
        tvprogress = (TextView) findViewById(R.id.tvprogress);
        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mtask = new MyTask();
                mtask.execute(10);
                Log.d(TAG, "onClick: " + TAG);
            }
        });
        btnstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mtask.cont = false;
            }
        });
    }

    public class MyTask extends AsyncTask<Integer, Float, String> {
        public boolean cont = true;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Float... values) {
            tvprogress.setText("" + ((int) (values[0] * 100)) + "%");
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(Integer... params) {
            for (int i = 1; i <= params[0]; i++) {
                if (cont) {
                    oneLoop();
                    Log.d(TAG, "doInBackground: " + i);
                    publishProgress((float) i / (float) params[0]);
                }
            }
            if (cont) {
                return "Complete.";
            } else {
                return "Stopped.";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d(TAG, "onPostExecute: ");
            tvprogress.setText(s);
            btnstart.setText("Restart.");
            super.onPostExecute(s);
        }


    }

    static void oneLoop() {
        long timenow = System.currentTimeMillis();
        while (System.currentTimeMillis() - timenow < 1000) {

        }

    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }

}
