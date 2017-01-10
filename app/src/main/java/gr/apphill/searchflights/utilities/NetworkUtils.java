package gr.apphill.searchflights.utilities;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import gr.apphill.searchflights.FlightsResults.MySearchFlight;
import gr.apphill.searchflights.data.ApplicationData;

import static android.content.ContentValues.TAG;

/**
 * Created by Dimitrios on 30-Dec-16.
 */

public class NetworkUtils {
    //airport search
    private static final String DYNAMIC_AMADEUS_URL_AIRPORTS = "https://api.sandbox.amadeus.com/v1.2/airports/autocomplete";
    final static String PARAM_TERM = "term";
    final static String PARAM_KEY = "apikey";

    public static URL buildUrlForAirportsSearch(String locationQuery) {
        Uri builtUri = Uri.parse(DYNAMIC_AMADEUS_URL_AIRPORTS).buildUpon()
                .appendQueryParameter(PARAM_KEY, ApplicationData.amadeusApiKey)
                .appendQueryParameter(PARAM_TERM, locationQuery)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Log.v(TAG, "Built URI AIRPORTS " + url);
        return url;
    }//buildUrlForAirportsSearch


    //flights search
    private static final String DYNAMIC_AMADEUS_URL_FLIGHTS = "https://api.sandbox.amadeus.com/v1.2/flights/low-fare-search";
    final static String PARAM_ORIGIN = "origin";
    final static String PARAM_DESTINATION = "destination";
    final static String PARAM_DEPARTURE_DATE = "departure_date";
    final static String PARAM_RETURN_DATE = "return_date";
    final static String PARAM_ADULTS = "adults";
    final static String PARAM_CHILDREN = "children";
    final static String PARAM_INFANTS = "infants";
    final static String PARAM_NONSTOP = "nonstop";
    final static String PARAM_MAX_PRICE = "max_price";
    final static String PARAM_CURRENCY = "currency";
    final static String PARAM_TRAVEL_CLASS = "travel_class";
    final static String PARAM_RESULTS_NUM = "number_of_results";


    public static URL buildUrlForFlightsSearch(MySearchFlight myFlight) {
        Uri builtUri = null;

        if (myFlight.isOneWay()) {//no return date parameter
            builtUri = Uri.parse(DYNAMIC_AMADEUS_URL_FLIGHTS).buildUpon()
                    .appendQueryParameter(PARAM_KEY, ApplicationData.amadeusApiKey)
                    .appendQueryParameter(PARAM_ORIGIN, myFlight.getOrigin())
                    .appendQueryParameter(PARAM_DESTINATION, myFlight.getDestination())
                    .appendQueryParameter(PARAM_DEPARTURE_DATE, myFlight.getDeparture_date())
                    .appendQueryParameter(PARAM_ADULTS, myFlight.getAdults() + "")
                    .appendQueryParameter(PARAM_CHILDREN, myFlight.getChildren() + "")
                    .appendQueryParameter(PARAM_INFANTS, myFlight.getInfants() + "")
                    .appendQueryParameter(PARAM_NONSTOP, myFlight.isNonstop() + "")
                    .appendQueryParameter(PARAM_MAX_PRICE, myFlight.getMax_price() + "")
                    .appendQueryParameter(PARAM_CURRENCY, myFlight.getCurrency())
                    .appendQueryParameter(PARAM_TRAVEL_CLASS, myFlight.getTravel_class())
                    .appendQueryParameter(PARAM_RESULTS_NUM, myFlight.getNumber_of_results() + "")
                    .build();
        } else {
            builtUri = Uri.parse(DYNAMIC_AMADEUS_URL_FLIGHTS).buildUpon()
                    .appendQueryParameter(PARAM_KEY, ApplicationData.amadeusApiKey)
                    .appendQueryParameter(PARAM_ORIGIN, myFlight.getOrigin())
                    .appendQueryParameter(PARAM_DESTINATION, myFlight.getDestination())
                    .appendQueryParameter(PARAM_DEPARTURE_DATE, myFlight.getDeparture_date())
                    .appendQueryParameter(PARAM_RETURN_DATE, myFlight.getReturn_date())
                    .appendQueryParameter(PARAM_ADULTS, myFlight.getAdults() + "")
                    .appendQueryParameter(PARAM_CHILDREN, myFlight.getChildren() + "")
                    .appendQueryParameter(PARAM_INFANTS, myFlight.getInfants() + "")
                    .appendQueryParameter(PARAM_NONSTOP, myFlight.isNonstop() + "")
                    .appendQueryParameter(PARAM_MAX_PRICE, myFlight.getMax_price() + "")
                    .appendQueryParameter(PARAM_CURRENCY, myFlight.getCurrency())
                    .appendQueryParameter(PARAM_TRAVEL_CLASS, myFlight.getTravel_class())
                    .appendQueryParameter(PARAM_RESULTS_NUM, myFlight.getNumber_of_results() + "")
                    .build();
        }


        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Log.v(TAG, "Built URI FLIGHTS " + url);
        return url;
    }//buildUrlForFlightsSearch


    //cities search
    private static final String DYNAMIC_IATACODES_URL_CITIES = "https://iatacodes.org/api/v6/cities";
    final static String IATACODES_PARAM_KEY = "api_key";
    final static String IATACODES_PARAM_CODE = "code";

    public static URL buildUrlForCitiesSearch(String airportCode) {
        Uri builtUri = Uri.parse(DYNAMIC_IATACODES_URL_CITIES).buildUpon()
                .appendQueryParameter(IATACODES_PARAM_KEY, ApplicationData.iatacodesApiKey)
                .appendQueryParameter(IATACODES_PARAM_CODE, airportCode)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Log.v(TAG, "Built URI CITIES " + url);
        return url;
    }//buildUrlForAirportsSearch


    //airlines search
    private static final String DYNAMIC_IATACODES_URL_AIRLINES = "https://iatacodes.org/api/v6/airlines";
    public static URL buildUrlForAirlinesSearch(String airlinesCodes) {
        Uri builtUri = Uri.parse(DYNAMIC_IATACODES_URL_AIRLINES).buildUpon()
                .appendQueryParameter(IATACODES_PARAM_KEY, ApplicationData.iatacodesApiKey)
                .appendQueryParameter(IATACODES_PARAM_CODE, airlinesCodes)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Log.v(TAG, "Built URI AIRLINES " + url);
        return url;
    }//buildUrlForAirportsSearch

}//NetworkUtils
