package com.example.adwitiyasingh.weatherapp;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class ForecastActivity extends AppCompatActivity {
    RecyclerView JSONRecyclerView;
    JSONAdapter RecycleAdapter;
    TextView tvForePlace, tvForeLat, tvForeLong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
        tvForeLat = (TextView) findViewById(R.id.tvforelat);
        tvForeLong = (TextView) findViewById(R.id.tvforelon);
        tvForePlace = (TextView) findViewById(R.id.tvforeplace);

        String json = getIntent().getStringExtra("json");
        ForecastModel fm = getJson(json);
        ForecastModel.City ctyNow = fm.cty;

        tvForeLong.setText("Latitude : "+ctyNow.getC().getLongi().toString());
        tvForeLat.setText("Longitude : "+ctyNow.getC().getLati().toString());
        tvForePlace.setText( ctyNow.getCity()+", "+ctyNow.getCountry());

        JSONRecyclerView = (RecyclerView) findViewById(R.id.recycle);


        RecycleAdapter = new JSONAdapter(fm.lst, ForecastActivity.this);


        JSONRecyclerView.setLayoutManager(new LinearLayoutManager(ForecastActivity.this));

        JSONRecyclerView.setAdapter(RecycleAdapter);

    }

    ForecastModel getJson(String json) {
        ForecastModel model = new ForecastModel();


        try {
            JSONObject thisJsonObject = new JSONObject(json);
            org.json.JSONObject city = thisJsonObject.getJSONObject("city");
            JSONObject coord = city.getJSONObject("coord");
            org.json.JSONArray list = thisJsonObject.getJSONArray("list");

            com.example.adwitiyasingh.weatherapp.model.Coord c = new model.Coord(coord.getDouble("lat"), coord.getDouble("lon"));
            model.cty = new ForecastModel.City(c, city.getString("name"), city.getString("country"));

            for (int i = 0; i < list.length(); i++) {
                JSONObject now = list.getJSONObject(i);
                org.json.JSONArray wtr = now.getJSONArray("weather");
                JSONObject wtr0 = wtr.getJSONObject(0);
                org.json.JSONObject man = now.getJSONObject("main");
                org.json.JSONObject wnd = now.getJSONObject("wind");
                String dttime = now.getString("dt_txt");
                com.example.adwitiyasingh.weatherapp.model.Weather wdrob = new model.Weather(wtr0.getInt("id"), wtr0.getString("main"), wtr0.getString("description"), wtr0.getString("icon"));
                com.example.adwitiyasingh.weatherapp.model.Main mnob = new model.Main(man.getInt("humidity"), man.getInt("pressure"), man.getDouble("temp"), man.getDouble("temp_max"), man.getDouble("temp_min"));
                com.example.adwitiyasingh.weatherapp.model.Wind wndob = new model.Wind(wnd.getDouble("speed"), wnd.getInt("deg"));
                ForecastModel.List lstnow = new ForecastModel.List(mnob, wdrob, wndob, dttime);
                model.lst.add(lstnow);
            }


        } catch (JSONException e) {

        }
        return model;
    }
}
