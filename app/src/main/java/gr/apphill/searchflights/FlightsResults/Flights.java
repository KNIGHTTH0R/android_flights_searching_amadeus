package gr.apphill.searchflights.FlightsResults;

import java.util.Date;

/**
 * Created by Dimitrios on 02-Jan-17.
 */

public class Flights {
    private String departs_at;
    private String arrives_at;
    private Origin origin;
    private Destination destination;
    private String marketing_airline;
    private String operating_airline;
    private String flight_number;
    private String aircraft;
    private Booking_info booking_info;
    private String departureDate,departureTime,arriveDate,arriveTime;


    public Flights(String departs_at, String arrives_at, Origin origin, Destination destination, String marketing_airline, String operating_airline, String flight_number, String aircraft, Booking_info booking_info) {
        this.departs_at = departs_at;
        setDepartureDateTimeFormat(this.departs_at);
        this.arrives_at = arrives_at;
        setArriveDateTimeFormat(this.arrives_at);
        this.origin = origin;
        this.destination = destination;
        this.marketing_airline = marketing_airline;
        this.operating_airline = operating_airline;
        this.flight_number = flight_number;
        this.aircraft = aircraft;
        this.booking_info = booking_info;
    }//Flights

    private void setDepartureDateTimeFormat(String departs_at){
        String[] separateDateTime = departs_at.split("T");
    }//setDepartureDateTimeFormat

    private void setArriveDateTimeFormat(String arrives_at){
        String[] separateDateTime = arrives_at.split("T");
    }//setDepartureDateTimeFormat

    private void setDepartureDate(String departureDate){
        this.departureDate = departureDate;
    }//getDepartureDate

    private void setDepartureTimes(String departureTime){
        this.departureTime = departureTime;
    }//getDepartureTime

    private void setArriveDate(String arriveDate){
        this.arriveDate = arriveDate;
    }//setArriveDate

    private void setArriveTime(String arriveTime){
        this.arriveTime = arriveTime;
    }//setArriveTime

    private String getDepartureDate(){
        return this.departureDate;
    }//getDepartureDate

    private String getDepartureTime(){
        return this.departureTime;
    }//getDepartureTime

    private String getArriveDate(){
        return this.arriveDate;
    }//getDepartureDate

    private String getArriveTime(){
        return this.arriveTime;
    }//getDepartureDate


    public String getDeparts_at() {
        return departs_at;
    }

    public void setDeparts_at(String departs_at) {
        this.departs_at = departs_at;
    }

    public String getArrives_at() {
        return arrives_at;
    }

    public void setArrives_at(String arrives_at) {
        this.arrives_at = arrives_at;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public String getMarketing_airline() {
        return marketing_airline;
    }

    public void setMarketing_airline(String marketing_airline) {
        this.marketing_airline = marketing_airline;
    }

    public String getOperating_airline() {
        return operating_airline;
    }

    public void setOperating_airline(String operating_airline) {
        this.operating_airline = operating_airline;
    }

    public String getFlight_number() {
        return flight_number;
    }

    public void setFlight_number(String flight_number) {
        this.flight_number = flight_number;
    }

    public String getAircraft() {
        return aircraft;
    }

    public void setAircraft(String aircraft) {
        this.aircraft = aircraft;
    }

    public Booking_info getBooking_info() {
        return booking_info;
    }

    public void setBooking_info(Booking_info booking_info) {
        this.booking_info = booking_info;
    }

    public class Origin {
        private String airport;

        public Origin(String airport) {
            this.airport = airport;
        }

        public String getAirport() {
            return airport;
        }

        public void setAirport(String airport) {
            this.airport = airport;
        }
    }//Origin

    public class Destination {
        private String airport;

        public Destination(String airport) {
            this.airport = airport;
        }

        public String getAirport() {
            return airport;
        }

        public void setAirport(String airport) {
            this.airport = airport;
        }
    }//Destination

    public class Booking_info {
        private String travel_class;
        private String booking_code;
        private int seats_remaining;

        public Booking_info(String travel_class, String booking_code, int seats_remaining) {
            this.travel_class = travel_class;
            this.booking_code = booking_code;
            this.seats_remaining = seats_remaining;
        }

        public String getTravel_class() {
            return travel_class;
        }

        public void setTravel_class(String travel_class) {
            this.travel_class = travel_class;
        }

        public String getBooking_code() {
            return booking_code;
        }

        public void setBooking_code(String booking_code) {
            this.booking_code = booking_code;
        }

        public int getSeats_remaining() {
            return seats_remaining;
        }

        public void setSeats_remaining(int seats_remaining) {
            this.seats_remaining = seats_remaining;
        }
    }//booking_info



}//Flights
