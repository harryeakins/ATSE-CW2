package com.acmetelecom;

import com.acmetelecom.customer.Customer;

import java.util.List;

public class FileBillGenerator implements BillGenerator {

    public void send(Customer customer, List<BillingSystem.LineItem> calls, String totalBill) {

        Printer printer = FilePrinter.getInstance();
        printer.printHeading(customer.getFullName(), customer.getPhoneNumber(), customer.getPricePlan());
        for (BillingSystem.LineItem call : calls) {
            printer.printItem(call.date(), call.callee(), call.durationMinutes(), MoneyFormatter.penceToPounds(call.cost()));
        }
        printer.printTotal(totalBill);
    }

}