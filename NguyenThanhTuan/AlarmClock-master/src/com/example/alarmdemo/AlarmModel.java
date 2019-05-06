package com.example.alarmdemo;

import android.net.Uri;

public class AlarmModel {
	public static final int SUNDAY = 0;
    public static final int MONDAY = 1;
    public static final int TUESDAY = 2;
    public static final int WEDNESDAY = 3;
    public static final int THURSDAY = 4;
    public static final int FRDIAY = 5;
    public static final int SATURDAY = 6;
    
    long id = -1;
    int timeHour;
    int timeMinute;
    boolean repeatingDays[];
    boolean repeatWeekly;
    Uri alarmTone;
    String name;
    boolean isEnabled;
    
    public AlarmModel() {
    	repeatingDays = new boolean[7];
    }
    
    public AlarmModel(long id, int timeHour, int timeMinute,
			boolean[] repeatingDays, boolean repeatWeekly, Uri alarmTone,
			String name, boolean isEnabled) {
		this.id = id;
		this.timeHour = timeHour;
		this.timeMinute = timeMinute;
		this.repeatingDays = repeatingDays;
		this.repeatWeekly = repeatWeekly;
		this.alarmTone = alarmTone;
		this.name = name;
		this.isEnabled = isEnabled;
	}



	public void setRepeatingDay(int dayOfWeek, boolean value) {
    	repeatingDays[dayOfWeek] = value;
    }
    
    public boolean getRepeatingDay(int dayOfWeek) {
    	return repeatingDays[dayOfWeek];
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getTimeHour() {
		return timeHour;
	}

	public void setTimeHour(int timeHour) {
		this.timeHour = timeHour;
	}

	public int getTimeMinute() {
		return timeMinute;
	}

	public void setTimeMinute(int timeMinute) {
		this.timeMinute = timeMinute;
	}

	public boolean[] getRepeatingDays() {
		return repeatingDays;
	}

	public void setRepeatingDays(boolean[] repeatingDays) {
		this.repeatingDays = repeatingDays;
	}

	public boolean isRepeatWeekly() {
		return repeatWeekly;
	}

	public void setRepeatWeekly(boolean repeatWeekly) {
		this.repeatWeekly = repeatWeekly;
	}

	public Uri getAlarmTone() {
		return alarmTone;
	}

	public void setAlarmTone(Uri alarmTone) {
		this.alarmTone = alarmTone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
    
    
     
}
