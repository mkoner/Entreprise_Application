package lab.lab4parta.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Passenger {
    @Id
    private int id;
    private String name;
    @OneToMany
    private List<Flight> flights;
}
