package gr.apphill.searchflights.FlightsResults;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dimitrios on 02-Jan-17.
 */

public class OutboundInbound {
    List<Flights> flights = new ArrayList<Flights>();

    public OutboundInbound(List<Flights> flights) {
        this.flights = flights;
    }

    public List<Flights> getFlights() {
        return flights;
    }

    public void setFlights(List<Flights> flights) {
        this.flights = flights;
    }

    public int getFlightsNumber(){
        return flights.size();
    }//getFlightsNumber


    public List<String> getAirlineName(){
        List<String> marketingAirlines = new ArrayList<String>();

        if(getFlightsNumber()>1){//multiple flights
            marketingAirlines.add(flights.get(0).getMarketing_airline());
            for(int i=1;i<flights.size();i++){
                if(!marketingAirlines.contains(flights.get(i).getMarketing_airline())){
                    marketingAirlines.add(flights.get(i).getMarketing_airline());
                }
            }
            return marketingAirlines;
        }

        marketingAirlines.add(flights.get(0).getMarketing_airline());
        return marketingAirlines;

    }//getAirlineName

}//OutboundInbound
