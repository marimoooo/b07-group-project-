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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;

public class MainActivity2 extends AppCompatActivity {

    private ActivityMain2Binding binding;
//    TextView enteredCourseCode;
    DatabaseReference databaseRef;

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
            Toast.makeText(MainActivity2.this, "Log " + code, Toast.LENGTH_SHORT).show();
            databaseRef.child("Course details").child(code).child("Course Name").setValue(newCourseName.getText().toString());
        });
        Button buttonModifyCourseCode = findViewById(R.id.button_modify_course_code);
        buttonModifyCourseCode.setOnClickListener(v -> {
            String code=oldCourseCode.getText().toString();
            databaseRef.child("Course details").child(code).child("Course Code").setValue(newCourseCode.getText().toString());
        });
        Button buttonModifyOffering = findViewById(R.id.button_modify_offering);
        buttonModifyOffering.setOnClickListener(v -> {
            String code=oldCourseCode.getText().toString();
            databaseRef.child("Course details").child(code).child("Session").setValue(newOffering.getText().toString());
        });
        Button buttonModifyPreq = findViewById(R.id.button_modify_preq);
        buttonModifyPreq.setOnClickListener(v -> {
            String code= oldCourseCode.getText().toString();
            databaseRef.child("Course details").child(code).child("Course Pre-requisites").setValue(newPreq.getText().toString());
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