package com.acmetelecom.test;

import com.acmetelecom.ITimeGetter;

import java.util.Date;

public class TestTimeGetter implements ITimeGetter {

	private Date time;
	
	public TestTimeGetter(Date time) {
		this.time = time;
	}

    public TestTimeGetter() {
        this.time = new Date();
	}

    public void set(Date time) {
		this.time = time;
	}

	public long getCurrentTime() {
		return time.getTime();
	}

}
