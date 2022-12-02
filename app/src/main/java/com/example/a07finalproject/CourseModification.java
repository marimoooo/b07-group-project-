package com.example.a07finalproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
//import androidx.navigation.fragment.NavHostFragment;

import com.example.a07finalproject.databinding.CourseModificationBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class CourseModification extends Fragment {

    private CourseModificationBinding binding;
    TextView oldCourseCode, newCourseCode, newCourseName, oldOffering, newOffering, oldPreq, newPreq;
    DatabaseReference databaseRef;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = CourseModificationBinding.inflate(inflater, container, false);
        databaseRef = FirebaseDatabase.getInstance().getReference();
        return binding.getRoot();

    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonModifyName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code=oldCourseCode.getText().toString();
                String name=newCourseName.getText().toString();

                HashMap hashMap=new HashMap();
                hashMap.put("course_name", name);

                databaseRef.child(code).updateChildren(hashMap);
            }
        });

        ////come back to this////
        binding.buttonModifyCourseCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code=oldCourseCode.getText().toString();
                String newCode=newCourseCode.getText().toString();

                HashMap hashMap=new HashMap();
                hashMap.put("course_code", newCode);

                databaseRef.child(code).updateChildren(hashMap);
            }
        });

        binding.buttonModifyOffering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code=oldCourseCode.getText().toString();
                String oldOffer=oldOffering.getText().toString();
                String newOffer=newOffering.getText().toString();

                HashMap hashMap=new HashMap();
                hashMap.put(newOffer, newOffer);
                //delete original offering
                databaseRef.child(code).child("course_offering").child(oldOffer).setValue(null);
                //add new offering
                databaseRef.child(code).child("course_offering").child(newOffer).updateChildren(hashMap);
            }
        });

        binding.buttonModifyPreq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code=oldCourseCode.getText().toString();
                String oldP=oldPreq.getText().toString();
                String newP=newPreq.getText().toString();

                HashMap hashMap=new HashMap();
                hashMap.put(newP, newP);
                //delete original offering
                databaseRef.child(code).child("course_offering").child(oldP).setValue(null);
                //add new offering
                databaseRef.child(code).child("course_offering").child(newP).updateChildren(hashMap);
            }
        });



//        binding.buttonModifyName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//            String name=newCourseName.getText().toString();
//            String code=newCourseCode.getText().toString();
//            String offer=offering.getText().toString();
//            HashMap hashMap=new HashMap();
//            hashMap.put("Name", name);
//            hashMap.put("Course Code", code);
//            hashMap.put("Session Offers", offer);
//
//            databaseRef.child(code).updateChildren(hashMap);//.addOnSuccessListener(new OnSuccessLinstener() {
////                @Override
////                public void onSuccess(Object o) {
////                    Toast.makeText(CourseModification.this, "Course successfully updated", Toast.LENGTH_SHORT).show();
////                }
////            });
//            }
//        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}