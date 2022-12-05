package com.example.b07;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.b07.databinding.ActivityCourseAdditionAdminBinding;
import com.example.b07.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Course_addition_admin extends AppCompatActivity {

    Spinner s1;
    List<String> items1;


    private Button first_to_second;

    Spinner s2;
    List<String> items2;

    //    public ListView listView;
    ArrayList<String> listView = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        super.onCreate(savedInstanceState);

        com.example.b07.databinding.ActivityCourseAdditionAdminBinding binding = ActivityCourseAdditionAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final String[] sq = new String[1];
        final String[] pq = new String[1];

        s2 = findViewById(R.id.dropdownmenuq);

        items1 = new ArrayList<>();
        items2 = new ArrayList<>();

        items1.add("Fall");
        items1.add("Winter");
        items1.add("Summer");
        final String[] z = {""};
        DatabaseReference reference = FirebaseDatabase.getInstance("https://course-planner-14-default-rtdb.firebaseio.com/").getReference().child("Course details");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    items2.add(snapshot.getKey().toString());
                    z[0] = z[0] + snapshot.getKey().toString();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        // The Course Pre-req (to be defined)
        items2.add("--select--");

        if(items2 == null) {
            // Do something
        } else {
            // Do something else
            s2.setAdapter(new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, items2));
        }

        CheckBox c4 = findViewById(R.id.checkBox4);
        CheckBox c2 = findViewById(R.id.checkBox2);
        CheckBox c3 = findViewById(R.id.checkBox3);

        final String[] d = {""};


        final String[] k = {""};


        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = items2.get(i);
                if(!Objects.equals(item, "--select--")){
                    if(!Objects.equals(k[0], "")) k[0] = k[0] + ",";
                    k[0] = k[0] + item;
                    Toast.makeText(Course_addition_admin.this, "" + item + "is added to the course pre-req.", Toast.LENGTH_SHORT).show();}
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        final EditText edit_course_name = findViewById(R.id.editTextTextPersonName3);
        final EditText edit_course_code = findViewById(R.id.editTextTextPersonName2);

        listView.add(edit_course_code.getText().toString());

        Button btn = findViewById(R.id.button_first);

        DAOCourseAddition dao = new DAOCourseAddition();

        btn.setOnClickListener(v->
        {
            if(c2.isChecked()){
                if(!Objects.equals(d[0], "")) d[0] = d[0] + ",";
                d[0] = d[0] + "Fall";
            }
            if(c3.isChecked()){
                if(!Objects.equals(d[0], "")) d[0] = d[0] + ",";
                d[0] = d[0] + "Winter";
            }
            if(c4.isChecked()){
                if(!Objects.equals(d[0], "")) d[0] = d[0] + ",";
                d[0] = d[0] + "Summer";
            }
            Course_details emp = new Course_details(edit_course_name.getText().toString(), edit_course_code.getText().toString(), d[0], k[0]);
            d[0] = "";
            dao.add(emp).addOnSuccessListener(suc->
            {
                Toast.makeText(this, edit_course_code.getText().toString() + " is added!", Toast.LENGTH_SHORT).show();
                emp.setCourse_pre_req("");
//                Toast.makeText(Course_addition_admin.this, "$$$" + emp.getCourse_pre_req().toString() + "$$$", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(Course_addition_admin.this, admin_main.class);
                intent.putExtra("username", username);
                startActivity(intent1);
            }).addOnFailureListener(er->
            {
                Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
            });
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


