package edu.miu.Lab2PartA.service.customer;

import edu.miu.Lab2PartA.data.customer.CustomerRepository;
import edu.miu.Lab2PartA.domain.Address;
import edu.miu.Lab2PartA.domain.Customer;
import edu.miu.Lab2PartA.integration.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

	CustomerRepository customerRepository;

	EmailSender emailSender;

	@Autowired
	public void setCustomerRepository(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Autowired
	public void setEmailSender(EmailSender emailSender) {
		this.emailSender = emailSender;
	}

	public void addCustomer(String name, String email, String street,
							String city, String zip) {
		Customer customer = new Customer(name, email);
		Address address = new Address(street, city, zip);
		customer.setAddress(address);
		customerRepository.save(customer);
		emailSender.sendEmail(email, "Welcome " + name + " as a new customer");
	}
}
