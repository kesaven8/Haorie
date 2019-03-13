package com.example.kesavenvulliamay.healthapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Intent intent;

    private Toolbar toolbar;

    private int REQUEST_CODE_CAMERA = 1;

    private int REQUEST_CODE_STORAGE= 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        RequestPermission();

    }

    //start the main menu
    public void Log_In(View view){
        intent = new Intent(this,MainMenu.class);
        startActivity(intent);

    }

    // function to start activity for sign up
    public void SignUp(View view){
        intent = new Intent(this,SignUp.class);
        startActivity(intent);

    }

    public void RequestPermission(){

        // If permission is not granted
        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        && (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)){

            // Permission is not granted
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {
                Toast.makeText(this, "Add Permission for Camera", Toast.LENGTH_SHORT).show();

            }
                else if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                    Toast.makeText(this,"Add Permission for strorage",Toast.LENGTH_SHORT).show();

            } else {
           //request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA} ,REQUEST_CODE_CAMERA);

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE} ,REQUEST_CODE_STORAGE);

            }
        } else {
            // Permission has already been granted
        }




    }


}
