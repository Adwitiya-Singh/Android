package com.example.adwitiyasingh.databases;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.adwitiyasingh.databases.db.MyDatabaseHelper;
import com.example.adwitiyasingh.databases.db.tables.TodoTable;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
ArrayList<String> todos = new ArrayList<>();
    ListView lvtodos;
    EditText ettodo;
    Button btnadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvtodos = (ListView) findViewById(R.id.lvtodos);
        ettodo = (EditText) findViewById(R.id.ettodo);
        btnadd = (Button) findViewById(R.id.btnadd);


        MyDatabaseHelper dbHelper = new MyDatabaseHelper(this);
        final SQLiteDatabase mydb = dbHelper.getWritableDatabase();

        todos = TodoTable.fetchTodos(mydb);


        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,android.R.id.text1,todos);
        lvtodos.setAdapter(arrayAdapter);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todos.add(ettodo.getText().toString());
                arrayAdapter.notifyDataSetChanged();
                TodoTable.addTask(mydb, ettodo.getText().toString());
            }
        });
    }
}
