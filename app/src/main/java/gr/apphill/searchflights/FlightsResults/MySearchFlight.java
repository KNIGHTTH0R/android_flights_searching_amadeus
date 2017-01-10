package gr.apphill.searchflights.FlightsResults;

import android.util.Log;

import java.io.Serializable;

/**
 * Created by Dimitrios on 01-Jan-17.
 */

public class MySearchFlight implements Serializable {
    private static final long serialVersionUID = 1L;//Serialiazable in order to pass the object from one activity to another
    private String origin;//origin airport code
    private String destination;//destination airport code
    private String departure_date;
    private String return_date;
    private int adults;
    private int children;
    private int infants;
    private String travel_class;
    private String currency;
    private boolean oneWay;

    //default variables
    private int number_of_results = 10;
    private boolean nonstop = false;
    private int max_price = 1000;

    public MySearchFlight(String origin, String destination, String departure_date, String return_date, int adults, int children, int infants, String travel_class, String currency) {
        this.origin = origin;
        this.destination = destination;
        this.departure_date = departure_date;
        this.return_date = return_date;
        this.adults = adults;
        this.children = children;
        this.infants = infants;
        this.travel_class = travel_class;
        this.currency = currency;
        if (return_date == null) {
            oneWay = true;
        } else {
            oneWay = false;
        }
        /*
        //MySearchFlight values
        Log.v("MySearchFlight"," Values:");
        Log.v("origin",""+" "+origin);
        Log.v("destination"," "+destination);
        Log.v("departure_date"," "+departure_date);
        Log.v("return_date"," "+return_date);
        Log.v("adults"," "+adults);
        Log.v("children"," "+children);
        Log.v("travel_class"," "+travel_class);
        Log.v("currency"," "+currency);
        Log.v("oneWay"," "+oneWay);
        */
    }//MySearchFlight

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public boolean isOneWay() {
        return oneWay;
    }

    public void setOneWay(boolean oneWay) {
        this.oneWay = oneWay;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(String departure_date) {
        this.departure_date = departure_date;
    }

    public String getReturn_date() {
        return return_date;
    }

    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }

    public int getAdults() {
        return adults;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public int getInfants() {
        return infants;
    }

    public void setInfants(int infants) {
        this.infants = infants;
    }

    public String getTravel_class() {
        return travel_class;
    }

    public void setTravel_class(String travel_class) {
        this.travel_class = travel_class;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getNumber_of_results() {
        return number_of_results;
    }//getNumber_of_results

    public void setNumber_of_results(int number_of_results) {
        this.number_of_results = number_of_results;
    }//setNumber_of_results

    public boolean isNonstop() {
        return nonstop;
    }//isNonstop

    public void setNonstop(boolean nonstop) {
        this.nonstop = nonstop;
    }//setNonstop

    public int getMax_price() {
        return max_price;
    }//getMax_price

    public void setMax_price(int max_price) {
        this.max_price = max_price;
    }//setMax_price


    @Override
    public String toString() {
        return "MySearchFlight{" +
                "origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", departure_date='" + departure_date + '\'' +
                ", return_date='" + return_date + '\'' +
                ", adults=" + adults +
                ", children=" + children +
                ", infants=" + infants +
                ", travel_class='" + travel_class + '\'' +
                ", currency='" + currency + '\'' +
                ", oneWay=" + oneWay +
                ", number_of_results=" + number_of_results +
                ", nonstop=" + nonstop +
                ", max_price=" + max_price +
                '}';
    }
}//MySearchFlight
