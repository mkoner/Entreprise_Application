package client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;

@SpringBootApplication
public class Application implements CommandLineRunner {

	private RestTemplate restTemplate = new RestTemplate();

	private String serverUrl = "http://localhost:8080/accounts";

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		//Create account
		CreateAccountParam createAccountParam = new CreateAccountParam();
		createAccountParam.setAccountNumber(9080L);
		createAccountParam.setCustomerName("John");
		AccountDTO createdAccount = restTemplate.postForEntity(serverUrl, createAccountParam, AccountDTO.class).getBody();

		//deposit
		restTemplate.put(serverUrl+ "/" + createdAccount.getAccountNumber() + "/deposit", 1000.0);

		Collection<AccountDTO> accounts = Arrays.stream(restTemplate.getForObject(serverUrl, AccountDTO[].class)).toList();
		accounts.forEach(System.out::println);


//		// get frank
//		Contact contact= restTemplate.getForObject(serverUrl+"/{firstName}", Contact.class, "Frank");
//		System.out.println(contact);
//
//		// add John
//		restTemplate.postForLocation(serverUrl, new Contact("John","Doe", "jdoe@acme.com", "6739127563"));
//
//		// get john
//		contact= restTemplate.getForObject(serverUrl+"/{firstName}", Contact.class, "John");
//		System.out.println(contact);
//
//		// delete mary
//		restTemplate.delete(serverUrl+"/{firstName}", "Mary");
//
//		// update John
//		contact.setEmail("johndoe@acme.com");
//		restTemplate.put(serverUrl+"/{firstName}" , contact, "John");
//
//		// get john
//		contact= restTemplate.getForObject(serverUrl+"/{firstName}", Contact.class, "John");
//		System.out.println(contact);
//
//        // get all contacts
//		Contacts contacts = restTemplate.getForObject(serverUrl, Contacts.class);
//		System.out.println(contacts);
	}

}
