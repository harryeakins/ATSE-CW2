package com.acmetelecom.test;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.acmetelecom.Call;
import com.acmetelecom.CallEvent;

public class CallTest {
	
	public Call call;

	@SuppressWarnings("deprecation")
	@Before
	public void setUp() {
		Date start_date = new Date(2011, 11, 25, 19, 0, 0);
		Date end_date = new Date(2011, 11, 25, 19, 20, 0);
		
		CallEvent start = CallEvent.startEvent("447711232343", "447766814143", start_date.getTime());
		CallEvent end = CallEvent.endEvent("447711232343", "447766814143", end_date.getTime());
		
		call = new Call(start, end);
	}
	
	@Test
	public void duration() {
		assertEquals(call.durationSeconds(), 20*60);
	}

	
	@Test
	public void date() {
		//Java Date says December is the 11th month (0-indexing)...
		assertEquals(call.date(), "25/12/11 19:00");
	}
}
