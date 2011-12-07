package com.acmetelecom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.acmetelecom.customer.Customer;
import com.acmetelecom.customer.CustomerDatabase;

public class SystemCustomerDatabase implements ICustomerDatabase {
	final private CustomerDatabase db;
	
	public SystemCustomerDatabase(CustomerDatabase db) {
		this.db = db;
	}
	
	@Override
	public List<ICustomer> getCustomers() {
		ArrayList<ICustomer> customers = new ArrayList<ICustomer>();
		List<Customer> customerList = db.getCustomers();
		Iterator<Customer> iter = customerList.iterator();
		while (iter.hasNext()) {
			customers.add(new SystemCustomer(iter.next()));
		}
		return customers;
	}
}
