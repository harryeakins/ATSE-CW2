package com.acmetelecom.test;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Test;

import com.acmetelecom.BillingSystem;
import com.acmetelecom.SystemTimeGetter;

public class BillingSystemTest {
	@Test
	public void offPeak() throws Exception {
		ArrayList<Date> times = new ArrayList<Date>();
		times.add(new Date(2011, 11, 29, 14, 0, 0));
		times.add(new Date(2011, 11, 29, 14, 20, 0));
		System.out.println("Running...");
		BillingSystem billingSystem = new BillingSystem(new TestTimeGetter(times));
		billingSystem.callInitiated("447722113434", "447766814143");
		billingSystem.callCompleted("447722113434", "447766814143");
		billingSystem.createCustomerBills();
	}
}
