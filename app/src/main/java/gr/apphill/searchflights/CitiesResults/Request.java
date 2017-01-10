package gr.apphill.searchflights.CitiesResults;

/**
 * Created by Dimitrios on 03-Jan-17.
 */

public class Request {
    private Client client;

    public Request(Client client) {
        this.client = client;
    }


    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}//Request
