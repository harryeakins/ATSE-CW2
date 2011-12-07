package com.acmetelecom;

import com.acmetelecom.customer.Tariff;
import com.acmetelecom.customer.TariffLibrary;

public class SystemTariffLibrary implements ITariffLibrary {

	final private TariffLibrary tariffLibrary;
	
	public SystemTariffLibrary(TariffLibrary tariffLibrary) {
		this.tariffLibrary = tariffLibrary;
	}
	
	@Override
	public Tariff tariffFor(ICustomer customer) {
		return tariffLibrary.tarriffFor(((SystemCustomer)customer).getCustomer());
	}

}
