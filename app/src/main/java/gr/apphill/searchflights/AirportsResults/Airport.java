package gr.apphill.searchflights.AirportsResults;

/**
 * Created by Dimitrios on 30-Dec-16.
 */

public class Airport {
    private String value;
    private String label;
    private String image;

    public Airport(String value, String label){
        setValue(value);
        setLabel(label);
        setImage(label);
    }//Airport

    public String getValue() {
        return value;
    }//getValue

    public void setValue(String value) {
        this.value = value;
    }//setValue

    public String getLabel() {
        return label;
    }//getLabel

    public void setLabel(String label) {
        this.label = label;
    }//setLabel

    public String getImage() {
        return image;
    }//getImage

    public void setImage(String label) {
        if(isAirportAsSubstring(label)){
            this.image = "airport";
        } else {
            this.image = "location";
        }
    }//setImage

    private boolean isAirportAsSubstring(String label) {//check if label string contans airport substring, in that case with have an airport or else with have a location(with more than one airports)
        String airportSubstring = "airport";
        return label.toLowerCase().contains(airportSubstring.toLowerCase());
    }//isAirportAsSubstring

}//Airport
