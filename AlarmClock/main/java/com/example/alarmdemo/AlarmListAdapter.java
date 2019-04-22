package com.example.alarmdemo;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;

public class AlarmListAdapter extends BaseAdapter {
	public static String TAG = AlarmListAdapter.class.getSimpleName();
	private Context mContext;
	private List<AlarmModel> mAlarms;
	
	public AlarmListAdapter(Context context, List<AlarmModel> alarms) {
		mContext = context;
		mAlarms = alarms;
	}
	
	public void setAlarms(List<AlarmModel> alarms) {
		mAlarms = alarms;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (mAlarms != null){
			return mAlarms.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		if (mAlarms != null) {
			return mAlarms.get(position);
		}
		return null;
	}
	

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		if (mAlarms != null) {
			return mAlarms.get(position).getId();
		}
		return 0;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		// TODO Auto-generated method stub
		final ViewHolder viewholder;
		if(view == null){
			LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.alarm_list_item, null, false);
			
			viewholder = new ViewHolder();
			viewholder.txtTime = (TextView) view.findViewById(R.id.alarm_item_time);
			viewholder.txtName = (TextView) view.findViewById(R.id.alarm_item_name);
			viewholder.btnToggle = (ToggleButton) view.findViewById(R.id.alarm_item_toogle);
			
			view.setTag(viewholder);
			
		}else{
			viewholder = (ViewHolder) view.getTag();
		}

		final AlarmModel model = (AlarmModel) getItem(position);
		
		viewholder.txtTime.setText(String.format("%02d : %02d", model.timeHour, model.timeMinute));
		viewholder.txtName.setText(model.name);
		
		viewholder.btnToggle.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				((MainActivity) mContext).setAlarmEnabled(Long.valueOf(model.id), isChecked);
			}
		});
		viewholder.btnToggle.setChecked(model.isEnabled);
		
		view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				((MainActivity) mContext).startAlarmDetailsActivity(Long.valueOf(model.id));
			}
		});
		
		view.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				Log.i(null, "abc1223");
				// TODO Auto-generated method stub
				((MainActivity) mContext).deleteAlarm(model.id);
				return true;
			}
		});
		
		return view;
	}
	
	private void updateTextColor(TextView view, boolean isOn){
		if (isOn) {
			view.setTextColor(Color.GREEN);
		} else {
			view.setTextColor(Color.BLACK);
		}
	}
	
	private class ViewHolder {
		TextView txtTime;
		TextView txtName;
		ToggleButton btnToggle;
	}
	
}
