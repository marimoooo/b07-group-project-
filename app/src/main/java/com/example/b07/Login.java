    package com.example.b07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

    public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText name = findViewById(R.id.username);
        final EditText password = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.loginButton);
        final TextView signupButton = findViewById(R.id.signUpButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String nameText = name.getText().toString();
                final String passwordText = password.getText().toString();

                if(nameText.isEmpty() || passwordText.isEmpty()){
                    Toast.makeText(Login.this, "Please enter your username or password", Toast.LENGTH_SHORT).show();
                }
                else{

                }
            }
        });
    }
}