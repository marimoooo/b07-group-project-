package com.example.timelinegenerator.model;

import java.util.List;
public class Course {

    public String code;
    public List<String> session;
    public List<String> prerequisites;

    public Course(String code, List<String> session, List<String> prerequisites) {
        this.code = code;
        this.session = session;
        this.prerequisites = prerequisites;
    }


}