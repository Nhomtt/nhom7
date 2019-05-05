package com.example.alarmdemo;

import java.util.Calendar;

public class DateAndTime {

	public static String getAMPM() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.AM_PM) == 0 ? "AM " : "PM ";
	}

	public static String getTime() {
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR);
		int minute = calendar.get(Calendar.MINUTE);
		return (hour < 10 ? "0" + hour : hour) + ":"
				+ (minute < 10 ? "0" + minute : minute);
	}

	public static String getSecond() {
		Calendar calendar = Calendar.getInstance();
		int second = calendar.get(Calendar.SECOND);
		return second < 10 ? "0" + second : second + "";
	}

	public static String getDate() {
		Calendar calendar = Calendar.getInstance();
		int dayOfWeekInt = calendar.get(Calendar.DAY_OF_WEEK);
		int monthInt = calendar.get(Calendar.MONDAY);
		int dayOfMonthInt = calendar.get(Calendar.DATE);
		int yearInt = calendar.get(Calendar.YEAR);
		String dayOfWeek, month;
		switch (dayOfWeekInt) {
		case 1:
			dayOfWeek = "Sunday";
			break;
		case 2:
			dayOfWeek = "Monday";
			break;
		case 3:
			dayOfWeek = "Tuesday";
			break;
		case 4:
			dayOfWeek = "Wednesday";
			break;
		case 5:
			dayOfWeek = "Thurday";
			break;
		case 6:
			dayOfWeek = "Friday";
			break;
		default:
			dayOfWeek = "Saturday";
			break;
		}
		switch (monthInt) {
		case 1:
			month = "January";
			break;
		case 2:
			month = "February";
			break;
		case 3:
			month = "March";
			break;
		case 4:
			month = "April";
			break;
		case 5:
			month = "May";
			break;
		case 6:
			month = "June";
			break;
		case 7:
			month = "July";
			break;
		case 8:
			month = "August";
			break;
		case 9:
			month = "September";
			break;
		case 10:
			month = "October";
			break;
		case 11:
			month = "November";
			break;
		default:
			month = "December";
			break;
		}
		return dayOfWeek + ", " + month + " " + dayOfMonthInt + ", " + yearInt;
	}

}