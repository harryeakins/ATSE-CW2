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
    public String Event;
	public String Caller;
	public String Callee;
    public String Time;

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
        Event = null;
        Caller = null;
        Callee = null;
        Time = null;
	}

	@Override
	public void execute() throws Exception {		
		
		if (Event.equals("startCall")){
            atTime(Time);
            SystemUnderTest.billingSystem.callInitiated(Caller, Callee);
		} else if (Event.equals("endCall")) {
            atTime(Time);
            SystemUnderTest.billingSystem.callCompleted(Caller, Callee);
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
