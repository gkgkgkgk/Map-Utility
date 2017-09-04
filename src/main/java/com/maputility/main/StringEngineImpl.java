package com.maputility.main;

public class StringEngineImpl implements StringEngine{

	@Override
	public int getStartTime(String cellString) {
		String time = cellString.substring(0, cellString.indexOf(" "));
		String firstTime = cellString.substring(0, cellString.indexOf('-'));
		int hour;		
		int minutes;
		
		int returnTime = 0;
		
		if(time.contains("am") && time.contains("pm")) {
			hour = firstTime.contains(":") ? Integer.valueOf(firstTime.substring(0, firstTime.indexOf(':'))) : Integer.valueOf(firstTime);
			returnTime += (hour * 60);
			
			minutes = firstTime.contains(":") ? Integer.valueOf(time.substring(time.indexOf(":") + 1, time.indexOf("am"))) : 0;
			returnTime += minutes;
		}
	
		if(time.contains("pm") && time.contains("12:") && !time.contains("am")) {
			hour = firstTime.contains(":") ? Integer.valueOf(firstTime.substring(0, firstTime.indexOf(':'))) : Integer.valueOf(firstTime);
			returnTime += (hour * 60);
			
			minutes = firstTime.contains(":") ? Integer.valueOf(time.substring(time.indexOf(":") + 1, time.indexOf("-"))) : 0;
			returnTime += minutes;
		}
		
		else if(!(time.contains("am") && time.contains("pm"))){
			hour = firstTime.contains(":") ? Integer.valueOf(firstTime.substring(0, firstTime.indexOf(':'))) : Integer.valueOf(firstTime);
			returnTime += time.contains("am") ? (hour * 60) : ((hour+12) * 60);
			
			minutes = firstTime.contains(":") ? Integer.valueOf(time.substring(time.indexOf(":") + 1, time.indexOf("-"))) : 0;
			returnTime += minutes;

		}
		return returnTime;
	}

	@Override
	public int getEndTime(String cellString) {
		String time = cellString.substring(0, cellString.indexOf(" "));
		String secondTime = time.substring(cellString.indexOf('-') + 1, time.length());

		int hour;		
		int minutes;
		
		int returnTime = 0;
		
		if(time.contains("am") && time.contains("pm")) {
			hour = secondTime.contains(":") ? Integer.valueOf(secondTime.substring(0, secondTime.indexOf(':'))) : Integer.valueOf(secondTime);
			returnTime += (hour * 60);
			
			minutes = secondTime.contains(":") ? Integer.valueOf(time.substring(secondTime.indexOf(":") + 1, secondTime.indexOf("pm"))) : 0;
			returnTime += minutes;
		}
	
		if(time.contains("pm") && time.contains("12:") && !time.contains("am")) {
			hour = secondTime.contains(":") ? Integer.valueOf(secondTime.substring(0, secondTime.indexOf(':'))) : Integer.valueOf(secondTime);
			returnTime += (hour * 60);
			
			minutes = secondTime.contains(":") ? Integer.valueOf(secondTime.substring(secondTime.indexOf(":") + 1, secondTime.indexOf("pm"))) : 0;
			returnTime += minutes;
		}
		
		else if(!(time.contains("am") && time.contains("pm"))){
			hour = secondTime.contains(":") ? Integer.valueOf(secondTime.substring(0, secondTime.indexOf(':'))) : Integer.valueOf(secondTime);
			returnTime += time.contains("am") ? (hour * 60) : ((hour+12) * 60);
			
			minutes = secondTime.contains(":") ? Integer.valueOf(secondTime.substring(secondTime.indexOf(":") + 1, secondTime.indexOf("m")-1)) : 0;
			returnTime += minutes;

		}
		return returnTime;
	}

	@Override
	public String getClassName(String cellString) {
		String className = cellString.substring(cellString.indexOf(" "), cellString.length());
		className = className.substring(className.indexOf(" ") + 3, className.length());
		className = className.substring(0, className.indexOf(" "));
		return className;
	}

}
