package com.example.b07;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Student_add_course extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://course-planner-14-default-rtdb.firebaseio.com/").getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_add_course);

        final EditText courseCode = findViewById(R.id.courseCode);
        final EditText courseSession = findViewById(R.id.courseSession);
        final Button addCourseButton = findViewById(R.id.addCourseButton);
        final Button backButton = findViewById(R.id.backButton);
        final List<String> coursesList = new ArrayList<>();

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        addCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String courseCodeText = courseCode.getText().toString();
                final String courseSessionText = courseSession.getText().toString();

                if(courseCodeText.isEmpty() || courseSessionText.isEmpty()){
                    Toast.makeText(Student_add_course.this, "Please fill in details", Toast.LENGTH_SHORT).show();
                }
                else{
                    databaseReference.child("Course details").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(courseCodeText)){

                                final String courseSessions = snapshot.child(courseCodeText).child("session").getValue(String.class);

                                // ArrayList<String> sessions = (ArrayList<String>) Arrays.asList(courseSessions.split(","));
                                List<String> sessions = Arrays.asList(courseSessions.split(","));

                                if(sessions.contains(courseSessionText)){
                                    // check if the pre-requisites are subset of the courses taken by a student

                                    // creating a list of pre-requisites of a course
                                    final String preRequisites = snapshot.child(courseCodeText).child("prereq").getValue(String.class);
                                    // ArrayList<String> prereqs1 = new ArrayList<>();
                                    final List<String> prereqs = Arrays.asList(preRequisites.split(","));

                                    // creating a list for student's courses
                                    databaseReference.child("students").child(username).child("courses").addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            for(DataSnapshot d: snapshot.getChildren()) {
                                                // String code = d.getKey();
                                                coursesList.add(d.getKey());
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });

                                    if(coursesList.containsAll(prereqs) || prereqs.contains("")){
                                        final String courseNameText = snapshot.child(courseCodeText).child("name").getValue(String.class);
                                        databaseReference.child("students").child(username).child("courses").child(courseCodeText).child("code").setValue(courseCodeText);
                                        databaseReference.child("students").child(username).child("courses").child(courseCodeText).child("session").setValue(courseSessionText);
                                        databaseReference.child("students").child(username).child("courses").child(courseCodeText).child("name").setValue(courseNameText);
                                        Toast.makeText(Student_add_course.this,"course added successfully", Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        Toast.makeText(Student_add_course.this, "Please add pre-requisites first", Toast.LENGTH_SHORT).show();
                                    }


                                }
                                else{
                                    Toast.makeText(Student_add_course.this, "Cannot add the course. Course is not offered in this session", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(Student_add_course.this, "Cannot add the course", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Student_add_course.this, student_main.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });
    }
}