package com.example.alarmdemo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alarmdemo.R;
import com.example.alarmdemo.model.Group;
import com.example.alarmdemo.ui.MainActivity;

import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.GroupVH> {

    private List<Group> mGroupList;
    private MainActivity mActivity;

    public GroupAdapter(List<Group> groupList, MainActivity activity) {
        mGroupList = groupList;
        mActivity = activity;
    }

    @NonNull
    @Override
    public GroupVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.profile_row, parent, false);
        return new GroupVH(view);

    }

    @Override
    public void onBindViewHolder(@NonNull GroupVH holder, int position) {
        Group group = mGroupList.get(position);
        holder.image.setImageResource(group.image);
        holder.tv_name.setText(group.name);
        holder.tv_id.setText(group.id);
    }

    @Override
    public int getItemCount() {
        return mGroupList.size();
    }

    class GroupVH extends RecyclerView.ViewHolder {
        ImageView image;
        TextView tv_name, tv_id;

        public GroupVH(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_id = itemView.findViewById(R.id.tv_id);
        }
    }
}
