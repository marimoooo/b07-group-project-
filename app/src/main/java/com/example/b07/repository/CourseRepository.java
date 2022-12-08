package com.example.b07.repository;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.b07.model.Course;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CourseRepository extends AppCompatActivity{

    private List<Course> courses = new ArrayList<>();
    private FirebaseDatabase data;
    private DatabaseReference reference;
    public static CourseRepository courseRepository;

    public CourseRepository(){
        this.data = FirebaseDatabase.getInstance("https://course-planner-14-default-rtdb.firebaseio.com/");
        this.reference = data.getReference("Course details");
    }

    public FirebaseDatabase getData() {
        return data;
    }

    public DatabaseReference getReference() {
        return reference;
    }

    public static CourseRepository getInstance(){
        if (courseRepository == null){
            courseRepository = new CourseRepository();
        }
        return courseRepository;
    }
    public List<Course> getCourses() {
        return this.courses;
    }

    public Course getCourse(String code) {

        Log.d("CODE",code);
        return this.courses
                .stream()
                .filter(course -> course.code.equals(code))
                .findFirst()
                .orElse(null);
//        return null;
    }
}