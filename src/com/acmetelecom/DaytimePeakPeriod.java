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
        return (int) ((7*60*60 - time.getHours()*60*60 - time.getMinutes()*60 - time.getSeconds()));   	
    }
    
    public int peakDiff(Date time){
        return (int) ((19*60*60 - time.getHours()*60*60 - time.getMinutes()*60 - time.getSeconds()));   	   	
    }
}
