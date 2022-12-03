package com.example.timelinegenerator.model;

import java.util.List;


public class Student {

    public String name;
    public List<String> courses; //los que yo estudiante he cursado ['csb32', 'csss32']

    public Student(String name, List<String> courses) {
        this.name = name;
        this.courses = courses;
    }

}