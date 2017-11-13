package com.example.adwitiyasingh.firstclass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn;
    TextView tv;
    public static final String TAG = "MAin";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button)findViewById(R.id.btnScndActivity);
        tv = (TextView) findViewById(R.id.textView2);
        String str = getIntent().getStringExtra("returned");
        if (str == null) {

            tv.setText("First Activity.");
        }
else {
            tv.setText(str);
        }

        Log.d(TAG, "onCreate: " + str);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AnotherActivity.class);
                startActivity(i);
            }
        });

    }
}
