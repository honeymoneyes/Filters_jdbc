package model;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Ticket {
    private BigInteger id;
    private String passenger_no;
    private String passenger_name;
    private BigInteger flight_id;
    private String seat_no;
    private BigDecimal cost;

    public Ticket(BigInteger id, String passenger_no, String passenger_name, BigInteger flight_id, String seat_no, BigDecimal cost) {
        this.id = id;
        this.passenger_no = passenger_no;
        this.passenger_name = passenger_name;
        this.flight_id = flight_id;
        this.seat_no = seat_no;
        this.cost = cost;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getPassenger_no() {
        return passenger_no;
    }

    public void setPassenger_no(String passenger_no) {
        this.passenger_no = passenger_no;
    }

    public String getPassenger_name() {
        return passenger_name;
    }

    public void setPassenger_name(String passenger_name) {
        this.passenger_name = passenger_name;
    }

    public BigInteger getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(BigInteger flight_id) {
        this.flight_id = flight_id;
    }

    public String getSeat_no() {
        return seat_no;
    }

    public void setSeat_no(String seat_no) {
        this.seat_no = seat_no;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Ticket{" +
               "id=" + id +
               ", passenger_no='" + passenger_no + '\'' +
               ", passenger_name='" + passenger_name + '\'' +
               ", flight_id=" + flight_id +
               ", seat_no='" + seat_no + '\'' +
               ", cost=" + cost +
               '}';
    }
}
