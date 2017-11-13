package com.example.adwitiyasingh.customadaptor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;

import java.util.ArrayList;

public class RecycleActivity extends AppCompatActivity {
    ArrayList<Courses> courseList;
    RecyclerView courseRecyclerView;
    CourseRecycleAdapter RecycleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_activity);

        courseRecyclerView = (RecyclerView) findViewById(R.id.recycleView);

        courseList = RandomCourseGenerator.RandomList(200);

        RecycleAdapter = new CourseRecycleAdapter(courseList, this);

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int columns = displayMetrics.widthPixels>1200 ? 2 : 1;
//        courseRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        courseRecyclerView.setLayoutManager(new GridLayoutManager(this,columns));

        courseRecyclerView.setAdapter(RecycleAdapter);
    }
}
