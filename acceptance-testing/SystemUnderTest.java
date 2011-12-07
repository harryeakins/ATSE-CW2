import com.acmetelecom.SystemBillGenerator;
import com.acmetelecom.BillingSystem;
import com.acmetelecom.SystemCustomerDatabase;
import com.acmetelecom.SystemTariffLibrary;
import com.acmetelecom.customer.CentralCustomerDatabase;
import com.acmetelecom.customer.CentralTariffDatabase;
import com.acmetelecom.fakes.FakeCustomerDatabase;
import com.acmetelecom.fakes.FitPrinter;
import com.acmetelecom.test.FakeTariffLibrary;
import com.acmetelecom.test.TestTimeGetter;

/**
 * Created by IntelliJ IDEA.
 * User: elatier
 * Date: 02/12/11
 * Time: 20:13
 * To change this template use File | Settings | File Templates.
 */
public class SystemUnderTest {
	public static final BillingSystem billingSystem = new BillingSystem(new TestTimeGetter(), 
																		new SystemBillGenerator( new FitPrinter()),
																		new SystemTariffLibrary(CentralTariffDatabase.getInstance()),
                                                                        new SystemCustomerDatabase(CentralCustomerDatabase.getInstance()));
}