package com.example.b07;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//public class Database_search extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_database_search);
//    }
//
//}
//
//

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Database_search extends AppCompatActivity {

    DatabaseReference reference;
    Button readdataBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Button readdataBtn = findViewById(R.id.readdataBtn);
        final EditText edit_course_name = findViewById(R.id.etusername);

        readdataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edit_course_name.getText().toString();
                if (!username.isEmpty()){

//                    readData(username);
                }else{

                    Toast.makeText(Database_search.this,"PLease Enter Username",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

//    private void readData(String username) {
//
//        reference = FirebaseDatabase.getInstance().getReference("Users");
//        reference.child(username).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DataSnapshot> task) {
////                final  tvFirstName = findViewById(R.id.StudentName);
////                final Button tvLastName = findViewById(R.id.readdataBtn);
////                final Button tvAge = findViewById(R.id.readdataBtn);
//
//
//                if (task.isSuccessful()){
//
//                    if (task.getResult().exists()){
//
//                        Toast.makeText(Database_search.this,"Successfully Read",Toast.LENGTH_SHORT).show();
//                        DataSnapshot dataSnapshot = task.getResult();
//                        String firstName = String.valueOf(dataSnapshot.child("firstName").getValue());
//                        String lastName = String.valueOf(dataSnapshot.child("lastName").getValue());
//                        String age = String.valueOf(dataSnapshot.child("age").getValue());
////                        .tvFirstName.setText(firstName);
////                        .tvLastName.setText(lastName);
////                        .tvAge.setText(age);
//                    }else {
//
//                        Toast.makeText(Database_search.this,"User Doesn't Exist",Toast.LENGTH_SHORT).show();
//
//                    }
//
//
//                }else {
//
//                    Toast.makeText(Database_search.this,"Failed to read",Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
//
//    }
}