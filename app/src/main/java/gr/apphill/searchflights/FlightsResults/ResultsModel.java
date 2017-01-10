package gr.apphill.searchflights.FlightsResults;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dimitrios on 02-Jan-17.
 */

public class ResultsModel {
    private static String currency;
    private List<Results> results = new ArrayList<Results>();

    public ResultsModel(String currency, List<Results> results) {
        this.currency = currency;
        this.results = results;
    }

    public static String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

}//ResultsModel
