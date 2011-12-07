import com.acmetelecom.SystemBillGenerator;
import com.acmetelecom.BillingSystem;
import com.acmetelecom.fakes.FakeCustomerDatabase;
import com.acmetelecom.fakes.FakeTariffLibrary;
import com.acmetelecom.fakes.FakePrinter;
import com.acmetelecom.test.TestTimeGetter;

public class SystemUnderTest {

    public static final TestTimeGetter timeGetter = new TestTimeGetter();
    public static final FakePrinter printer = new FakePrinter();
    public static final FakeTariffLibrary library = new FakeTariffLibrary();
    public static final FakeCustomerDatabase database = new FakeCustomerDatabase();
	public static final BillingSystem billingSystem = new BillingSystem(timeGetter, 
																		new SystemBillGenerator(printer),
																		library,
                                                                        database);

}