import fit.RowFixture;

import java.util.ArrayList;
import java.util.List;

public class TheGeneratedBill extends RowFixture {

    public static class Row {
        public String Customer;
        public String Time;
        public String Number;
        public String Duration;
        public String Cost;
        public String PricePlan;

        public Row(String Customer,String PricePlan,String Time,String Number,String Duration,String Cost) {
            this.Customer = Customer;
            this.PricePlan = PricePlan;
            this.Time = Time;
            this.Number = Number;
            this.Duration = Duration;
            this.Cost = Cost;
        }
        
        public Row(String line){
        	String[] arr= line.split(" ");
            this.Customer = arr[0];
            this.PricePlan = arr[1];
        	this.Time = arr[2].concat(" "+arr[3]);
            this.Number = arr[4];
            this.Duration = arr[5];
            this.Cost = arr[6];
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
            rows.add(new Row(line));
        }

        return rows.toArray();
    }
}


