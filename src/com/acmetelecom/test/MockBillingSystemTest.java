package com.acmetelecom.test;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.acmetelecom.BillGenerator;
import com.acmetelecom.BillingSystem;
import com.acmetelecom.CustomerDatabaseInterface;
import com.acmetelecom.CustomerInterface;
import com.acmetelecom.TariffLibraryInterface;
import com.acmetelecom.customer.*;

@RunWith(JMock.class)
public class MockBillingSystemTest {

	public final Mockery context = new Mockery();
    DateFormat df;
    BillingSystem billingSystem;
    TestTimeGetter timeGetter;
    final TariffLibraryInterface tariffLibrary = context.mock(TariffLibraryInterface.class);
    final BillGenerator billGenerator = context.mock(BillGenerator.class);
    final CustomerDatabaseInterface customerDatabase = context.mock(CustomerDatabaseInterface.class);
    final CustomerInterface customer = context.mock(CustomerInterface.class);

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
    
    @SuppressWarnings("unchecked")
	@Test
	public void peak() throws Exception {
		timeGetter.set(df.parse("2011, 11, 29, 14, 0, 0")); // 20 minutes = 1200 seconds
		billingSystem.callInitiated("447711232343", "447766814143");
		
		timeGetter.set(df.parse("2011, 11, 29, 14, 20, 0"));
		billingSystem.callCompleted("447711232343", "447766814143");
		
		final List<CustomerInterface> customers = new ArrayList<CustomerInterface>();
		customers.add(customer);
		
		context.checking(new Expectations() {{
    		oneOf(billGenerator).send(with(same(customer)), with(any(List.class)), with(any(BigDecimal.class)));
    		
    		allowing(tariffLibrary).tariffFor(with(any(CustomerInterface.class)));
    		will(returnValue(Tariff.Standard));
    		
    		allowing(customerDatabase).getCustomers();
    		will(returnValue(customers));
    		
    		allowing(customer).getPhoneNumber();
    		will(returnValue("447711232343"));
		}});
		
		billingSystem.createCustomerBills();
	}
}

