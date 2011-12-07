package com.acmetelecom;

public class SystemTimeGetter implements ITimeGetter{

	public long getCurrentTime() {
		return System.currentTimeMillis();
	}

}
