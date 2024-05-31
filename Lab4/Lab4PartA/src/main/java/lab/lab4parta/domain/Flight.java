package lab.lab4parta.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Flight {
    @Id
    @GeneratedValue
    private int id;
    private String flightNumber;
    @Column(name = "from_departure")
    private String from;
    @Column(name = "to_destination")
    private String to;
    private LocalDate date;
    public Flight() {}
    public Flight(String flightNumber, String from, String to, LocalDate departureDate) {
        this.flightNumber = flightNumber;
        this.from = from;
        this.to = to;
        this.date = departureDate;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", flightNumber='" + flightNumber + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", date=" + date +
                '}';
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
