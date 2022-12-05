package com.example.b07;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MainActivity3 extends AppCompatActivity {

    Intent intent, intent2;
    DatabaseReference databaseRef;
    Button delete, backButton;
    String username, code, newP;
    EditText newCourseName;
    List<String> prereqList;

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
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                    //delete course in course pre-req of other courses
                    databaseRef.child("Course details").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                Log.d("myTag", "current course: " + snapshot.getKey());
                                databaseRef.child("Course details").child(Objects.requireNonNull(snapshot.getKey())).child("prereq").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                                        if (!task.isSuccessful()) {
                                            Log.e("firebase", "Error getting data", task.getException());
                                        } else {
                                            Log.d("firebase", String.valueOf(task.getResult().getValue()));
                                            if (task.getResult().getValue(String.class).contains(code)){
                                                String p = task.getResult().getValue(String.class);
                                                Log.d("myTag", "current preq of " +snapshot.getKey()+ ": " + p);
                                                prereqList = Arrays.asList(p.split(","));
                                                newP="";
                                                for( String s : prereqList){
                                                    Log.d("myTag", "origional newP: " + newP);
                                                    if(!s.equals(code)){
                                                        newP+=s + ",";
                                                        Log.d("myTag", "new newP: " + newP);
                                                    }
                                                }
                                                Log.d("myTag", "final newP: " + newP);
                                                if(newP.equals("")){
                                                    databaseRef.child("Course details").child(Objects.requireNonNull(snapshot.getKey())).child("prereq").setValue("");
                                                    Toast.makeText(MainActivity3.this, code + " is deleted", Toast.LENGTH_SHORT).show();
                                                    //finish();
                                                }else{
                                                    databaseRef.child("Course details").child(Objects.requireNonNull(snapshot.getKey())).child("prereq").setValue(newP.substring(0,newP.length()-1));
                                                    Toast.makeText(MainActivity3.this, code + " is deleted", Toast.LENGTH_SHORT).show();
                                                    //finish();
                                                }
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
