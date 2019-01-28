package com.example.kesavenvulliamay.healthapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void Test(View view){

        Toast.makeText(this,"i am alive",Toast.LENGTH_LONG).show();
    }
}
