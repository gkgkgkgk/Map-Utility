package com.maputility.dao;

import java.util.Date;

public class PeriodDao {

	Date date;
	int startTime;
	int endTime;
	String description;

	public PeriodDao(Date date, int startTime, int endTime, String description) {
		super();
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
