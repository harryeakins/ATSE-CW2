package com.acmetelecom;

import com.acmetelecom.customer.Customer;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: elatier
 * Date: 02/12/11
 * Time: 23:04
 * To change this template use File | Settings | File Templates.
 */
public class HtmlBillGenerator implements BillGenerator {

     public void send(Customer customer, List<BillingSystem.LineItem> calls, String totalBill) {

        Printer printer = HtmlPrinter.getInstance();
        printer.printHeading(customer.getFullName(), customer.getPhoneNumber(), customer.getPricePlan());
        for (BillingSystem.LineItem call : calls) {
            printer.printItem(call.date(), call.callee(), call.durationMinutes(), MoneyFormatter.penceToPounds(call.cost()));
        }
        printer.printTotal(totalBill);
    }
}
