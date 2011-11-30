package com.acmetelecom;

public class SystemTimeGetter implements TimeGetter{

	@Override
	public long getCurrentTime() {
		return System.currentTimeMillis();
	}

}
