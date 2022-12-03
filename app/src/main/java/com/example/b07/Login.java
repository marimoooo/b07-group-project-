package com.example.b07;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

    public class Login extends AppCompatActivity {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://cscb07-project-ba4c9-default-rtdb.firebaseio.com/").getReference();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
            final EditText username = findViewById(R.id.username);
            final EditText password = findViewById(R.id.password);
            final Button studentLoginButton = findViewById(R.id.studentLoginButton);
            final Button adminLoginButton = findViewById(R.id.adminLoginButton);
            final TextView signupButton = findViewById(R.id.signUpButton);

            studentLoginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final String usernameText = username.getText().toString();
                    final String passwordText = password.getText().toString();

                    if(usernameText.isEmpty() || passwordText.isEmpty()){
                        Toast.makeText(Login.this, "Please enter your username or password", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        databaseReference.child("students").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.hasChild(usernameText)){
                                    final String getPassword = snapshot.child(usernameText).child("password").getValue(String.class);

                                    if(getPassword.equals(passwordText)){
                                        Toast.makeText(Login.this, "Log in successful", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(Login.this, student_main.class);
                                        intent.putExtra("username", usernameText);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else{
                                        Toast.makeText(Login.this, "Password incorrect", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else{
                                    Toast.makeText(Login.this, "Password incorrect", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }
            });

            adminLoginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final String usernameText = username.getText().toString();
                    final String passwordText = password.getText().toString();

                    if(usernameText.isEmpty() || passwordText.isEmpty()){
                        Toast.makeText(Login.this, "Please enter your username or password", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        databaseReference.child("admins").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.hasChild(usernameText)){
                                    final String getPassword = snapshot.child(usernameText).child("password").getValue(String.class);

                                    if(getPassword.equals(passwordText)){
                                        Toast.makeText(Login.this, "Log in successful", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(Login.this, student_main.class);
                                        intent.putExtra("username", usernameText);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else{
                                        Toast.makeText(Login.this, "Password incorrect", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else{
                                    Toast.makeText(Login.this, "Password incorrect", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }
            });

            signupButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(Login.this, Signup.class));
                }
            });
        }
}