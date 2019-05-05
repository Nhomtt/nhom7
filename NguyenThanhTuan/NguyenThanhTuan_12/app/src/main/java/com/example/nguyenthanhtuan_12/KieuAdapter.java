package com.example.nguyenthanhtuan_12;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class KieuAdapter extends RecyclerView.Adapter<KieuAdapter.ViewHolder> {
    ArrayList<DataKieu> data;

    private OnItemClickListener mListener;
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }
    Context context;
    public KieuAdapter(ArrayList<DataKieu> data, Context context){
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = layoutInflater.inflate(R.layout.custom_recyclerview_layout,viewGroup,false);
        return new ViewHolder(itemView, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.txtKieu.setText(data.get(i).getKieu());
        String stylekieu = data.get(i).getKieu();
        switch (stylekieu){
            case "Kieu 1":
                viewHolder.itemView.setBackgroundColor(Color.WHITE);
                viewHolder.txtKieu.setText("THEME_MATERIAL_LIGHT  - Color Background: gray");
//                viewHolder.tvTitle.setTextColor();
                break;
            case "Kieu 2":
                viewHolder.itemView.setBackgroundColor(Color.YELLOW);
                viewHolder.txtKieu.setText("Theme.AppCompat.Dialog.Alert  - Color Background: yellow");
//                viewHolder.tvTitle.setTextColor(Color.YELLOW);
                viewHolder.txtKieu.setTextColor(Color.BLUE);

                break;
            case "Kieu 3":
                viewHolder.itemView.setBackgroundColor(Color.BLUE);
                viewHolder.txtKieu.setText("Theme.AppCompat.DayNight.Dialog.Alert  - Color Background: blue");
                break;
            case "Kieu 4":
                viewHolder.itemView.setBackgroundColor(Color.GREEN);
                viewHolder.txtKieu.setText("ThemeOverlay.AppCompat.ActionBar  - Color Background: green");
                break;

        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtKieu;
//        TextView tvTitle;
        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            txtKieu = (TextView)itemView.findViewById(R.id.tvKieu);
//            tvTitle = (TextView)itemView.findViewById(R.id.tvHeader);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                    {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION)
                        {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

}
