package com.condingblocks.fragments;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tvCounter;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Button btnRed;
    Button btnBlue;
    int intBlue = 1;
    int intRed =0;

    int fragmentType = intBlue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        tvCounter = (TextView) findViewById(R.id.tvCounter);
        btnBlue = (Button) findViewById(R.id.blueBtn);
        btnRed = (Button) findViewById(R.id.redBtn);

        btnRed.setOnClickListener(this);
        btnBlue.setOnClickListener(this);

        if(savedInstanceState != null){
            fragmentType = savedInstanceState.getInt("fragtype");
        }

        fragmentManager = getSupportFragmentManager();
        FramentBlue framentBlue = new FramentBlue();
        if(fragmentType == intBlue){
            setFrament(intBlue);
        }else {
            setFrament(intRed);
        }



    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("fragtype" , fragmentType);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View view) {
        FramentBlue framentBlue = new FramentBlue();
        FragmentRed fragmentRed = new FragmentRed();
        switch (view.getId()){
            case R.id.blueBtn:
                setFrament(intBlue);
                break;
            case R.id.redBtn:
                setFrament(intRed);
                break;
        }
    }


    public void setFrament(int type){
        fragmentTransaction = fragmentManager.beginTransaction();
        if(type == intBlue){
            fragmentType = intBlue;
            FramentBlue framentBlue = new FramentBlue();
            fragmentTransaction.replace(R.id.fragmentFrame , framentBlue);
        }else{
            fragmentType = intRed;
            FragmentRed fragmentRed = new FragmentRed();
            fragmentTransaction.replace(R.id.fragmentFrame , fragmentRed);
        }
        fragmentTransaction.commit();

    }



    public void shiftCounter(int shift){
//        Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
        tvCounter.setText(
                String.valueOf(Integer.parseInt(tvCounter.getText().toString()) + shift)
        );

    }


}