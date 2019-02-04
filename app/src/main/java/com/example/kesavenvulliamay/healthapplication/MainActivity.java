package com.example.kesavenvulliamay.healthapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Intent intent;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

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


}
