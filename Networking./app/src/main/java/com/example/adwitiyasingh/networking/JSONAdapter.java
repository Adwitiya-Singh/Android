package com.example.adwitiyasingh.networking;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by adwitiyasingh on 3/26/17.
 */

public class JSONAdapter extends RecyclerView.Adapter<JSONAdapter.JSONItemHolder> {

    private ArrayList<model> model;
    private Context context;

    public JSONAdapter(ArrayList<model> model, Context context) {
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
        model thisitem = model.get(position);
        holder.usrid.setText("User ID : " + thisitem.getUserId());
        holder.id.setText("ID : " + thisitem.getId());
        holder.title.setText(thisitem.getTitle());
        holder.body.setText(thisitem.getBody());

    }

    @Override
    public int getItemCount() {
        return model.size();

    }

    class JSONItemHolder extends RecyclerView.ViewHolder {
        TextView usrid;
        TextView id;
        TextView title;
        TextView body;


        public JSONItemHolder(View itemView) {
            super(itemView);
            usrid = (TextView) itemView.findViewById(R.id.tvusrid);
            id = (TextView) itemView.findViewById(R.id.tvid);
            title = (TextView) itemView.findViewById(R.id.tvtitle);
            body = (TextView) itemView.findViewById(R.id.tvbody);


        }
    }
}
