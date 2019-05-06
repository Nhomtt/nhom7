package com.phongbm.smartclock;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

@SuppressLint("ViewHolder")
public class InforTimeLapAdapter extends ArrayAdapter<InforTimeLap> {
	private Activity activity;
	private int resource;
	private ArrayList<InforTimeLap> inforTimeLaps;

	public InforTimeLapAdapter(Activity activity, int resource,
			ArrayList<InforTimeLap> inforTimeLaps) {
		super(activity, resource, inforTimeLaps);
		this.activity = activity;
		this.resource = resource;
		this.inforTimeLaps = inforTimeLaps;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater layoutInflater = activity.getLayoutInflater();
		convertView = layoutInflater.inflate(resource, null);

		TextView indexTime = (TextView) convertView
				.findViewById(R.id.txtIndexTime);
		TextView txtItemTime = (TextView) convertView
				.findViewById(R.id.txtItemTime);
		TextView txtItemPercentSecond = (TextView) convertView
				.findViewById(R.id.txtItemPercentSecond);

		indexTime.setText(inforTimeLaps.get(position).getIndex());
		txtItemTime.setText(inforTimeLaps.get(position).getTime());
		txtItemPercentSecond.setText(inforTimeLaps.get(position)
				.getPercentTime());

		return convertView;
	}
}