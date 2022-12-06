package com.example.b07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Course extends AppCompatActivity {

    public String code;
    public String session;
    public String name;
    public List<String> prerequisite;

    public Course(){
        prerequisite = new ArrayList<>();
    }

    public Course(String code, String name, String session){
        this.code = code;
        this.session = session;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getSession() {
        return session;
    }
}