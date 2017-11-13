package com.example.adwitiyasingh.networking;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btndwnload;
    EditText eturl;
    RecyclerView JSONRecyclerView;
    JSONAdapter RecycleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btndwnload = (Button) findViewById(R.id.btngo);
        eturl = (EditText) findViewById(R.id.eturl);
        btndwnload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = eturl.getText().toString();
                DownloadData downloadData = new DownloadData();
                downloadData.execute(url);
            }
        });

    }


    public class DownloadData extends AsyncTask<Object, Object, ArrayList<model>> {

        @Override
        protected ArrayList<model> doInBackground(Object... params) {
            URL url = null;
            ArrayList<model> jsonArray = new ArrayList<>();
            try {
                url = new URL("https://jsonplaceholder.typicode.com/posts");
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
            HttpURLConnection httpURLConnection = null;
            try {
                httpURLConnection = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            int responseCode = 0;
            try {
                responseCode = httpURLConnection.getResponseCode();
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader streamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(streamReader);

                StringBuilder sb = new StringBuilder();
                String buffer;


                while ((buffer = reader.readLine()) != null) {
                    sb.append(buffer);
                }
                String json = sb.toString();
                jsonArray = getJson(json);
                return jsonArray;

            } catch (IOException e) {
                e.printStackTrace();
            }
            return jsonArray;
        }

        @Override
        protected void onPostExecute(ArrayList<model> jsonArray) {

            JSONRecyclerView = (RecyclerView) findViewById(R.id.recycle);


            RecycleAdapter = new JSONAdapter(jsonArray, MainActivity.this);


            JSONRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

            JSONRecyclerView.setAdapter(RecycleAdapter);
        }

    }

    static ArrayList<model> getJson(String json) {

        //convert string to array list coming in JSON format

        ArrayList<model> modelJson = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject thisJsonObject = jsonArray.getJSONObject(i);
                model model = new model(thisJsonObject.getInt("userId"), thisJsonObject.getInt("id"),
                        thisJsonObject.getString("title"), thisJsonObject.getString("body"));
                modelJson.add(model);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return modelJson;
    }
}
