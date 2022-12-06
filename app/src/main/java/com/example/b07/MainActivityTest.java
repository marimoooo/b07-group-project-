package com.example.b07;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivityTest extends AppCompatActivity {
    EditText password;
    Button studentLoginButton;// = findViewById(R.id.studentLoginButton);
    Button adminLoginButton;
    //    final TextView signupButton = findViewById(R.id.signUpButton);
    private Presenter presenter;

    public String getUserName(){
        EditText username = findViewById(R.id.username);
        return username.getText().toString();
    }
    public String getPassword(){
        password = findViewById(R.id.password);
        return password.getText().toString();
    }
    public void displayMessage(String message){
        TextView textview = findViewById(R.id.tvMessage);
        textview.setText(message);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test);
        studentLoginButton = findViewById(R.id.studentLoginButton);
        adminLoginButton = findViewById(R.id.adminLoginButton);
        presenter = new Presenter(new Model(),this);
//ok
        studentLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.checkUser();
            }
        });

        adminLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.checkAdmin();
            }
        });

    }
}