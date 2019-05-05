package com.example.nguyenthanhtuan_12;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

public class bai1 extends AppCompatActivity {
    private Spinner spThemes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // MUST BE SET BEFORE setContentView
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.bai1);


        setupSpinnerItemSelection();

        initView();


    }
    private void setupSpinnerItemSelection() {
        spThemes = (Spinner) findViewById(R.id.spThemes);
        spThemes.setSelection(ThemeApplication.currentPosition);
        ThemeApplication.currentPosition = spThemes.getSelectedItemPosition();

        spThemes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (ThemeApplication.currentPosition != position) {
                    Utils.changeToTheme(bai1.this, position);
                }
                ThemeApplication.currentPosition = position;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    public void initView() {
        RecyclerView rv = (RecyclerView) findViewById(R.id.rvKieu);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(llm);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, llm.getOrientation());
        rv.addItemDecoration(dividerItemDecoration);
        final ArrayList<DataKieu> arrayList = new ArrayList<>();
        arrayList.add(new DataKieu("Kieu 1"));
        arrayList.add(new DataKieu("Kieu 2"));
        arrayList.add(new DataKieu("Kieu 3"));
        arrayList.add(new DataKieu("Kieu 4"));
//        arrayList.add(new DataKieu("Kieu 5"));

        final KieuAdapter kieuAdapter = new KieuAdapter(arrayList, getApplicationContext());
        TextView tvheader = (TextView) findViewById(R.id.tvHeader);
//        tvkieu.setSelection(ThemeApplication.currentPosition);
//            arrayList.set(ThemeApplication.currentPosition,new DataKieu("Kieu 1") );
//                ThemeApplication.currentPosition = arrayList.get
//        ThemeApplication.currentPosition = spThemes.getSelectedItemPosition();

        kieuAdapter.setOnItemClickListener(new KieuAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(int position) {
//                arrayList.get(position).changeText("Click");
//                kieuAdapter.notifyDataSetChanged();
                if (ThemeApplication.currentPosition != position) {
                    Utils.changeToTheme(bai1.this, position);

                }

                ThemeApplication.currentPosition = position;

                Toast.makeText(bai1.this, "Thanh cong", Toast.LENGTH_SHORT).show();
            }
        });
        rv.setAdapter(kieuAdapter);
    }
}
