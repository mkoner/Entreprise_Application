package lab.lab4parta.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Passenger {
    @Id
    private int id;
    private String name;
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @OrderColumn(name="sequence")
    private List<Flight> flights;
    public Passenger() {}
    public Passenger(String name) {
        this.name = name;
        flights = new ArrayList<Flight>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }
    public void addFlight(Flight flight) {
        flights.add(flight);
    }
}
