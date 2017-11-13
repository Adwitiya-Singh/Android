package com.example.adwitiyasingh.customadaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by adwitiyasingh on 3/11/17.
 */

public class CourseListAdapter extends BaseAdapter {
    private ArrayList<Courses> cList;
    private Context ctx;

    public CourseListAdapter(ArrayList<Courses> cList, Context ctx) {
        this.cList = cList;
        this.ctx = ctx;
    }

    @Override
    public int getItemViewType(int position) {
        if (getItem(position).getCourseName().equals("Pandora")) {
            return 1;
        } else if (getItem(position).getCourseName().equals("Elixar")) {
            return 2;
        } else {
            return 0;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getCount() {
        return cList.size();
    }

    @Override
    public Courses getItem(int position) {
        return cList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater li = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        int layoutType = getItemViewType(position);
        if (convertView == null) {
            switch (layoutType) {
                case 2:
                    convertView = li.inflate(R.layout.course_list_item_right, null);
                    break;
                case 1:
                    convertView = li.inflate(R.layout.course_list_item_center, null);
                    break;
                case 0:
                default:
                    convertView = li.inflate(R.layout.course_list_item, null);
                    break;
            }
        }

        TextView tvCourse = (TextView) convertView.findViewById(R.id.tvCourse);
        TextView tvTeacher = (TextView) convertView.findViewById(R.id.tvTeacher);

        Courses thisCourse = getItem(position);
        tvCourse.setText(thisCourse.getCourseName());
        tvTeacher.setText(thisCourse.getTeacherName());

        return convertView;
    }

}
