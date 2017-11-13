package com.example.adwitiyasingh.fragments;

import android.app.Activity;
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

public class FragmentBlue extends Fragment {
    Button btnone;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragmentblue, container, false);
        btnone = (Button) rootview.findViewById(R.id.btnone);
        btnone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).showToast();
                }
                else {
                    Toast.makeText(getActivity(),"Clicked from Fragment",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return rootview;
    }
}
