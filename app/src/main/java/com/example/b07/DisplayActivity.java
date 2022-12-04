//package com.example.b07;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.ListAdapter;
//import android.widget.ListView;
//
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class DisplayActivity extends AppCompatActivity {
//
//    FirebaseDatabase data;
//    DatabaseReference reference;
//    List<Course> courses = new ArrayList<>();
//    Course theCourse;
//    //userinfo= course_item
//    //listview=courseList
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_display);
//        ListView courseList = (ListView) findViewById(R.id.CourseListView);
//        data = FirebaseDatabase.getInstance("https://course-planner-14-default-rtdb.firebaseio.com/");
//        //Intent intent = getIntent();
//        String name = "student2";//intent.getStringExtra("name");
//        theCourse = new Course();
//        reference = data.getReference("students").child(name).child("courses");
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                courses.clear();
//                for (DataSnapshot shot : snapshot.getChildren()) {
//                    theCourse = shot.getValue(Course.class);
//                    courses.add(theCourse);
//                }
//                CourseAdapter adapter = new CourseAdapter(DisplayActivity.this, R.layout.course_item, courses);
//                courseList.setAdapter(adapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }
//}


//to display array on listView

        /*String username = "name";


        List<Course> courses = new ArrayList<Course>();
        database = FirebaseDatabase.getInstance().getReference();


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                courses.clear();
                for(DataSnapshot crsSnapshot: snapshot.getChildren()){
                    Course course = snapshot.getValue(Course.class);
                    courses.add(course);
                }
                //data
               CourseAdapter adapter = new CourseAdapter(MainActivity.this,R.layout.course_item,courses);
                courseList.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/



//        CourseAdapter adapter = new CourseAdapter(this, R.layout.course_item, courses);
//
//        courseList.setAdapter(adapter);

       /* courseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {@Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Course theCourse = courses.get(i);
                Intent intent = new Intent(MainActivity.this, CourseInfoActivity.class);
                intent.putExtra(String.valueOf("7"), theCourse);
                startActivity(intent);
                                              }
                                          }

        );*/



package com.example.b07;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DisplayActivity extends AppCompatActivity {

//    FirebaseDatabase data;
//    DatabaseReference reference;

    DatabaseReference databaseReference = FirebaseDatabase.
            getInstance("https://course-planner-14-default-rtdb.firebaseio.com/").getReference("student");

    List<Course> courses = new ArrayList<>();
    Course theCourse;
    String name,code,session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        ListView courseList = (ListView) findViewById(R.id.CourseListView);
        //String name = "student2";
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        theCourse = new Course();
        databaseReference.child(username).child("courses").addValueEventListener(new ValueEventListener() {

            // reference.addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // courses.clear();
                for (DataSnapshot shot : snapshot.getChildren()) {
                    name = shot.child("name").getValue().toString();
                    //Toast.makeText(DisplayActivity.this, "name: " + name, Toast.LENGTH_SHORT).show();
                    code = shot.child("code").getValue().toString();
                    session = shot.child("session").getValue().toString();
                    Course course1 = new Course(code, name, session);
                    courses.add(course1);
                }
                CourseAdapter adapter = new CourseAdapter(DisplayActivity.this, R.layout.course_item, courses);
                courseList.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}