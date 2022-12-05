package com.example.b07;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class DAOCourseAddition {
    // private final DatabaseReference databaseReference;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://course-planner-14-default-rtdb.firebaseio.com/").getReference();
    public DAOCourseAddition(){
    }
    public Task<Void> add(Course_details emp){
        databaseReference.child("Course details").child(emp.get_code()).child("code").setValue(emp.get_code());
        databaseReference.child("Course details").child(emp.get_code()).child("session").setValue(emp.getCourse_offering().toString());
        emp.setCourse_offering("");
        databaseReference.child("Course details").child(emp.get_code()).child("prereq").setValue(emp.getCourse_pre_req());
        emp.setCourse_pre_req("");
        return databaseReference.child("Course details").child(emp.get_code()).child("name").setValue(emp.getCourse_name());
    }
}