package com.acmetelecom.test;

import com.acmetelecom.customer.Customer;
import com.acmetelecom.customer.Tariff;
import com.acmetelecom.customer.TariffLibrary;

import java.util.HashMap;

public class FakeTariffLibrary implements TariffLibrary {
	Tariff tariff = Tariff.Standard;
    HashMap<String,Tariff> map;

    public FakeTariffLibrary() {
        map = new HashMap<String, Tariff>();
        map.put("Standard",Tariff.Standard);
        map.put("Business",Tariff.Business);
        map.put("Leisure",Tariff.Leisure);
    }

	public Tariff tarriffFor(Customer cust) {
        return map.get(cust.getPricePlan());
	}
	
	public void setTarrif(Tariff tariff) {
		this.tariff = tariff;
	}

    public void addTariff(String name, Tariff tariff) {
       map.put(name,tariff);
    }

}
