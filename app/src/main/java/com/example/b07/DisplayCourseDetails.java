package com.example.b07;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DisplayCourseDetails extends AppCompatActivity {

    ListView myListView;
    ArrayList<String> myArrayList = new ArrayList<>();
    DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_course_details);

        ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<String>(DisplayCourseDetails.this, android.R.layout.simple_list_item_1, myArrayList);

        myListView = (ListView) findViewById(R.id.listview1);
        myListView.setAdapter(myArrayAdapter);

        mRef = FirebaseDatabase.getInstance("https://course-planner-14-default-rtdb.firebaseio.com/").getReference().child("Course details");

        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String course_Code = snapshot.getKey();
                assert course_Code != null;
                String course_Name = snapshot.child("name").getValue().toString();
                String course_Session = snapshot.child("session").getValue().toString();
                String course_PreReq = snapshot.child("prereq").getValue().toString();
                myArrayList.add("\n" + "Course Code: " + course_Code + "\n" + "Course Name: "+ course_Name + "\n" + "Course Offering: "+ course_Session + "\n" + "Course Pre-requisites: "+ course_PreReq + "\n");

                myArrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                myArrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Button gobackbutton = findViewById(R.id.button9);
        gobackbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { startActivity(new Intent(DisplayCourseDetails.this, admin_main.class));}
        });

    }
}