package com.acmetelecom.test;

import com.acmetelecom.BillGenerator;
import com.acmetelecom.BillingSystem;
import com.acmetelecom.FilePrinter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    TestTimeGetter timeGetter;

    @Before
    public void setUp() throws Exception {
        df = new SimpleDateFormat("yyyy, MM, dd, HH, mm, ss");
        timeGetter = new TestTimeGetter();
        billingSystem = new BillingSystem(timeGetter, new BillGenerator(new FilePrinter()));

    }

    @After
    public void tearDown() throws Exception {
    }

	@Test
	public void offPeak() throws Exception {
		timeGetter.add(df.parse("2011, 11, 29, 14, 0, 0"));
		timeGetter.add(df.parse("2011, 11, 29, 14, 20, 0"));
		billingSystem.callInitiated("447711232343", "447766814143");
		billingSystem.callCompleted("447711232343", "447766814143");
		billingSystem.createCustomerBills();
	}
	@Test
	public void Peak() throws Exception {
		timeGetter.add(new Date(2011, 11, 25, 19, 0, 0));
		timeGetter.add(new Date(2011, 11, 25, 19, 20, 0));
		billingSystem.callInitiated("447711232343", "447766814143");
		billingSystem.callCompleted("447711232343", "447766814143");
		billingSystem.createCustomerBills();
	}
	@Test
	public void OntoOffPeak() throws Exception {
		timeGetter.add(new Date(2011, 11, 27, 18, 50, 0));
		timeGetter.add(new Date(2011, 11, 27, 19, 20, 0));
		billingSystem.callInitiated("447711232343", "447766814143");
		billingSystem.callCompleted("447711232343", "447766814143");
		billingSystem.createCustomerBills();
	}
	@Test
	public void OfftoOnPeak() throws Exception {
		timeGetter.add(new Date(2011, 11, 27, 6, 50, 0));
		timeGetter.add(new Date(2011, 11, 27, 7, 20, 0));
		billingSystem.callInitiated("447711232343", "447766814143");
		billingSystem.callCompleted("447711232343", "447766814143");
		billingSystem.createCustomerBills();
	}
	@Test
	public void OfftoOntoOffPeak() throws Exception {
		timeGetter.add(new Date(2011, 11, 27, 6, 50, 0));
		timeGetter.add(new Date(2011, 11, 27, 19, 10, 0));
		billingSystem.callInitiated("447711232343", "447766814143");
		billingSystem.callCompleted("447711232343", "447766814143");
		billingSystem.createCustomerBills();
	}
	@Test
	public void OntoOfftoOnPeak() throws Exception {
		timeGetter.add(new Date(2011, 11, 27, 18, 50, 0));
		timeGetter.add(new Date(2011, 11, 28, 7, 10, 0));
		billingSystem.callInitiated("447711232343", "447766814143");
		billingSystem.callCompleted("447711232343", "447766814143");
		billingSystem.createCustomerBills();
	}
	@Test
	public void BigOfftoOnPeak() throws Exception {
		timeGetter.add(new Date(2011, 11, 27, 21, 0, 0));
		timeGetter.add(new Date(2011, 11, 28, 16, 0, 0));
		billingSystem.callInitiated("447711232343", "447766814143");
		billingSystem.callCompleted("447711232343", "447766814143");
		billingSystem.createCustomerBills();
	}

	@Test
	public void BigOntoOffPeak() throws Exception {
		timeGetter.add(new Date(2011, 11, 27, 9, 0, 0));
		timeGetter.add(new Date(2011, 11, 28, 4, 0, 0));
		billingSystem.callInitiated("447711232343", "447766814143");
		billingSystem.callCompleted("447711232343", "447766814143");
		billingSystem.createCustomerBills();
	}
}

