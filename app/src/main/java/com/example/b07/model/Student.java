package com.example.b07.model;

import java.util.ArrayList;
import java.util.List;


public class Student {

    public String username;
    public List<Course> courses; //los que yo estudiante he cursado ['csb32', 'csss32']

    public Student(){

    }
    /*public Student(String name, List<String> courses) {
        this.name = name;
        this.courses = courses;
    }*/

    public Student(String username, List<Course> courses) {
        this.username = username;
        this.courses = courses;
    }

    public List<String> coursesList() {
        List<String> courses = new ArrayList<>();
        for (Course course: this.courses) {
            courses.add(course.code);
        }
        return courses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "username='" + username + '\'' +
                ", courses=" + courses.toString() +
                '}';
    }
}