package com.maputility.dao;

public class RoomDaoMin {

	String className;
	int[] times;

	public RoomDaoMin(String className, int[] times) {
		super();
		this.className = className;
		this.times = times;
	}
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public int[] getTimes() {
		return times;
	}
	public void setTimes(int[] times) {
		this.times = times;
	}
	
	
	
	
}
