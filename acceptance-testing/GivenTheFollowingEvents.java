import fit.ColumnFixture;
import fit.Parse;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by IntelliJ IDEA.
 * User: elatier
 * Date: 02/12/11
 * Time: 21:43
 * To change this template use File | Settings | File Templates.
 */
public class GivenTheFollowingEvents extends ColumnFixture {
    public String event;
	public String caller;
	public String callee;
    public String time;

    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    void atTime(String time) throws ParseException{
		SystemUnderTest.timeGetter.set(df.parse(time));

	}

	@Override
	public void doRows(Parse rows) {
		super.doRows(rows);
	}
	
 	@Override
	public void reset() throws Exception {
        event = null;
        caller = null;
        callee = null;
        time = null;
	}

	@Override
	public void execute() throws Exception {		
		
		if (event.equals("startCall")){
            atTime(time);
            SystemUnderTest.billingSystem.callInitiated(caller, callee);
		} else if (event.equals("endCall")) {
            atTime(time);
            SystemUnderTest.billingSystem.callCompleted(caller, callee);
		}
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
