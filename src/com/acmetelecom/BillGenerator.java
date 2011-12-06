package com.acmetelecom;

import java.math.BigDecimal;
import java.util.List;

import com.acmetelecom.customer.Customer;

public interface BillGenerator {
	
	public void send(CustomerInterface customer, List<LineItem> calls, BigDecimal totalBill);
	    
}