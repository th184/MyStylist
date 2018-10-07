package com.example.priyankha.sample;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.nfc.Tag;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

public class MainActivity extends AppCompatActivity  {
    private static int RESULT_LOAD_IMG = 1;
    String imgDecodableString;

    public static TextView textView;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //mTemperature = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        //if(mTemperature==null)
           // Toast.makeText(this,"Temperature not supported", Toast.LENGTH_LONG).show();
        textView=(TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button_temperature);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            fetchData process = new fetchData();
            process.execute();
            }
        });
      //  getTemp();
        //if (NetworkCheck.isAvailableAndConnected(this)) {
            //Calling method to load newss
         //   getTemp();
       // }
    }

    public void load_closet(View v) {

        Intent galleryIntent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {


            // When an Image is picked
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                Uri selectedImage = data.getData();
                Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage));
                // Set the Image in ImageView after decoding the String
                ImageView imgView = (ImageView) findViewById(R.id.imgView);
                imgView.setImageBitmap(bitmap);


                // Get the cursor
               // Cursor cursor = getContentResolver().query(selectedImage,
                 //       filePathColumn, null, null, null);
                // Move to first row
                //cursor.moveToFirst();

                //int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
               // imgDecodableString = cursor.getString(columnIndex);
               // cursor.close();
                //Toast.makeText(this, imgDecodableString,
                  //      Toast.LENGTH_LONG).show();
                //ImageView imgView = (ImageView) findViewById(R.id.imgView);
               // File imgFile = new  File(imgDecodableString);
               // Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                //Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage));
                // Set the Image in ImageView after decoding the String
                //imgView.setImageBitmap(bitmap);


            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }

    }

    public void view_temp(View v)
    {

       String url ="http://api.openweathermap.org/data/2.5/weather?id=4460162&appid=7e7ef8afe61468c3b54bd7d149cdff97&units=imperial";
        JsonObjectRequest jor= new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    textView.setText("hello");
                    //Log.d("tag", response.toString());
                    textView.setText(response.toString());
                    //System.out.print(response.toString());
                    JSONObject main_object= response.getJSONObject("main");
                    //JSONArray array = response.getJSONArray("weather");
                    //JSONObject object = array.getJSONObject(0);
                    String temp= String.valueOf(main_object.getDouble("temp"));
                    Log.d("temperature",temp );
                    textView.setText(temp);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jor);
    }
void getTemp()
{
    String url ="http://api.openweathermap.org/data/2.5/weather?id=4460162&appid=7e7ef8afe61468c3b54bd7d149cdff97&units=imperial";
    //http://api.openweathermap.org/data/2.5/weather?id=4460162&appid=7e7ef8afe61468c3b54bd7d149cdff97&units=imperial
    JsonObjectRequest jor= new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            try {
                JSONObject main_object= response.getJSONObject("main");
                //JSONArray array = response.getJSONArray("weather");
               // JSONObject object = array.getJSONObject(0);
                String temp= String.valueOf(main_object.getDouble("temp"));
                textView.setText("hello");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

        }
    });
    RequestQueue queue = Volley.newRequestQueue(this);
    queue.add(jor);
}

}
