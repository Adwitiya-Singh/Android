package com.example.adwitiyasingh.uiandanimations;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ObjectAnimator animator = ObjectAnimator.ofFloat(findViewById(R.id.textanim), "translationX", 0f, 700f);
//        animator.setDuration(500);
//        animator.start();
    }
}
