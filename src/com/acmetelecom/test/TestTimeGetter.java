package com.acmetelecom.test;

import com.acmetelecom.TimeGetter;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class TestTimeGetter implements TimeGetter {

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
