package com.example.adwitiyasingh.sharedprefs;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String MY_PREF = "MY_PREF";
    public static final String  KEY_RESULT = "KEY_RESULT";
    public static final String INT_ONE = "INT_ONE";
    public static final String INT_TWO = "INT_TWO";
EditText etone, ettwo;
    TextView tvResult;
    Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SharedPreferences sPref = getSharedPreferences(MY_PREF,MODE_PRIVATE);
        etone = (EditText) findViewById(R.id.etnum1);
        ettwo = (EditText) findViewById(R.id.etnum2);
        tvResult = (TextView) findViewById(R.id.tvResult);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        tvResult.setText(String.valueOf(sPref.getInt(KEY_RESULT,0)));
        etone.setText(String.valueOf(sPref.getInt(INT_ONE,0)));
        ettwo.setText(String.valueOf(sPref.getInt(INT_TWO,0)));
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int one =  Integer.valueOf(etone.getText().toString());

                  int two =  Integer.valueOf(ettwo.getText().toString());
                int result = one+two;
                tvResult.setText(
                        String.valueOf(result));
                prefEditor.putInt(INT_ONE, one);
                prefEditor.putInt(INT_TWO, two);
                prefEditor.putInt(KEY_RESULT,result);
                prefEditor.apply();
            }
        });




    }
}
