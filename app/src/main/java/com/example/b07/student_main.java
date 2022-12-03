package com.example.b07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class student_main extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);

        final Button addCourseButton = findViewById(R.id.addCourseButton);
        final Button logoutButton = findViewById(R.id.logoutButton);
        textView = findViewById(R.id.textViewName);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        textView.setText(username);

        addCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(student_main.this, Student_add_course.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(student_main.this, Login.class));
            }
        });
    }
}