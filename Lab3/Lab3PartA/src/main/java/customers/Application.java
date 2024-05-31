package customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		customerRepository.clearDB();
		productRepository.clearDB();
		Customer customer = new Customer(101,"John doe", "johnd@acme.com", "0622341678");
		CreditCard creditCard = new CreditCard("12324564321", "Visa", "11/23");
		customer.setCreditCard(creditCard);
		customerRepository.save(customer);
		customer = new Customer(66,"James Johnson", "jj123@acme.com", "068633452");
		creditCard = new CreditCard("99876549876", "MasterCard", "01/24");
		customer.setCreditCard(creditCard);
		customerRepository.save(customer);
		System.out.println(customerRepository.getCustomer(101));
		System.out.println(customerRepository.getCustomer(66));
		System.out.println("-----------All customers ----------------");
		System.out.println(customerRepository.getAllCustomers());

		Product product1 = new Product(111, "Product1", 20);
		Product product2 = new Product(222, "Product2", 30);
		Product product3 = new Product(333, "Product3", 40);

		productRepository.save(product1);
		productRepository.save(product2);
		productRepository.save(product3);

		System.out.println("-----------All products --------------");
		productRepository.findAll().forEach(System.out::println);
		System.out.println("--------------------------------------");

		System.out.println("Find product by name Product2");
		System.out.println(productRepository.findProductByName("Product2"));

		System.out.println();
		System.out.println("Delete " + product2);
		productRepository.removeProduct(222);

		System.out.println("-----------All products ---------------");
		productRepository.findAll().forEach(System.out::println);
		System.out.println("--------------------------------------");
	}

}
