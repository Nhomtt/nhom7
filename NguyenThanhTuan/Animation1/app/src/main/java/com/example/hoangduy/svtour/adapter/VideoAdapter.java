package com.example.hoangduy.svtour.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.hoangduy.svtour.R;
import com.example.hoangduy.svtour.model.Image;
import com.example.hoangduy.svtour.model.Video;

import java.util.ArrayList;

/**
 * Created by HoangDuy on 5/11/2018.
 */

public class VideoAdapter extends BaseAdapter{

    private Context context;
    private int layout;
    private ArrayList<Video> VideoList;

    public VideoAdapter(Context context, int layout, ArrayList<Video> videoList) {
        this.context = context;
        this.layout = layout;
        VideoList = videoList;
    }

    @Override
    public int getCount() {
        return VideoList.size();
    }

    @Override
    public Object getItem(int position) {
        return VideoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        //VideoView videoView;
        TextView tvVideo;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        View row = view;
        ViewHolder holder = new ViewHolder();
        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout,null);
            holder.tvVideo = (TextView) row.findViewById(R.id.tvVideo);
            row.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) row.getTag();
        }

        Video video = VideoList.get(position);

        holder.tvVideo.setText(video.getVideo_url());

        return row;
    }
}
