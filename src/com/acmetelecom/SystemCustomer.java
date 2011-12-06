package com.acmetelecom;

import com.acmetelecom.customer.Customer;

public class SystemCustomer implements CustomerInterface {
	final private Customer customer;
	public SystemCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getPhoneNumber() {
		return customer.getPhoneNumber();
	}
	
	public Customer getCustomer() {
		return customer;
	}

	@Override
	public String getFullName() {
		return customer.getFullName();
	}

	@Override
	public String getPricePlan() {
		return customer.getPricePlan();
	}
}
