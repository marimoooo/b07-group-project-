package com.example.b07.model;


import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Course  {

    public String code;
    public String name;
    public List<String> sessionList = new ArrayList<>();
    public List<String> prereqList = new ArrayList<>();
    public String session;
    public String prereq;

    /*public Course(String code, List<String> session, List<String> prerequisites) {
        this.code = code;
        this.session = session;
        this.prerequisites = prerequisites;
    }*/
    public Course(){

    }

    public Course(String code, String name, String session, String prereq) {
        this.code = code;
        this.name = name;
        this.session = session;
        this.prereq = prereq;
        this.sessionList = Arrays.asList(session.split(","));
        if (prereq == null){
            this.prereqList = new ArrayList<>();
        }
        else {
            this.prereqList = Arrays.asList(prereq.split(","));
        }
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

/*    public List<String> getSession() {
        return session;
    }

    public void setSession(List<String> session) {
        this.session = session;
    }

    public List<String> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(List<String> prerequisites) {
        this.prerequisites = prerequisites;
    }*/

    public List<String> getSession() {
        return sessionList;
    }

    public void setSession(String session) {
        this.sessionList = Arrays.asList(session.split(","));
    }

    public List<String> getPrerequisites() {
        return prereqList;
    }

    public void setPrerequisites(String prerequisites) {
        if (prerequisites == null){
            this.prereqList = new ArrayList<>();
        }
        else {
            this.prereqList = Arrays.asList(prerequisites.split(","));
        }
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", code);
        result.put("came", name);
        result.put("prereq",prereq);
        result.put("session", session);

        return result;
    }

    @Override
    public String toString() {
        return "Course{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", session=" + session +
                ", prereq=" + prereq +
                '}';
    }

    // Attach a listener to read the data at our posts reference




}