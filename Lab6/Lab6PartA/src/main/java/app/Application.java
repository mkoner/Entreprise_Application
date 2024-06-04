package app;

import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repository.*;

@SpringBootApplication
@EnableJpaRepositories("repository")
@EntityScan("domain") 
public class Application implements CommandLineRunner{
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CDRepository cdRepository;
	@Autowired
	AddressRepository addressRepository;
	

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Product product = new Book("Hibernate 3", "Good book on Hibernate", 35.50, "123");
//		product.setName("Hibernate 3");
//		product.setDescription("Good book on Hibernate");
//		product.setPrice(35.50);
		OrderLine ol1 = new OrderLine(2, product);

		Product product2 = new CD("The best of Queen", "Album from 1995", 12.98, "Queen");
//		product2.setName("The best of Queen");
//		product2.setDescription("Album from 1995");
//		product2.setPrice(12.98);
		OrderLine ol2 = new OrderLine(4, product2);

		Order o1 = new Order("234743", "12/10/06", "closed");
		o1.addOrderLine(ol1);
		o1.addOrderLine(ol2);

		Customer c1 = new Customer("Frank", "Brown", "Mainstreet 1",
				"New york", "43221", "USA");
		Customer c2 = new Customer("Ben", "Badi", "Mainstreet 1",
				"Abidjan", "43221", "Cote d'Ivoire");
		Customer c3 = new Customer("Anna", "Brown", "Mainstreet 1",
				"New york", "43221", "USA");
		c1.addOrder(o1);
		o1.setCustomer(c1);
		customerRepository.save(c2);
		customerRepository.save(c3);


		printOrder(o1);

		orderRepository.save(o1);

		orderRepository.findAll().forEach(Application::printOrder);


		System.out.println("All customer");
		customerRepository.findAll().forEach(System.out::println);

		Product cd1 = new CD("The best of U2", "Album from 1995", 12.98, "U2");
		Product cd2 = new CD("OK", "Album from 1995", 5.98, "U2");
		Product cd3 = new CD("OKAY", "Album from 1995", 1.98, "U2");
		productRepository.save(cd1);
		productRepository.save(cd2);
		productRepository.save(cd3);
		System.out.println("Give all CD’s from U2 with a price smaller than 10 euro ");
		cdRepository.findByArtistAndPriceLessThan("U2", 10.0).forEach(System.out::println);

		System.out.println("Get all customer from USA using named query");
		customerRepository.getByCountry("USA").forEach(System.out::println);

		System.out.println("Get all CD’s from U2 with using named query");
		cdRepository.findByArtist("U2").forEach(System.out::println);

		System.out.println("Closed orders using JPQL Query");
		orderRepository.listOfOrderNumbersByStatus("closed").forEach(System.out::println);

		System.out.println("Get the first and lastnames of all customers who live in New York ");
		customerRepository.getCustomerByCity("New york").forEach(System.out::println);

		System.out.println("Number of Orders in new york" + orderRepository.countByCity("New york"));
		System.out.println("Order numbers: " + orderRepository.orderNumbersByCity("New york"));

		System.out.println("Get all CD’s from U2 with a price smaller than 10 euro using JPQL Query");
		cdRepository.findByArtistAndPriceLessThanUsingQuery("U2", 10.0).forEach(System.out::println);

		System.out.println("Get all address in new york");
		addressRepository.getAddressByCity("New york").forEach(System.out::println);
		System.out.println("All CD of U2 using native query");
		cdRepository.findByArtistUsingNativeQuery("U2").forEach(System.out::println);
	}

	public static void printOrder(Order order) {
		System.out.println("Order with orderNumber: " + order.getOrderNumber());
		System.out.println("Order date: " + order.getDate());
		System.out.println("Order status: " + order.getStatus());
		Customer cust = order.getCustomer();
		System.out.println("Customer: " + cust.getFirstName() + " "
				+ cust.getLastName());
		for (OrderLine orderline : order.getOrderLines()) {
			System.out.println("Order line: quantity= "
					+ orderline.getQuantity());
			Product product = orderline.getProduct();
			System.out.println("Product: " + product.getName() + " "
					+ product.getDescription() + " " + product.getPrice());
		}
	}
}
