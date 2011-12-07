package com.acmetelecom.test;

import com.acmetelecom.IBillGenerator;
import com.acmetelecom.BillingSystem;
import com.acmetelecom.TimeGetter;
import com.acmetelecom.customer.CentralCustomerDatabase;
import com.acmetelecom.customer.Tariff;
import com.acmetelecom.customer.TariffLibrary;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: elatier
 * Date: 05/12/11
 * Time: 13:31
 * To change this template use File | Settings | File Templates.
 */
public class BillingSystemTest {

    DateFormat df;
    BillingSystem billingSystem;
    TimeGetter timeGetter;
    TariffLibrary tariffLibrary = new FakeTariffLibrary();
    BillGenerator billGenerator = new FakeBillGenerator();

    @Before
    public void setUp() throws Exception {
        df = new SimpleDateFormat("yyyy, MM, dd, HH, mm, ss");
        timeGetter = new TestTimeGetter();
        billingSystem = new BillingSystem(  timeGetter, 
        									billGenerator,
        									tariffLibrary,
                                            CentralCustomerDatabase.getInstance()
        									);
    }

    @After
    public void tearDown() throws Exception {
    }

	@Test
	public void peak() throws Exception {
		tariffLibrary.setTarrif(Tariff.Standard); // 0.2 pence per second offPeak
		billGenerator.setInterestingPhoneNumber("447711232343");
		
		timeGetter.set(df.parse("2011, 11, 29, 14, 0, 0")); // 20 minutes = 1200 seconds
		billingSystem.callInitiated("447711232343", "447766814143");
		
		timeGetter.set(df.parse("2011, 11, 29, 14, 20, 0"));
		billingSystem.callCompleted("447711232343", "447766814143");
		
		billingSystem.createCustomerBills();
		
		assertEquals("6.00", billGenerator.totalBill); // 1200 sec * 0.5 p/sec = £6.00
	}
	
	@Test
	public void offPeak() throws Exception {
		tariffLibrary.setTarrif(Tariff.Standard); // 0.5 pence per second (peak)
		billGenerator.setInterestingPhoneNumber("447711232343");
		
		timeGetter.set(new Date(2011, 11, 25, 19, 0, 0));
		billingSystem.callInitiated("447711232343", "447766814143");
		
		timeGetter.set(new Date(2011, 11, 25, 19, 20, 0));
		billingSystem.callCompleted("447711232343", "447766814143");
		billingSystem.createCustomerBills();
		assertEquals("2.40", billGenerator.totalBill); // 1200 sec * 0.2 p/sec = £2.40
	}
	@Test
	public void OntoOffPeak() throws Exception {
		tariffLibrary.setTarrif(Tariff.Standard); 
		billGenerator.setInterestingPhoneNumber("447711232343");
		
		timeGetter.set(new Date(2011, 11, 27, 18, 50, 0));
		billingSystem.callInitiated("447711232343", "447766814143");
		
		timeGetter.set(new Date(2011, 11, 27, 19, 20, 0));
		billingSystem.callCompleted("447711232343", "447766814143");
		billingSystem.createCustomerBills();
		
		// 10 minutes peak = 600*0.5 = £3.00
		// 20 minutes off-peak = 1200*0.2 = £2.40
		assertEquals("5.40", billGenerator.totalBill); 
	}
	@Test
	public void OfftoOnPeak() throws Exception {
		tariffLibrary.setTarrif(Tariff.Standard); // 0.2 pence per second
		billGenerator.setInterestingPhoneNumber("447711232343");
		
		timeGetter.set(new Date(2011, 11, 27, 6, 50, 0));
		billingSystem.callInitiated("447711232343", "447766814143");

		timeGetter.set(new Date(2011, 11, 27, 7, 20, 0));
		billingSystem.callCompleted("447711232343", "447766814143");
		billingSystem.createCustomerBills();
		
		// 10 minutes off-peak = 600*0.2 = £1.20
		// 20 minutes peak = 1200*0.5 = £6.00
		assertEquals("7.20", billGenerator.totalBill); 
	}
	@Test
	public void OfftoOntoOffPeak() throws Exception {
		tariffLibrary.setTarrif(Tariff.Standard); // 0.2 pence per second
		billGenerator.setInterestingPhoneNumber("447711232343");
		
		timeGetter.set(new Date(2011, 11, 27, 6, 50, 0));
		billingSystem.callInitiated("447711232343", "447766814143");

		timeGetter.set(new Date(2011, 11, 27, 19, 10, 0));
		billingSystem.callCompleted("447711232343", "447766814143");
		billingSystem.createCustomerBills();
	}
	@Test
	public void OntoOfftoOnPeak() throws Exception {
		tariffLibrary.setTarrif(Tariff.Standard); // 0.2 pence per second
		billGenerator.setInterestingPhoneNumber("447711232343");
		
		timeGetter.set(new Date(2011, 11, 27, 18, 50, 0));
		billingSystem.callInitiated("447711232343", "447766814143");

		timeGetter.set(new Date(2011, 11, 28, 7, 10, 0));
		billingSystem.callCompleted("447711232343", "447766814143");
		billingSystem.createCustomerBills();
	}
	@Test
	public void BigOfftoOnPeak() throws Exception {
		tariffLibrary.setTarrif(Tariff.Standard); // 0.2 pence per second
		billGenerator.setInterestingPhoneNumber("447711232343");
		
		timeGetter.set(new Date(2011, 11, 27, 21, 0, 0));
		billingSystem.callInitiated("447711232343", "447766814143");

		timeGetter.set(new Date(2011, 11, 28, 16, 0, 0));
		billingSystem.callCompleted("447711232343", "447766814143");
		billingSystem.createCustomerBills();
	}

	@Test
	public void BigOntoOffPeak() throws Exception {
		tariffLibrary.setTarrif(Tariff.Standard); // 0.2 pence per second
		billGenerator.setInterestingPhoneNumber("447711232343");
		
		timeGetter.set(new Date(2011, 11, 27, 9, 0, 0));
		billingSystem.callInitiated("447711232343", "447766814143");

		timeGetter.set(new Date(2011, 11, 28, 4, 0, 0));
		billingSystem.callCompleted("447711232343", "447766814143");
		billingSystem.createCustomerBills();
	}
}

