package com.acmetelecom;

import com.acmetelecom.customer.Tariff;

public interface ITariffLibrary {
	Tariff tariffFor(ICustomer customer);
}
