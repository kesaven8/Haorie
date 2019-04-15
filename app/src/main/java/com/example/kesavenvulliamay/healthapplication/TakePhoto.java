package com.example.kesavenvulliamay.healthapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TakePhoto extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private ImageView imageView;

    private Bitmap imageBitmap;

    private InputStream inputStream;

    private EditText food_name;

    private EditText calorie_value;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);

        imageView = findViewById(R.id.imageView);

        food_name = findViewById(R.id.foodname);

        calorie_value = findViewById(R.id.calorie_value);




        // start the activity for the intent
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);


        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }

    }


    // can add the preprocess image here
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
             imageBitmap = (Bitmap) extras.get("data");
             imageView.setImageBitmap(imageBitmap);

        }
    }


    public class Send_Image_API extends AsyncTask<String,Void,String>{


        @Override
        protected String doInBackground(String... urls) {


            try {

                URL url = new URL(urls[0]);
                String result ="";

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                inputStream = httpURLConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuilder str = new StringBuilder();

                while ((result = reader.readLine()) != null) {

                    str.append(result);

                }

                result = str.toString();

                return result;


            }

            catch (Exception e){
                Log.e("Error",e.getMessage());
            }

            return null;
        }

    }


    // Function that is called to predict the result
    public void Predict(View view){


        // convert  the image to string
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);

        String encoded_image = Base64.encodeToString(byteArrayOutputStream.toByteArray(),Base64.URL_SAFE);



        //build the string for the api

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("http://127.0.0.1:3333/pre?");
        stringBuilder.append("img_encode=");
        stringBuilder.append(encoded_image);
        String api_call= stringBuilder.toString();
        Log.i("api call is ",api_call);

        try{

            Send_Image_API send_image_api = new Send_Image_API();
            String predict_answer = send_image_api.execute(api_call).get();





            JSONObject jsonObject = new JSONObject(predict_answer);

            String predict= (String) jsonObject.get("prediction");

            food_name.setText(predict);


            Log.i("prediction is", " "+ predict);

            String calorie = (String) jsonObject.get("calorie");

            calorie_value.setText(calorie);

            Log.i("calorie is", " "+ calorie);



            Log.i("answer is ",""+predict_answer);


        }catch(Exception e){

            Log.e("Error",e.getMessage()+"\n"+ e.getLocalizedMessage());
            Toast.makeText(this,"API call failed",Toast.LENGTH_SHORT).show();

        }

       // Toast.makeText(this,"API call successful",Toast.LENGTH_SHORT).show();

    }


}
