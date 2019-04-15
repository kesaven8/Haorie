package com.example.kesavenvulliamay.healthapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainMenu extends AppCompatActivity {

    private Intent intent;// used to start new activity



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    // function to navigate to the profile page
    public void Profile(View view){

        intent = new Intent(this,Profile.class);
        startActivity(intent);

    }

    // function to show history of the application
    public void History(View view){
        intent = new Intent(this,History.class);
        startActivity(intent);
    }

    // function to start take picture activity
    public void TakePicture(View view){

        intent = new Intent(this,TakePhoto.class);
        startActivity(intent);
    }

    public void InfoPage(View view){
        intent = new Intent(this, Info.class);
        startActivity(intent);
    }
}
