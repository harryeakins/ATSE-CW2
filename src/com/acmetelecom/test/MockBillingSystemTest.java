package com.acmetelecom.test;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.acmetelecom.BillGenerator;
import com.acmetelecom.BillingSystem;
import com.acmetelecom.TimeGetter;
import com.acmetelecom.customer.*;

@RunWith(JMock.class)
public class MockBillingSystemTest {

	public final Mockery context = new Mockery();
	
    BillingSystem billingSystem;
    final TimeGetter timeGetter = context.mock(TimeGetter.class);
    final TariffLibrary tariffLibrary = context.mock(TariffLibrary.class);
    final BillGenerator billGenerator = context.mock(BillGenerator.class);
    final CustomerDatabase customerDatabase = context.mock(CustomerDatabase.class);
    
    
    @SuppressWarnings("unchecked")
	@Test
	public void offPeak() throws Exception {
        final List<Customer> customerList = context.mock(List.class);
        
        billingSystem = new BillingSystem(  timeGetter, 
        									billGenerator,
        									tariffLibrary,
                                            customerDatabase
        									);
        
		context.checking(new Expectations(){{
			allowing(customerDatabase).getCustomers();
			allowing(customerList).iterator();
			will(returnValue(customerList));
		}});
		
		billingSystem.createCustomerBills();
	}
}

