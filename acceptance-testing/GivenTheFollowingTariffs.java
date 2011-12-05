import com.acmetelecom.customer.Tariff;
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
    public String TariffName;
	public BigDecimal PeakRate;
	public BigDecimal OffPeakRate;

 	@Override
	public void reset() throws Exception {
        TariffName = null;
        PeakRate = null;
        OffPeakRate = null;

	}

	@Override
	public void execute() throws Exception {
        SystemUnderTest.library.addTariff("Standard", Tariff.Standard);

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

