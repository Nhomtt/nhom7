package com.phongbm.smartclock;

public class InforTimeLap {
	private String index, time, percentTime;

	public InforTimeLap(String index, String time, String percentTime) {
		super();
		this.index = index;
		this.time = time;
		this.percentTime = percentTime;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPercentTime() {
		return percentTime;
	}

	public void setPercentTime(String percentTime) {
		this.percentTime = percentTime;
	}

}