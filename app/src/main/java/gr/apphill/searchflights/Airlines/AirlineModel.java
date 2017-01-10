package gr.apphill.searchflights.Airlines;


/**
 * Created by Dimitrios on 06-Jan-17.
 */

public class AirlineModel {
    private Object request;
    private Response[] response;

    public Object getRequest() {
        return request;
    }

    public void setRequest(Object request) {
        this.request = request;
    }

    public Response[] getResponse() {
        return response;
    }

    public void setResponse(Response[] response) {
        this.response = response;
    }

    public class Response {
        private String code;
        private String name;

        public Response(String code, String name) {
            this.code = code;
            this.name = name;
        }//Response

        public String getCode() {
            return code;
        }//getCode

        public void setCode(String code) {
            this.code = code;
        }//setCode

        public String getName() {
            return name;
        }//getName

        public void setName(String name) {
            this.name = name;
        }//setName

    }//Response

    public String getAirlineName(String airlineCode){
        for(int i=0;i<response.length;i++){
            if(response[i].getCode().equals(airlineCode)){
                return response[i].getName();
            }
        }
        return "null";
    }//getAirlineName

}//AirlineModel
