package edu.miu.bank;

import edu.miu.bank.domain.Account;
import edu.miu.bank.domain.AccountEntry;
import edu.miu.bank.domain.Customer;
import edu.miu.bank.integration.scheduling.BankStatementPrinter;
import edu.miu.bank.service.AccountService;
import edu.miu.bank.service.dto.AccountDTO;
import edu.miu.bank.service.dto.AccountEntryDTO;
import edu.miu.bank.service.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.kafka.annotation.EnableKafka;

import java.util.Collection;
import java.util.Timer;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableJms
@EnableKafka
public class Lab2PartCApplication implements CommandLineRunner {

	@Autowired
	private AccountService accountService;

	public static void main(String[] args) {
		SpringApplication.run(Lab2PartCApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// create 2 accounts;
		accountService.createAccount(1263862, "Frank Brown");
		accountService.createAccount(4253892, "John Doe");

		//use account 1;
		accountService.deposit(1263862, 240);
		accountService.deposit(1263862, 529);
		accountService.withdrawEuros(1263862, 230);

		//use account 2;
		accountService.deposit(4253892, 12450);
		accountService.depositEuros(4253892, 200);
		accountService.transferFunds(4253892, 1263862, 100, "payment of invoice 10232");


		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new BankStatementPrinter(accountService), 10000, 20000);

//		// show balances
//		Collection<AccountDTO> accountlist = accountService.getAllAccounts();
//		CustomerDTO customer = null;
//		for (AccountDTO account : accountlist) {
//			customer = account.getCustomer();
//			System.out.println("Statement for Account: " + account.getAccountNumber());
//			System.out.println("Account Holder: " + customer.getName());
//			System.out.println("-Date-------------------------"
//					+ "-Description------------------"
//					+ "-Amount-------------");
//			for (AccountEntryDTO entry : account.getAccountEntries()) {
//				System.out.printf("%30s%30s%20.2f\n", entry.getDate()
//						.toString(), entry.getDescription(), entry.getAmount());
//			}
//			System.out.println("----------------------------------------"
//					+ "----------------------------------------");
//			System.out.printf("%30s%30s%20.2f\n\n", "", "Current Balance:",
//					account.getBalance());
//		}
	}
}
