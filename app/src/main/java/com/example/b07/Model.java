package com.example.b07;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Model {
    DatabaseReference databaseRef;
    String message;
    Boolean exists;

    public Model() {
        databaseRef = FirebaseDatabase.
                getInstance("https://course-planner-14-default-rtdb.firebaseio.com/").getReference();
        message="";
    }

    public Boolean ifExist(String usernameText){
        return  true;
    }

    public void checkUserInDatabase(String usernameText, String passwordText){

        if(usernameText.isEmpty()|| passwordText.isEmpty()){
            message = "fields cannot be empty";
            Log.d("myTag", "message: " + message);
        }
        else{
            databaseRef.child("students").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.hasChild(usernameText)){
                        String getPassword = snapshot.child(usernameText).child("password").getValue(String.class);
                        if(getPassword.equals(passwordText)){
                            message = "User found";
                            Log.d("myTag", "message: " + message);
                        }
                        else{
                            message = "Wrong password";
                            Log.d("myTag", "message: " + message);
                        }
                    }
                    else{
                        message = "User not found";
                        Log.d("myTag", "message: " + message);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
    }

    public void checkAdminInDatabase(String usernameText, String passwordText){

        if(usernameText.isEmpty()|| passwordText.isEmpty()){
            message = "fields cannot be empty";
        }
        else{
            databaseRef.child("admins").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.hasChild(usernameText)){
                        String getPassword = snapshot.child(usernameText).child("password").getValue(String.class);
                        if(getPassword.equals(passwordText)){
                            message = "User found";
                        }
                        else{
                            message = "Wrong password";
                        }
                    }
                    else{
                        message = "User not found";
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
}
