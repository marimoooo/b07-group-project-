package com.example.b07;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.b07.databinding.ActivityMainBinding;
import android.widget.Toast;

import com.example.b07.databinding.ActivityWfsdfsfBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.os.Bundle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class wfsdfsf extends AppCompatActivity {
    ActivityWfsdfsfBinding binding;
    DatabaseReference reference;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wfsdfsf);
        binding = ActivityWfsdfsfBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.readdataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(wfsdfsf.this,"PLease Enter Polo!!!!",Toast.LENGTH_SHORT).show();
                String username = binding.etusername.getText().toString();
                if (!username.isEmpty()){
                    readData(username);
                    Toast.makeText(wfsdfsf.this,"PLease Enter Hello",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(wfsdfsf.this,"PLease Enter Username",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void readData(String username) {
        Toast.makeText(wfsdfsf.this,"A",Toast.LENGTH_SHORT).show();

        reference = FirebaseDatabase.getInstance().getReference("admins");

        reference.child(username).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                Toast.makeText(wfsdfsf.this,"B",Toast.LENGTH_SHORT).show();

                if (task.isSuccessful()){
                    if (task.getResult().exists()){
                        Toast.makeText(wfsdfsf.this,"Successfully Read",Toast.LENGTH_SHORT).show();
                        DataSnapshot dataSnapshot = task.getResult();
                        String firstName = String.valueOf(dataSnapshot.child("course_code").getValue());
                        String lastName = String.valueOf(dataSnapshot.child("course_code").getValue());
                        String age = String.valueOf(dataSnapshot.child("course_code").getValue());
                        binding.StudentName.setText(firstName);
                        binding.tvLastName.setText(lastName);
//                        binding.coursesTaken.setText(age);


                    }else {
                        Toast.makeText(wfsdfsf.this,"User Doesn't Exist",Toast.LENGTH_SHORT).show();
                    }


                }else {
                    Toast.makeText(wfsdfsf.this,"Failed to read",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}