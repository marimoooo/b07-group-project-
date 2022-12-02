package com.example.recyclerview2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DisplayActivity extends AppCompatActivity {

    FirebaseDatabase data;
    DatabaseReference reference;
    List<Course> courses = new ArrayList<Course>();
    Course theCourse;


    //userinfo= course_item
    //listview=courseList



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_activity);
       ListView courseList = (ListView) findViewById(R.id.CourseListView);
       data = FirebaseDatabase.getInstance();
       String name = "abc";
       theCourse = new Course();
       reference = data.getReference("students").child(name).child("takenCourse");
       reference.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               courses.clear();
               for(DataSnapshot shot:snapshot.getChildren()){
                theCourse = shot.getValue(Course.class);
                courses.add(theCourse);
               }
               CourseAdapter adapter = new CourseAdapter(DisplayActivity.this,R.layout.course_item,courses);
               courseList.setAdapter(adapter);
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });


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

//        courses.add(new Course("CSCA08","Intro to CS"));
//        courses.add(new Course("CSCA48","Intro to CS2"));
//        courses.add(new Course("CSCB07","Intro to CS3"));
//        courses.add(new Course("CSCA08","Intro to CS"));
//        courses.add(new Course("CSCA48","Intro to CS2"));
//        courses.add(new Course("CSCB07","Intro to CS3"));
//        courses.add(new Course("CSCA08","Intro to CS"));
//        courses.add(new Course("CSCA48","Intro to CS2"));
//        courses.add(new Course("CSCB07","Intro to CS3"));
//        courses.add(new Course("CSCA08","Intro to CS"));
//        courses.add(new Course("CSCA48","Intro to CS2"));
//        courses.add(new Course("CSCB07","Intro to CS3"));
//        courses.add(new Course("CSCA08","Intro to CS"));
//        courses.add(new Course("CSCA48","Intro to CS2"));
//        courses.add(new Course("CSCB07","Intro to CS3"));
//        courses.add(new Course("CSCA08","Intro to CS"));
//        courses.add(new Course("CSCA48","Intro to CS2"));
//        courses.add(new Course("CSCB07","Intro to CS3"));


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
    }

}