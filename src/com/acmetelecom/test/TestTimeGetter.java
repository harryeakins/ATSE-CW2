package com.acmetelecom.test;

import com.acmetelecom.TimeGetter;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class TestTimeGetter implements TimeGetter {

	private List<Date> times;
	
	public TestTimeGetter(List<Date> times) {
		this.times = times;
	}

    public TestTimeGetter() {
        times = new ArrayList<Date>();
	}

    public void add(Date time) {
		this.times.add(time);
	}

	public long getCurrentTime() {
		return times.remove(0).getTime();
	}

}
