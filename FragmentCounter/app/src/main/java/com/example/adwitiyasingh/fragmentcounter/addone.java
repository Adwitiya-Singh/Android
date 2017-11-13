package com.example.adwitiyasingh.fragmentcounter;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by adwitiyasingh on 3/18/17.
 */

public class addone extends Fragment {
    Button btnone;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.plusone, container, false);
        btnone = (Button) rootview.findViewById(R.id.btnone);
        btnone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                  Integer in = Integer.parseInt(((MainActivity) getActivity()).tv.getText().toString());
                in++;
                ((MainActivity) getActivity()).tv.setText(in.toString());


                }

        });
        return rootview;
    }
}
