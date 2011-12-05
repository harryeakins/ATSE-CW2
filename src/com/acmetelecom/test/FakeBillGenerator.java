package com.acmetelecom.test;

import java.math.BigDecimal;
import java.util.List;

import com.acmetelecom.BillGenerator;
import com.acmetelecom.LineItem;
import com.acmetelecom.MoneyFormatter;
import com.acmetelecom.customer.Customer;

public class FakeBillGenerator implements BillGenerator {
	public String totalBill;
	public String number;
	public FakeBillGenerator() {
		super();
	}
	
	public void setInterestingPhoneNumber(String number) {
		this.number = number;
	}
	
	@Override
	public void send(Customer customer, List<LineItem> calls, BigDecimal totalBill) {
		if(customer.getPhoneNumber() == this.number) {
			this.totalBill = MoneyFormatter.penceToPounds(totalBill);
		}
	}
}
