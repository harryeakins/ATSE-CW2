import fit.RowFixture;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: elatier
 * Date: 02/12/11
 * Time: 23:07
 * To change this template use File | Settings | File Templates.
 */
public class TheGeneratedBill extends RowFixture {

    public static class Row {
        public String Time;
        public String Number;
        public String Duration;
        public String Cost;

        public Row(String Time,String Number,String Duration,String Cost) {
            this.Time = Time;
            this.Number = Number;
            this.Duration = Duration;
            this.Cost = Cost;
        }
    }

    @Override
    public Class<?> getTargetClass() {
        return Row.class;
    }

    @Override
    public Object[] query() throws Exception {
        SystemUnderTest.billingSystem.createCustomerBills();

        List<Row> rows = new ArrayList<Row>();
        for (String line : SystemUnderTest.printer.output().split("\n")) {
            rows.add(new Row(rows.size() + 1, line));
        }
        return rows.toArray();
    }
}

}
