package com.acmetelecom.fakes;

import com.acmetelecom.*;
import com.acmetelecom.customer.Customer;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: elatier
 * Date: 02/12/11
 * Time: 23:28
 * To change this template use File | Settings | File Templates.
 */
public class FakeBillGenerator implements BillGenerator {
	public static Printer printer = FakePrinter.getInstance();

     public void send(Customer customer, List<BillingSystem.LineItem> calls, String totalBill) {
        printer.printHeading(customer.getFullName(), customer.getPhoneNumber(), customer.getPricePlan());
        for (BillingSystem.LineItem call : calls) {
            printer.printItem(call.date(), call.callee(), call.durationMinutes(), MoneyFormatter.penceToPounds(call.cost()));
        }
        printer.printTotal(totalBill);
    }

     public String output(){
    	 return ((FakePrinter) printer).output();
     }
}
