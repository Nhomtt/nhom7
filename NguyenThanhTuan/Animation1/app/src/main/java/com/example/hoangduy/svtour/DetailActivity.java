package com.example.hoangduy.svtour;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hoangduy.svtour.adapter.ImageAdapter;
import com.example.hoangduy.svtour.adapter.NoteAdapter;
import com.example.hoangduy.svtour.adapter.VideoAdapter;
import com.example.hoangduy.svtour.database.DatabaseHelper;
import com.example.hoangduy.svtour.model.Image;
import com.example.hoangduy.svtour.model.Note;
import com.example.hoangduy.svtour.model.Tour;
import com.example.hoangduy.svtour.model.Video;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    GridView gridViewImage;
    ListView gridViewVideo;
    ListView listViewNote;

    ArrayList<Image> listImage;
    ArrayList<Video> listVideo;
    ArrayList<Note> listNote;

    ImageAdapter adapterImage = null;
    VideoAdapter adapterVideo = null;
    NoteAdapter adapterNote = null;

    DatabaseHelper DatabaseHelper;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Tour tour = (Tour) getIntent().getExtras().getSerializable("tour");
        TextView tvTourName = (TextView) findViewById(R.id.tvTourName);


        final String tour_name = tour.getTour_name();
        final int tour_id = tour.getTour_id();

        tvTourName.setText(tour_name);

        DatabaseHelper = new DatabaseHelper(this);

        //GRID VIEW IMAGE
        gridViewImage = (GridView) findViewById(R.id.gvImage);
        listImage = new ArrayList<>();
        listImage = DatabaseHelper.getListImageByTouId(tour_id);
        adapterImage = new ImageAdapter(this, R.layout.gridview_item_image, listImage);
        gridViewImage.setAdapter(adapterImage);

        //GRID VIEW VIDEO
        gridViewVideo = (ListView) findViewById(R.id.lvVideo);
        listVideo = new ArrayList<>();
        listVideo = DatabaseHelper.getListVideoByTouId(tour_id);
        adapterVideo = new VideoAdapter(this, R.layout.gridview_item_video, listVideo);
        gridViewVideo.setAdapter(adapterVideo);

        //LIST VIEW NOTE
        listViewNote = (ListView) findViewById(R.id.lvNote);
        listNote = new ArrayList<>();
        listNote = DatabaseHelper.getListNoteByTouId(tour_id);
        adapterNote = new NoteAdapter(this, R.layout.listview_item_note, listNote);
        listViewNote.setAdapter(adapterNote);

        ImageButton btnAdd = (ImageButton) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, UpdateActivity.class);
                intent.putExtra("tour_id",tour_id);
                intent.putExtra("tour_name",tour_name);
                startActivity(intent);
            }
        });

        // Khởi tạo dialog thêm ghi chú
        dialog = new Dialog(DetailActivity.this);
        dialog.setTitle(tour_name);
        dialog.setContentView(R.layout.dialog_view_note);

        // Lấy id các đối tượng trên dialog
        Button cancel_dialog = (Button) dialog.findViewById(R.id.btnCancel);
        final TextView note_title = (TextView) dialog.findViewById(R.id.note_view_title);
        final TextView note_description = (TextView) dialog.findViewById(R.id.note_view_description);

        // Nhấn vào image gridview
        gridViewImage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Image image = listImage.get(position);
                Intent intent = new Intent(DetailActivity.this, ViewImageActivity.class);
                intent.putExtra("image",image);
                startActivity(intent);
            }
        });

        // Nhấn vào video listview
        gridViewVideo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Video video = listVideo.get(position);
                Intent intent = new Intent(DetailActivity.this, ViewVideoActivity.class);
                intent.putExtra("video",video);
                startActivity(intent);
            }
        });

        // Nhấn vào item list view
        listViewNote.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                note_title.setText(listNote.get(position).getNote_title());
                note_description.setText(listNote.get(position).getNote_description());
                dialog.show();
            }
        });
        cancel_dialog.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.cancel();
            }
        });
    }

}
