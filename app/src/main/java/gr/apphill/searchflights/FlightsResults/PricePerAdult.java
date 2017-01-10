package gr.apphill.searchflights.FlightsResults;

/**
 * Created by Dimitrios on 02-Jan-17.
 */

public class PricePerAdult {
    private String total_fare;
    private String tax;
    public PricePerAdult(String total_fare, String tax) {
        this.total_fare = total_fare;
        this.tax = tax;
    }//PricePerAdult

    public String getTotal_fare() {
        return total_fare;
    }

    public void setTotal_fare(String total_fare) {
        this.total_fare = total_fare;
    }

    public String getTax() {
        return tax;
    }
    public void setTax(String tax) {
        this.tax = tax;
    }
}//PricePerAdult
