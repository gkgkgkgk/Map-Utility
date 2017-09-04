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
			returnTime += firstTime.contains("am") ? (hour * 60) : ((hour+12) * 60);
			
			minutes = firstTime.contains(":") ? Integer.valueOf(time.substring(time.indexOf(":") + 1, time.indexOf("-"))) : 0;
			returnTime += minutes;

		}
				
		return returnTime;
	}

	@Override
	public String getEndTime(String cellString) {

		return null;
	}

}
