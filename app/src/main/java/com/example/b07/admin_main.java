package com.example.b07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class admin_main extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        final Button addCourseButton = findViewById(R.id.addCourseButton);
        final Button logoutButton = findViewById(R.id.logoutButton);
        textView = findViewById(R.id.textViewName2);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        textView.setText(username);

        addCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(admin_main.this, admin_add_courses.class);
//                intent.putExtra("username", username);
//                startActivity(intent);
                startActivity(new Intent(admin_main.this, admin_add_course.class));
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(admin_main.this, Login.class));
            }
        });

    }
}