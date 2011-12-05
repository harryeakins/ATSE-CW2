package com.acmetelecom.fakes;

import com.acmetelecom.HtmlPrinter;
import com.acmetelecom.Printer;

public class FakePrinter implements Printer{
	
		private StringBuilder output = new StringBuilder();
		
	    public FakePrinter() {
	    }

	    public void printItem(String time, String callee, String duration, String cost) {
	        output.append(time + " " + callee + " "  + duration + " "  + cost + "\n");
	    }

	    public void printTotal(String total) {
	        //output.append("Total: " + total);
	    }
	    
	    public String output(){
	    	return output.toString();
	    }

		public void printHeading(String name, String phoneNumber,
					String pricePlan) {
		}


}
