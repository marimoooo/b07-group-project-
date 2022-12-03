package com.example.timelinegenerator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.timelinegenerator.generator.Session;
import com.example.timelinegenerator.generator.Timeline;
import com.example.timelinegenerator.model.Student;
import com.example.timelinegenerator.repository.CourseRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TimelineT extends AppCompatActivity {

    TableLayout tableTimeline;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline_t);

        Intent intent = getIntent();

         List<String> courses = intent.getStringArrayListExtra("futureCourses");
         Student student = new Student("Victoria", Arrays.asList("CSCA08", "CSCA48", "CSCA67"));
         Timeline timeline = new Timeline(new CourseRepository());
         List<Session> sessions = timeline.sessions(courses, student.courses);
         tableTimeline = (TableLayout) findViewById(R.id.Timeline);

         for (Session session: sessions) {
         TableRow row = new TableRow(this);
         TextView tvSession = new TextView(this);
         tvSession.setText(session.season + " " + session.year);
         row.addView(tvSession);

         TextView tvCourses = new TextView(this);
         tvCourses.setText(session.courses.toString());
         row.addView(tvCourses);
         tableTimeline.addView(row);
         }

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