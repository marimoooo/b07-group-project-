package com.example.recyclerview2;

public class Course {
    public String code;
    public String session;

    public Course(){

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
