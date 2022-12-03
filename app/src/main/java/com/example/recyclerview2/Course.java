package com.example.recyclerview2;

import java.util.ArrayList;
import java.util.List;

public class Course {
    public String code;
    public String session;
    public String name;
    public List<String> preq;

    public Course(){
        preq = new ArrayList<>();

    }

    public Course(String code, String session, String name){
        this.code = code;
        this.session = session;
        this.name = name;
    }

}
