package com.example.finaltimelinegenerator.repository;

import com.example.finaltimelinegenerator.model.Course;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CourseRepository {

    private final List<Course> courses = new ArrayList<>();

    public CourseRepository() {
        this.courses.add(
                new Course(
                        "CSCC24",
                        Arrays.asList("Winter", "Summer"),
                        Arrays.asList("CSCB07", "CSCB09")
                )
        );
        this.courses.add(
                new Course(
                        "CSCB07",
                        Arrays.asList("Fall", "Summer"),
                        Arrays.asList("CSCA48")
                )
        );
        this.courses.add(
                new Course(
                        "CSCB09",
                        Arrays.asList("Winter", "Summer"),
                        Arrays.asList("CSCA48")
                )
        );
        this.courses.add(
                new Course(
                        "CSCA48",
                        Arrays.asList("Winter", "Summer"),
                        Arrays.asList("CSCA08")
                )
        );
        this.courses.add(
                new Course(
                        "CSCA08",
                        Arrays.asList("Fall", "Winter"),
                        Arrays.asList()
                )
        );
        this.courses.add(
                new Course(
                        "CSCC63",
                        Arrays.asList("Fall", "Winter"),
                        Arrays.asList("CSCB63", "CSCB36")
                )
        );
        this.courses.add(
                new Course(
                        "CSCB63",
                        Arrays.asList("Winter", "Summer"),
                        Arrays.asList("CSCB36")
                )
        );
        this.courses.add(
                new Course(
                        "CSCB36",
                        Arrays.asList("Fall", "Summer"),
                        Arrays.asList("CSCA48", "CSCA67")
                )
        );
        this.courses.add(
                new Course(
                        "CSCA67",
                        Arrays.asList("Winter", "Fall"),
                        Arrays.asList()
                )
        );
    }

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
