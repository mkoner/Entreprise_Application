package repository;

import domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    @Query(value = "SELECT * FROM address WHERE CITY = :city", nativeQuery = true)
    List<Address> getAddressByCity(String city);
}
