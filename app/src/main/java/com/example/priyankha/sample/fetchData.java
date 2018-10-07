package com.example.priyankha.sample;

import android.os.AsyncTask;
import android.widget.Toast;

import com.android.volley.toolbox.HttpResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.FloatBuffer;

import javax.net.ssl.HttpsURLConnection;

public class fetchData  extends AsyncTask<Void,Void,Void> {

    String data;
    String parsed;
    @Override
    protected Void doInBackground(Void... voids) {
        try {

          //  URL url =  new URL("http://api.openweathermap.org/data/2.5/weather?id=4460162&appid=7e7ef8afe61468c3b54bd7d149cdff97&units=imperial");
           // URL url =  new URL("https://api.myjson.com/bins/6jtrg");
            URL url= new URL("https://api.myjson.com/bins/18russ");
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
            InputStream inputStream = httpsURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line="";
            while(line!=null){
                line = bufferedReader.readLine();
                data = data+line;

            }
            //HttpResponse<String> response = Unirest.get("https://api.myjson.com/bins/6jtrg")
              //      .header("Cache-Control", "no-cache")
                //    .header("Postman-Token", "7ce28234-87f1-4cf6-b4be-c2afc93e4d7f")
                  //  .asString();

            JSONArray jarray   = new JSONArray(data);
            JSONObject JO=(JSONObject) jarray.get(1);
            parsed=JO.get("temp");


           // JSONObject obj= new JSONObject(data);
            //String parsed=obj.getJSONObject("sys").getString("country");


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
        MainActivity.textView.setText(this.parsed);
    }
}
