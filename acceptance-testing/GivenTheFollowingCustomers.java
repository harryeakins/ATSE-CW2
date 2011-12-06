import com.acmetelecom.customer.Customer;
import fit.ColumnFixture;
import fit.Parse;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: elatier
 * Date: 05/12/11
 * Time: 18:00
 * To change this template use File | Settings | File Templates.
 */
public class GivenTheFollowingCustomers extends ColumnFixture {
    public String phoneNumber;
	public String fullName;
	public String pricePlan;

 	@Override
	public void reset() throws Exception {
        phoneNumber = null;
        fullName = null;
        pricePlan = null;
	}

	@Override
	public void execute() throws Exception {
        SystemUnderTest.database.addCustomer(new Customer(fullName, phoneNumber, pricePlan));
	}

    @Override
	public void doRows(Parse rows) {
		super.doRows(rows);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public Object parse(String s, Class type) throws Exception {
		if (type == BigDecimal.class) {
			return new BigDecimal(s);
		}
		else {
			return super.parse(s, type);
		}
	}
}
