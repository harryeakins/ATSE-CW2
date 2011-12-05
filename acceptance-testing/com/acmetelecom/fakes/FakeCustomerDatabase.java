package com.acmetelecom.fakes;

import com.acmetelecom.customer.Customer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: elatier
 * Date: 05/12/11
 * Time: 17:02
 * To change this template use File | Settings | File Templates.
 */

public class FakeCustomerDatabase  implements com.acmetelecom.customer.CustomerDatabase {

    private List<Customer> customers;

    public FakeCustomerDatabase() {
        customers = new ArrayList<Customer>();
    }

    public List<Customer> getCustomers() { return customers; }

    public void addCustomer(Customer customer) {
         customers.add(customer);
    }

}
