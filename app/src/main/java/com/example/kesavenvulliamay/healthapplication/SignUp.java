package com.example.kesavenvulliamay.healthapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.TooManyListenersException;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void Create(View View){

        Toast.makeText(this,"Account Created Successsfully",Toast.LENGTH_SHORT).show();
    }
}
