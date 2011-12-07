package com.acmetelecom;

import java.math.BigDecimal;
import java.util.List;

import com.acmetelecom.customer.Customer;

public interface IBillGenerator {
	public void send(ICustomer customer, List<LineItem> calls, BigDecimal totalBill);
}