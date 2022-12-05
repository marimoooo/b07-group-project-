package com.example.b07.repository;

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


//            public CourseRepository() {
//        this.courses.add(
//                new Course(
//                        "CSCC24",
//                        Arrays.asList("Winter", "Summer"),
//                        Arrays.asList("CSCB07", "CSCB09")
//                )
//        );
//        this.courses.add(
//                new Course(
//                        "CSCB07",
//                        Arrays.asList("Fall", "Summer"),
//                        Arrays.asList("CSCA48")
//                )
//        );
//        this.courses.add(
//                new Course(
//                        "CSCB09",
//                        Arrays.asList("Winter", "Summer"),
//                        Arrays.asList("CSCA48")
//                )
//        );
//        this.courses.add(
//                new Course(
//                        "CSCA48",
//                        Arrays.asList("Winter", "Summer"),
//                        Arrays.asList("CSCA08")
//                )
//        );
//        this.courses.add(
//                new Course(
//                        "CSCA08",
//                        Arrays.asList("Fall", "Winter"),
//                        Arrays.asList()
//                )
//        );
//        this.courses.add(
//                new Course(
//                        "CSCC63",
//                        Arrays.asList("Fall", "Winter"),
//                        Arrays.asList("CSCB63", "CSCB36")
//                )
//        );
//        this.courses.add(
//                new Course(
//                        "CSCB63",
//                        Arrays.asList("Winter", "Summer"),
//                        Arrays.asList("CSCB36")
//                )
//        );
//        this.courses.add(
//                new Course(
//                        "CSCB36",
//                        Arrays.asList("Fall", "Summer"),
//                        Arrays.asList("CSCA48", "CSCA67")
//                )
//        );
//        this.courses.add(
//                new Course(
//                        "CSCA67",
//                        Arrays.asList("Winter", "Fall"),
//                        Arrays.asList()
//                )
//        );
//    }
//   }
    public List<Course> getCourses() {
        return this.courses;
    }

    public Course getCourse(String code) {
        return this.courses
                .stream()
                .filter(course -> course.code.equals(code))
                .findFirst()
                .get();
    }
}
