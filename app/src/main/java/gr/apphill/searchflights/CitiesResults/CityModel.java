package gr.apphill.searchflights.CitiesResults;

/**
 * Created by Dimitrios on 03-Jan-17.
 */

public class CityModel {
    private Request request;
    private City[] response;


    public CityModel(Request request, City[] response) {
        this.request = request;
        this.response = response;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public City[] getResponse() {
        return response;
    }

    public void setResponse(City[] response) {
        this.response = response;
    }
}//CitiesModel
