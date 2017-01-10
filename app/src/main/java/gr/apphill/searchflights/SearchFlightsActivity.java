package gr.apphill.searchflights;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import gr.apphill.searchflights.Airlines.AirlineModel;
import gr.apphill.searchflights.FlightsResults.Flights;
import gr.apphill.searchflights.FlightsResults.MySearchFlight;
import gr.apphill.searchflights.FlightsResults.Results;
import gr.apphill.searchflights.FlightsResults.ResultsAdapter;
import gr.apphill.searchflights.FlightsResults.ResultsModel;
import gr.apphill.searchflights.utilities.NetworkUtils;

/**
 * Created by Dimitrios on 01-Jan-17.
 */

public class SearchFlightsActivity extends Activity {
    List<Results> myFlightResults1;
    private List<Results> resultsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ResultsAdapter mAdapter;

    MySearchFlight mySearchFlight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_flights);
        initViews();


        mySearchFlight = getMySearchFlightExtra();//get MySearchFlight object from parent Activity

        startFlightsSearch(getMySearchFlightExtra());
    }//onCreate

    private void initViews() {
        recyclerView = (RecyclerView) findViewById(R.id.flightsResultRecuclerView);
        mAdapter = new ResultsAdapter(resultsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }//initViews

    private MySearchFlight getMySearchFlightExtra() {
        return (MySearchFlight) getIntent().getSerializableExtra("mySearchFlight");//get passed intent & get mySearchFlight from intent
    }//getMySearchFlightExtra


    private void startFlightsSearch(MySearchFlight mySearchFlight) {
        URL flightsRequestUrl = NetworkUtils.buildUrlForFlightsSearch(mySearchFlight);//build the url
        getFlightsResponce(flightsRequestUrl.toString());
    }//startFlightsSearch


    public void getFlightsResponce(String flightsRequestUrl) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);//FIFO when a request in added in the queue
        StringRequest request = new StringRequest(Request.Method.GET, flightsRequestUrl, onResultsLoaded, onResultsError);//we instantiate a StringRequest that will perform a GET
        request.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);//send the request and check the response by adding it to the RequestQueue
    }//getAirportsGson

    //volley method
    private Response.Listener<String> onResultsLoaded = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            //GSON
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            ResultsModel newFlightsData = (gson.fromJson(response, ResultsModel.class));
            //Log.v("itineraries count: ",""+newFlightsData.getResults().size());
            //mAdapter.setNewData(newFlightsData.getResults());
            //Log.i("PostActivity", response);
            getAllMarketingAirlineCodes(newFlightsData.getResults());
        }
    };//onPostsLoaded

    //volley method
    private final Response.ErrorListener onResultsError = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(getApplicationContext(), "Could not retrieve any data", Toast.LENGTH_LONG).show();
            Log.e("PostActivity", error.toString());
        }
    };//onPostsError


    private void getAllMarketingAirlineCodes(List<Results> myFlightResults) {
        List<String> airlinesList = new ArrayList<String>();

        myFlightResults1 = myFlightResults;

        for (Results results : myFlightResults) {
            for (Flights flight : results.getItineraries().get(0).getOutbound().getFlights()) {
                if (!airlinesList.contains(flight.getMarketing_airline())) {
                    airlinesList.add(flight.getMarketing_airline());
                }//if
                if (!airlinesList.contains(flight.getOperating_airline())) {
                    airlinesList.add(flight.getOperating_airline());
                }//if
            }//for
            for (Flights flight : results.getItineraries().get(0).getInbound().getFlights()) {
                if (!airlinesList.contains(flight.getMarketing_airline())) {
                    airlinesList.add(flight.getMarketing_airline());
                }//if
                if (!airlinesList.contains(flight.getOperating_airline())) {
                    airlinesList.add(flight.getOperating_airline());
                }//if
            }//for
        }//for


        String airlinesCodes = "";
        for (String airlineCode : airlinesList) {
            airlinesCodes += airlineCode + ",";
        }


        URL airlinesRequestUrl = NetworkUtils.buildUrlForAirlinesSearch(airlinesCodes);//build the url
        getAirlinesResponce(airlinesRequestUrl.toString());


    }//getAllMarketingAirlineCodes

    public void getAirlinesResponce(String airlinesRequestUrl) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);//FIFO when a request in added in the queue
        StringRequest request = new StringRequest(Request.Method.GET, airlinesRequestUrl, onAirlinesLoaded, onAirlinesError);//we instantiate a StringRequest that will perform a GET
        request.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);//send the request and check the response by adding it to the RequestQueue
    }//getAirportsGson

    //volley method
    private Response.Listener<String> onAirlinesLoaded = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            //GSON
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            AirlineModel newAirlineData = (gson.fromJson(response, AirlineModel.class));
            //Log.v("airlines response: ",""+response);


            for (Results results : myFlightResults1) {
                for (Flights flight : results.getItineraries().get(0).getOutbound().getFlights()) {
                    flight.setMarketing_airline(newAirlineData.getAirlineName(flight.getMarketing_airline()));
                    flight.setOperating_airline(newAirlineData.getAirlineName(flight.getOperating_airline()));
                }//for
                for (Flights flight : results.getItineraries().get(0).getInbound().getFlights()) {
                    flight.setMarketing_airline(newAirlineData.getAirlineName(flight.getMarketing_airline()));
                    flight.setOperating_airline(newAirlineData.getAirlineName(flight.getOperating_airline()));
                }//for
            }//for


            mAdapter.setNewData(myFlightResults1);

        }
    };//onPostsLoaded

    //volley method
    private final Response.ErrorListener onAirlinesError = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(getApplicationContext(), "Could not retrieve any data", Toast.LENGTH_LONG).show();
            Log.e("PostActivity", error.toString());
        }
    };//onPostsError

}//SearchFlightsActivity