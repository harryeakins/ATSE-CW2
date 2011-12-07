package fakes;

import java.util.HashMap;

import com.acmetelecom.ICustomer;
import com.acmetelecom.ITariffLibrary;
import com.acmetelecom.customer.Tariff;

public class FakeTariffLibrary implements ITariffLibrary {
    HashMap<String,Tariff> map;

    public FakeTariffLibrary() {
        map = new HashMap<String, Tariff>();
        map.put("Standard",Tariff.Standard);
        map.put("Business",Tariff.Business);
        map.put("Leisure",Tariff.Leisure);
        System.out.print(Tariff.values());
    }

	public Tariff tariffFor(ICustomer cust) {
        return map.get(cust.getPricePlan());
	}

    public void addTariff(String name, Tariff tariff) {
       map.put(name,tariff);
    }

}
