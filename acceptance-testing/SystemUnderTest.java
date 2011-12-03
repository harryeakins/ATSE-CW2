import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.acmetelecom.BillingSystem;
import com.acmetelecom.FileBillGenerator;
import com.acmetelecom.fakes.FakeBillGenerator;
import com.acmetelecom.test.TestTimeGetter;

/**
 * Created by IntelliJ IDEA.
 * User: elatier
 * Date: 02/12/11
 * Time: 20:13
 * To change this template use File | Settings | File Templates.
 */
public class SystemUnderTest {
	static DateFormat df = new SimpleDateFormat("yyyy, MM, dd, HH, mm, ss");

    public static final FakeBillGenerator printer = new FakeBillGenerator();
	public static BillingSystem billingSystem;

	public static void setUp(String start, String end) throws ParseException{
		ArrayList<Date> times = new ArrayList<Date>();
		times.add(df.parse(start));
		times.add(df.parse(end));
		billingSystem = new BillingSystem(new TestTimeGetter(times), printer);
		
	}
}