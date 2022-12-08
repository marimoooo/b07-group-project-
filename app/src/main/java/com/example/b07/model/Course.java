package com.example.b07.model;


import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Course  {

    public String code;
    public String name;
    public String session;
    public String prereq;


    public Course(){

    }

    public Course(String code, String name, String session, String prereq) {
        this.code = code;
        this.name = name;
        this.session = session;
        this.prereq = prereq;

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSessionList() {
        if (prereq == null){
            return new ArrayList<>();
        }
        else {
            return Arrays.asList(session.split(","));
        }
    }



    public List<String> getPrereqList() {
        if (Objects.equals(prereq, "")){
            return new ArrayList<>();
        }
        else {
            return Arrays.asList(prereq.split(","));
        }
    }




    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getPrereq() {
        return prereq;
    }

    public void setPrereq(String prereq) {
        this.prereq = prereq;
    }

    @Override
    public String toString() {
        return "Course{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", session=" + getSessionList() +
                ", prereq=" + getPrereqList() +
                '}';
    }

    // Attach a listener to read the data at our posts reference




}