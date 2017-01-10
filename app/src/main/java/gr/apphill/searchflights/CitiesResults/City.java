package gr.apphill.searchflights.CitiesResults;

/**
 * Created by Dimitrios on 03-Jan-17.
 */

public class City {
    private String code;
    private String coyntry_code;
    private String name;

    public City(String code, String coyntry_code, String name) {
        this.code = code;
        this.coyntry_code = coyntry_code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCoyntry_code() {
        return coyntry_code;
    }

    public void setCoyntry_code(String coyntry_code) {
        this.coyntry_code = coyntry_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}//City
