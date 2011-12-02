import fit.ColumnFixture;

import java.text.DateFormat;
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

    DateFormat df = new SimpleDateFormat("yyyy, MM, dd, HH, mm, ss");

 	@Override
	public void reset() throws Exception {
        Event = null;
        Caller = null;
        Callee = null;
        Time = null;
	}

	@Override
	public void execute() throws Exception {
        if (Event.equals("startCall")) {
             SystemUnderTest.billingSystem.callInitiated(Caller,Callee,df.parse(Time));
        }
        else if (Event.equals("endCall"))
		    SystemUnderTest.billingSystem.callCompleted(Caller,Callee,df.parse(Time));
	}
}
