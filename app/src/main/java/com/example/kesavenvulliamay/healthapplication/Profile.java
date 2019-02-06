package com.example.kesavenvulliamay.healthapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.NumberPicker;

public class Profile extends AppCompatActivity {

    private NumberPicker numberPicker;//getting references from ui to choose lifesyle

    String value []={"sedentary","light sports "
    ,"Moderate","Very Active",
    "Very Hard"};

    //array to store values for number picker

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        numberPicker = findViewById(R.id.picker);

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

}
