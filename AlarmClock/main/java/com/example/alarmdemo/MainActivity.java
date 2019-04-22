package com.example.alarmdemo;

import java.util.GregorianCalendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends ListActivity {
	private AlarmListAdapter mAdapter;
	private AlarmDBHelper dbHelper = new AlarmDBHelper(this);
	private Context mContext;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mContext = this;
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        
        setContentView(R.layout.activity_main);

        //test
        
//        Button testBtn = (Button) findViewById(R.id.test_btn);
//        testBtn.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Long time = new GregorianCalendar().getTimeInMillis() + 5*1000;
//				Intent intent = new Intent(mContext, AlarmService.class);
//				AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//
//				alarmManager.set(AlarmManager.RTC_WAKEUP, time, PendingIntent.getService(mContext, 5, intent, PendingIntent.FLAG_UPDATE_CURRENT));
//			}
//		});
        
        mAdapter = new AlarmListAdapter(this, dbHelper.getAlarms());
        
        setListAdapter(mAdapter);
    }
    
    public boolean onCreateOptionsMenu(Menu menu) {
    	getMenuInflater().inflate(R.menu.alarm_list, menu);
		return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
    	
	    switch (item.getItemId()) {
			case R.id.action_add_new_alrm: {
				startAlarmDetailsActivity(-1);
				break;
			}
	    }
		
		return super.onOptionsItemSelected(item);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	// TODO Auto-generated method stub
    	super.onActivityResult(requestCode, resultCode, data);
    	
    	if (resultCode == RESULT_OK) {
    		mAdapter.setAlarms(dbHelper.getAlarms());
    		mAdapter.notifyDataSetChanged();
    	}
    }
    
    public void setAlarmEnabled(long id, boolean isEnabled) {
    	AlarmManagerHelper.cancelAlarms(this);
    	
    	AlarmModel model = dbHelper.getAlarm(id);
    	model.isEnabled = isEnabled;
    	dbHelper.updateAlarm(model);
    	
    	mAdapter.setAlarms(dbHelper.getAlarms());
    	mAdapter.notifyDataSetChanged();
    	
    	AlarmManagerHelper.setAlarms(this);
    }
    
    public void startAlarmDetailsActivity(long id) {
    	Intent intent = new Intent(this, AlarmDetailsActivity.class);
    	intent.putExtra("id", id);
    	startActivityForResult(intent, 0);
    }

//    xoa trong danh sach
    public void deleteAlarm(long id) {
    	final long alarmId = id;
    	Log.i(null, "1234567");
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setMessage("Do you want delete?").setTitle("Truely set?").setCancelable(true)
    	.setNegativeButton("Cancel", null)
    	.setPositiveButton("Ok", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dbHelper.deleteAlarm(alarmId);
				mAdapter.setAlarms(dbHelper.getAlarms());
				mAdapter.notifyDataSetChanged();
			}
		}).show();
    }
}
