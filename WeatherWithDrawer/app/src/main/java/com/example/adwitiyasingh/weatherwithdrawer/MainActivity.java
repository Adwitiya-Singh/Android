package com.example.adwitiyasingh.weatherwithdrawer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
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

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static final String MY_PREF = "MY_PREF";
    static char temp = 'c';

    Button btngo, btnadd;
    TextView tv, tvMin, tvMax, tvPlace, tvSunrise, tvSunset, tvMain, tvVis, tvLat, tvLong, tvHum, tvPres, tvWind, tvMyCities;
    ImageView iv;
    EditText etlocation;
    CardView cv1, cv2, cv3;
    CoordinatorLayout cl;
    SQLiteDatabase mydb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sPref2 = getApplicationContext().getSharedPreferences("DARKMODE",MODE_PRIVATE);
        SharedPreferences.Editor prefEditor2 = sPref2.edit();
        if(sPref2.getBoolean("drkmode", false)){



        }
        setContentView(R.layout.activity_main);
        SharedPreferences sPref = getApplicationContext().getSharedPreferences(MY_PREF,MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = sPref.edit();



        if (sPref.getInt("temp", 7) == 7) {
            prefEditor.putInt("temp", 1);
            prefEditor.apply();
        } else {
            int val = sPref.getInt("temp", 1);
            {
                if (val == 1) {
                    temp = 'c';
                } else if (val == 2) {
                    temp = 'f';
                } else if(val ==3){
                    temp = 'k';
                }
            }

            cl = (CoordinatorLayout) findViewById(R.id.coord);
            cv1 = (CardView) findViewById(R.id.CardView1);
            cv2 = (CardView) findViewById(R.id.CardView2);
            cv3 = (CardView) findViewById(R.id.CardView3);
            btngo = (Button) findViewById(R.id.btngo);
            tv = (TextView) findViewById(R.id.tvtemp);
            tvMin = (TextView) findViewById(R.id.tvtmpmin);
            tvMax = (TextView) findViewById(R.id.tvtmpmax);
            tvPlace = (TextView) findViewById(R.id.tvplace);
            tvSunrise = (TextView) findViewById(R.id.tvsunrise);
            tvSunset = (TextView) findViewById(R.id.tvsunset);
            tvMain = (TextView) findViewById(R.id.tvmain);
            tvVis = (TextView) findViewById(R.id.tvvis);
            tvLat = (TextView) findViewById(R.id.tvlat);
            btnadd = (Button) findViewById(R.id.btnadd);
            tvLong = (TextView) findViewById(R.id.tvlon);
            tvHum = (TextView) findViewById(R.id.tvhum);
            tvPres = (TextView) findViewById(R.id.tvtpres);
            tvWind = (TextView) findViewById(R.id.tvwnd);
            iv = (ImageView) findViewById(R.id.ivicon);
            etlocation = (EditText) findViewById(R.id.etlocation);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            Intent i = getIntent();


            MyDatabaseHelper dbHelper = new MyDatabaseHelper(this);
            mydb = dbHelper.getWritableDatabase();

            final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

            btngo.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            ConnectivityManager conMan = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                            NetworkInfo active = conMan.getActiveNetworkInfo();
                            if (active != null && active.getState() == NetworkInfo.State.CONNECTED) {
                                String loc = etlocation.getText().toString();
                                if (loc == null || loc.isEmpty()) {
                                    Toast.makeText(MainActivity.this, "Wrong Syntax.", Toast.LENGTH_SHORT).show();
                                } else {
                                    DownloadData d = new DownloadData();
                                    String locnow = "";
                                    for (int i = 0; i < loc.length(); i++) {
                                        if (loc.charAt(i) == ' ') {
                                            locnow += "%20";
                                        } else {
                                            locnow += loc.charAt(i);
                                        }
                                    }
                                    d.execute(locnow);
                                }

                            } else {
                                Snackbar sb = Snackbar.make(cl, "Internet not Available", Snackbar.LENGTH_LONG).
                                        setAction("Retry", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                btngo.performClick();
                                            }
                                        });
                                sb.show();

                            }
                        }
                    });
            DynamicCC();
            fab.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ConnectivityManager conMan = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                            NetworkInfo active = conMan.getActiveNetworkInfo();
                            if (active != null && active.getState() == NetworkInfo.State.CONNECTED) {
                                String loc = etlocation.getText().toString();
                                if (loc == null || loc.isEmpty()) {
                                    Toast.makeText(MainActivity.this, "Wrong Syntax.", Toast.LENGTH_SHORT).show();
                                } else {
                                    DownloadForecastData d = new DownloadForecastData();
                                    String locnow = "";
                                    for (int i = 0; i < loc.length(); i++) {
                                        if (loc.charAt(i) == ' ') {
                                            locnow += "%20";
                                        } else {
                                            locnow += loc.charAt(i);
                                        }
                                    }
                                    d.execute(locnow);
                                }


                            } else {
                                Snackbar sb = Snackbar.make(cl, "Internet not Available", Snackbar.LENGTH_LONG).
                                        setAction("Retry", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                fab.performClick();
                                            }
                                        });
                                sb.show();

                            }
                        }
                    });

            btnadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String place = tvPlace.getText().toString();
                    ArrayList<String> citys = mycities.fetchCities(mydb);
                    Boolean isthere = false;
                    for (int i = 0; i < citys.size(); i++) {
                        if (citys.get(i).equals(place)) isthere = true;
                    }
                    if (isthere) {
                        Snackbar sb = Snackbar.make(cl, "City Already Added.", Snackbar.LENGTH_LONG).
                                setAction("Go to my cities", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        MyDatabaseHelper dbHelper = new MyDatabaseHelper(MainActivity.this);
                                        mydb = dbHelper.getWritableDatabase();
                                        ArrayList<String> cities = mycities.fetchCities(mydb);
                                        DownloadcityData d = new DownloadcityData();
                                        d.execute(cities);
                                    }
                                });
                        sb.show();


                    } else {
                        mycities.addCity(mydb, place);
                        Snackbar sb = Snackbar.make(cl, "City Added!", Snackbar.LENGTH_LONG).
                                setAction("Go to my cities", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        MyDatabaseHelper dbHelper = new MyDatabaseHelper(MainActivity.this);
                                        mydb = dbHelper.getWritableDatabase();
                                        ArrayList<String> cities = mycities.fetchCities(mydb);
                                        DownloadcityData d = new DownloadcityData();
                                        d.execute(cities);
                                    }
                                });
                        sb.show();

                    }
                }
            });


            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
            if (i != null) {
                if (i.getStringExtra("city") != null) {
                    if (i.getStringExtra("city").equals("MyCities")) {
                        StartMyCities();
                        this.finish();
                    } else {
                        etlocation.setText(i.getStringExtra("city"));
                        btngo.performClick();
                    }
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_cities) {
            MyDatabaseHelper dbHelper = new MyDatabaseHelper(MainActivity.this);
            mydb = dbHelper.getWritableDatabase();
            ArrayList<String> cities = mycities.fetchCities(mydb);
            DownloadcityData d = new DownloadcityData();
            d.execute(cities);

        } else if (id == R.id.nav_setting) {
            Intent i = new Intent(MainActivity.this,SettingsActivity.class);
            startActivity(i);

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    void DynamicCC() {
        SensorManager manager = (SensorManager) getSystemService(SENSOR_SERVICE);

        Sensor accSensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


        SensorEventListener sel = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                Float vals[] = new Float[3];
                for (int i = 0; i < event.values.length; i++) {
                    vals[i] = event.values[i];
                }

                cv1.setCardBackgroundColor(
                        Color.rgb(
                                accel2color(vals[0]),
                                accel2color(vals[1]),
                                accel2color(vals[2])));
                cv2.setCardBackgroundColor(
                        Color.rgb(
                                accel2color(vals[0]),
                                accel2color(vals[1]),
                                accel2color(vals[2])));
                cv3.setCardBackgroundColor(
                        Color.rgb(
                                accel2color(vals[0]),
                                accel2color(vals[1]),
                                accel2color(vals[2])));


            }


            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        manager.registerListener(sel, accSensor, manager.SENSOR_DELAY_NORMAL);


    }

   public void StartMyCities(){
       MyDatabaseHelper dbHelper = new MyDatabaseHelper(MainActivity.this);
       mydb = dbHelper.getWritableDatabase();
       ArrayList<String> cities = mycities.fetchCities(mydb);
       DownloadcityData d = new DownloadcityData();
       d.execute(cities);
    }
    int accel2color(float acccel) {
        return (int) (((acccel + 12) / 24) * 255);
    }

    public class DownloadData extends AsyncTask<String, Object, model> {
        ProgressBar progressBarb = (ProgressBar) findViewById(R.id.progressBar);

        @Override
        protected void onPreExecute() {
            progressBarb.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }


        @Override
        protected model doInBackground(String... params) {
            URL url = null;
            model model = null;
            try {
                url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + params[0] + "&APPID=3237c00769e8cb7115d03c189ca4e5d5");
            } catch (MalformedURLException ex) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "City doesn't exist.", Toast.LENGTH_SHORT).show();
                        tv.setText("");

                    }
                });
            }
            HttpURLConnection httpURLConnection = null;
            try {
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.connect();
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader streamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(streamReader);

                StringBuilder sb = new StringBuilder();
                String buffer;

                while ((buffer = reader.readLine()) != null) {
                    sb.append(buffer);
                }
                String json = sb.toString();
                model = getJson(json);
                return model;

            } catch (IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "City doesn't exist.", Toast.LENGTH_SHORT).show();
                        tv.setText("");
                    }
                });
            }
            return model;
        }

        @Override
        protected void onPostExecute(model model) {
            super.onPostExecute(model);
            progressBarb.setVisibility(View.INVISIBLE);

            if (model == null) {
                tv.setText("");
                tvMin.setText("");
                tvMax.setText("");
                tvPlace.setText("");
                tvSunset.setText("");
                tvSunrise.setText("");
                tvMain.setText("");
                tvVis.setText("");
                tvLat.setText("");
                tvLong.setText("");
                tvHum.setText("");
                tvPres.setText("");
                tvWind.setText("");
                iv.setImageResource(android.R.color.transparent);
                cv1.setVisibility(View.INVISIBLE);
                cv2.setVisibility(View.INVISIBLE);
                cv3.setVisibility(View.INVISIBLE);
                btnadd.setVisibility(View.INVISIBLE);
                return;
            } else {
                cv1.setVisibility(View.VISIBLE);
                cv2.setVisibility(View.VISIBLE);
                cv3.setVisibility(View.VISIBLE);
                btnadd.setVisibility(View.VISIBLE);

                tv.setText("            Temp " + (gettemp(model.main.getTemp().intValue())));
                if (!(model.main.getTemp_min().intValue() == model.main.getTemp().intValue())) {
                    tvMin.setText("Min Temp " + (gettemp(model.main.getTemp_min().intValue())));
                    tvMax.setText("     Max Temp " + (gettemp(model.main.getTemp_max().intValue())));
                } else {
                    tvMax.setText("");
                    tvMin.setText("");
                }
                tvPlace.setText(model.getName() + ", " + model.sys.getCountry());
                tvSunrise.setText("Sunrise " + gettime(model.sys.getSunrise()));
                tvSunset.setText("Sunset " + gettime(model.sys.getSunset()));
                tvMain.setText("         Condition : " + model.weather.getDescription());
                if (model.getVisibility() != 0) {
                    tvVis.setText("          Visibility " + model.getVisibility() + "m");
                } else {
                    tvVis.setText("");
                }
                tvLat.setText("          Latitude " + model.coord.getLati());
                tvLong.setText("          Longitude " + model.coord.getLongi());
                tvHum.setText("                       Humidity " + model.main.getHumidity() + "%");
                tvPres.setText("                   Pressure " + model.main.getPressure() + " hPa");
                if (model.wind.getSpeed() != null) {
                    tvWind.setText("        Wind " + model.wind.getSpeed() + "mps" + " (" + getdir(model.wind.getDeg()) + ")");
                } else {
                    tvWind.setText("");
                }
                Picasso.with(MainActivity.this).load("http://openweathermap.org/img/w/" + model.weather.getIcon() + ".png").into(iv);

            }
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

        public String gettime(long unix) {
            long unixSeconds = unix + 19800;
            Date date = new Date(unixSeconds * 1000L);
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss ");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT-5.30"));
            String formattedDate = sdf.format(date);
            return formattedDate + "IST";
        }

        public String getdir(double val) {
            String directions[] = {"N", "NE", "E", "SE", "S", "SW", "W", "NW", "N"};
            return directions[(int) Math.round((((double) val % 360) / 45))];
        }

    public String gettemp(int temp){
        if (MainActivity.temp=='k'){
            return temp+"K";
        }
       else if(MainActivity.temp=='c'){
            return (temp-273)+"°C";
        }
        else if(MainActivity.temp=='f'){
            return ((9/5)*(temp-273))+32+"°F";
        }
        else return "";
    }

    }

    @Override
    protected void onResume() {
        super.onResume();
        tvPlace = (TextView) findViewById(R.id.tvplace);
            if (tvPlace.getText() != null && !tvPlace.getText().equals("")) {
                etlocation.setText(tvPlace.getText());
                btngo.performClick();
            }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences.Editor prefEditor = getApplicationContext().getSharedPreferences(MY_PREF,MODE_PRIVATE).edit();
        if(temp=='c'){
            prefEditor.putInt("temp",1);
        }else if(temp=='f'){
            prefEditor.putInt("temp",2);
        } if(temp=='k'){
            prefEditor.putInt("temp",3);
        }
        prefEditor.apply();
    }

    public class DownloadForecastData extends AsyncTask<String, Object, String> {
        ProgressBar progressBarb = (ProgressBar) findViewById(R.id.progressBar);

        @Override
        protected void onPreExecute() {
            progressBarb.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }


        @Override
        protected String doInBackground(String... params) {
            URL url = null;
            String json = null;
            try {
                url = new URL("http://api.openweathermap.org/data/2.5/forecast?q=" + params[0] + "&APPID=3237c00769e8cb7115d03c189ca4e5d5");
            } catch (MalformedURLException ex) {
                runOnUiThread(
                        new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "Hello World.", Toast.LENGTH_SHORT).show();
                                tv.setText("");
                            }
                        });
            }
            HttpURLConnection httpURLConnection = null;
            try {
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.connect();
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader streamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(streamReader);

                StringBuilder sb = new StringBuilder();
                String buffer;

                while ((buffer = reader.readLine()) != null) {
                    sb.append(buffer);
                }
                json = sb.toString();


                return json;

            } catch (IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "City doesn't exist.", Toast.LENGTH_SHORT).show();
                        tv.setText("");
                    }
                });
            }
            return json;
        }

        @Override
        protected void onPostExecute(String json) {
            progressBarb.setVisibility(View.INVISIBLE);
            if (json == null || json == "") {
                return;
            }
            {
                Intent i = new Intent(MainActivity.this, ForecastActivity.class);
                i.putExtra("json", json);
                startActivity(i);

                super.onPostExecute(json);


            }
        }


    }

    public  class DownloadcityData extends AsyncTask<ArrayList<String>, Object, ArrayList<String>> {
          ProgressBar progressBarb = (ProgressBar) findViewById(R.id.progressBar);

        @Override
        protected void onPreExecute() {
            progressBarb.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }


        @Override
        protected ArrayList<String> doInBackground(ArrayList<String>... params) {
            URL url = null;
            ArrayList<String> models = new ArrayList<>();
            for (int i = 0; i < params[0].size(); i++) {
                try {
                    url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + params[0].get(i) + "&APPID=3237c00769e8cb7115d03c189ca4e5d5 ");
                } catch (MalformedURLException ex) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {


                        }
                    });
                }
                HttpURLConnection httpURLConnection = null;
                try {
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.connect();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    InputStreamReader streamReader = new InputStreamReader(inputStream);
                    BufferedReader reader = new BufferedReader(streamReader);

                    StringBuilder sb = new StringBuilder();
                    String buffer;

                    while ((buffer = reader.readLine()) != null) {
                        sb.append(buffer);
                    }
                    String json = sb.toString();
                    models.add(json);


                } catch (IOException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });
                }
            }
            return models;
        }

        @Override
        protected void onPostExecute(ArrayList<String> models) {
            progressBarb.setVisibility(View.INVISIBLE);
            Intent i = new Intent(MainActivity.this, MyCitiesActivity.class);
            i.putStringArrayListExtra("models", models);
            startActivity(i);

        }


    }
}
