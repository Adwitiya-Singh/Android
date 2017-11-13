package com.example.adwitiyasingh.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> Values = new ArrayList<>();


    ListView Courses;
    Button btnGnrt;
    EditText etAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Courses = (ListView) findViewById(R.id.listCourses);
        btnGnrt = (Button) findViewById(R.id.Btngnrt);
        etAdd = (EditText) findViewById(R.id.Etadd);
        final ArrayAdapter<String> myAdaptor = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, Values);
        Courses.setAdapter(myAdaptor);
        btnGnrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String rslt = etAdd.getText().toString();
                for (int i = 0; i < rslt.length(); i++) {
                    Character now = rslt.charAt(i);
                    if (!Character.isDigit(now)) {
                        Toast.makeText(getApplicationContext(), "Not a number!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                Integer size = Integer.parseInt(rslt);
                ArrayList<String> temp = new ArrayList<String>();
                int size2 = Values.size();
                if (Values.size() == 0) {
                    Values.clear();
                    for (Integer j = 1; j <= size; j++) {
                        String Final = "";

                        if (j % 3 == 0) {
                            Final += "fizz";
                        }
                        if (j % 5 == 0) {
                            Final += "buzz";

                        }
                        if (Final.equals("")) {
                            Final += j.toString();
                        }
                        Values.add(Final);

                    }
                } else if (size > Values.size()) {
                    for (Integer j = Values.size() + 1; j <= size; j++) {
                        String Final = "";

                        if (j % 3 == 0) {
                            Final += "fizz";
                        }
                        if (j % 5 == 0) {
                            Final += "buzz";

                        }
                        if (Final.equals("")) {
                            Final += j.toString();
                        }
                        Values.add(Final);

                    }
                }
                 else if(size<Values.size()){
                    ArrayList<String> now= (ArrayList<String>)Values.subList(0,size);
                    temp=Values;
                    Values=now;

                }
                 else if (size == Values.size()) {
                    return;
                }

                myAdaptor.notifyDataSetChanged();
                if(size<size2) {
                    Values = temp;
                }

            }
        });


    }

}
