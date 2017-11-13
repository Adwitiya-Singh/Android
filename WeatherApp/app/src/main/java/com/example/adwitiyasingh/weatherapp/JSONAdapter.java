package com.example.adwitiyasingh.weatherapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by adwitiyasingh on 4/12/17.
 */

public class JSONAdapter extends RecyclerView.Adapter<JSONAdapter.JSONItemHolder> {
    private ArrayList<ForecastModel.List> model;
    private Context context;

    public JSONAdapter(ArrayList<ForecastModel.List> model, Context context) {
        this.model = model;
        this.context = context;
    }

    @Override
    public JSONItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = li.inflate(R.layout.jsonview, parent, false);
        return new JSONItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(JSONItemHolder holder, int position) {
        ForecastModel.List thsitem = model.get(position);
        Picasso.with(context).load("http://openweathermap.org/img/w/" + thsitem.getWtr().getIcon() + ".png").into(holder.icon);
        holder.Main.setText("         Condition : " + thsitem.getWtr().getDescription());
        holder.HumPres.setText("Humidity : " + thsitem.getMn().getHumidity() + "%     Pressure : " + thsitem.getMn().getPressure()+"hPa");
holder.Temp.setText("                 Temp : "+ (thsitem.getMn().getTemp().intValue()-274)+"°C");
        holder.tempMinMax.setText("Min : "+ (thsitem.getMn().getTemp_min().intValue()-274)+"°C     Max : "+(thsitem.getMn().getTemp_max().intValue()-274)+"°C");
        holder.tvDateTime.setText("      "+thsitem.getDtTime());
        if (thsitem.getWnd().getSpeed() != null) {
            holder.Wind.setText("Wind " + thsitem.getWnd().getSpeed() + "mps" + " (" + getdir(thsitem.getWnd().getDeg()) + ")");
        } else {
            holder.Wind.setText("");
        }
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    class JSONItemHolder extends RecyclerView.ViewHolder {
        TextView tvDateTime, Temp, tempMinMax, HumPres, Wind, Main;
        ImageView icon;

        public JSONItemHolder(View itemView) {
            super(itemView);
            tvDateTime = (TextView) itemView.findViewById(R.id.tvdatetime);
            Temp = (TextView) itemView.findViewById(R.id.tvforetemp);
            tempMinMax = (TextView) itemView.findViewById(R.id.tvforetempminmax);
            HumPres = (TextView) itemView.findViewById(R.id.tvforehumpres);
            Wind = (TextView) itemView.findViewById(R.id.tvforewind);
            Main = (TextView) itemView.findViewById(R.id.tvforeMain);
            icon = (ImageView) itemView.findViewById(R.id.ivForecastIcon);

        }
    }
    public String getdir(double val) {
        String directions[] = {"N", "NE", "E", "SE", "S", "SW", "W", "NW", "N"};
        return directions[(int) Math.round((((double) val % 360) / 45))];
    }
}
