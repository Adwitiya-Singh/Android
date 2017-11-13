package com.example.adwitiyasingh.weatherwithdrawer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adwitiyasingh.weatherwithdrawer.db.MyDatabaseHelper;
import com.example.adwitiyasingh.weatherwithdrawer.db.tables.mycities;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by adwitiyasingh on 4/15/17.
 */

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ClassItemHolder> {
    Context context;
    ArrayList<model> models;

    public CityAdapter(ArrayList<model> models, Context context) {
        this.context = context;
        this.models = models;
    }

    @Override
    public ClassItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = li.inflate(R.layout.jsoncityview, parent, false);
        return new CityAdapter.ClassItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ClassItemHolder holder, int position) {
        model now = models.get(position);
        Picasso.with(context).load("http://openweathermap.org/img/w/" + now.weather.getIcon() + ".png").into(holder.ivcity);
        holder.tvmaincity.setText("Conditions : " + now.weather.getDescription());
        holder.tvnamecity.setText(now.getName() + ", " + now.sys.getCountry());
        holder.tvtempcity.setText(String.valueOf(gettemp(now.main.getTemp().intValue())));
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    class ClassItemHolder extends RecyclerView.ViewHolder {
        ImageView ivcity;
        TextView tvmaincity, tvnamecity, tvtempcity;
        CardView crdcity;
        SQLiteDatabase mydb;


        public ClassItemHolder(View itemView) {
            super(itemView);
            ivcity = (ImageView) itemView.findViewById(R.id.ivcityicon);
            tvmaincity = (TextView) itemView.findViewById(R.id.tvcityMain);
            tvnamecity = (TextView) itemView.findViewById(R.id.tvcityPlace);
            tvtempcity = (TextView) itemView.findViewById(R.id.tvcitytemp);
            crdcity = (CardView) itemView.findViewById(R.id.crdcity);
            crdcity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, MainActivity.class);
                    i.putExtra("city", tvnamecity.getText());
                    context.startActivity(i);

                }
            });
            crdcity.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    MyDatabaseHelper dbHelper = new MyDatabaseHelper(context);
                    mydb = dbHelper.getWritableDatabase();
                    mycities.removeCity(mydb, tvnamecity.getText().toString());
                    Intent i = new Intent(context, MainActivity.class);
                    i.putExtra("city","MyCities");
                    context.startActivity(i);
                    ((Activity)context).finish();

                    return true;
                }
            });

        }
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
