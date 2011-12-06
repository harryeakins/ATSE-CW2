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
        public String customer;
        public String time;
        public String number;
        public String duration;
        public String cost;
        public String pricePlan;

        public Row(String Customer,String PricePlan,String Time,String Number,String Duration,String Cost) {
            this.customer = Customer;
            this.pricePlan = PricePlan;
            this.time = Time;
            this.number = Number;
            this.duration = Duration;
            this.cost = Cost;
        }
        
        public Row(String line){
        	String[] arr= line.split(" ");
            this.customer = arr[0];
            this.pricePlan = arr[1];
        	this.time = arr[2].concat(" "+arr[3]);
            this.number = arr[4];
            this.duration = arr[5];
            this.cost = arr[6];
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
        //rows.add(new Row("27/11/11 09:00", "447766814143", "1140:00", "244.80"));
        return rows.toArray();
    }
}


