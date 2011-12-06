package com.acmetelecom;

import com.acmetelecom.customer.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class BillingSystem {

    private List<CallEvent> callLog = new ArrayList<CallEvent>();
    private TimeGetter timeGetter;
    private BillGenerator billGenerator;
    private TariffLibrary tariffLibrary;
    private CustomerDatabase customerDatabase;

    public BillingSystem(TimeGetter timeGetter, BillGenerator billGenerator, TariffLibrary tariffLibrary, CustomerDatabase customerDatabase) {
    	this.timeGetter = timeGetter;
        this.billGenerator = billGenerator;
        this.tariffLibrary = tariffLibrary;
        this.customerDatabase = customerDatabase;
    }
    
    public void callInitiated(String caller, String callee) {
        callLog.add(CallEvent.startEvent(caller, callee, timeGetter.getCurrentTime()));
    }

    public void callCompleted(String caller, String callee) {
        callLog.add(CallEvent.endEvent(caller, callee, timeGetter.getCurrentTime()));
    }

    public void createCustomerBills() {
        List<Customer> customers = customerDatabase.getCustomers();
        for (Customer customer : customers) {
            createBillFor(customer);
        }
        callLog.clear();
    }

    public void createBillFor(Customer customer) {
        List<CallEvent> customerEvents = new ArrayList<CallEvent>();
        for (CallEvent callEvent : callLog) {
            if (callEvent.getCaller().equals(customer.getPhoneNumber())) {
                customerEvents.add(callEvent);
            }
        }

        List<Call> calls = new ArrayList<Call>();

        CallEvent start = null;
        for (CallEvent event : customerEvents) {
            if (event.getEventType() == "start") {
                start = event;
            }
            if (event.getEventType() == "end" && start != null) {
                calls.add(new Call(start, event));
                start = null;
            }
        }

        BigDecimal totalBill = new BigDecimal(0);
        List<LineItem> items = new ArrayList<LineItem>();

        for (Call call : calls) {

            Tariff tariff = tariffLibrary.tarriffFor(customer);

            BigDecimal cost = new BigDecimal(0);

            DaytimePeakPeriod peakPeriod = new DaytimePeakPeriod();
            
            final int TWELVEHOURS = 12*60*60;
            int callDuration = call.durationSeconds();
            
            boolean isOffPeak = peakPeriod.offPeak(call.startTime());
            
            int firstTimeBlock;
            if (isOffPeak) {
            	firstTimeBlock = peakPeriod.nextPeakBoundary(call.startTime()) - (int)(call.startTime().getTime() / 1000);
            	if (callDuration < firstTimeBlock)
            		firstTimeBlock = callDuration;
            	cost = cost.add(new BigDecimal(firstTimeBlock).multiply(tariff.offPeakRate()));
            } else {
            	firstTimeBlock = peakPeriod.nextOffPeakBoundary(call.startTime()) - (int)(call.startTime().getTime() / 1000);
            	if (callDuration < firstTimeBlock)
            		firstTimeBlock = callDuration;
            	cost = cost.add(new BigDecimal(firstTimeBlock).multiply(tariff.peakRate()));	 
            }

            callDuration -= firstTimeBlock;

            isOffPeak = !isOffPeak;
            while(callDuration > TWELVEHOURS){
            	cost = isOffPeak ? cost.add(new BigDecimal(TWELVEHOURS).multiply(tariff.offPeakRate()))
            					 : cost.add(new BigDecimal(TWELVEHOURS).multiply(tariff.peakRate()));
            	callDuration-=TWELVEHOURS;
            	isOffPeak = !isOffPeak;
            }
            
            cost = isOffPeak ? cost.add(new BigDecimal(callDuration).multiply(tariff.offPeakRate())) 
            		         : cost.add(new BigDecimal(callDuration).multiply(tariff.peakRate()));	 
           
            cost = cost.setScale(0, RoundingMode.HALF_UP);
            BigDecimal callCost = cost;
            totalBill = totalBill.add(callCost);
            items.add(new LineItem(call, callCost));
        }

        billGenerator.send(customer, items, totalBill);
    }
}
