package com.example.b07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
//ok
import androidx.annotation.NonNull;
//import androidx.navigation.fragment.NavHostFragment;

import com.example.b07.databinding.ActivityMain2Binding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class MainActivity2 extends AppCompatActivity {

    private ActivityMain2Binding binding;
    //    TextView enteredCourseCode;
    DatabaseReference databaseRef;
    String offering, preq, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        final Button backButton = findViewById(R.id.backButton);
        databaseRef = FirebaseDatabase.getInstance("https://course-planner-14-default-rtdb.firebaseio.com/").getReference();

        String z[] = {""};
        final String[] k1 = {""};
        List<String> items2;
        items2 = new ArrayList<>();
        items2.add("--select--");
        Spinner s2 = findViewById(R.id.dropdownmenuq2);
        DatabaseReference reference2 = FirebaseDatabase.getInstance("https://course-planner-14-default-rtdb.firebaseio.com/").getReference().child("Course details");
        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    items2.add(snapshot.getKey().toString());
                    z[0] = z[0] + snapshot.getKey().toString();
                    final TextView edit_pre = findViewById(R.id.textView27);
//                    Toast.makeText(Course_addition_admin.this, "" + snapshot.getKey().toString() + "is added to the course pre-req.", Toast.LENGTH_SHORT).show();
//                    list.add(snapshot.getValue().toString());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        final String[] d = {""};
        final String[] k = {""};
        final TextView edit_pre = findViewById(R.id.textView29);
        edit_pre.setText("");
        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = items2.get(i);
                if(!Objects.equals(item, "--select--")){
                    if(!Objects.equals(k[0], "")) k[0] = k[0] + ",";
                    k[0] = item;
                    edit_pre.setText(k[0]);
                    Toast.makeText(MainActivity2.this, "" + item + " is selected to edit", Toast.LENGTH_SHORT).show();}
                else{
                    edit_pre.setText("");
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        if(items2 == null) {
            // Do something
        } else {
            // Do something else
            s2.setAdapter(new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, items2));
        }
        String oldCourseCode = k[0];
//        oldCourseCode = k[0];
        EditText newCourseName = findViewById(R.id.newCourseName);
        EditText newCourseCode = findViewById(R.id.newCourseCode);
//        EditText newOffering = findViewById(R.id.newOffering);
        EditText newPreq = findViewById(R.id.newPreq);
        Button buttonModifyName = findViewById(R.id.button_modify_name);
        buttonModifyName.setOnClickListener(v -> {
            String code= oldCourseCode;
            String newName = newCourseName.getText().toString();
            Toast.makeText(MainActivity2.this, "Log " + code, Toast.LENGTH_SHORT).show();
            //set new name on the admin side
            databaseRef.child("Course details").child(code).child("name").setValue(newName);
            DatabaseReference reference = FirebaseDatabase.getInstance("https://course-planner-14-default-rtdb.firebaseio.com/").getReference().child("students");
            reference.addValueEventListener(new ValueEventListener() {
                @Override

                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot snapshot: dataSnapshot.getChildren()){
//                        Toast.makeText(MainActivity2.this, "$$$" + Objects.requireNonNull(snapshot.child("courses")), Toast.LENGTH_SHORT).show();
                        HashMap hashMap2 = new HashMap();
                        hashMap2.put("name", newName);
                        databaseRef.child("students").child(Objects.requireNonNull(snapshot.getKey())).child("courses").child(""+code+"").updateChildren(hashMap2);
                        Toast.makeText(MainActivity2.this, "Course name is changed", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        });
        Button buttonModifyCourseCode = findViewById(R.id.button_modify_course_code);
        buttonModifyCourseCode.setOnClickListener(v -> {
            String code= oldCourseCode;
            String newCode=newCourseCode.getText().toString();
            code = newCode;
            // String name, offering, preq;
            HashMap hashMap=new HashMap();
            hashMap.put("code", newCode);
            databaseRef.child("Course details").child(newCode).updateChildren(hashMap);
            //String name;
            databaseRef.child("Course details").child(code).child("name").get( ).addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                        Log.e("firebase", "Error getting data", task.getException());
                    }
                    else {
                        Log.d("firebase", String.valueOf(task.getResult().getValue()));
                        name = task.getResult().getValue().toString();
                        HashMap hashMap2 = new HashMap();
                        hashMap2.put("name", name);
                        databaseRef.child("Course details").child(newCode).updateChildren(hashMap2);
                    }
                }
            });
            databaseRef.child("Course details").child(code).child("prereq").get( ).addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                        Log.e("firebase", "Error getting data", task.getException());
                    }
                    else {
                        Log.d("firebase", String.valueOf(task.getResult().getValue()));
                        preq = task.getResult().getValue().toString();
                        HashMap hashMap2 = new HashMap();
                        hashMap2.put("prereq", preq);
                        databaseRef.child("Course details").child(newCode).updateChildren(hashMap2);
                    }
                }
            });
            databaseRef.child("Course details").child(code).child("session").get( ).addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                        Log.e("firebase", "Error getting data", task.getException());
                    }
                    else {
                        Log.d("firebase", String.valueOf(task.getResult().getValue()));
                        offering = task.getResult().getValue().toString();
                        HashMap hashMap2 = new HashMap();
                        hashMap2.put("session", offering);
                        databaseRef.child("Course details").child(newCode).updateChildren(hashMap2);
                    }
                }
            });
            //delete old course
            databaseRef.child("Course details").child(code).setValue(null);
            //databaseRef.child("Course details").child(code).child("Course Code").setValue(newCourseCode.getText().toString());

            DatabaseReference reference = FirebaseDatabase.getInstance("https://course-planner-14-default-rtdb.firebaseio.com/").getReference().child("students");
//                Toast.makeText(MainActivity3.this, "$$$", Toast.LENGTH_SHORT).show();
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot snapshot: dataSnapshot.getChildren()){
//                        Toast.makeText(MainActivity2.this, "$$$" + Objects.requireNonNull(snapshot.child("courses")), Toast.LENGTH_SHORT).show();
                        HashMap hashMap2 = new HashMap();
                        hashMap2.put("session", offering);
                        hashMap2.put("name", name);
                        hashMap2.put("code", newCourseCode.getText().toString());
                        String code= oldCourseCode;
                        databaseRef.child("students").child(Objects.requireNonNull(snapshot.getKey())).child("courses").child(""+newCourseCode.getText().toString()+"").updateChildren(hashMap2);
                        Toast.makeText(MainActivity2.this, "Course code is changed", Toast.LENGTH_SHORT).show();
                        databaseRef.child("students").child(Objects.requireNonNull(snapshot.getKey())).child("courses").child(""+code+"").setValue(null);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        });
        Button buttonModifyOffering = findViewById(R.id.button_modify_offering);
        buttonModifyOffering.setOnClickListener(v -> {
            String code= oldCourseCode;
            String offernew = k1[0];
            //change the session offering in the admin list
            databaseRef.child("Course details").child(code).child("session").setValue(offernew);
            //databaseRef.child("Course details").child(code).child("Course Code").setValue(newCourseCode.getText().toString());
            DatabaseReference reference = FirebaseDatabase.getInstance("https://course-planner-14-default-rtdb.firebaseio.com/").getReference().child("students");
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot snapshot: dataSnapshot.getChildren()){
//                        Toast.makeText(MainActivity2.this, "$$$" + Objects.requireNonNull(snapshot.child("courses")), Toast.LENGTH_SHORT).show();
                        HashMap hashMap2 = new HashMap();
                        hashMap2.put("session", offernew);
                        databaseRef.child("students").child(Objects.requireNonNull(snapshot.getKey())).child("courses").child(""+code+"").updateChildren(hashMap2);
                        Toast.makeText(MainActivity2.this, "Course offerings are changed", Toast.LENGTH_SHORT).show();
                        //databaseRef.child("students").child(Objects.requireNonNull(snapshot.getKey())).child("courses").child(""+code+"").setValue(null);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        });
        List<String> items3;
        items3 = new ArrayList<>();
        items3.add("--select--");
        items3.add("Fall");
        items3.add("Winter");
        items3.add("Summer");
        TextView newOFF = findViewById(R.id.textView28);
        Spinner s3 = findViewById(R.id.sessionlist);
        s3.setAdapter(new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, items3));

        final String[] p = {""};
//        final TextView edit_pre = findViewById(R.id.textView29);
        newOFF.setText("");
        s3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = items3.get(i);
                if(!Objects.equals(item, "--select--")){
                    if(!k1[0].contains(item)){
                    if(!Objects.equals(k1[0], "")) k1[0] = "," + k1[0];
                    k1[0] = item + k1[0];
                    newOFF.setText(k1[0]);
                    Toast.makeText(MainActivity2.this, "" + item + " is added to the offering", Toast.LENGTH_SHORT).show();}
                    else{
                        Toast.makeText(MainActivity2.this, "" + item + " is already added", Toast.LENGTH_SHORT).show();
                    }
                    Button xxx = findViewById(R.id.button10);
                    xxx.setOnClickListener(v -> {
//            String code= oldCourseCode;
//            databaseRef.child("Course details").child(code).child("prereq").setValue(k1[0]);
                        newOFF.setText("");
                        k1[0] = "";
                        Toast.makeText(MainActivity2.this, "Course Pre-req are changed", Toast.LENGTH_SHORT).show();
                    });
                }
                else{
                    newOFF.setText("");
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        Button buttonModifyPreq = findViewById(R.id.button_modify_preq);
        buttonModifyPreq.setOnClickListener(v -> {
            String code= oldCourseCode;
            databaseRef.child("Course details").child(code).child("prereq").setValue(k1[0]);
            Toast.makeText(MainActivity2.this, "Course Pre-req are changed", Toast.LENGTH_SHORT).show();
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, admin_main.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });
    }
}