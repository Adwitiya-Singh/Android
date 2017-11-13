package com.example.adwitiyasingh.weatherwithdrawer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity {
    RadioGroup temp;
    public static final String MY_PREF = "MY_PREF";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);



        RadioButton rbtn;
        Switch drkmode;
        if(MainActivity.temp=='c'){
            rbtn = (RadioButton) findViewById(R.id.rbtncelcius);
            rbtn.toggle();
        }else if(MainActivity.temp=='f'){
            rbtn = (RadioButton) findViewById(R.id.rbtnfarheneit);
            rbtn.toggle();
        }else if (MainActivity.temp== 'k'){
            rbtn = (RadioButton) findViewById(R.id.rbtnkelvin);
            rbtn.toggle();
        }
        temp = (RadioGroup) findViewById(R.id.rgtemp);
        temp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rbtncelcius:
                        MainActivity.temp = 'c';
                        break;
                    case R.id.rbtnfarheneit:
                        MainActivity.temp = 'f';

                        break;
                    case R.id.rbtnkelvin:
                        MainActivity.temp = 'k';
                        break;
                }
            }
        });

    }


}
