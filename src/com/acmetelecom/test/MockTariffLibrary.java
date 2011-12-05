package com.acmetelecom.test;

import com.acmetelecom.customer.Customer;
import com.acmetelecom.customer.Tariff;
import com.acmetelecom.customer.TariffLibrary;

public class MockTariffLibrary implements TariffLibrary {
	Tariff tariff = Tariff.Standard;
	@Override
	public Tariff tarriffFor(Customer arg0) {
		return this.tariff;
	}
	
	public void setTarrif(Tariff tariff) {
		this.tariff = tariff;
	}

}
