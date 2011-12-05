package com.acmetelecom;

import com.acmetelecom.customer.Customer;

import java.math.BigDecimal;
import java.util.List;

public class CustomerBillGenerator implements BillGenerator {
	
	Printer printer;
	
	public CustomerBillGenerator(Printer printer) {
		this.printer = printer;
	}
	public void send(Customer customer, List<LineItem> calls, BigDecimal totalBill) {
        printer.printHeading(customer.getFullName(), customer.getPhoneNumber(), customer.getPricePlan());
        for (LineItem call : calls) {
            printer.printItem(call.date(), call.callee(), call.durationMinutes(), MoneyFormatter.penceToPounds(call.cost()));
        }
        printer.printTotal(MoneyFormatter.penceToPounds(totalBill));
    }
}
