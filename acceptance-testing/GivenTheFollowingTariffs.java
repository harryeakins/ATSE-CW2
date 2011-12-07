import fit.ColumnFixture;

import java.math.BigDecimal;

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
        //Could not create own tariffs :(
       // SystemUnderTest.library.addTariff(TariffName);

	}


	@SuppressWarnings("unchecked")
	@Override
	public Object parse(String s, Class type) throws Exception {
		if (type == BigDecimal.class) {
			return new BigDecimal(s);
		}
		else {
			return super.parse(s, type);
		}
	}
}

