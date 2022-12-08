package com.example.b07.generator;

import android.util.Log;

import com.example.b07.model.Course;
import com.example.b07.repository.CourseRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Timeline {

    private final int year;
    private final int month;
    private final CourseRepository courseRepo;

    public Timeline(CourseRepository courseRepository) {
        this.courseRepo = courseRepository;
        this.year = LocalDate.now().getYear();
        this.month = LocalDate.now().getMonthValue();
        Log.d("COURSE REPO",courseRepo.getCourses().toString());
    }

    public List<Session> sessions(List<String> futureCourses, List<String> takenCourses) {
        List<String> coursesFounded = this.foundCourses(futureCourses, takenCourses, 0);
        List<Session> timeLine = new ArrayList<>();

        Season mySeason = Season.valueOf(currentSeason().getNextSeason().toUpperCase());
        int myYear = year;
        if (currentSeason().getName().equals("Fall")) { myYear++; }

        while (!coursesFounded.isEmpty()) {
            List<String> coursesInSeason = new ArrayList<>();

            for (int i = 0; i < coursesFounded.size(); i++) {

//            System.out.println(coursesFounded);
                boolean available = true;
                String course = coursesFounded.get(i);

                Course courseData = courseRepo.getCourse(course);
                if (courseData.getSessionList().contains(mySeason.getName())) {
                    for (String prerequisite : courseData.getPrereqList()) {
                        available = !coursesFounded.contains(prerequisite);
                        if (!available)
                            break;
                    }
                    if (available) {
                        coursesInSeason.add(course);
                    }
                }
            }

            if (!coursesInSeason.isEmpty()) {
                timeLine.add(new Session(mySeason.getName(), myYear, coursesInSeason));
            }

//            coursesFounded[1,2,3,4,5]  coursesInSeason[2,3]
//            coursesFounded[1,4,5]
            coursesFounded = coursesFounded.stream().filter(c -> !coursesInSeason.contains(c)).collect(Collectors.toList());

            if (mySeason.getName().equals("Fall")) {
                myYear++;
            }
            mySeason = Season.valueOf(mySeason.getNextSeason().toUpperCase());
        }

        return timeLine;
    }

    private Season currentSeason() {
        if(month >= Season.WINTER.getMonthStart() && month <= Season.WINTER.getMonthEnd()) {
            return Season.WINTER;
        }
        if(month >= Season.SUMMER.getMonthStart() && month <= Season.SUMMER.getMonthEnd()) {
            return Season.SUMMER;
        }
        return Season.FALL;
    }

    private List<String> foundCourses(List<String> futureCourses, List<String> takenCourses, int index) {
        Log.d("FUTURE COURSE", futureCourses.toString());
        List<String> coursesLeft = this.validTakenCourses(futureCourses, takenCourses);
        if (!coursesLeft.isEmpty()) {

            List<String> lastCourses, firstCourses;
            Log.d("INDEX", String.valueOf(index));
            Log.d("FUTURE COURSES SIZE", String.valueOf(futureCourses.size()));

            if(index == futureCourses.size()) {
                return new ArrayList<>();
            } else {
                Course course = this.courseRepo.getCourse(futureCourses.get(index));

                firstCourses = foundCourses(course.getPrereqList(), takenCourses, 0);

                lastCourses = foundCourses(futureCourses, takenCourses, ++index);

            }

            Set<String> allCourses = new LinkedHashSet<>(firstCourses);
            allCourses.addAll(lastCourses);
            allCourses.addAll(coursesLeft);
            return new ArrayList<>(allCourses);
        }

        return coursesLeft;
    }

    private List<String> validTakenCourses(List<String> futureCourses, List<String> takenCourses) {
        return futureCourses.stream().filter(c -> !takenCourses.contains(c)).collect(Collectors.toList());
    }
}