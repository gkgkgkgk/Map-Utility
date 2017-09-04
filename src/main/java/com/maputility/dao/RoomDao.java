package com.maputility.dao;

import java.util.ArrayList;
import java.util.List;

public class RoomDao {

	List<PeriodDao> periods;
	String className;
	
	public RoomDao(List<PeriodDao> periods, String className) {
		super();
		this.periods = periods;
		this.className = className;
	}

	public RoomDao(PeriodDao period, String className) {
		super();
		periods = new ArrayList<PeriodDao>();
		periods.add(period);
		this.className = className;
	}
	
	public List<PeriodDao> getPeriods() {
		return periods;
	}

	public void setPeriods(List<PeriodDao> periods) {
		this.periods = periods;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	public void addPeriod(PeriodDao period) {
		periods.add(period);
	}
	
}
