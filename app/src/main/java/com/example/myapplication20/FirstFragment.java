package com.example.myapplication20;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
//import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
//import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication20.databinding.FragmentFirstBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    TextView oldCourseCode, newCourseCode, newCourseName, newOffering, newPreq;
    DatabaseReference databaseRef;
    DatabaseReference reference;
    FirebaseDatabase data;
//ok
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        databaseRef = FirebaseDatabase.getInstance().getReference();
        data = FirebaseDatabase.getInstance();
        //data = FirebaseDatabase.getInstance("https://cscb07-project-ba4c9-default-rtdb.firebaseio.com");
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

            // String name, offering, preq;
            HashMap hashMap=new HashMap();
            hashMap.put("Course Code", newCode);
            databaseRef.child("Course_details").child(newCode).updateChildren(hashMap);
            //String name;

            databaseRef.child("Course_details").child(code).child("Course Name").get( ).addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                        Log.e("firebase", "Error getting data", task.getException());
                    }
                    else {
                        Log.d("firebase", String.valueOf(task.getResult().getValue()));
                        String name = task.getResult().getValue().toString();
                        HashMap hashMap2 = new HashMap();
                        hashMap2.put("Course Name", name);
                        databaseRef.child("Course_details").child(newCode).updateChildren(hashMap2);
                    }
                }
            });

            databaseRef.child("Course_details").child(code).child("Course Pre_requisites").get( ).addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                        Log.e("firebase", "Error getting data", task.getException());
                    }
                    else {
                        Log.d("firebase", String.valueOf(task.getResult().getValue()));
                        String preq = task.getResult().getValue().toString();
                        HashMap hashMap2 = new HashMap();
                        hashMap2.put("Course Pre_requisites", preq);
                        databaseRef.child("Course_details").child(newCode).updateChildren(hashMap2);
                    }
                }
            });

            databaseRef.child("Course_details").child(code).child("Course Offerings").get( ).addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                        Log.e("firebase", "Error getting data", task.getException());
                    }
                    else {
                        Log.d("firebase", String.valueOf(task.getResult().getValue()));
                        String offering = task.getResult().getValue().toString();
                        HashMap hashMap2 = new HashMap();
                        hashMap2.put("Course Offerings", offering);
                        databaseRef.child("Course_details").child(newCode).updateChildren(hashMap2);
                    }
                }
            });



//            reference = data.getReference("Course_details").child(code).child("Course Name");
//            reference.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        String name = snapshot.getValue(String.class);
//                        HashMap hashMap2 = new HashMap();
//                        hashMap2.put("Course Name", name);
//                        databaseRef.child("Course_details").child(newCode).updateChildren(hashMap2);
//                }
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });
//            reference = data.getReference("Course_details").child(code).child("Course Pre_requisites");
//            reference.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    for(DataSnapshot shot:snapshot.getChildren()) {
//                        String preq = shot.getValue(String.class);
//                        HashMap hashMap2=new HashMap();
//                        hashMap2.put("Course Pre_requisites", preq);
//
//                        databaseRef.child("Course_details").child(newCode).updateChildren(hashMap2);
//                    }
//                }
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });
//            reference = data.getReference("Course_details").child(code).child("Course Offerings");
//            reference.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    for(DataSnapshot shot:snapshot.getChildren()) {
//                        String offerings = shot.getValue(String.class);
//                        HashMap hashMap2=new HashMap();
//                        hashMap2.put("Course Offerings", offerings);
//
//                        databaseRef.child("Course_details").child(newCode).updateChildren(hashMap2);
//                    }
//                }
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });

            //delete old course
            databaseRef.child("Course_details").child(code).setValue(null);


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