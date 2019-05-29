package com.example.anastasia.planner;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.graphics.PorterDuff.Mode.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private static final String TAG = "MainActivity";
    private Button btnToCalendar;
    private Button btnToPlant;

    private EditText editT;
    private Button addT;
    private ListView listT;


    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main);
        btnToCalendar = (Button) findViewById(R.id.btnGoCalendar);

        btnToCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });

        btnToPlant = (Button) findViewById(R.id.btnGoPlant);
        btnToPlant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PlantActivity.class);
                startActivity(intent);
            }
        });



        editT =  findViewById(R.id.edit_text);
        addT =  findViewById(R.id.add_task);
        listT =  findViewById(R.id.list_task);

        items = FileHelper.readData(this);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);
        listT.setAdapter(adapter);

        addT.setOnClickListener(this);
        listT.setOnItemClickListener(this);


    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_task:
                String itemEntered = editT.getText().toString();
                adapter.add(itemEntered);
                editT.setText("");
                FileHelper.writeData(items,this);
                Toast.makeText(this,"Task Added",Toast.LENGTH_SHORT).show();
                break;
        }

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        items.remove(position);
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "Done. You can check your plant", Toast.LENGTH_SHORT).show();

    }
}