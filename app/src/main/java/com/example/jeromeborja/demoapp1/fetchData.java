package com.example.jeromeborja.demoapp1;

import android.os.AsyncTask;

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

//Create a AsyncTask class to handle background operations
public class fetchData extends AsyncTask<Void,Void,Void> {
    //string variables used to manipulate data
    String data ="";
    String dataParsed = "";
    String singleParsed ="";
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            //link to resource
            URL url = new URL("https://api.myjson.com/bins/nuwk2");
            //Opening a connection with the URL address
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            //Create an inputstream object from the connection in order to read/manipulate the data.
            InputStream inputStream = httpURLConnection.getInputStream();
            //Reads the data from the stream.
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            //Variable to keep track of every single line of data
            String line = "";
            //loop to go read each line of data
            while(line != null){
                line = bufferedReader.readLine();

                //append each line
                data = data + line;
            }

            //Now that data is retrieved, it needs to be parsed
            //Define JSONArray for holding data
            JSONArray JA = new JSONArray(data);
            //loop through the data
            for(int i =0 ;i <JA.length(); i++){
                //define a JSON Object, each JSONObject is going to be an element of the array
                JSONObject JO = (JSONObject) JA.get(i);

                //formats each JSON object, singledParsed holds 1 JSON Object
                singleParsed =  "Name:" + JO.get("name") + "\n"+
                        "Password:" + JO.get("password") + "\n"+
                        "Contact:" + JO.get("contact") + "\n"+
                        "Country:" + JO.get("country") + "\n";

                //Appends all entries into one entry
                dataParsed = dataParsed + singleParsed +"\n" ;
            }
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

        //set text to display data.
        MainActivity.tvDemo.setText(this.dataParsed);
    }
}
