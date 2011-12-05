package com.acmetelecom;

public class CallEvent {
    private String caller;
    private String callee;
    private long time;
    private String type;

    private CallEvent(String caller, String callee, long timeStamp, String type) {
        this.caller = caller;
        this.callee = callee;
        this.time = timeStamp;
        this.type = type;
    }
    
    public static CallEvent endEvent(String caller, String callee, long timeStamp) {
    	return new CallEvent(caller, callee, timeStamp, "end");
    }
    
    public static CallEvent startEvent(String caller, String callee, long timeStamp) {
    	return new CallEvent(caller, callee, timeStamp, "start");
    }

    public String getCaller() {
        return caller;
    }

    public String getCallee() {
        return callee;
    }
    
    public String getEventType() {
    	return type;
    }

    public long time() {
        return time;
    }
}
