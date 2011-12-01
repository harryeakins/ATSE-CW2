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
		billingSystem.callInitiated("447711232343", "447766814143");
		billingSystem.callCompleted("447711232343", "447766814143");
		billingSystem.createCustomerBills();
	}
	@Test
	public void Peak() throws Exception {
		ArrayList<Date> times = new ArrayList<Date>();
		times.add(new Date(2011, 11, 25, 19, 0, 0));
		times.add(new Date(2011, 11, 25, 19, 20, 0));
		System.out.println("Running...");
		BillingSystem billingSystem = new BillingSystem(new TestTimeGetter(times));
		billingSystem.callInitiated("447711232343", "447766814143");
		billingSystem.callCompleted("447711232343", "447766814143");
		billingSystem.createCustomerBills();
	}
	@Test
	public void OntoOffPeak() throws Exception {
		ArrayList<Date> times = new ArrayList<Date>();
		times.add(new Date(2011, 11, 27, 18, 50, 0));
		times.add(new Date(2011, 11, 27, 19, 20, 0));
		System.out.println("Running...");
		BillingSystem billingSystem = new BillingSystem(new TestTimeGetter(times));
		billingSystem.callInitiated("447711232343", "447766814143");
		billingSystem.callCompleted("447711232343", "447766814143");
		billingSystem.createCustomerBills();
	}
	@Test
	public void OfftoOnPeak() throws Exception {
		ArrayList<Date> times = new ArrayList<Date>();
		times.add(new Date(2011, 11, 27, 6, 50, 0));
		times.add(new Date(2011, 11, 27, 7, 20, 0));
		System.out.println("Running...");
		BillingSystem billingSystem = new BillingSystem(new TestTimeGetter(times));
		billingSystem.callInitiated("447711232343", "447766814143");
		billingSystem.callCompleted("447711232343", "447766814143");
		billingSystem.createCustomerBills();
	}
	@Test
	public void OfftoOntoOffPeak() throws Exception {
		ArrayList<Date> times = new ArrayList<Date>();
		times.add(new Date(2011, 11, 27, 6, 50, 0));
		times.add(new Date(2011, 11, 27, 19, 10, 0));
		System.out.println("Running...");
		BillingSystem billingSystem = new BillingSystem(new TestTimeGetter(times));
		billingSystem.callInitiated("447711232343", "447766814143");
		billingSystem.callCompleted("447711232343", "447766814143");
		billingSystem.createCustomerBills();
	}
}
