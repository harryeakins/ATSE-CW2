package com.acmetelecom;

import com.acmetelecom.customer.CentralCustomerDatabase;
import com.acmetelecom.customer.CentralTariffDatabase;

public class Runner {
	public static void main(String[] args) throws Exception {
		System.out.println("Running...");
		BillingSystem billingSystem = new BillingSystem(new SystemTimeGetter(), 
														new CustomerBillGenerator(new FilePrinter()),
														CentralTariffDatabase.getInstance(),
                CentralCustomerDatabase.getInstance()
														);
		
		billingSystem.callInitiated("447722113434", "447766814143");
		sleepSeconds(20);
		billingSystem.callCompleted("447722113434", "447766814143");
		billingSystem.callInitiated("447722113434", "447711111111");
		sleepSeconds(30);
		billingSystem.callCompleted("447722113434", "447711111111");
		billingSystem.callInitiated("447777765432", "447711111111");
		sleepSeconds(60);
		billingSystem.callCompleted("447777765432", "447711111111");
		billingSystem.createCustomerBills();
	}

	private static void sleepSeconds(int n) throws InterruptedException {
		Thread.sleep(n * 1000);
	}
}
