package com.acmetelecom.test;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.acmetelecom.Call;
import com.acmetelecom.CallEvent;
import com.acmetelecom.CustomerInterface;
import com.acmetelecom.LineItem;
import com.acmetelecom.Printer;
import com.acmetelecom.SystemBillGenerator;

@RunWith(JMock.class)
public class SystemBillGeneratorTest {
	public final Mockery context = new Mockery();
	SystemBillGenerator billGenerator;
	final Printer printer = context.mock(Printer.class);
	final CustomerInterface customer = context.mock(CustomerInterface.class);
	
	@Before
    public void setUp() throws Exception {
        billGenerator = new SystemBillGenerator(printer);
    }

    @After
    public void tearDown() throws Exception {
    }
	
	@Test
	public void oneCall() {
		final ArrayList<LineItem> calls = new ArrayList<LineItem>();
		final LineItem item = new LineItem(new Call(CallEvent.startEvent("0123", "4567", 3000),
				                                    CallEvent.endEvent("0123", "4567", 6000)), new BigDecimal(36));
		calls.add(item);
		
		context.checking(new Expectations() {{
			allowing(customer).getFullName();
    		will(returnValue("Alice"));
    		
    		allowing(customer).getPhoneNumber();
    		will(returnValue("0123"));
    		
    		allowing(customer).getPricePlan();
    		will(returnValue("standard"));
    		
    		oneOf(printer).printHeading("Alice", "0123", "standard");
			
			oneOf(printer).printItem(item.date(),
    				                 item.callee(),
    				                 item.durationMinutes(),
    				                 "0.36");
    		
			oneOf(printer).printTotal("0.36");
    		
		}});
		billGenerator.send(customer, calls, new BigDecimal(36));
	}
	
	@Test
	public void twoCalls() {
		final ArrayList<LineItem> calls = new ArrayList<LineItem>();
		final LineItem item1 = new LineItem(new Call(CallEvent.startEvent("0123", "4567", 3000),
				                                     CallEvent.endEvent("0123", "4567", 6000)), new BigDecimal(36));
		final LineItem item2 = new LineItem(new Call(CallEvent.startEvent("0123", "1111", 8000),
                                                     CallEvent.endEvent("0123", "1111", 14000)), new BigDecimal(72));
		calls.add(item1);
		calls.add(item2);
		
		context.checking(new Expectations() {{
			allowing(customer).getFullName();
    		will(returnValue("Alice"));
    		
    		allowing(customer).getPhoneNumber();
    		will(returnValue("0123"));
    		
    		allowing(customer).getPricePlan();
    		will(returnValue("standard"));
    		
    		oneOf(printer).printHeading("Alice", "0123", "standard");
			
			oneOf(printer).printItem(item1.date(),
    				                 item1.callee(),
    				                 item1.durationMinutes(),
    				                 "0.36");
			oneOf(printer).printItem(item2.date(),
	                 				 item2.callee(),
	                 				 item2.durationMinutes(),
	                 				 "0.72");
    		
			oneOf(printer).printTotal("1.08");
		}});
		billGenerator.send(customer, calls, new BigDecimal(108));
	}

}