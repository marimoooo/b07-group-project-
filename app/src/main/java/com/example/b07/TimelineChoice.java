package com.example.b07;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.content.Intent;
import android.icu.text.LocaleDisplayNames;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.b07.model.Course;
import com.example.b07.repository.CourseRepository;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TimelineChoice extends AppCompatActivity {
    ListView listCourses;
    ArrayAdapter<String> adapter;
    List<String> arrayCourses = new ArrayList<>();
    CourseRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline_choice);
        repository = CourseRepository.getInstance();
        listCourses = (ListView) findViewById(R.id.ListCourses);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, arrayCourses);
        listCourses.setAdapter(adapter);
        repository.getReference().addChildEventListener(this.onChildEventListener());

//        int id = item.getItemId();
        Button button = findViewById(R.id.button);
        button.setOnClickListener(view -> {
            ArrayList<String> itemSelected = new ArrayList<>();
            for (int i = 0; i < listCourses.getCount(); i++) {
                if (listCourses.isItemChecked(i)){
                    itemSelected.add((String) listCourses.getItemAtPosition(i));
                }
            }
//                Intent intent = new Intent(this,TimelineTable.class);
            Intent intent = new Intent(TimelineChoice.this,TimelineTable.class);
            intent.putStringArrayListExtra("futureCourses", itemSelected);
//                TimelineTable.("futureCourses", itemSelected);
            startActivity(intent);
        });;
        Button signupButton = findViewById(R.id.xyz4);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TimelineChoice.this, TimelineTable.class));
            }
        });
    }
    
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Button button = findViewById(R.id.button);
        button.setOnClickListener(view -> {
            ArrayList<String> itemSelected = new ArrayList<>();
            for (int i = 0; i < listCourses.getCount(); i++) {
                if (listCourses.isItemChecked(i)){
                    itemSelected.add((String) listCourses.getItemAtPosition(i));
                }
            }
//                Intent intent = new Intent(this,TimelineTable.class);
            Intent intent = new Intent(TimelineChoice.this,TimelineTable.class);
        intent.putStringArrayListExtra("futureCourses", itemSelected);
//                TimelineTable.("futureCourses", itemSelected);
            startActivity(intent);
        });;
//        if (id == R.id.) {
//            ArrayList<String> itemSelected = new ArrayList<>();
//
//            for (int i = 0; i < listCourses.getCount(); i++) {
//
//                if (listCourses.isItemChecked(i)){
//                    itemSelected.add((String) listCourses.getItemAtPosition(i));
//                }
//            }
//            Intent intent = new Intent(this,TimelineTable.class);
//            intent.putStringArrayListExtra("futureCourses", itemSelected);
//            startActivity(intent);
//        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
        //return super.onCreateOptionsMenu(menu);
    }

    public ChildEventListener onChildEventListener (){
        return new ChildEventListener(){
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.d("CHILD COURSE", String.valueOf(snapshot.getValue()));
                Course course = snapshot.getValue(Course.class);
                Log.d("CHILD COURSE 2", course.toString());
                arrayCourses.add(snapshot.getKey());
                repository.getCourses().add(course);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                int indexCourse =  arrayCourses.indexOf(snapshot.getKey());
                repository.getCourses().set(indexCourse,snapshot.getValue(Course.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                String code = snapshot.getKey();
                arrayCourses.remove(code);
                Optional<Course> course = repository.getCourses().stream().filter(c -> code.equals(c.code)).findFirst();
                repository.getCourses().remove(course.get());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"Failed to load courses", Toast.LENGTH_SHORT).show();
            }
        };
    }

}