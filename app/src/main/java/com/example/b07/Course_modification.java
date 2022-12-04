package com.example.b07;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.b07.databinding.ActivityCourseModificationBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Course_modification extends AppCompatActivity {

    ActivityCourseModificationBinding binding;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCourseModificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = binding.userName.getText().toString();
                String fisrtName = binding.firstName.getText().toString();
                String lastName = binding.lastname.getText().toString();
                String age = binding.age.getText().toString();

                updatedata(userName, fisrtName, lastName, age);

            }

        });
    }

    private void updatedata(String userName, String firstName, String lastName, String age) {

        HashMap User = new HashMap();
        User.put("firstName", firstName);
        User.put("lastName", lastName);
        User.put("age", age);
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.child(userName).updateChildren(User).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {

                if (task.isSuccessful()) {

                    binding.userName.setText("");
                    binding.firstName.setText("");
                    binding.lastname.setText("");
                    binding.age.setText("");
                    Toast.makeText(Course_modification.this, "Successfully Updated", Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(Course_modification.this, "Failed to Update", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }


}