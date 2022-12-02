package com.example.recyclerview2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddActivity extends AppCompatActivity {
    FirebaseDatabase data;
    DatabaseReference ref;
    private EditText OfferingSession, CourseCode;
    private Button btnSave;
    TextView tvTitle;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        OfferingSession = findViewById(R.id.ETOffer);
        CourseCode = findViewById(R.id.ETCode);
        btnSave = findViewById(R.id.button);
        tvTitle = findViewById(R.id.tvTitle);


        data = FirebaseDatabase.getInstance();
        String name = "abc";
        ref = data.getReference("students").child(name).child("takenCourse");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = CourseCode.getText().toString();
                String session = OfferingSession.getText().toString();
                Course course = new Course(code,session);
                ref.setValue(course).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddActivity.this,
                                "Course is Added!",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


    }
}