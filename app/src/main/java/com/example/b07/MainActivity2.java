package com.example.b07;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
//ok
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
//import androidx.navigation.fragment.NavHostFragment;

import com.example.b07.databinding.ActivityCourseModificationBinding;
import com.example.b07.databinding.ActivityMain2Binding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Objects;

public class MainActivity2 extends AppCompatActivity {

    private ActivityMain2Binding binding;
    //    TextView enteredCourseCode;
    DatabaseReference databaseRef;
    String offering, preq, name;


//    @Nullable
//    @Override
//    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
//        databaseRef = FirebaseDatabase.getInstance().getReference();
//
//        HashMap hashMap=new HashMap();
//        hashMap.put("Course Name", "intro to cs2");
//        hashMap.put("Course Code", "cscb48");
//        hashMap.put("Course Offerings", "winter");
//        hashMap.put("Course Pre_requisites", "cscb08");
//        databaseRef.child("Course_details").child("cscb48").updateChildren(hashMap);
//
//
//        binding = ActivityMain2Binding.inflate(getLayoutInflater());
//        return binding.getRoot();
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        final Button backButton = findViewById(R.id.backButton);
        databaseRef = FirebaseDatabase.getInstance("https://course-planner-14-default-rtdb.firebaseio.com/").getReference();

//        binding.buttonModifyName.setOnClickListener();

//        binding.buttonModifyName.setOnClickListener(view1 -> {
//            String code=binding.newCourseName.getText().toString();
//            databaseRef.child("Course_details").child(code).child("Course Name").setValue(code);
//        });
        EditText oldCourseCode = findViewById(R.id.oldCourseCode);
        EditText newCourseName = findViewById(R.id.newCourseName);
        EditText newCourseCode = findViewById(R.id.newCourseCode);
        EditText newOffering = findViewById(R.id.newOffering);
        EditText newPreq = findViewById(R.id.newPreq);
        oldCourseCode.setOnClickListener(v -> {
            String code=oldCourseCode.getText().toString();
            Toast.makeText(MainActivity2.this, "Log " + code, Toast.LENGTH_SHORT).show();
        });
        Button buttonModifyName = findViewById(R.id.button_modify_name);
        buttonModifyName.setOnClickListener(v -> {
            String code=oldCourseCode.getText().toString();
            String newName = newCourseName.getText().toString();
            Toast.makeText(MainActivity2.this, "Log " + code, Toast.LENGTH_SHORT).show();
            //set new name on the admin side
            databaseRef.child("Course details").child(code).child("Course Name").setValue(newName);
            DatabaseReference reference = FirebaseDatabase.getInstance("https://course-planner-14-default-rtdb.firebaseio.com/").getReference().child("students");
            reference.addValueEventListener(new ValueEventListener() {
                @Override

                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot snapshot: dataSnapshot.getChildren()){
//                        Toast.makeText(MainActivity2.this, "$$$" + Objects.requireNonNull(snapshot.child("courses")), Toast.LENGTH_SHORT).show();
                        HashMap hashMap2 = new HashMap();
                        hashMap2.put("name", newName);
                        databaseRef.child("students").child(Objects.requireNonNull(snapshot.getKey())).child("courses").child(""+code+"").updateChildren(hashMap2);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        });
        Button buttonModifyCourseCode = findViewById(R.id.button_modify_course_code);
        buttonModifyCourseCode.setOnClickListener(v -> {
            String code=oldCourseCode.getText().toString();
            String newCode=newCourseCode.getText().toString();
            // String name, offering, preq;
            HashMap hashMap=new HashMap();
            hashMap.put("Course Code", newCode);
            databaseRef.child("Course details").child(newCode).updateChildren(hashMap);
            //String name;
            databaseRef.child("Course details").child(code).child("Course Name").get( ).addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                        Log.e("firebase", "Error getting data", task.getException());
                    }
                    else {
                        Log.d("firebase", String.valueOf(task.getResult().getValue()));
                        name = task.getResult().getValue().toString();
                        HashMap hashMap2 = new HashMap();
                        hashMap2.put("Course Name", name);
                        databaseRef.child("Course details").child(newCode).updateChildren(hashMap2);
                    }
                }
            });
            databaseRef.child("Course details").child(code).child("Course Pre-requisites").get( ).addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                        Log.e("firebase", "Error getting data", task.getException());
                    }
                    else {
                        Log.d("firebase", String.valueOf(task.getResult().getValue()));
                        preq = task.getResult().getValue().toString();
                        HashMap hashMap2 = new HashMap();
                        hashMap2.put("Course Pre-requisites", preq);
                        databaseRef.child("Course details").child(newCode).updateChildren(hashMap2);
                    }
                }
            });
            databaseRef.child("Course details").child(code).child("Session").get( ).addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                        Log.e("firebase", "Error getting data", task.getException());
                    }
                    else {
                        Log.d("firebase", String.valueOf(task.getResult().getValue()));
                        offering = task.getResult().getValue().toString();
                        HashMap hashMap2 = new HashMap();
                        hashMap2.put("Session", offering);
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
                        hashMap2.put("Session", offering);
                        hashMap2.put("name", name);
                        hashMap2.put("code", newCourseCode.getText().toString());
                        databaseRef.child("students").child(Objects.requireNonNull(snapshot.getKey())).child("courses").child(""+newCourseCode.getText().toString()+"").updateChildren(hashMap2);
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
            String code=oldCourseCode.getText().toString();
            String offernew = newOffering.getText().toString();
            //change the session offering in the admin list
            databaseRef.child("Course details").child(code).child("Session").setValue(offernew);
            //databaseRef.child("Course details").child(code).child("Course Code").setValue(newCourseCode.getText().toString());
            DatabaseReference reference = FirebaseDatabase.getInstance("https://course-planner-14-default-rtdb.firebaseio.com/").getReference().child("students");
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot snapshot: dataSnapshot.getChildren()){
//                        Toast.makeText(MainActivity2.this, "$$$" + Objects.requireNonNull(snapshot.child("courses")), Toast.LENGTH_SHORT).show();
                        HashMap hashMap2 = new HashMap();
                        hashMap2.put("Session", offernew);
                        databaseRef.child("students").child(Objects.requireNonNull(snapshot.getKey())).child("courses").child(""+code+"").updateChildren(hashMap2);
                        //databaseRef.child("students").child(Objects.requireNonNull(snapshot.getKey())).child("courses").child(""+code+"").setValue(null);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        });
        Button buttonModifyPreq = findViewById(R.id.button_modify_preq);
        buttonModifyPreq.setOnClickListener(v -> {
            String code= oldCourseCode.getText().toString();
            databaseRef.child("Course details").child(code).child("Course Pre-requisites").setValue(newPreq.getText().toString());
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, admin_main.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });



////            databaseRef.child("Course_details").child(code).removeValue()get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
////                @Override
////                public void onComplete(@NonNull Task<DataSnapshot> task) {
////                    if (!task.isSuccessful()) {
////                        Log.e("firebase", "Error getting data", task.getException());
////                    }
////                    else {
//////                        for(DataSnapshot ds: task.getResult().getChildren()) {
//////                            String key = ds.getKey();
//////                            if(key.equals(code)) {
//////
//////                            }
//////                        }
////
////                        //Log.d("firebase", String.valueOf(task.getResult().getValue()));
////                    }
////                }
//            });

        //child("cscb48").setValue(null);

//        binding.buttonModifyName.setOnClickListener(view12 -> {
//            String code=binding.newCourseName.getText().toString();
//            databaseRef.child("Course_details").child(code).child("Course Name").setValue(code);
//        });
//
//        binding.buttonModifyCourseCode.setOnClickListener(view13 -> {
//            String code=binding.newCourseName.getText().toString();
//            databaseRef.child("Course_details").child(code).child("Course Code").setValue(code);
//        });
//
//        binding.buttonModifyOffering.setOnClickListener(view14 -> {
//            String code=binding.newOffering.getText().toString();
//            databaseRef.child("Course_details").child(code).child("Course Offerings").setValue(code);
//        });
//
//        binding.buttonModifyPreq.setOnClickListener(view15 -> {
//            String code=binding.newPreq.getText().toString();
//            databaseRef.child("Course_details").child(code).child("Course Pre_requisites").setValue(code);
//        });
    }

}