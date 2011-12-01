package com.acmetelecom;

import java.util.Calendar;
import java.util.Date;

class DaytimePeakPeriod {

    public boolean offPeak(Date time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        return hour < 7 || hour >= 19;
    }
    
    public int offPeakDiff(Date time){
    	
    	Date nextPeak = new Date(time.getTime());
    	if (nextPeak.getHours() < 7) {
    		nextPeak.setHours(7);
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
    
    public int peakDiff(Date time){
        Date nextPeak = new Date(time.getTime());
  		nextPeak.setHours(19);
   		nextPeak.setMinutes(0);
   		nextPeak.setSeconds(0);    	
        return (int)(nextPeak.getTime() / 1000);   	
    }
}
