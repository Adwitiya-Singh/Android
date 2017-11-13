package com.example.adwitiyasingh.customadaptor;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by adwitiyasingh on 3/12/17.
 */

public class CourseRecycleAdapter extends RecyclerView.Adapter<CourseRecycleAdapter.CourseItemHolder> {

    private ArrayList<Courses> courses;
    private Context context;

    public CourseRecycleAdapter(ArrayList<Courses> courses, Context context) {
        this.courses = courses;
        this.context = context;
    }

    @Override
    public CourseItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = li.inflate(R.layout.course_list_item, parent, false);


        return new CourseItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CourseItemHolder holder, int position) {
       Courses thisCourse = courses.get(position);
        holder.tvCourseName.setText(thisCourse.getCourseName());

    }

    @Override
    public int getItemCount() {
        return courses.size();

    }

    class CourseItemHolder extends RecyclerView.ViewHolder {
        TextView tvCourseName;
        TextView tvTeacherName;

        public CourseItemHolder(View itemView) {
            super(itemView);
            tvCourseName = (TextView) itemView.findViewById(R.id.tvCourse);
            tvTeacherName = (TextView) itemView.findViewById(R.id.tvTeacher);


        }
    }
}
