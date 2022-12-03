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

import java.util.Objects;

public class MainActivity3 extends AppCompatActivity {

    DatabaseReference databaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        EditText newCourseName = findViewById(R.id.newCourseName);
        databaseRef = FirebaseDatabase.getInstance("https://course-planner-14-default-rtdb.firebaseio.com/").getReference();
        Button delete = findViewById(R.id.button3);
        delete.setOnClickListener(v -> {
//            String code = newCourseName.getText().toString();
////            databaseRef.child("Course details").child(code).child("Course Pre-requisites").setValue(newPreq.getText().toString());
//            Toast.makeText(MainActivity3.this, ""+ code + "is deleted", Toast.LENGTH_SHORT).show();
//            databaseRef.child("Course details").child(""+code+"").removeValue();
//            Toast.makeText(MainActivity3.this, ""+ code + "is deleted", Toast.LENGTH_SHORT).show();

        });

        delete = findViewById(R.id.button3);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity3.this, Admin_Homepage.class));
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = newCourseName.getText().toString();
//            databaseRef.child("Course details").child(code).child("Course Pre-requisites").setValue(newPreq.getText().toString());
                Toast.makeText(MainActivity3.this, ""+ code + " is deleted", Toast.LENGTH_SHORT).show();
                databaseRef.child("Course details").child(""+code+"").removeValue();
//                databaseRef.child("students").child("takenCourse").child("course01").removeValue();
//                Toast.makeText(MainActivity3.this, ""+ databaseRef.child("students").child("abc").toString() + " is deleted", Toast.LENGTH_SHORT).show();
//                databaseRef.child("students").child("takenCourse").child("course01").removeValue();

                DatabaseReference reference = FirebaseDatabase.getInstance("https://course-planner-14-default-rtdb.firebaseio.com/").getReference().child("students");
//                Toast.makeText(MainActivity3.this, "$$$", Toast.LENGTH_SHORT).show();

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                            Toast.makeText(MainActivity3.this, "$$$" + Objects.requireNonNull(snapshot.child("courses")), Toast.LENGTH_SHORT).show();
                            databaseRef.child("students").child(Objects.requireNonNull(snapshot.getKey())).child("courses").child(""+code+"").removeValue();

//                            Toast.makeText(Admin_course_addition.this, "" + snapshot.child("Course Code").getValue().toString() + "is added to the course pre-req.", Toast.LENGTH_SHORT).show();
//                    list.add(snapshot.getValue().toString());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}