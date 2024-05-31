package lab.lab4parta;

import lab.lab4parta.domain.*;
import lab.lab4parta.repository.DepartmentRepository;
import lab.lab4parta.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class Lab4PartAApplication implements CommandLineRunner {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private PassengerRepository passengerRepository;

    public static void main(String[] args) {
        SpringApplication.run(Lab4PartAApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Department department = new Department("Sales");
        Employee employee = new Employee("John", department);
        department.add(employee);
        departmentRepository.save(department);

        System.out.println("Fetch department from DB");
        departmentRepository.findAll().forEach(System.out::println);

        Book book = new Book("book", "author");


        Passenger passenger = new Passenger("passenger");
        Flight flight1 = new Flight("flight1", "Fairfield", "Abidjan", LocalDate.now());
        Flight flight2 = new Flight("flight2", "Abidjan", "Soubre", LocalDate.now());
        Flight flight3 = new Flight("flight3", "Abidjan", "New York", LocalDate.now());
        Flight flight4 = new Flight("flight4", "Abidjan", "Bouake", LocalDate.now());
        Flight flight5 = new Flight("flight5", "Abidjan", "New York", LocalDate.now());
        Flight flight6 = new Flight("flight6", "Abidjan", "New York", LocalDate.now());
        passenger.addFlight(flight1);
        passenger.addFlight(flight2);
        passenger.addFlight(flight3);
        passenger.addFlight(flight4);
        passenger.addFlight(flight5);
        passenger.addFlight(flight6);
        Flight flight;
        for(int i=7; i<1500; i++){
            flight = new Flight("flight" + i, "Abidjan", "New York", LocalDate.now());
            passenger.addFlight(flight);
        }
        passengerRepository.save(passenger);

        System.out.println("Fetch passenger from DB");
        passengerRepository.findAll().forEach(p -> p.getFlights().forEach(System.out::println));


    }
}
