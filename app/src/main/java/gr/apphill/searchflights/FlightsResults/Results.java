package gr.apphill.searchflights.FlightsResults;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dimitrios on 02-Jan-17.
 */
public class Results {
    private List<Itineraries> itineraries = new ArrayList<Itineraries>();
    private Fare fare;

    public Results(List<Itineraries> itineraries, Fare fare) {
        this.itineraries = itineraries;
        this.fare = fare;
    }

    public List<Itineraries> getItineraries() {
        return itineraries;
    }

    public void setItineraries(List<Itineraries> itineraries) {
        this.itineraries = itineraries;
    }

    public Fare getFare() {
        return fare;
    }

    public void setFare(Fare fare) {
        this.fare = fare;
    }
}//Results
