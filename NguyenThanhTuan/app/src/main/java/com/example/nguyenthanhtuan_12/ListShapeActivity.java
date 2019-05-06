package com.example.nguyenthanhtuan_12;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListShapeActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> list;
    ImageButton imageButtonBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_shape);
        final Intent intent = getIntent();
        listView = (ListView)findViewById(R.id.lvShape);
        list = new ArrayList<>();
        list.add("Tạo mới");
        list.add("Hình tròn");
        list.add("Hình vuông");
        list.add("Hình tam giác");
        list.add("Hình chữ nhật");
        list.add("Back");

        ArrayAdapter adapter = new ArrayAdapter(ListShapeActivity.this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        imageButtonBack = (ImageButton)findViewById(R.id.ibtBack);
        imageButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ListShapeActivity.this, "Please press Back", Toast.LENGTH_SHORT).show();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String vt = list.get(position);
                Toast.makeText(ListShapeActivity.this, list.get(position), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ListShapeActivity.this, MainActivity.class);
                intent.putExtra("vị trí",vt);
                setResult(RESULT_OK,intent);
               finish();
            }
        });


    }
}
