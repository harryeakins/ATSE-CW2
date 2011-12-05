package com.acmetelecom;

import com.acmetelecom.customer.CentralCustomerDatabase;
import com.acmetelecom.customer.CentralTariffDatabase;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.customer.Tariff;
import com.acmetelecom.customer.TariffLibrary;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class BillingSystem {

    private List<CallEvent> callLog = new ArrayList<CallEvent>();
    private TimeGetter timeGetter;
    private BillGenerator billGenerator;
    private TariffLibrary tariffLibrary;

    public BillingSystem(TimeGetter timeGetter, BillGenerator billGenerator, TariffLibrary tariffLibrary) {
    	this.timeGetter = timeGetter;
        this.billGenerator = billGenerator;
        this.tariffLibrary = tariffLibrary;
    }
    
    public void callInitiated(String caller, String callee) {
        callLog.add(CallEvent.startEvent(caller, callee, timeGetter.getCurrentTime()));
    }

    public void callCompleted(String caller, String callee) {
        callLog.add(CallEvent.endEvent(caller, callee, timeGetter.getCurrentTime()));
    }

    public void createCustomerBills() {
        List<Customer> customers = CentralCustomerDatabase.getInstance().getCustomers();
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

            Tariff tariff = this.tariffLibrary.tarriffFor(customer);

            BigDecimal cost = new BigDecimal(0);

            DaytimePeakPeriod peakPeriod = new DaytimePeakPeriod();
            

            int duration = call.durationSeconds();
            int TWELVEHOURS = 12*60*60; 
            boolean offPeak = peakPeriod.offPeak(call.startTime());
            
            
            int duration1;
            if (offPeak) {
            	duration1 = peakPeriod.offPeakDiff(call.startTime()) - (int)(call.startTime().getTime() / 1000);
            	if (duration <= duration1)
            		duration1 = duration;
            	cost = cost.add(new BigDecimal(duration1).multiply(tariff.offPeakRate()));
            } else {
            	duration1 = peakPeriod.peakDiff(call.startTime()) - (int)(call.startTime().getTime() / 1000);
            	if (duration <= duration1)
            		duration1 = duration;
            	cost = cost.add(new BigDecimal(duration1).multiply(tariff.peakRate()));	 
            }

            duration -= duration1;

            /* Mod by 12 hour blocks if necessary */
            offPeak = !offPeak;
            while(duration>TWELVEHOURS){
            	
            	if (offPeak) cost = cost.add(new BigDecimal(TWELVEHOURS).multiply(tariff.offPeakRate()));
            	else cost = cost.add(new BigDecimal(TWELVEHOURS).multiply(tariff.peakRate()));	 
            	duration-=TWELVEHOURS;
            	offPeak = !offPeak;
            }
            
            cost = offPeak ? cost.add(new BigDecimal(duration).multiply(tariff.offPeakRate())) 
            		       : cost.add(new BigDecimal(duration).multiply(tariff.peakRate()));	 
           
            cost = cost.setScale(0, RoundingMode.HALF_UP);
            BigDecimal callCost = cost;
            totalBill = totalBill.add(callCost);
            items.add(new LineItem(call, callCost));
        }

        billGenerator.send(customer, items, totalBill);
    }
}
