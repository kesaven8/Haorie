package com.example.kesavenvulliamay.healthapplication;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity {

    private Intent intent;// used to start new activity



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    // This is a test function for the buttons in the application
    public void Test(View view){

        Toast.makeText(this,"i am alive",Toast.LENGTH_LONG).show();
    }

    public void Profile(View view){

        intent = new Intent(this,Profile.class);
        startActivity(intent);

    }

    public void History(View view){
        intent = new Intent(this,History.class);
        startActivity(intent);
    }

    public void TakePicture(View view){

        intent = new Intent(this,TakePhoto.class);
        startActivity(intent);
    }
}
