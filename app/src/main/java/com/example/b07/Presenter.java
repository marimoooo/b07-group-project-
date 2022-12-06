package com.example.b07;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class Presenter {
    private MainActivityTest view;
    private Model model;

    public Presenter(Model model, MainActivityTest view){
        this.model = model;
        this.view = view;
    }

    public void checkUser(){

        String usernameText = view.getUserName();
        String passwordText = view.getPassword();

        if(usernameText.isEmpty()|| passwordText.isEmpty()){
            view.displayMessage("Username can't be empty");
        }
        else{
            model.databaseRef.child("students").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.hasChild(usernameText)){
                        String getPassword = snapshot.child(usernameText).child("password").getValue(String.class);
                        if(getPassword.equals(passwordText)){
                            view.displayMessage("User found");
                        }
                        else{
                            view.displayMessage("Wrong password");
                        }
                    }
                    else{
                        view.displayMessage("User not found");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    public void checkAdmin(){

        String usernameText = view.getUserName();
        String passwordText = view.getPassword();

        if(usernameText.isEmpty()|| passwordText.isEmpty()){
            view.displayMessage("Username can't be empty");
        }
        else{
            model.databaseRef.child("admins").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.hasChild(usernameText)){
                        String getPassword = snapshot.child(usernameText).child("password").getValue(String.class);
                        if(getPassword.equals(passwordText)){
                            view.displayMessage("Admin found");
                        }
                        else{
                            view.displayMessage("Wrong password");
                        }
                    }
                    else{
                        view.displayMessage("Admin not found");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

}
