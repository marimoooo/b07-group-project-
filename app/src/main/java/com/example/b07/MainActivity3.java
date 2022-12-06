package com.example.b07;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity3 extends AppCompatActivity {

    DatabaseReference databaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
//        EditText newCourseName = findViewById(R.id.newCourseName);
        final TextView edit_pre = findViewById(R.id.textView27);
        final Button delete2 = findViewById(R.id.button12);
        edit_pre.setText("");
        final String[] m = {""};
        databaseRef = FirebaseDatabase.getInstance("https://course-planner-14-default-rtdb.firebaseio.com/").getReference();
        Button delete = findViewById(R.id.button3);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        String z[] = {""};
        List<String> items2;
        items2 = new ArrayList<>();
        items2.add("--select--");
        Spinner s2 = findViewById(R.id.dropdownmenuq5);
        DatabaseReference reference = FirebaseDatabase.getInstance("https://course-planner-14-default-rtdb.firebaseio.com/").getReference().child("Course details");
        reference.addValueEventListener(new ValueEventListener() {
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
        delete2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_pre.setText("");
//                Toast.makeText(Course_addition_admin.this, "Selected courses removed", Toast.LENGTH_SHORT).show();
            }
        });
        if(items2 == null) {
            // Do something
        } else {
            // Do something else
            s2.setAdapter(new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, items2));
        }
        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        String k[] = {""};
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = items2.get(i);
                if(!Objects.equals(item, "--select--")){
//                    if(!Objects.equals(k[0], "")) k[0] = k[0] + ",";
                    m[0] = item;
                    edit_pre.setText(item);

//                    edit_pre.setText(k[0]);
                    Toast.makeText(MainActivity3.this, "" + item + " is selected to delete.", Toast.LENGTH_SHORT).show();}
                else{
                    edit_pre.setText("");
                    m[0] = "";
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        final Button backButton = findViewById(R.id.backButton);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = m[0];
                //check if the code is empty
                if (!(code.equals("") || code.equals("--select--"))) {
                        Toast.makeText(MainActivity3.this, "" + code + " is deleted", Toast.LENGTH_SHORT).show();
                        databaseRef.child("Course details").child("" + code + "").removeValue();
                        startActivity(new Intent(MainActivity3.this, admin_main.class));
                        finish();

                        DatabaseReference reference = FirebaseDatabase.getInstance("https://course-planner-14-default-rtdb.firebaseio.com/").getReference().child("students");
                        reference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                    databaseRef.child("students").child(Objects.requireNonNull(snapshot.getKey())).child("courses").child("" + code + "").removeValue();
                                    finish();
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

//                    DatabaseReference ref = FirebaseDatabase.getInstance("https://course-planner-14-default-rtdb.firebaseio.com/").getReference().child("Course details");
//                    ref.addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                                String code = snapshot.getKey();
//                                Toast.makeText(MainActivity3.this, code, Toast.LENGTH_SHORT).show();
//                                DatabaseReference mostafa = FirebaseDatabase.getInstance("https://course-planner-14-default-rtdb.firebaseio.com/").getReference().child("Course details").child(code);
//                                mostafa.addValueEventListener(new ValueEventListener() {
//                                    @Override
//                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                                            String list = Objects.requireNonNull(snapshot.getValue()).toString();
//                                            Toast.makeText(MainActivity3.this, snapshot.getValue().toString(), Toast.LENGTH_SHORT).show();
//                                            Toast.makeText(MainActivity3.this, list, Toast.LENGTH_SHORT).show();
//                                            list.replace("," + code, "");
//                                            mostafa.setValue(list);
//                                        }
//                                    }
//                                    @Override
//                                    public void onCancelled(@NonNull DatabaseError error) {
//
//                                    }
//                                });
//                            }
//                        }
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//
//                        }
//                    });
//
                } else {
                    Toast.makeText(MainActivity3.this, "please select some code", Toast.LENGTH_SHORT).show();
                    edit_pre.setText("");
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