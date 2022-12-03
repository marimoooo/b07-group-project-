package com.example.b07;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import com.example.b07.databinding.ActivityAdminCourseAdditionBinding;
import com.example.b07.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Admin_course_addition extends AppCompatActivity {

    Spinner s1;
    List<String> items1;

    Spinner s2;
    List<String> items2;

    ArrayList<String> listView = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        ArrayList<String> list = new ArrayList<>();
//        DatabaseReference reference = FirebaseDatabase.getInstance("https://course-planner-14-default-rtdb.firebaseio.com/").getReference();
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
//                    list.add(snapshot.getValue().toString());
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        super.onCreate(savedInstanceState);
        final String[] sq = new String[1];
        final String[] pq = new String[1];

//        s1 = findViewById(R.id.dropdownmenu);
        s2 = findViewById(R.id.dropdownmenuq);

        items1 = new ArrayList<>();
        items2 = new ArrayList<>();

        // The Course Offering schedule
        items1.add("Fall");
        items1.add("Winter");
        items1.add("Summer");
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Course details");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    items2.add(snapshot.child("Course Code").getValue().toString());
                    Toast.makeText(Admin_course_addition.this, "" + snapshot.child("Course Code").getValue().toString() + "is added to the course pre-req.", Toast.LENGTH_SHORT).show();
//                    list.add(snapshot.getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        // The Course Pre-req (to be defined)
        items2.add("--select--");
        items2.add("CSCA08");
        items2.add("CSCA48");
        items2.add("CSCB07");
        items2.add("CSCB09");
        items2.add("CSCB58");
        items2.add("CSCB36");
        items2.add("CSCB63");
        items2.add("MATA22");
        items2.add("MATA31");
        items2.add("CSCA67");
        items2.add("MATA37");
        items2.add("MATB41");
        items2.add("STAB52");
        items2.add("MATB24");

        if(s2 == null || items2 == null || androidx.appcompat.R.layout.support_simple_spinner_dropdown_item == 0) {
            // Do something
        } else {
            // Do something else
            s2.setAdapter(new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, items2));
        }
//        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                String item = items1.get(i);
//                sq[0] = item;
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });


        CheckBox c4 = findViewById(R.id.checkBox4);
        CheckBox c2 = findViewById(R.id.checkBox2);
        CheckBox c3 = findViewById(R.id.checkBox3);

        final String[] d = {""};


        final String[] k = {""};

        if(s2 != null) {
            s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String item = items2.get(i);
                    if (!Objects.equals(item, "--select--")) {
                        if (!Objects.equals(k[0], "")) k[0] = k[0] + ",";
                        k[0] = k[0] + item;
                        Toast.makeText(Admin_course_addition.this, "" + item + "is added to the course pre-req.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
        // Firebase thing by member 1
        final EditText edit_course_name = findViewById(R.id.editTextTextPersonName3);
        final EditText edit_course_code = findViewById(R.id.editTextTextPersonName2);
//        final View edit_course_offering = findViewById(R.id.dropdownmenu);
//        final View edit_course_pre_req = findViewById(R.id.dropdownmenuq);

        Button btn = findViewById(R.id.button_first);

        DAOCourseAddition dao = new DAOCourseAddition();
        if(btn != null){
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
            dao.add(emp).addOnSuccessListener(suc->
            {
                d[0] = "";
                k[0] = "";
                Toast.makeText(this, edit_course_code.getText().toString() + " is added!", Toast.LENGTH_SHORT).show();
//                emp.setCourse_pre_req("");
//                Toast.makeText(Admin_course_addition.this, "$$$" + emp.getCourse_pre_req().toString() + "$$$", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(er->
            {
                Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
            });

        });
        }
        Button first_to_second = findViewById(R.id.move);


//            @Override
//            public void onClick(View v) {
////                first_to_second.setVisibility(View.GONE);
////                Fragment fragment = new SecondFragment();
////                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
////                fragmentTransaction.replace(R.id.container, fragment).commit();
//            }
//        });

    }

//    int year_setter = 0;
//    int session_setter = 0;
//    int year = 0;
//    public void init() {
//        year_setter = 0;
//        TableLayout stk = (TableLayout) findViewById(R.id.tableLayout);
//        TableRow tbrow0 = new TableRow(this);
//        TextView tv0 = new TextView(this);
//        tv0.setText(" Sessions ");
//        tv0.setTextColor(Color.WHITE);
//        tbrow0.addView(tv0);
//        TextView tv1 = new TextView(this);
//        tv1.setText(" Courses ");
//        tv1.setTextColor(Color.WHITE);
//        tbrow0.addView(tv1);
//        stk.addView(tbrow0);
//        for (int i = 0; i < 25; i++) {
//            if(year_setter == 0){
//                year++;
//            }
//            else if(year_setter == 2){
//                year_setter = 0;
//            }
//            year_setter++;
//            TableRow tbrow = new TableRow(this);
//            TextView t1v = new TextView(this);
//            String current_session = "";
//            if(session_setter == 0) current_session = current_session + "Fall ";
//            else if(session_setter == 1) current_session = current_session + "Summer ";
//            else if(session_setter == 2) current_session = current_session + "Winter ";
//            current_session = current_session + year;
//            t1v.setText(current_session);
//            t1v.setTextColor(Color.WHITE);
//            t1v.setGravity(Gravity.CENTER);
//            tbrow.addView(t1v);
//            TextView t2v = new TextView(this);
//            t2v.setText("Product " + i);
//            t2v.setTextColor(Color.WHITE);
//            t2v.setGravity(Gravity.CENTER);
//            tbrow.addView(t2v);
//            stk.addView(tbrow);
//        }
//    }
}