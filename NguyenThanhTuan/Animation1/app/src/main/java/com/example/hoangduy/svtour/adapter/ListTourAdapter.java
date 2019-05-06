package com.example.hoangduy.svtour.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoangduy.svtour.R;
import com.example.hoangduy.svtour.model.Tour;

import java.util.List;

/**
 * Created by HoangDuy on 5/8/2018.
 */

public class ListTourAdapter extends BaseAdapter{

    private Context mContext;
    private List<Tour> mTourList;

    public ListTourAdapter(Context mContext, List<Tour> mTourList) {
        this.mContext = mContext;
        this.mTourList = mTourList;
    }


    @Override
    public int getCount() {
        return mTourList.size();
    }

    @Override
    public Object getItem(int position) {
        return mTourList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mTourList.get(position).getTour_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = View.inflate(mContext, R.layout.tour_item_listview, null);

        ImageView ivTourAvatar = (ImageView) view.findViewById(R.id.ivTourAvatar);
        TextView tvTourName = (TextView) view.findViewById(R.id.tvTourName);
        TextView tvTourDate = (TextView) view.findViewById(R.id.tvTourDate);

        tvTourName.setText(mTourList.get(position).getTour_name());
        tvTourDate.setText(mTourList.get(position).getTour_date());

        try{
            byte[] tour_avatar = mTourList.get(position).getTour_avatar();
            if(tour_avatar != null) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(tour_avatar, 0, tour_avatar.length);
                ivTourAvatar.setImageBitmap(bitmap);
            }else {
                ivTourAvatar.setImageResource(R.drawable.noimage);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return view;
    }
}
