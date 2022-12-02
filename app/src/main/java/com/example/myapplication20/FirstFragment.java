package com.example.myapplication20;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
//import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
//import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication20.databinding.FragmentFirstBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    TextView oldCourseCode, newCourseCode, newCourseName, newOffering, newPreq;
    DatabaseReference databaseRef;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        databaseRef = FirebaseDatabase.getInstance().getReference();

        HashMap hashMap=new HashMap();
        hashMap.put("Course Name", "intro to cs2");
        hashMap.put("Course Code", "cscb48");
        hashMap.put("Course Offerings", "winter");
        hashMap.put("Course Pre_requisites", "cscb08");
        databaseRef.child("Course_details").child("cscb48").updateChildren(hashMap);

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonModifyName.setOnClickListener(view14 -> {
            String code=binding.oldCourseCode.getText().toString();
            String name=binding.newCourseName.getText().toString();

            HashMap hashMap=new HashMap();
            hashMap.put("Course Name", name);

            databaseRef.child("Course_details").child(code).updateChildren(hashMap);
        });

        ////come back to this////
        binding.buttonModifyCourseCode.setOnClickListener(view1 -> {
            String code=binding.oldCourseCode.getText().toString();
            String newCode=binding.newCourseCode.getText().toString();

            HashMap hashMap=new HashMap();
            hashMap.put("Course Code", newCode);
            databaseRef.child("Course_details").child(code).updateChildren(hashMap);
        });

        binding.buttonModifyOffering.setOnClickListener(view13 -> {
            String code=binding.oldCourseCode.getText().toString();
            String newOffer=binding.newOffering.getText().toString();

            HashMap hashMap=new HashMap();
            hashMap.put(newOffer, newOffer);
            //add new offering
            databaseRef.child("Course_details").child("Course Offerings").updateChildren(hashMap);
        });

        binding.buttonModifyPreq.setOnClickListener(view12 -> {
            String code=binding.oldCourseCode.getText().toString();
            String newP=binding.newPreq.getText().toString();

            HashMap hashMap=new HashMap();
            hashMap.put(newP, newP);
            databaseRef.child("Course_details").child("Course Pre_requisites").updateChildren(hashMap);
        });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}