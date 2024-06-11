package edu.miu.bank.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;


@SpringBootApplication
@EnableKafka
public class Application implements CommandLineRunner {
	@Autowired
	private Sender sender;


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		CreateAccountParam createAccountParam = new CreateAccountParam(9008L, "Alfred Gomis");
//		sender.send("new-account", createAccountParam);
//
//		TransactionParam transactionParam = new TransactionParam(0L, 9008L, 800.0, "New deposit", TransactionType.DEPOSIT);
//		sender.send("new-transaction", transactionParam);

		TransactionParam transfer =new TransactionParam (9080L, 9008L, 500, "Transferred 500", TransactionType.TRANSFER);
		sender.send("new-transaction", transfer);

	}

}
