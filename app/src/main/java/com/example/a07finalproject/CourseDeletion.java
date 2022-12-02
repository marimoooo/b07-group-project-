package com.example.a07finalproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
//import androidx.navigation.fragment.NavHostFragment;

import com.example.a07finalproject.databinding.CourseDeletionBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CourseDeletion extends Fragment {

    private CourseDeletionBinding binding;
    TextView enteredCourseCode;
    DatabaseReference databaseRef;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = CourseDeletionBinding.inflate(inflater, container, false);
        databaseRef = FirebaseDatabase.getInstance().getReference();
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code=enteredCourseCode.getText().toString();
                databaseRef.child(code).setValue(null);
            }
        });

        binding.buttonDeleteName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code=enteredCourseCode.getText().toString();
                databaseRef.child(code).child("course_name").setValue(null);
            }
        });

        binding.buttonDeleteCourseCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code=enteredCourseCode.getText().toString();
                databaseRef.child(code).child("course_code").setValue(null);
            }
        });

        binding.buttonDeleteOffering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code=enteredCourseCode.getText().toString();
                databaseRef.child(code).child("course_offering").setValue(null);
            }
        });

        binding.buttonDeletePreq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code=enteredCourseCode.getText().toString();
                databaseRef.child(code).child("course_pre_req").setValue(null);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}