package com.example.adwitiyasingh.customadaptor;

/**
 * Created by adwitiyasingh on 3/11/17.
 */

public class Courses {
    private String courseName;
    private String teacherName;

    public Courses(String courseName, String teacherName) {
        this.courseName = courseName;
        this.teacherName = teacherName;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
