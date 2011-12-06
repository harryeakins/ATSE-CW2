package com.acmetelecom;

public class CallEvent {
    private String caller;
    private String callee;
    private long time;
    private EventType type;
    public enum EventType { START, END }

    private CallEvent(String caller, String callee, long timeStamp, EventType type) {
        this.caller = caller;
        this.callee = callee;
        this.time = timeStamp;
        this.type = type;
    }
    
    public static CallEvent endEvent(String caller, String callee, long timeStamp) {
    	return new CallEvent(caller, callee, timeStamp, EventType.END);
    }
    
    public static CallEvent startEvent(String caller, String callee, long timeStamp) {
    	return new CallEvent(caller, callee, timeStamp, EventType.START);
    }

    public String getCaller() {
        return caller;
    }

    public String getCallee() {
        return callee;
    }
    
    public EventType getEventType() {
    	return type;
    }

    public long time() {
        return time;
    }
}
