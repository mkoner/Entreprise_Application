package repository;

import domain.Address;
import domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> getByCountry(String country);
    @Query("select concat(c.firstName, ' ', c.lastName) from Customer c where c.address.city = :city")
    List<String> getCustomerByCity(String city);
}
