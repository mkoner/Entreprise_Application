package repository;

import domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("select o.orderNumber from Order o where o.status = :status")
    List<String> listOfOrderNumbersByStatus(String status);
    @Query("select count(o) from Order o where o.customer.address.city = :city")
    int countByCity(String city);
    @Query("select o.orderNumber from Order o where o.customer.address.city = :city")
    List<String> orderNumbersByCity(String city);
}
