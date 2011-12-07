package com.acmetelecom.test;

import java.math.BigDecimal;
import java.util.List;

import com.acmetelecom.IBillGenerator;
import com.acmetelecom.ICustomer;
import com.acmetelecom.LineItem;

public class MockBillGenerator implements IBillGenerator {
	public ICustomer customer;
	public List<LineItem> calls;
	public BigDecimal totalBill;
	public int numCalls = 0;
	
	@Override
	public void send(ICustomer customer, List<LineItem> calls, BigDecimal totalBill) {
		this.customer = customer;
		this.calls = calls;
		this.totalBill = totalBill;
		++numCalls;
	}
}
