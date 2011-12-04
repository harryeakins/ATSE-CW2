import com.acmetelecom.BillingSystem;
import com.acmetelecom.FileBillGenerator;
import com.acmetelecom.test.TestTimeGetter;

/**
 * Created by IntelliJ IDEA.
 * User: elatier
 * Date: 02/12/11
 * Time: 20:13
 * To change this template use File | Settings | File Templates.
 */
public class SystemUnderTest {

    //public static final FakeBillGenerator printer = new FakeBillGenerator();
    public static final TestTimeGetter timeGetter = new TestTimeGetter();
	public static final BillingSystem billingSystem = new BillingSystem(timeGetter, new FileBillGenerator());

}