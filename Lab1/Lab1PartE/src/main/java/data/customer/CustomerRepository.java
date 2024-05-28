package data.customer;

import domain.Customer;
import org.springframework.stereotype.Repository;

public interface CustomerRepository {

	void save(Customer customer) ;

}
