package com.example.adwitiyasingh.weatherwithdrawer;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.adwitiyasingh.weatherwithdrawer.db.MyDatabaseHelper;
import com.example.adwitiyasingh.weatherwithdrawer.db.tables.mycities;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class MyCitiesActivity extends AppCompatActivity {

    SQLiteDatabase mydb;
    ArrayList<model> ctys = new ArrayList();
    RecyclerView JSONRecyclerView;
    CityAdapter RecycleAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cities);
        JSONRecyclerView = (RecyclerView) findViewById(R.id.recyclecities);

        ArrayList<String> jsons = new ArrayList<>();
        Intent in = getIntent();
        if(in!=null) {
            jsons = in.getStringArrayListExtra("models");
            if(jsons!=null) {
                for (int i = 0; i < jsons.size(); i++) {
                    ctys.add(getJson(jsons.get(i)));
                }
            }
        }



if(ctys.size()==0){
    Toast.makeText(this, "No cities added.", Toast.LENGTH_SHORT).show();
}
        RecycleAdapter = new CityAdapter(ctys, MyCitiesActivity.this);


        JSONRecyclerView.setLayoutManager(new LinearLayoutManager(MyCitiesActivity.this));

        JSONRecyclerView.setAdapter(RecycleAdapter);


    }

    model getJson(String json) {
        model model = new model();


        try {
            JSONObject thisJsonObject = new JSONObject(json);
            org.json.JSONObject sys = thisJsonObject.getJSONObject("sys");
            org.json.JSONObject coord = thisJsonObject.getJSONObject("coord");
            org.json.JSONArray wtr = thisJsonObject.getJSONArray("weather");
            JSONObject wtr0 = wtr.getJSONObject(0);
            org.json.JSONObject man = thisJsonObject.getJSONObject("main");
            org.json.JSONObject wnd = thisJsonObject.getJSONObject("wind");
            model.coord = new model.Coord(coord.getDouble("lat"), coord.getDouble("lon"));
            model.sys = new model.Sys(sys.getString("country"), sys.getInt("sunrise"), sys.getInt("sunset"));
            model.weather = new model.Weather(wtr0.getInt("id"), wtr0.getString("main"), wtr0.getString("description"), wtr0.getString("icon"));
            model.main = new model.Main(man.getInt("humidity"), man.getInt("pressure"), man.getDouble("temp"), man.getDouble("temp_max"), man.getDouble("temp_min"));
            model.wind = new model.Wind(wnd.getDouble("speed"), wnd.getInt("deg"));
            model.name = thisJsonObject.getString("name");
            ;
            model.visibility = thisJsonObject.getInt("visibility");

        } catch (JSONException e) {

        }
        return model;
    }

}
