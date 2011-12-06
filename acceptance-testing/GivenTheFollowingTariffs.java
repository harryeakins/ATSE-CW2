import fit.ColumnFixture;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: elatier
 * Date: 05/12/11
 * Time: 17:42
 * To change this template use File | Settings | File Templates.
 */
public class GivenTheFollowingTariffs extends ColumnFixture {
    public String tariffName;
	public BigDecimal peakRate;
	public BigDecimal offPeakRate;

 	@Override
	public void reset() throws Exception {
        tariffName = null;
        peakRate = null;
        offPeakRate = null;

	}

	@Override
	public void execute() throws Exception {
        //Could not create own tariffs :(
       // SystemUnderTest.library.addTariff(TariffName);

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

