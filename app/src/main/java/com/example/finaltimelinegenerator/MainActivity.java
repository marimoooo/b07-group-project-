package com.example.finaltimelinegenerator;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.finaltimelinegenerator.model.Course;
import com.example.finaltimelinegenerator.repository.CourseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {
    ListView listCourses;
    ArrayAdapter<String> adapter;
    List<String> arrayCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CourseRepository repository = new CourseRepository();
        List<Course> courses = repository.getCourses();
        arrayCourses = courses.stream().map(c -> c.code).collect(Collectors.toList());
        listCourses = (ListView) findViewById(R.id.ListCourses);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, arrayCourses);
        listCourses.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
        //return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.ItemDone) {
            ArrayList<String> itemSelected = new ArrayList<>();

            for (int i = 0; i < listCourses.getCount(); i++) {

                if (listCourses.isItemChecked(i)){
                    itemSelected.add((String) listCourses.getItemAtPosition(i));
                }
            }
            Intent intent = new Intent(this,TimelineTable.class);
            intent.putStringArrayListExtra("futureCourses", itemSelected);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


}