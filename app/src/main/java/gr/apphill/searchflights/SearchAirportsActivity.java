package gr.apphill.searchflights;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

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
import java.util.Arrays;
import java.util.List;

import gr.apphill.searchflights.AirportsResults.Airport;
import gr.apphill.searchflights.AirportsResults.AirportsArrayAdapter;
import gr.apphill.searchflights.R;
import gr.apphill.searchflights.utilities.NetworkUtils;

/**
 * Created by Dimitrios on 29-Dec-16.
 */

public class SearchAirportsActivity extends Activity {//LoaderManager Manages your Loaders for you. Responsible for dealing with the Activity or Fragment lifecycle
    SearchView airports_searchView;
    AirportsArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_airports);

        Bundle extras = getIntent().getExtras();
        final String newString = extras.getString("chooseAirport");

        final ListView listview = (ListView) findViewById(R.id.listview);
        adapter = new AirportsArrayAdapter(this, new ArrayList<Airport>());
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Airport airport = adapter.getItem(i);
                Intent intent = new Intent();
                intent.putExtra("airportLabel", airport.getLabel());
                intent.putExtra("airportValue", airport.getValue());
                if (newString.equals("departure")) {
                    setResult(1, intent);
                } else {
                    setResult(2, intent);
                }
                finish();
            }//onItemClick
        });

        setSearchView(newString);

    }//onCreate

    private void setSearchView(String newString){
        airports_searchView = (SearchView) findViewById(R.id.airports_searchView);
        if (newString.equals("departure")) {
            airports_searchView.setQueryHint("Search for departure location");
        } else {
            airports_searchView.setQueryHint("Search for destination location");
        }

        airports_searchView.setIconified(false);

        airports_searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                airports_searchView.setIconified(false);
            }
        });

        //*** setOnQueryTextListener ***
        airports_searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }//onQueryTextSubmit

            @Override
            public boolean onQueryTextChange(String query) {
                if (query.length() > 2) {
                    startAirportsSearch(query);
                } else if (query.length() == 0) {
                    adapter.clear();
                }

                return false;
            }//onQueryTextChange
        });
    }//setSearchView

    private void startAirportsSearch(String query) {
        URL airportsRequestUrl = NetworkUtils.buildUrlForAirportsSearch(query.toString());//build the url
        getAirportsResponce(airportsRequestUrl.toString());
    }//startSearchAirportLoader


    public void getAirportsResponce(String airportsRequestUrl) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);//FIFO when a request in added in the queue
        StringRequest request = new StringRequest(Request.Method.GET, airportsRequestUrl, onAirportsLoaded, onAirportsError);//we instantiate a StringRequest that will perform a GET
        requestQueue.add(request);//send the request and check the response by adding it to the RequestQueue
    }//getAirportsGson

    //volley method
    private Response.Listener<String> onAirportsLoaded = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            //GSON
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            List<Airport> newAirportsData = Arrays.asList(gson.fromJson(response, Airport[].class));
            adapter.clear();
            adapter.setNewData(newAirportsData);
            //Log.i("PostActivity", response);
        }
    };//onPostsLoaded

    //volley method
    private final Response.ErrorListener onAirportsError = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            adapter.clear();
            Toast.makeText(getApplicationContext(), "Could not retrieve any data", Toast.LENGTH_LONG).show();
            //Log.e("PostActivity", error.toString());
        }
    };//onPostsError


}//SearchAirportsActivity