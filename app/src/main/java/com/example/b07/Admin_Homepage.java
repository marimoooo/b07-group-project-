package com.example.b07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Admin_Homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_homepage);

        Button go_to_admin_course_addition = findViewById(R.id.admin_course_add);
        Button sign_out = findViewById(R.id.sign_out);

        go_to_admin_course_addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Admin_Homepage.this, Admin_course_addition.class));
            }
        });

        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Admin_Homepage.this, Login.class));
            }
        });
    }
}