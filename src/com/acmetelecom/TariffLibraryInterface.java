package com.acmetelecom;

import com.acmetelecom.customer.Tariff;

public interface TariffLibraryInterface {
	Tariff tariffFor(CustomerInterface customer);
}
