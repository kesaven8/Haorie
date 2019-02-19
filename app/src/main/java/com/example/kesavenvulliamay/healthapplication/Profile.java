package com.example.kesavenvulliamay.healthapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends AppCompatActivity {

    private TextView displayBMI;//used to display the BMI value

    private TextView displayTDEE;//used to display the TDEE values

    private EditText heightView;//used to display the height value

    private EditText ageView;//used to get the age value

    private EditText weightView;//used to get the weight value

    private RadioGroup radioGroup;// used to select male or female

    private RadioButton radioButtonMale;// used to select male or female options

    private RadioButton radioButtonFemale; // used to select male or female options


    private int ActivityState=0;// used initially set to 0 representing sedentary lifestyle

    private NumberPicker numberPicker;//getting references from ui to choose lifesyle

    String value[] = {"Sedentary", "Light Sports "
            , "Moderate", "Very Active",
            "Very Hard"};

    //array to store values for number picker



    private Context context;// used to store values on device using Shared Preferences

    private String BMI_String; // used to store the value of the bmi as a string

    private String string_displayTDEE;// used to store the value of tdee

    private int ActivitySelected;// used to store the value of lifestyle



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //getting refences for the views
        numberPicker = findViewById(R.id.picker);
        displayBMI = findViewById(R.id.displaybmi);
        displayTDEE = findViewById(R.id.displayTDEE);
        heightView = findViewById(R.id.height);
        ageView = findViewById(R.id.Age);
        weightView = findViewById(R.id.weight);
        radioGroup = findViewById(R.id.sex);
        radioButtonFemale = findViewById(R.id.radiofemale);
        radioButtonMale = findViewById(R.id.radiomale);


        //getting the context to be used for shared preferences
        context = getApplicationContext();


        // setting the number picker to pick lifestyle values
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(value.length - 1);
        numberPicker.setDisplayedValues(value);
        numberPicker.setScrollbarFadingEnabled(true);
        numberPicker.setScrollbarFadingEnabled(true);
        numberPicker.setWrapSelectorWheel(true);

        // the listener aims to help the user to choose between
        numberPicker.setOnScrollListener(new NumberPicker.OnScrollListener() {
            @Override
            public void onScrollStateChange(NumberPicker numberPicker, int i) {
                int ActivitySelected = numberPicker.getValue();

                Log.i("log messsge", "number is .. " + ActivitySelected);

                switch (ActivitySelected){
                    case 0:{
                        Toast.makeText(getApplicationContext(),"Very little physical exercises" ,Toast.LENGTH_SHORT).show();
                        ActivityState = 0;
                        break;
                    }
                    case 1:{
                        Toast.makeText(getApplicationContext(),"Individuals that exercise lightly from one to three times a week",Toast.LENGTH_SHORT).show();
                        ActivityState = 1;
                        break;
                    }
                    case 2:{
                        Toast.makeText(getApplicationContext(),"for individuals that perform moderate exercises three to 5\n" +
                                "times per week",Toast.LENGTH_SHORT).show();
                        ActivityState = 2;
                        break;
                    }
                    case 3:{
                        Toast.makeText(getApplicationContext(),"Hard exercises that are performed six - seven days per week",Toast.LENGTH_SHORT).show();
                        ActivityState = 3;
                        break;
                    }
                    case 4:{
                        Toast.makeText(getApplicationContext(),"For hard daily exercises / physical job",Toast.LENGTH_SHORT).show();
                        ActivityState = 4;
                        break;
                    }
                }

            }
        });



    }


    // Save button will save the values as a key pair using Shared Preferences android
    public void Save(View view) {

        CalculateBMI();
        CalculateTDEE();

        SharedPreferences sharedPreferences = context.getSharedPreferences(getString(R.string.profilevales),Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        //add in string.xml
        editor.putString(getString(R.string.age),ageView.getText().toString());
        editor.putString(getString(R.string.weight),weightView.getText().toString());
        editor.putString(getString(R.string.height),heightView.getText().toString());
        editor.putString(getString(R.string.bmi),BMI_String);
        editor.putString(getString(R.string.tdee),string_displayTDEE);
        editor.putInt(getString(R.string.lifestyle),ActivitySelected);
        boolean check= editor.commit();

        Log.i("save",".."+check);




    }

    public void CalculateBMI() {

        try {

            float height_int = Float.parseFloat(heightView.getText().toString()) / 100;
            float weight_int = Float.parseFloat(weightView.getText().toString());

            float bmi_int_value = (weight_int) / (height_int * height_int);

             BMI_String = String.format("%.2f", bmi_int_value);

//            heightView.setFocusable(false);
//            weightView.setFocusable(false);

            displayBMI.setText(BMI_String);

            Log.i("BMI value is", BMI_String);
            Log.i("BMI value is", "i" + height_int);
            Log.i("BMI value is", "i" + weight_int);

        } catch (Exception e) {

            Toast.makeText(this, "fill the fields", Toast.LENGTH_SHORT).show();
        }

    }


    /***
     * the following values are used to calculate the TDEE
     */
    /**
    *
    Sedentary = BMR x 1.2: for very little physical exercises
    Lightly Active = BMR x 1.375: for individuals that exercise lightly from one to three times a
    week
    Moderately Active = BMR x 1.55: for individuals that perform moderate exercises three to 5
    times per week
    Very Active = BMR x 1.725: for hard exercises that are performed six to seven days per week
    Extremely Active = BMR x 1.9: for hard daily exercises / physical job.
    **/
    public double CalculateTDEE() {

        double bmr_value = CalculateBMR();
        double TDEE_value = 0; // initally the value is 0


        switch (ActivityState){

            case 0:{
                TDEE_value = bmr_value * 1.2;
                Log.i("TDEE",".. "+TDEE_value);
                break;

            }
            case 1 :{
                TDEE_value = bmr_value * 1.375;
                Log.i("TDEE",".. "+TDEE_value);
                break;
            }
            case 2 :{
                TDEE_value = bmr_value * 1.55;
                Log.i("TDEE",".. "+TDEE_value);
                break;
            }
            case 3 :{
                TDEE_value = bmr_value * 1.725;
                Log.i("TDEE",".. "+TDEE_value);
                break;
            }
            case 4 :{
                TDEE_value = bmr_value * 1.9;
                Log.i("TDEE",".. "+TDEE_value);
                break;
            }

        }

        try {

            string_displayTDEE = String.format("%.0f", TDEE_value);
            displayTDEE.setText(string_displayTDEE);

        } catch (Exception e) {
            Toast.makeText(this,""+e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
        }
        return TDEE_value;
    }

    // using the Harris-Benedict Equation
    // for men :BMR = (10 × weight in kg) + (6.25 × height in cm) - (5 × age in years) + 5
    // For women : BMR = (10 × weight in kg) + (6.25 × height in cm) - (5 × age in years) - 161
    // This function returns the BMi value as double
    public double CalculateBMR() {

        int selected_sex = radioGroup.getCheckedRadioButtonId();
        double bmr = 0;
        try {
            if (selected_sex == radioButtonMale.getId()) {

                bmr = (10 * Float.parseFloat(weightView.getText().toString())) +
                        (6.25 * Float.parseFloat(heightView.getText().toString()))
                        - (5 * Float.parseFloat(ageView.getText().toString())) + 5;

                Toast.makeText(this, "male is pressed"+  " BMR is :"+ + bmr, Toast.LENGTH_SHORT).show();

            } else if (selected_sex == radioButtonFemale.getId()) {

                bmr = (10 * Float.parseFloat(weightView.getText().toString())) +
                        (6.25 * Float.parseFloat(heightView.getText().toString()))
                        - (5 * Float.parseFloat(ageView.getText().toString())) - 161;

                Toast.makeText(this, "female is pressed  " +  " BMR is :"+ bmr, Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(this, "Select Gender", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            Log.e("error", e.getLocalizedMessage());
        }
        return bmr;

    }

}




