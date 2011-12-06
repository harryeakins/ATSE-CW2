package com.acmetelecom;

import com.acmetelecom.customer.Customer;

import java.math.BigDecimal;
import java.util.List;

public class SystemBillGenerator implements BillGenerator {
	
	Printer printer;
	
	public SystemBillGenerator(Printer printer) {
		this.printer = printer;
	}
	public void send(CustomerInterface customer, List<LineItem> calls, BigDecimal totalBill) {
        printer.printHeading(customer.getFullName(), customer.getPhoneNumber(), customer.getPricePlan());
        for (LineItem call : calls) {
            printer.printItem(call.date(), call.callee(), call.durationMinutes(), MoneyFormatter.penceToPounds(call.cost()));
        }
        printer.printTotal(MoneyFormatter.penceToPounds(totalBill));
    }
}
