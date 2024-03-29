package fakes;

import com.acmetelecom.IPrinter;

public class FakePrinter implements IPrinter {
	
    private StringBuilder output = new StringBuilder();
    private String currentCustomer;
    private String currentPricePlan;

    public FakePrinter() {
	}

    public void printItem(String time, String callee, String duration, String cost) {
        output.append(currentCustomer + " " + currentPricePlan +" " + time + " " + callee + " "  + duration + " "  + cost + "\n");
    }

    public void printTotal(String total) {
        //output.append("Total: " + total);
    }
    
    public String output(){
    	return output.toString();
    }

    public void reset(){
    	output = new StringBuilder();
    }

	public void printHeading(String name, String phoneNumber,
				String pricePlan) {
        currentCustomer = phoneNumber;
        currentPricePlan = pricePlan;
	}


}
