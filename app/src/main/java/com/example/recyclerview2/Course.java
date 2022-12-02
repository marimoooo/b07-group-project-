package com.example.recyclerview2;

public class Course {
    public String code;
    public String name;

    public Course(){

    }

    public Course(String code, String name){
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
