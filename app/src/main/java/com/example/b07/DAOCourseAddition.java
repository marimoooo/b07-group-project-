package com.example.b07;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOCourseAddition {
    private final DatabaseReference databaseReference;
    public DAOCourseAddition(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Course_details.class.getSimpleName());
    }
    public Task<Void> add(Course_details emp){
//        if(emp == null) //throw exp

        databaseReference.child(emp.get_code()).child("Course Code").setValue(emp.get_code());
        databaseReference.child(emp.get_code()).child("Course Name").setValue(emp.getCourse_name());
        databaseReference.child(emp.get_code()).child("Course Offerings").setValue(emp.getCourse_offering());
        return databaseReference.child(emp.get_code()).child("Course Pre-requisites").setValue(emp.getCourse_pre_req());
    }
}
