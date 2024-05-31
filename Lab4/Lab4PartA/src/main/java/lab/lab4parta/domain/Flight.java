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
    private LocalDate departureDate;
}
