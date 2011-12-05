import com.acmetelecom.BillGenerator;
import com.acmetelecom.BillingSystem;
import com.acmetelecom.fakes.FakeCustomerDatabase;
import com.acmetelecom.fakes.FakePrinter;
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

    public static final TestTimeGetter timeGetter = new TestTimeGetter();
    public static final FakePrinter printer = new FakePrinter();
    public static final FakeTariffLibrary library = new FakeTariffLibrary();
    public static final FakeCustomerDatabase database = new FakeCustomerDatabase();
	public static final BillingSystem billingSystem = new BillingSystem(timeGetter, 
																		new BillGenerator(printer),
																		library,
                                                                        database
																		);

}