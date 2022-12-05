package com.example.b07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
//ok
import androidx.annotation.NonNull;
//import androidx.navigation.fragment.NavHostFragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class MainActivity2 extends AppCompatActivity {

    Intent intent, intent2;
    DatabaseReference databaseRef;
    String username, offering, preq, name, code, newName, newCode, offernew, preqNew, newP, finalPreq, finalOffer, newO;
    Button backButton, buttonModifyName, buttonModifyCourseCode, buttonModifyOffering, buttonModifyPreq;
    EditText oldCourseCode, newCourseName, newCourseCode, newOffering, newPreq;
    List<String> prereqList; //= new ArrayList<>();
    List<String> prereqList2; //= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        intent = getIntent();
        username = intent.getStringExtra("username");
        databaseRef = FirebaseDatabase.getInstance("https://course-planner-14-default-rtdb.firebaseio.com/").getReference();
        backButton = findViewById(R.id.backButton);
        oldCourseCode = findViewById(R.id.oldCourseCode);
        newCourseName = findViewById(R.id.newCourseName);
        newCourseCode = findViewById(R.id.newCourseCode);
        newOffering = findViewById(R.id.newOffering);
        newPreq = findViewById(R.id.newPreq);
        buttonModifyName = findViewById(R.id.button_modify_name);
        buttonModifyCourseCode = findViewById(R.id.button_modify_course_code);
        buttonModifyOffering = findViewById(R.id.button_modify_offering);
        buttonModifyPreq = findViewById(R.id.button_modify_preq);

        buttonModifyName.setOnClickListener(v -> {
            code = oldCourseCode.getText().toString();
            newName = newCourseName.getText().toString();
            if (!code.equals("") && !newName.equals("") ){
                Toast.makeText(MainActivity2.this, "Log " + code, Toast.LENGTH_SHORT).show();
                //set new name on the admin side
                databaseRef.child("Course details").child(code).child("name").setValue(newName);
                //set new name on the student side
                databaseRef.child("students").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            databaseRef.child("students").child(Objects.requireNonNull(snapshot.getKey())).child("courses").child(code).child("name").setValue(newName);
                            Toast.makeText(MainActivity2.this, "Course name is changed", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }else{
                Toast.makeText(MainActivity2.this, "input fields can't be empty", Toast.LENGTH_SHORT).show();
            }
        });

        buttonModifyCourseCode.setOnClickListener(v -> {
            code = oldCourseCode.getText().toString();
            newCode = newCourseCode.getText().toString();
            if (!code.equals("") && !newCode.equals("")) {
                databaseRef.child("Course details").child(newCode).child("code").setValue(newCode);
                //get name from old course code add it to the new course code
                databaseRef.child("Course details").child(code).child("name").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (!task.isSuccessful()) {
                            Log.e("firebase", "Error getting data", task.getException());
                        } else {
                            Log.d("firebase", String.valueOf(task.getResult().getValue()));
                            name = task.getResult().getValue().toString();
                            HashMap hashMap2 = new HashMap();
                            hashMap2.put("name", name);
                            databaseRef.child("Course details").child(newCode).updateChildren(hashMap2);
                        }
                    }
                });
                //get preq from old course code add it to the new course code
                databaseRef.child("Course details").child(code).child("prereq").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (!task.isSuccessful()) {
                            Log.e("firebase", "Error getting data", task.getException());
                        } else {
                            Log.d("firebase", String.valueOf(task.getResult().getValue()));
                            preq = task.getResult().getValue().toString();
                            HashMap hashMap2 = new HashMap();
                            hashMap2.put("prereq", preq);
                            databaseRef.child("Course details").child(newCode).updateChildren(hashMap2);
                        }
                    }
                });
                //get offering from old course code and add it to the new course code
                databaseRef.child("Course details").child(code).child("session").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (!task.isSuccessful()) {
                            Log.e("firebase", "Error getting data", task.getException());
                        } else {
                            Log.d("firebase", String.valueOf(task.getResult().getValue()));
                            offering = task.getResult().getValue().toString();
                            databaseRef.child("Course details").child(newCode).child("session").setValue(offering);
                        }
                    }
                });
                //delete old course in admin
                databaseRef.child("Course details").child(code).setValue(null);
                //update course code in student
                databaseRef.child("students").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            databaseRef.child("students").child(Objects.requireNonNull(snapshot.getKey())).child("courses").child(newCode).child("name").setValue(name);
                            databaseRef.child("students").child(Objects.requireNonNull(snapshot.getKey())).child("courses").child(newCode).child("code").setValue(newCode);
                            databaseRef.child("students").child(Objects.requireNonNull(snapshot.getKey())).child("courses").child(newCode).child("session").setValue(offering);
                            databaseRef.child("students").child(Objects.requireNonNull(snapshot.getKey())).child("courses").child(code).setValue(null);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                //update the course code for courses that has the current course as pre-req
                databaseRef.child("Course details").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            databaseRef.child("Course details").child(Objects.requireNonNull(snapshot.getKey())).child("prereq").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DataSnapshot> task) {
                                    if (!task.isSuccessful()) {
                                        Log.e("firebase", "Error getting data", task.getException());
                                    } else {
                                        Log.d("firebase", String.valueOf(task.getResult().getValue()));
                                        if (task.getResult().getValue(String.class).contains(code)){
                                            String p = task.getResult().getValue(String.class);
                                            prereqList = Arrays.asList(p.split(","));
                                            newP="";
                                            for( String s : prereqList){
                                                if(s.equals(code)){
                                                    newP+=newCode + ",";
                                                }else{
                                                    newP+=s + ",";
                                                }
                                            }
                                            databaseRef.child("Course details").child(Objects.requireNonNull(snapshot.getKey())).child("prereq").setValue(newP.substring(0,newP.length()-1));
                                            Toast.makeText(MainActivity2.this, "Course code is changed", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            });
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }else{
                Toast.makeText(MainActivity2.this, "input fields can't be empty", Toast.LENGTH_SHORT).show();
            }
        });

        buttonModifyOffering.setOnClickListener(v -> {
            finalOffer="";
            code = oldCourseCode.getText().toString();
            offernew = newOffering.getText().toString();
            if (!code.equals("") && !offernew.equals("")) {
                //change the session offering in the admin list
                databaseRef.child("Course details").child(code).child("session").setValue(offernew);
                //change the session offering in the student list
                databaseRef.child("students").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            databaseRef.child("students").child(Objects.requireNonNull(snapshot.getKey())).child("courses").child(code).child("session").setValue(offernew);
                            Toast.makeText(MainActivity2.this, "Course offerings are changed", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }else{
                Toast.makeText(MainActivity2.this, "input fields can't be empty", Toast.LENGTH_SHORT).show();
            }
        });

        buttonModifyPreq.setOnClickListener(v -> {
            finalPreq="";
            code = oldCourseCode.getText().toString();
            preqNew = newPreq.getText().toString();
            if (!code.equals("")) {
                //if there are multiple pre-reqs split them and add them accordingly
                if (preqNew.contains(",")){
                    prereqList2 = Arrays.asList(preqNew.split(","));
                    for(String p : prereqList2) {
                        //check if the new pre-req is actually a course in the database
                       databaseRef.child("Course details").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.hasChild(p)) {
                                    //if the current pre-req exists in the database add it to the string that will represent the new pre-req
                                    finalPreq += p + ",";
                                }
                                if (finalPreq.equals("")) {
                                    Toast.makeText(MainActivity2.this, "None of the entered pre-req exist in database", Toast.LENGTH_SHORT).show();
                                } else {
                                    databaseRef.child("Course details").child(code).child("prereq").setValue(finalPreq.substring(0, finalPreq.length() - 1));
                                    Toast.makeText(MainActivity2.this, "Course Pre-req is changed. If a course has not been added it is not in the database", Toast.LENGTH_SHORT).show();
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                    }
                }
                else{
                    //check if the new pre-req is actually a course in the database
                    databaseRef.child("Course details").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.hasChild(preqNew)){
                                //change admin, no need to change student because pre-req does not need to be added to the student database
                                databaseRef.child("Course details").child(code).child("prereq").setValue(preqNew);
                                Toast.makeText(MainActivity2.this, "Course Pre-req is changed", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(MainActivity2.this, "Entered pre-req does not exist in database", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }
            }else{
                Toast.makeText(MainActivity2.this, "input fields can't be empty", Toast.LENGTH_SHORT).show();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent2 = new Intent(MainActivity2.this, admin_main.class);
                intent2.putExtra("username", username);
                startActivity(intent2);
            }
        });
    }
}