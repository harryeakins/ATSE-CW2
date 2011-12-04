package com.acmetelecom;

public class SystemTimeGetter implements TimeGetter{

	public long getCurrentTime() {
		return System.currentTimeMillis();
	}

}
