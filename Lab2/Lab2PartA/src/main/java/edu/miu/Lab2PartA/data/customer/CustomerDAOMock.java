package edu.miu.Lab2PartA.data.customer;

import edu.miu.Lab2PartA.domain.Customer;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Profile("test")
@Repository
public class CustomerDAOMock implements CustomerRepository{
    @Override
    public void save(Customer customer) {
        System.out.println("Customer saved using CustomerDAOMock");
    }
}
