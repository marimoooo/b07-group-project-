package com.example.myapplication20;

import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.myapplication20.databinding.ActivityMainBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
private ActivityMainBinding binding;
//    private Button btnDelete, btnModify, buttonDeleteName, buttonDeletePreq, buttonDeleteCourseCode, buttonDeleteOffering;
//    private TextView enteredCourseCode;
//    DatabaseReference databaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

     binding = ActivityMainBinding.inflate(getLayoutInflater());
     setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


//        databaseRef = FirebaseDatabase.getInstance().getReference();
//        enteredCourseCode=findViewById(R.id.enteredCourseCode);
//
//
//        btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String code=enteredCourseCode.getText().toString();
//                databaseRef.child(code).setValue(null);
//            }
//        });
//
//        buttonDeleteName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String code=enteredCourseCode.getText().toString();
//                databaseRef.child(code).child("course_name").setValue(null);
//            }
//        });
//
//        buttonDeleteCourseCode.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String code=enteredCourseCode.getText().toString();
//                databaseRef.child(code).child("course_code").setValue(null);
//            }
//        });
//
//        buttonDeleteOffering.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String code=enteredCourseCode.getText().toString();
//                databaseRef.child(code).child("course_offering").setValue(null);
//            }
//        });
//
//        buttonDeletePreq.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String code=enteredCourseCode.getText().toString();
//                databaseRef.child(code).child("course_pre_req").setValue(null);
//            }
//        });

    }
@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}