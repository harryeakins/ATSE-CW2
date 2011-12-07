package com.acmetelecom;

import java.util.Calendar;
import java.util.Date;

class DaytimePeakPeriod {	
	private final int startPeakTime;
	private final int endPeakTime;
	
	/**
	 * @param start
	 * @param end
	 */
	public DaytimePeakPeriod(int start, int end){
		startPeakTime = start;
		endPeakTime = end;
	}
	
    public boolean offPeak(Date time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        return hour < startPeakTime || hour >= endPeakTime;
    }
    
    @SuppressWarnings("deprecation")
	public int nextPeakBoundary(Date time){
    	
    	Date nextPeak = new Date(time.getTime());
    	if (nextPeak.getHours() < startPeakTime) {
    		nextPeak.setHours(startPeakTime);
    		nextPeak.setMinutes(0);
    		nextPeak.setSeconds(0);
    	} else {
    		nextPeak.setHours(7);
    		nextPeak.setMinutes(0);
    		nextPeak.setSeconds(0);
    		nextPeak = new Date(nextPeak.getTime() + 24 * 60 * 60 * 1000);
    	}
    	
        return (int)(nextPeak.getTime() / 1000);   	
    }
    
    @SuppressWarnings("deprecation")
	public int nextOffPeakBoundary(Date time){
        Date nextPeak = new Date(time.getTime());
  		nextPeak.setHours(endPeakTime);
   		nextPeak.setMinutes(0);
   		nextPeak.setSeconds(0);    	
        return (int)(nextPeak.getTime() / 1000);   	
    }
}
