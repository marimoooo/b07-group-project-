package com.example.b07;

import android.widget.Toast;

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
        model.checkUserInDatabase(usernameText, passwordText);
        view.displayMessage(model.message);
    }

    public void checkAdmin(){
        String usernameText = view.getUserName();
        String passwordText = view.getPassword();
        model.checkUserInDatabase(usernameText, passwordText);
        view.displayMessage(model.message);
    }

}
