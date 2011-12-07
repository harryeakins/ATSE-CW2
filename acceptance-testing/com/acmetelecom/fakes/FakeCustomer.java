package com.acmetelecom.fakes;

import com.acmetelecom.ICustomer;

public class FakeCustomer implements ICustomer {

	String fullName;
	String phoneNumber;
	String pricePlan;
	
	public FakeCustomer(String fullName, String phoneNumber, String pricePlan) {
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.pricePlan = pricePlan;
	}
	
	@Override
	public String getFullName() {
		return fullName;
	}

	@Override
	public String getPhoneNumber() {
		return phoneNumber;
	}

	@Override
	public String getPricePlan() {
		return pricePlan;
	}

}
