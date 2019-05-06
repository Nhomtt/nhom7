package com.example.hoangduy.svtour.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoangduy.svtour.R;
import com.example.hoangduy.svtour.model.Image;
import com.example.hoangduy.svtour.model.Note;

import java.util.ArrayList;

/**
 * Created by HoangDuy on 5/11/2018.
 */

public class NoteAdapter extends BaseAdapter{

    private Context context;
    private int layout;
    private ArrayList<Note> NoteList;

    public NoteAdapter(Context context, int layout, ArrayList<Note> imageList) {
        this.context = context;
        this.layout = layout;
        NoteList = imageList;
    }


    @Override
    public int getCount() {
        return NoteList.size();
    }

    @Override
    public Object getItem(int position) {
        return NoteList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        TextView tvNoteTitle, tvNoteDescription;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        View row = view;
        ViewHolder holder = new ViewHolder();
        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout,null);
            holder.tvNoteTitle = (TextView) row.findViewById(R.id.tvNoteTiTle);
            //holder.tvNoteDescription = (TextView) row.findViewById(R.id.tvNoteDesctiption);
            row.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) row.getTag();
        }
        Note note = NoteList.get(position);
        holder.tvNoteTitle.setText(note.getNote_title());
        //holder.tvNoteDescription.setText(note.getNote_description());

        return row;
    }
}
