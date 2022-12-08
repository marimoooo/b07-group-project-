//package com.example.b07;
//
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TableLayout;
//import android.widget.TableRow;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.b07.generator.Session;
//import com.example.b07.generator.Timeline;
//import com.example.b07.model.Student;
//import com.example.b07.repository.CourseRepository;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Map;
//
//
//public class TimelineTable extends AppCompatActivity {
//
//    TableLayout tableTimeline;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_timeline_table);
//
//        Intent intent = getIntent();
////        Intent intent = getIntent();
//        String username = intent.getStringExtra("username");
//        List<String> courses = intent.getStringArrayListExtra("futureCourses");
//        FirebaseDatabase database = FirebaseDatabase.getInstance("https://course-planner-14-default-rtdb.firebaseio.com/");
//        DatabaseReference reference = database.getReference("students");
//        // preguntar sobre el id del estudiante loggeado
////        String studentid = "student2"; //tiene que ser el que este logeado
//        reference.child(username).child("courses").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            //String code = reference.child(studentid).child("courses").getKey();
//            @Override
//            public void onComplete(@NonNull Task<DataSnapshot> task) {
//                if (!task.isSuccessful()) {
//                    Log.e("firebase", "Error getting data", task.getException());
//                }
//                else {
//                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
//                    Map<String, Object> dataCourses = (Map<String, Object>) task.getResult().getValue();
//                    if (dataCourses != null) {
//                        if (!dataCourses.isEmpty()) {
//                            List<String> codeCourses = new ArrayList<>(dataCourses.keySet());
//                            Log.d("STUDENT COURSES", codeCourses.toString());
//                            Timeline timeline = new Timeline(CourseRepository.getInstance());
//                            List<Session> sessions = timeline.sessions(courses, codeCourses);
//                            tableTimeline = (TableLayout) findViewById(R.id.Timeline);
//
//                            for (Session session : sessions) {
//                                TableRow row = new TableRow(getApplicationContext());
//                                TextView tvSession = new TextView(getApplicationContext());
//                                tvSession.setText(session.season + " " + session.year);
//                                row.addView(tvSession);
//
//                                TextView tvCourses = new TextView(getApplicationContext());
//                                tvCourses.setText(session.courses.toString());
//                                row.addView(tvCourses);
//                                tableTimeline.addView(row);
//                            }
//                        }
//                    }
//
//                }
//            }
//        });
//        // Student student = new Student("Victoria", Arrays.asList("CSCA08"));
//        Button signupButton = findViewById(R.id.button4);
//        signupButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(TimelineTable.this, student_main.class));
//                finish();
//            }
//        });
//
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.back,menu);
//        return true;
//        //return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//
//        if (id == R.id.ItemBack) {
//            finish();
//        }
//        return super.onOptionsItemSelected(item);
//    }
//}

package com.example.b07;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.b07.generator.Session;
import com.example.b07.generator.Timeline;
import com.example.b07.model.Student;
import com.example.b07.repository.CourseRepository;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class TimelineTable extends AppCompatActivity {

    TableLayout tableTimeline;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline_table);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        List<String> courses = intent.getStringArrayListExtra("futureCourses");
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://course-planner-14-default-rtdb.firebaseio.com/");
        DatabaseReference reference = database.getReference("students");
        // preguntar sobre el id del estudiante loggeado
        String studentid = "student2"; //tiene que ser el que este logeado
        reference.child(username).child("courses").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            //String code = reference.child(studentid).child("courses").getKey();
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                    Map<String, Object> dataCourses = (Map<String, Object>) task.getResult().getValue();
                    if (dataCourses != null) {
                        if (!dataCourses.isEmpty()) {
                            List<String> codeCourses = new ArrayList<>(dataCourses.keySet());
                            Log.d("STUDENT COURSES", codeCourses.toString());
                            Timeline timeline = new Timeline(CourseRepository.getInstance());
                            List<Session> sessions = timeline.sessions(courses, codeCourses);
                            tableTimeline = (TableLayout) findViewById(R.id.Timeline);

                            for (Session session : sessions) {
                                TableRow row = new TableRow(getApplicationContext());
                                TextView tvSession = new TextView(getApplicationContext());
                                tvSession.setText(session.season + " " + session.year);
                                row.addView(tvSession);

                                TextView tvCourses = new TextView(getApplicationContext());
                                tvCourses.setText(session.courses.toString());
                                row.addView(tvCourses);
                                tableTimeline.addView(row);
                            }
                        }
                    }

                }
            }
        });
        // Student student = new Student("Victoria", Arrays.asList("CSCA08"));
        Button signupButton = findViewById(R.id.button4);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // startActivity(new Intent(TimelineTable.this, student_main.class));
                Intent intent = new Intent(TimelineTable.this, student_main.class);
                intent.putExtra("username", username);
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.back,menu);
        return true;
        //return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.ItemBack) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}