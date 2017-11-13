package com.example.adwitiyasingh.customadaptor;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by adwitiyasingh on 3/11/17.
 */

public class RandomCourseGenerator {

    static String[] Courses = {"Crux",
            "Pandora",
            "Elixar",
            "Launchpad",
            "Django",
            "Perception",
            "GameDev"
    };
    static String[] Teachers = {"Arnav",
            "Sumeet",
            "Prateek",
            "Rishab",
            "Deepak",
            "Shubham"
    };
    static Random r = new Random();

    static Courses RandomCourse() {
        int c = r.nextInt(Courses.length);
        int t = r.nextInt(Teachers.length);
        Courses crs = new Courses(Courses[c], Teachers[t]);
        return crs;
    }

    static ArrayList<Courses> RandomList(int n) {
        ArrayList<Courses> rv = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            rv.add(RandomCourse());
        }
        return rv;
    }

}
