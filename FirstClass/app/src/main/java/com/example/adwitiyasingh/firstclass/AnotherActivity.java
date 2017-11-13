package com.example.adwitiyasingh.firstclass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AnotherActivity extends AppCompatActivity {
    Button btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);
        btn2=(Button) findViewById(R.id.btnback);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(AnotherActivity.this, MainActivity.class);
                i2.putExtra("returned","Welcome back to the first Activity!");
                startActivity(i2);
            }
        });

    }


}
