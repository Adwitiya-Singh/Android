package com.example.adwitiyasingh.customadaptor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ArrayList<Courses> courseDetails= new ArrayList<Courses>();
    ListView lvCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvCourses = (ListView) findViewById(R.id.listCourses);

        courseDetails = RandomCourseGenerator.RandomList(300);
        CourseListAdapter clAdapter = new CourseListAdapter(courseDetails,this);

        lvCourses.setAdapter(clAdapter);





    }
}
