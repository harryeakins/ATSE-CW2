import com.acmetelecom.SystemCustomer;
import com.acmetelecom.customer.Customer;
import fit.ColumnFixture;
import fit.Parse;

import java.math.BigDecimal;

public class GivenTheFollowingCustomers extends ColumnFixture {
    public String PhoneNumber;
	public String FullName;
	public String PricePlan;

 	@Override
	public void reset() throws Exception {
        PhoneNumber = null;
        FullName = null;
        PricePlan = null;
	}

	@Override
	public void execute() throws Exception {
        SystemUnderTest.database.addCustomer(new SystemCustomer(new Customer(FullName,PhoneNumber,PricePlan)));
	}

    @Override
	public void doRows(Parse rows) {
        SystemUnderTest.database.reset();
		super.doRows(rows);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object parse(String s, Class type) throws Exception {
		if (type == BigDecimal.class) {
			return new BigDecimal(s);
		}
		else {
			return super.parse(s, type);
		}
	}
}
