package com.acmetelecom.fakes;

import com.acmetelecom.ICustomer;
import com.acmetelecom.ICustomerDatabase;
import com.acmetelecom.SystemCustomer;
import com.acmetelecom.customer.Customer;

import java.util.ArrayList;
import java.util.List;

public class FakeCustomerDatabase  implements ICustomerDatabase {

    private List<ICustomer> customers;

    public FakeCustomerDatabase() {
        customers = new ArrayList<ICustomer>();
    }

    public List<ICustomer> getCustomers() { return customers; }

    public void addCustomer(SystemCustomer customer) {
         customers.add(customer);
    }
}
