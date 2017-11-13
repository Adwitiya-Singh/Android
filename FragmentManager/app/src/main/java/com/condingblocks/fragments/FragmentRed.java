package com.condingblocks.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by rishabhkhanna on 18/03/17.
 */

public class FragmentRed extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmentred , container , false);
        Button btn = (Button) rootView.findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //call mainactiivty method
                if(getActivity() instanceof MainActivity) {
//                    ((MainActivity) getActivity()).showToast();
                    ((MainActivity)getActivity()).shiftCounter(-1);
                }


            }
        });
        return rootView;
    }
}
