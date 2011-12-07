package com.acmetelecom;

import com.acmetelecom.customer.Customer;

import java.math.BigDecimal;
import java.util.List;

public class SystemBillGenerator implements IBillGenerator {
	
	IPrinter printer;
	
	public SystemBillGenerator(IPrinter printer) {
		this.printer = printer;
	}
	public void send(ICustomer customer, List<LineItem> calls, BigDecimal totalBill) {
        printer.printHeading(customer.getFullName(), customer.getPhoneNumber(), customer.getPricePlan());
        for (LineItem call : calls) {
            printer.printItem(call.date(), call.callee(), call.durationMinutes(), MoneyFormatter.penceToPounds(call.cost()));
        }
        printer.printTotal(MoneyFormatter.penceToPounds(totalBill));
    }
}
