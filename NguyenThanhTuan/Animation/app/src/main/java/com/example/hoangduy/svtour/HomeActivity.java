package com.example.hoangduy.svtour;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hoangduy.svtour.adapter.ListTourAdapter;
import com.example.hoangduy.svtour.database.DatabaseHelper;
import com.example.hoangduy.svtour.model.Tour;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

public class HomeActivity extends AppCompatActivity {

    private ListView listviewTour;
    private ListTourAdapter adapter;
    private List<Tour> TourList;
    private DatabaseHelper DatabaseHelper;

    Button btnThem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        DatabaseHelper = new DatabaseHelper(this);
        listviewTour = (ListView) findViewById(R.id.lvDulich);
        TourList = DatabaseHelper.getListTour();
        adapter = new ListTourAdapter(this, TourList);
        listviewTour.setAdapter(adapter);

        btnThem = (Button) findViewById(R.id.btnThem);
        btnThem.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        listviewTour.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Tour tour = TourList.get(position);
                Intent intent = new Intent(HomeActivity.this, DetailActivity.class);
                intent.putExtra("tour",tour);
                startActivity(intent);
            }
        });
    }
}
