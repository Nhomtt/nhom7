package com.example.alarmdemo;

import android.app.Activity;
import android.app.AlarmManager;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

public class AlarmDetailsActivity extends Activity {
	private AlarmModel alarmDetails;
	AlarmDBHelper dbHelper = new AlarmDBHelper(this);
	
	private TimePicker timePicker;
	private EditText editName;
	private CustomSwitch chkWeekly;
	private CustomSwitch chkSunday;
	private CustomSwitch chkMonday;
	private CustomSwitch chkTuesday;
	private CustomSwitch chkWednesday;
	private CustomSwitch chkThursday;
	private CustomSwitch chkFriday;
	private CustomSwitch chkSaturday;
	private TextView txtToneSelection;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_ACTION_BAR);
		
		setContentView(R.layout.activity_details);
		
		getActionBar().setTitle("Create New Alarm");
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		timePicker = (TimePicker) findViewById(R.id.alarm_details_time_picker);
		editName = (EditText) findViewById(R.id.alarm_details_name);
		chkWeekly = (CustomSwitch) findViewById(R.id.alarm_details_repeat_weekly);
		chkSunday = (CustomSwitch) findViewById(R.id.alarm_details_repeat_sunday);
		chkMonday = (CustomSwitch) findViewById(R.id.alarm_details_repeat_monday);
		chkTuesday = (CustomSwitch) findViewById(R.id.alarm_details_repeat_tuesday);
		chkWednesday = (CustomSwitch) findViewById(R.id.alarm_details_repeat_wednesday);
		chkThursday = (CustomSwitch) findViewById(R.id.alarm_details_repeat_thursday);
		chkFriday = (CustomSwitch) findViewById(R.id.alarm_details_repeat_friday);
		chkSaturday = (CustomSwitch) findViewById(R.id.alarm_details_repeat_saturday);
		txtToneSelection = (TextView)findViewById(R.id.alarm_label_tone_selection);
		
		long id = getIntent().getExtras().getLong("id");
		
		if (id == -1) {
			alarmDetails = new AlarmModel();
		} else {
			alarmDetails = dbHelper.getAlarm(id);
			
			timePicker.setCurrentMinute(alarmDetails.timeMinute);
			timePicker.setCurrentHour(alarmDetails.timeHour);
			
			editName.setText(alarmDetails.name);
			chkWeekly.setChecked(alarmDetails.repeatWeekly);
			chkSunday.setChecked(alarmDetails.getRepeatingDay(AlarmModel.SUNDAY));
			chkMonday.setChecked(alarmDetails.getRepeatingDay(AlarmModel.MONDAY));
			chkTuesday.setChecked(alarmDetails.getRepeatingDay(AlarmModel.TUESDAY));
			chkWednesday.setChecked(alarmDetails.getRepeatingDay(AlarmModel.WEDNESDAY));
			chkThursday.setChecked(alarmDetails.getRepeatingDay(AlarmModel.THURSDAY));
			chkFriday.setChecked(alarmDetails.getRepeatingDay(AlarmModel.FRDIAY));
			chkSaturday.setChecked(alarmDetails.getRepeatingDay(AlarmModel.SATURDAY));
			
			txtToneSelection.setText(RingtoneManager.getRingtone(this, alarmDetails.alarmTone).getTitle(this));
		}
		
		final LinearLayout ringToneContainer = (LinearLayout) findViewById(R.id.alarm_ringtone_container);
		ringToneContainer.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
			    startActivityForResult(intent , 1);
			}
		});
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if (resultCode == RESULT_OK) {
			switch (requestCode){
				case 1: {
					alarmDetails.alarmTone = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
					txtToneSelection.setText(RingtoneManager.getRingtone(this, alarmDetails.alarmTone).getTitle(this));
					break;
				}
				default: {
					break;
				}
			}
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.alarm_details, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
			case android.R.id.home: {
				finish();
				break;
			}
			
			case R.id.action_save_alarm_details: {
				updateModelFromLayout();
				Log.i("abc123", "save alarmed");
				
				AlarmManagerHelper.cancelAlarms(this);
				
				if (alarmDetails.id < 0) {
					dbHelper.createAlarm(alarmDetails);
				} else {
					dbHelper.updateAlarm(alarmDetails);
				}
				
				AlarmManagerHelper.setAlarms(this);
				
				setResult(RESULT_OK);
				finish();
			}
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	private void updateModelFromLayout() {
		alarmDetails.timeMinute = timePicker.getCurrentMinute().intValue();
		alarmDetails.timeHour = timePicker.getCurrentHour().intValue();
		
		alarmDetails.name = editName.getText().toString();
		
		alarmDetails.repeatWeekly = chkWeekly.isChecked();
		
		alarmDetails.setRepeatingDay(AlarmModel.SUNDAY, chkSunday.isChecked());
		alarmDetails.setRepeatingDay(AlarmModel.MONDAY, chkMonday.isChecked());
		alarmDetails.setRepeatingDay(AlarmModel.TUESDAY, chkTuesday.isChecked());
		alarmDetails.setRepeatingDay(AlarmModel.WEDNESDAY, chkWednesday.isChecked());
		alarmDetails.setRepeatingDay(AlarmModel.THURSDAY, chkThursday.isChecked());
		alarmDetails.setRepeatingDay(AlarmModel.FRDIAY, chkFriday.isChecked());
		alarmDetails.setRepeatingDay(AlarmModel.SATURDAY, chkSaturday.isChecked());
		
		alarmDetails.isEnabled = true;
	}
}
