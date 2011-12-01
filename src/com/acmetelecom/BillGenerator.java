package com.acmetelecom;

import com.acmetelecom.customer.Customer;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: elatier
 * Date: 01/12/11
 * Time: 16:44
 * To change this template use File | Settings | File Templates.
 */
public interface BillGenerator {
    void send(Customer customer, List<BillingSystem.LineItem> calls, String totalBill);
}
