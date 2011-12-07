package com.acmetelecom.test;

import com.acmetelecom.BillingSystem;
import com.acmetelecom.ICustomer;
import com.acmetelecom.ICustomerDatabase;
import com.acmetelecom.ITariffLibrary;
import com.acmetelecom.customer.Tariff;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BillingSystemTest {
	public final Mockery context = new Mockery();
    DateFormat df;
    BillingSystem billingSystem;
    TestTimeGetter timeGetter;
    final ITariffLibrary tariffLibrary = context.mock(ITariffLibrary.class);
    final MockBillGenerator billGenerator = new MockBillGenerator();
    final ICustomerDatabase customerDatabase = context.mock(ICustomerDatabase.class);
    final ICustomer customer = context.mock(ICustomer.class);

    @Before
    public void setUp() throws Exception {
        df = new SimpleDateFormat("yyyy, MM, dd, HH, mm, ss");
        timeGetter = new TestTimeGetter();
        billingSystem = new BillingSystem(timeGetter, 
        								  billGenerator,
        								  tariffLibrary,
                                          customerDatabase);
    }

    @After
    public void tearDown() throws Exception {
    }
    
	@SuppressWarnings("deprecation")
	@Test
	public void peak() throws Exception {
		timeGetter.set(new Date(2011, 11, 29, 14, 0, 0)); // 20 minutes = 1200 seconds
		billingSystem.callInitiated("447711232343", "447766814143");
		
		timeGetter.set(new Date(2011, 11, 29, 14, 20, 0));
		billingSystem.callCompleted("447711232343", "447766814143");
		
		final List<ICustomer> customers = new ArrayList<ICustomer>();
		customers.add(customer);
		
		context.checking(new Expectations() {{
    		allowing(tariffLibrary).tariffFor(with(any(ICustomer.class)));
    		will(returnValue(Tariff.Standard));
    		
    		allowing(customerDatabase).getCustomers();
    		will(returnValue(customers));
    		
    		allowing(customer).getPhoneNumber();
    		will(returnValue("447711232343"));
		}});
		
		billingSystem.createCustomerBills();
		assertEquals(1, billGenerator.numCalls);
		assertEquals(600, billGenerator.totalBill.intValue()); // 1200 sec * 0.5 p/sec = 600p
	}
    
    @SuppressWarnings("deprecation")
	@Test
	public void offPeak() throws Exception {
    	timeGetter.set(new Date(2011, 11, 25, 19, 0, 0));
		billingSystem.callInitiated("447711232343", "447766814143");
		
		timeGetter.set(new Date(2011, 11, 25, 19, 20, 0));
		billingSystem.callCompleted("447711232343", "447766814143");
		
		final List<ICustomer> customers = new ArrayList<ICustomer>();
		customers.add(customer);
		
		context.checking(new Expectations() {{
    		allowing(tariffLibrary).tariffFor(with(any(ICustomer.class)));
    		will(returnValue(Tariff.Standard));
    		
    		allowing(customerDatabase).getCustomers();
    		will(returnValue(customers));
    		
    		allowing(customer).getPhoneNumber();
    		will(returnValue("447711232343"));
		}});
		
		billingSystem.createCustomerBills();
		assertEquals(1, billGenerator.numCalls);
		assertEquals(240, billGenerator.totalBill.intValue()); // 1200 sec * 0.5 p/sec = 600p
	}
    
    @SuppressWarnings("deprecation")
	@Test
	public void onToOffPeak() throws Exception {
    	timeGetter.set(new Date(2011, 11, 27, 18, 50, 0));
		billingSystem.callInitiated("447711232343", "447766814143");
		
		timeGetter.set(new Date(2011, 11, 27, 19, 20, 0));
		billingSystem.callCompleted("447711232343", "447766814143");
		
		final List<ICustomer> customers = new ArrayList<ICustomer>();
		customers.add(customer);
		
		context.checking(new Expectations() {{
    		allowing(tariffLibrary).tariffFor(with(any(ICustomer.class)));
    		will(returnValue(Tariff.Standard));
    		
    		allowing(customerDatabase).getCustomers();
    		will(returnValue(customers));
    		
    		allowing(customer).getPhoneNumber();
    		will(returnValue("447711232343"));
		}});
		
		billingSystem.createCustomerBills();
		assertEquals(1, billGenerator.numCalls);
		assertEquals(540, billGenerator.totalBill.intValue()); // 1200 sec * 0.5 p/sec = 600p
	}
    
    @SuppressWarnings("deprecation")
	@Test
	public void offToOnPeak() throws Exception {
    	timeGetter.set(new Date(2011, 11, 27, 6, 50, 0));
		billingSystem.callInitiated("447711232343", "447766814143");

		timeGetter.set(new Date(2011, 11, 27, 7, 20, 0));
		billingSystem.callCompleted("447711232343", "447766814143");
		
		final List<ICustomer> customers = new ArrayList<ICustomer>();
		customers.add(customer);
		
		context.checking(new Expectations() {{
    		allowing(tariffLibrary).tariffFor(with(any(ICustomer.class)));
    		will(returnValue(Tariff.Standard));
    		
    		allowing(customerDatabase).getCustomers();
    		will(returnValue(customers));
    		
    		allowing(customer).getPhoneNumber();
    		will(returnValue("447711232343"));
		}});
		
		billingSystem.createCustomerBills();
		assertEquals(1, billGenerator.numCalls);
		assertEquals(720, billGenerator.totalBill.intValue()); // 1200 sec * 0.5 p/sec = 600p
	}
    
    @SuppressWarnings("deprecation")
	@Test
	public void offToOnToOffPeak() throws Exception {
    	timeGetter.set(new Date(2011, 11, 27, 6, 50, 0));
		billingSystem.callInitiated("447711232343", "447766814143");

		timeGetter.set(new Date(2011, 11, 27, 19, 10, 0));
		billingSystem.callCompleted("447711232343", "447766814143");
		
		final List<ICustomer> customers = new ArrayList<ICustomer>();
		customers.add(customer);
		
		context.checking(new Expectations() {{
    		allowing(tariffLibrary).tariffFor(with(any(ICustomer.class)));
    		will(returnValue(Tariff.Standard));
    		
    		allowing(customerDatabase).getCustomers();
    		will(returnValue(customers));
    		
    		allowing(customer).getPhoneNumber();
    		will(returnValue("447711232343"));
		}});
		
		billingSystem.createCustomerBills();
		assertEquals(1, billGenerator.numCalls);
		assertEquals(21840, billGenerator.totalBill.intValue()); // 1200 sec * 0.5 p/sec = 600p
	}
    
    @SuppressWarnings("deprecation")
	@Test
	public void onToOffToOnPeak() throws Exception {
    	timeGetter.set(new Date(2011, 11, 27, 18, 50, 0));
		billingSystem.callInitiated("447711232343", "447766814143");

		timeGetter.set(new Date(2011, 11, 28, 7, 10, 0));
		billingSystem.callCompleted("447711232343", "447766814143");
		
		final List<ICustomer> customers = new ArrayList<ICustomer>();
		customers.add(customer);
		
		context.checking(new Expectations() {{
    		allowing(tariffLibrary).tariffFor(with(any(ICustomer.class)));
    		will(returnValue(Tariff.Standard));
    		
    		allowing(customerDatabase).getCustomers();
    		will(returnValue(customers));
    		
    		allowing(customer).getPhoneNumber();
    		will(returnValue("447711232343"));
		}});
		
		billingSystem.createCustomerBills();
		assertEquals(1, billGenerator.numCalls);
		assertEquals(9240, billGenerator.totalBill.intValue()); // 1200 sec * 0.5 p/sec = 600p
	}
    
    @SuppressWarnings("deprecation")
	@Test
	public void bigOffToOnPeak() throws Exception {
    	timeGetter.set(new Date(2011, 11, 27, 21, 0, 0));
		billingSystem.callInitiated("447711232343", "447766814143");

		timeGetter.set(new Date(2011, 11, 28, 16, 0, 0));
		billingSystem.callCompleted("447711232343", "447766814143");
		
		final List<ICustomer> customers = new ArrayList<ICustomer>();
		customers.add(customer);
		
		context.checking(new Expectations() {{
    		allowing(tariffLibrary).tariffFor(with(any(ICustomer.class)));
    		will(returnValue(Tariff.Standard));
    		
    		allowing(customerDatabase).getCustomers();
    		will(returnValue(customers));
    		
    		allowing(customer).getPhoneNumber();
    		will(returnValue("447711232343"));
		}});
		
		billingSystem.createCustomerBills();
		assertEquals(1, billGenerator.numCalls);
		assertEquals(23400, billGenerator.totalBill.intValue()); // 1200 sec * 0.5 p/sec = 600p
	}
    
    @SuppressWarnings("deprecation")
	@Test
	public void bigOnToOffPeak() throws Exception {
    	timeGetter.set(new Date(2011, 11, 27, 9, 0, 0));
		billingSystem.callInitiated("447711232343", "447766814143");

		timeGetter.set(new Date(2011, 11, 28, 4, 0, 0));
		billingSystem.callCompleted("447711232343", "447766814143");
		
		final List<ICustomer> customers = new ArrayList<ICustomer>();
		customers.add(customer);
		
		context.checking(new Expectations() {{
    		allowing(tariffLibrary).tariffFor(with(any(ICustomer.class)));
    		will(returnValue(Tariff.Standard));
    		
    		allowing(customerDatabase).getCustomers();
    		will(returnValue(customers));
    		
    		allowing(customer).getPhoneNumber();
    		will(returnValue("447711232343"));
		}});
		
		billingSystem.createCustomerBills();
		assertEquals(1, billGenerator.numCalls);
		assertEquals(24480, billGenerator.totalBill.intValue()); // 1200 sec * 0.5 p/sec = 600p
	}
}

