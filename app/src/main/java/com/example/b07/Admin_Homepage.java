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
//        Button see_database = findViewById(R.id.button431);

        go_to_admin_course_addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Admin_Homepage.this, Course_addition_admin.class));
            }
        });

        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Admin_Homepage.this, Login.class));
            }
        });

        Button go_to_course_modification = findViewById(R.id.button2);
        go_to_course_modification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Admin_Homepage.this, MainActivity2.class));
            }
        });

        Button delete_course = findViewById(R.id.button431);
        delete_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Admin_Homepage.this, MainActivity3.class));
            }
        });
    }
}