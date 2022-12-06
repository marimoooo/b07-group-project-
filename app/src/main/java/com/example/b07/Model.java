package com.example.b07;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Model {
    DatabaseReference databaseRef;

    public Model() {
        databaseRef = FirebaseDatabase.
                getInstance("https://course-planner-14-default-rtdb.firebaseio.com/").getReference();

    }



    /*public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }*/
}

