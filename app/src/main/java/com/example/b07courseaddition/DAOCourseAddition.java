package com.example.b07courseaddition;

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
        return databaseReference.push().setValue(emp);
    }
}
