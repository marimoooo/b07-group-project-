package com.example.myapplication20;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
//import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication20.databinding.FragmentFirstBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    TextView enteredCourseCode;
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


        binding.buttonDelete.setOnClickListener(view1 -> {

            String code=binding.enteredCourseCode.getText().toString();
            databaseRef.child("Course_details").child(code).removeValue();
////            databaseRef.child("Course_details").child(code).removeValue()get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
////                @Override
////                public void onComplete(@NonNull Task<DataSnapshot> task) {
////                    if (!task.isSuccessful()) {
////                        Log.e("firebase", "Error getting data", task.getException());
////                    }
////                    else {
//////                        for(DataSnapshot ds: task.getResult().getChildren()) {
//////                            String key = ds.getKey();
//////                            if(key.equals(code)) {
//////
//////                            }
//////                        }
////
////                        //Log.d("firebase", String.valueOf(task.getResult().getValue()));
////                    }
////                }
//            });

                    //child("cscb48").setValue(null);
        });

        binding.buttonDeleteName.setOnClickListener(view12 -> {
            String code=binding.enteredCourseCode.getText().toString();
            databaseRef.child("Course_details").child(code).child("Course Name").setValue(null);
        });

        binding.buttonDeleteCourseCode.setOnClickListener(view13 -> {
            String code=binding.enteredCourseCode.getText().toString();
            databaseRef.child("Course_details").child(code).child("Course Code").setValue(null);
        });

        binding.buttonDeleteOffering.setOnClickListener(view14 -> {
            String code=binding.enteredCourseCode.getText().toString();
            databaseRef.child("Course_details").child(code).child("Course Offerings").setValue(null);
        });

        binding.buttonDeletePreq.setOnClickListener(view15 -> {
            String code=binding.enteredCourseCode.getText().toString();
            databaseRef.child("Course_details").child(code).child("Course Pre_requisites").setValue(null);
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}