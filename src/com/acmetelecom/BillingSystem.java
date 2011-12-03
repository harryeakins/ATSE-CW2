package com.acmetelecom;

import com.acmetelecom.customer.CentralCustomerDatabase;
import com.acmetelecom.customer.CentralTariffDatabase;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.customer.Tariff;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BillingSystem {

    private List<CallEvent> callLog = new ArrayList<CallEvent>();
    private TimeGetter timeGetter;
    private BillGenerator billGenerator;

    public BillingSystem(TimeGetter timeGetter, BillGenerator billGenerator) {
    	this.timeGetter = timeGetter;
        this.billGenerator = billGenerator;
    }
    
    public void callInitiated(String caller, String callee) {
        callLog.add(new CallStart(caller, callee, timeGetter.getCurrentTime()));
    }

    public void callInitiated(String caller, String callee, Date currentTime) {
        callLog.add(new CallStart(caller, callee, currentTime.getTime()));
    }

    public void callCompleted(String caller, String callee) {
        callLog.add(new CallEnd(caller, callee, timeGetter.getCurrentTime()));
    }

    public void callCompleted(String caller, String callee, Date currentTime) {
        callLog.add(new CallEnd(caller, callee, currentTime.getTime()));
    }

    public void createCustomerBills() {
        List<Customer> customers = CentralCustomerDatabase.getInstance().getCustomers();
        for (Customer customer : customers) {
            createBillFor(customer);
        }
        callLog.clear();
    }

    private void createBillFor(Customer customer) {
        List<CallEvent> customerEvents = new ArrayList<CallEvent>();
        for (CallEvent callEvent : callLog) {
            if (callEvent.getCaller().equals(customer.getPhoneNumber())) {
                customerEvents.add(callEvent);
            }
        }

        List<Call> calls = new ArrayList<Call>();

        CallEvent start = null;
        for (CallEvent event : customerEvents) {
            if (event instanceof CallStart) {
                start = event;
            }
            if (event instanceof CallEnd && start != null) {
                calls.add(new Call(start, event));
                start = null;
            }
        }

        BigDecimal totalBill = new BigDecimal(0);
        List<LineItem> items = new ArrayList<LineItem>();

        for (Call call : calls) {

            Tariff tariff = CentralTariffDatabase.getInstance().tarriffFor(customer);

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

        billGenerator.send(customer, items, MoneyFormatter.penceToPounds(totalBill));
    }

    public static class LineItem {
        private Call call;
        private BigDecimal callCost;

        public LineItem(Call call, BigDecimal callCost) {
            this.call = call;
            this.callCost = callCost;
        }

        public String date() {
            return call.date();
        }

        public String callee() {
            return call.callee();
        }

        public String durationMinutes() {
            return "" + call.durationSeconds() / 60 + ":" + String.format("%02d", call.durationSeconds() % 60);
        }

        public BigDecimal cost() {
            return callCost;
        }
    }
}
