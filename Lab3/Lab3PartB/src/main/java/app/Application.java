package app;

import domain.Book;
import domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repository.BookRepository;
import repository.CustomerRepository;

import java.util.Optional;

@SpringBootApplication
@EnableJpaRepositories("repository")
@EntityScan("domain") 
public class Application implements CommandLineRunner{
	
	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	BookRepository bookRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		customerRepository.save(new Customer("Jack", "Bauer", "jack@acme.com"));
		customerRepository.save(new Customer("Chloe", "O'Brian", "chloe@acme.com"));
		customerRepository.save(new Customer("Kim", "Bauer", "kim@acme.com"));
		customerRepository.save(new Customer("David", "Palmer", "dpalmer@gmail.com"));
		customerRepository.save(new Customer("Michelle", "Dessler", "mich@hotmail.com"));

		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Customer customer : customerRepository.findAll()) {
			System.out.println(customer);
		}
		System.out.println();

		// fetch an individual customer by ID
		Optional<Customer> custOpt = customerRepository.findById(1L);
		Customer customer = custOpt.get();
		System.out.println("Customer found with findOne(1L):");
		System.out.println("--------------------------------");
		System.out.println(customer);
		System.out.println();

		Book book1 = new Book("Title1", "isbn1", "Author1", 2000);
		Book book2 = new Book("Title2", "isbn2", "Author2", 2000);
		Book book3 = new Book("Title3", "isbn3", "Author3", 2000);
		bookRepository.save(book1);
		bookRepository.save(book2);
		bookRepository.save(book3);
		System.out.println("Print All Books:");
		System.out.println("-------------------------------");
		bookRepository.findAll().forEach(System.out::println);
		System.out.println();
		book1.setAuthor("Updated Author");
		System.out.println("Update Book: " + book1);
		bookRepository.save(book1);
		System.out.println("Delete Book: " + book2);
		bookRepository.delete(book2);
		System.out.println("Print All Books:");
		System.out.println("-------------------------------");
		bookRepository.findAll().forEach(System.out::println);
		System.out.println();


	}

}
