package com.example.kesavenvulliamay.healthapplication;

import android.content.pm.ApplicationInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends AppCompatActivity {


    private TextView displayBMI;//used to display the BMI value

    private EditText heightView;//used to display the height value

    private EditText ageView;//used to get the age value

    private EditText weightView;//used to get the weight value




    private NumberPicker numberPicker;//getting references from ui to choose lifesyle

    String value []={"Sedentary","Light Sports "
    ,"Moderate","Very Active",
    "Very Hard"};

    //array to store values for number picker

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //getting refences for the views
        numberPicker = findViewById(R.id.picker);
        displayBMI = findViewById(R.id.displaybmi);
        heightView = findViewById(R.id.height);
        ageView = findViewById(R.id.Age);
        weightView = findViewById(R.id.weight);





        numberPicker.setMinValue(0);

        numberPicker.setMaxValue(value.length-1);

        numberPicker.setDisplayedValues(value);

        numberPicker.setScrollbarFadingEnabled(true);

        numberPicker.setScrollbarFadingEnabled(true);

        numberPicker.setWrapSelectorWheel(true);

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                // do something when value is changed
            }
        });

    }

    public void Save(View view){

        CalculateBMI();



    }

    public void CalculateBMI() {


        try {

            float height_int = Float.parseFloat(heightView.getText().toString()) / 100;
            float weight_int = Float.parseFloat(weightView.getText().toString());


            float bmi_int_value = (weight_int) / (height_int * height_int);

            String BMI_String = String.format("%.2f", bmi_int_value);

            heightView.setFocusable(false);
            weightView.setFocusable(false);

            displayBMI.setText(BMI_String);

            Log.i("BMI value is", BMI_String);
            Log.i("BMI value is", "i" + height_int);
            Log.i("BMI value is", "i" + weight_int);

        }catch (Exception e){

            Toast.makeText(this,"fill the fields",Toast.LENGTH_SHORT).show();
        }

    }


}




