package com.example.b07;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class MainActivity3 extends AppCompatActivity {

    Intent intent, intent2;
    DatabaseReference databaseRef;
    Button delete, backButton;
    String username, code;
    EditText newCourseName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        databaseRef = FirebaseDatabase.getInstance("https://course-planner-14-default-rtdb.firebaseio.com/").getReference();
        newCourseName = findViewById(R.id.newCourseName);
        backButton = findViewById(R.id.backButton);
        delete = findViewById(R.id.button3);
        intent = getIntent();
        username = intent.getStringExtra("username");

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                code = newCourseName.getText().toString();
                //check if the code is empty
                if(!code.equals("")){
                    //delete course in admin
                    databaseRef.child("Course details").child(code).setValue(null);
                    //delete course in student
                    databaseRef.child("students").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                                databaseRef.child("students").child(Objects.requireNonNull(snapshot.getKey())).child("courses").child(code).setValue(null);
                                Toast.makeText(MainActivity3.this, code + " is deleted", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }
                else{
                    Toast.makeText(MainActivity3.this, "code can't be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent2 = new Intent(MainActivity3.this, admin_main.class);
                intent2.putExtra("username", username);
                startActivity(intent2);
            }
        });
    }
}
