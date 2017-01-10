package gr.apphill.searchflights.FlightsResults;

/**
 * Created by Dimitrios on 02-Jan-17.
 */

public class Fare {
    private String total_price;
    PricePerAdult price_per_adult;
    Restrictions restrictions;

    public Fare(String total_price, PricePerAdult price_per_adult, Restrictions restrictions) {
        this.total_price = total_price;
        this.price_per_adult = price_per_adult;
        this.restrictions = restrictions;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public PricePerAdult getPrice_per_adult() {
        return price_per_adult;
    }

    public void setPrice_per_adult(PricePerAdult price_per_adult) {
        this.price_per_adult = price_per_adult;
    }

    public Restrictions getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(Restrictions restrictions) {
        this.restrictions = restrictions;
    }
}//Fare
