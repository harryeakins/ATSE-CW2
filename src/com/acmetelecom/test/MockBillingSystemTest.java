package com.acmetelecom.test;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import com.acmetelecom.customer.*;

@RunWith(JMock.class)
public class MockBillingSystemTest {

	public final Mockery context = new Mockery();
    DateFormat df;
    BillingSystem billingSystem;
    TestTimeGetter timeGetter;
    FakeTariffLibrary tariffLibrary = new FakeTariffLibrary();
    //FakeBillGenerator billGenerator = new FakeBillGenerator();
    //final TariffLibrary tariffLibrary = context.mock(TariffLibrary.class);
    final BillGenerator billGenerator = context.mock(BillGenerator.class);
    final CustomerDatabase customerDatabase = context.mock(CustomerDatabase.class);

    @Before
    public void setUp() throws Exception {
        df = new SimpleDateFormat("yyyy, MM, dd, HH, mm, ss");
        timeGetter = new TestTimeGetter();
        billingSystem = new BillingSystem(timeGetter, 
        								  billGenerator,
        								  tariffLibrary,
                                          CentralCustomerDatabase.getInstance());
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
		
		context.checking(new Expectations() {{
    		atLeast(1).of(billGenerator).send(with(any(Customer.class)), with(any(List.class)), with(any(BigDecimal.class)));
    		//allowing(tariffLibrary).tarriffFor(with(any(Customer.class)));
    		}});
		
		billingSystem.createCustomerBills();
		
	}
}

