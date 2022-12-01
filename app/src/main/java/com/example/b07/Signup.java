package com.example.b07;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.database.DatabaseUtilsCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Signup extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://course-planner-14-default-rtdb.firebaseio.com/").getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        final EditText username = findViewById(R.id.username);
        final EditText password = findViewById(R.id.password);
        final EditText confirmPassword = findViewById(R.id.confirmPassword);

        final Button studentSignUpButton = findViewById(R.id.studentSignUpButton);
        final Button adminSignUpButton = findViewById(R.id.adminSignUpButton);
        final Button loginButton = findViewById(R.id.loginButton);

        studentSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String usernameText = username.getText().toString();
                final String passwordText = password.getText().toString();
                final String confirmPasswordText = confirmPassword.getText().toString();

                if(usernameText.isEmpty() || passwordText.isEmpty() || confirmPasswordText.isEmpty()){
                    Toast.makeText(Signup.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
                else if(!passwordText.equals(confirmPasswordText)){
                    Toast.makeText(Signup.this, "Passwords are not matching", Toast.LENGTH_SHORT).show();
                }
                else{
                    databaseReference.child("students").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(usernameText)){
                                Toast.makeText(Signup.this, "Username unavailable", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                databaseReference.child("students").child(usernameText).child("username").setValue(usernameText);
                                databaseReference.child("students").child(usernameText).child("password").setValue(passwordText);

                                Toast.makeText(Signup.this, "Student sign up successful", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

        adminSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String usernameText = username.getText().toString();
                final String passwordText = password.getText().toString();
                final String confirmPasswordText = confirmPassword.getText().toString();

                if(usernameText.isEmpty() || passwordText.isEmpty() || confirmPasswordText.isEmpty()){
                    Toast.makeText(Signup.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
                else if(!passwordText.equals(confirmPasswordText)){
                    Toast.makeText(Signup.this, "Passwords are not matching", Toast.LENGTH_SHORT).show();
                }
                else{
                    databaseReference.child("admins").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(usernameText)){
                                Toast.makeText(Signup.this, "Username unavailable", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                databaseReference.child("admins").child(usernameText).child("username").setValue(usernameText);
                                databaseReference.child("admins").child(usernameText).child("password").setValue(passwordText);

                                Toast.makeText(Signup.this, "Admin sign up successful", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {startActivity(new Intent(Signup.this, Login.class));}
        });
    }
}