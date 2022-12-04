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

    DatabaseReference databaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        EditText newCourseName = findViewById(R.id.newCourseName);
        databaseRef = FirebaseDatabase.getInstance("https://course-planner-14-default-rtdb.firebaseio.com/").getReference();
        Button delete = findViewById(R.id.button3);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        final Button backButton = findViewById(R.id.backButton);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = newCourseName.getText().toString();
                //check if the code is empty
                if(!code.equals("")){
                    Toast.makeText(MainActivity3.this, ""+ code + " is deleted", Toast.LENGTH_SHORT).show();
                    databaseRef.child("Course details").child(""+code+"").removeValue();
                    startActivity(new Intent(MainActivity3.this, admin_main.class));
                    finish();

                    DatabaseReference reference = FirebaseDatabase.getInstance("https://course-planner-14-default-rtdb.firebaseio.com/").getReference().child("students");
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                                databaseRef.child("students").child(Objects.requireNonNull(snapshot.getKey())).child("courses").child(""+code+"").removeValue();
                                finish();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }else{
                    Toast.makeText(MainActivity3.this, "code can't be empty", Toast.LENGTH_SHORT).show();
                }

            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this, admin_main.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });
    }
}




//  DatabaseReference reference = FirebaseDatabase.getInstance("https://course-planner-14-default-rtdb.firebaseio.com/").getReference().child("students");
//reference.addValueEventListener(new ValueEventListener() {
//@Override
//
//public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//        for(DataSnapshot snapshot: dataSnapshot.getChildren()){
////                        Toast.makeText(MainActivity2.this, "$$$" + Objects.requireNonNull(snapshot.child("courses")), Toast.LENGTH_SHORT).show();
//        HashMap hashMap2 = new HashMap();
//        hashMap2.put("name", newName);
//        databaseRef.child("students").child(Objects.requireNonNull(snapshot.getKey())).child("courses").child(""+code+"").updateChildren(hashMap2);
//        }
//        }
//@Override
//public void onCancelled(@NonNull DatabaseError error) {
//
//        }
//        });