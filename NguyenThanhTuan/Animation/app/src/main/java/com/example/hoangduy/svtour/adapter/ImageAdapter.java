package com.example.hoangduy.svtour.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoangduy.svtour.R;
import com.example.hoangduy.svtour.model.Image;

import java.util.ArrayList;

/**
 * Created by HoangDuy on 5/11/2018.
 */

public class ImageAdapter extends BaseAdapter{

    private Context context;
    private int layout;
    private ArrayList<Image> ImageList;

    public ImageAdapter(Context context, int layout, ArrayList<Image> imageList) {
        this.context = context;
        this.layout = layout;
        ImageList = imageList;
    }


    @Override
    public int getCount() {
        return ImageList.size();
    }

    @Override
    public Object getItem(int position) {
        return ImageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        ImageView imageView;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        View row = view;
        ViewHolder holder = new ViewHolder();
        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout,null);
            holder.imageView = (ImageView) row.findViewById(R.id.imageView);
            row.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) row.getTag();
        }

        Image image = ImageList.get(position);

        byte[] tour_byte = image.getImage_byte();
        Bitmap bitmap = BitmapFactory.decodeByteArray(tour_byte, 0, tour_byte.length);
        holder.imageView.setImageBitmap(bitmap);
        return row;
    }
}
