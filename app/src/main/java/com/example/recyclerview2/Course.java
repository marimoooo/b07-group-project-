package com.example.recyclerview2;

import java.util.ArrayList;
import java.util.List;

public class Course {
    public String code;
    public String session;
    public List<String> prerequisite;

    public Course(){
        prerequisite = new ArrayList<>();
    }

    public Course(String code, String session){
        this.code = code;
        this.session = session;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return session;
    }
}
