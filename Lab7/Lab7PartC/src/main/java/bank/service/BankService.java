package bank.service;

import bank.domain.Account;
import bank.domain.Customer;
import bank.domain.TraceRecord;
import bank.integration.EmailSender;
import bank.repository.AccountRepository;
import bank.repository.CustomerRepository;
import bank.repository.TraceRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class BankService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private EmailSender emailSender;

	@Autowired
	private TraceRecordRepository traceRecordRepository;
	

	@Transactional
	public void createCustomerAndAccount(int customerId, String customerName, String emailAddress, String AccountNumber){
		//String message = "";
		try {
			Account account = new Account(AccountNumber);
			accountRepository.save(account);
			Customer customer = new Customer(customerId, customerName);
			customer.setAccount(account);
			customerRepository.saveCustomer(customer);
			emailSender.sendEmail(emailAddress, "Welcome "+customerName);
			//message = ("Customer " + customerName + " created with account "+ AccountNumber);
			//saveTraceRecord(message);
		}
		catch (Exception e){
			emailSender.sendEmail(emailAddress, "We could not create your account "+customerName);
			//message = ("Could not create customer " + customerName + " with account "+ AccountNumber);
			//saveTraceRecord(message);
			throw new RuntimeException(e);
		}
		finally {
			System.out.println("finally");
			//saveTraceRecord(message);
		}
	}

	@Transactional(propagation = Propagation.NESTED)
	public void saveTraceRecord(String message) {
		System.out.println("Inside saveTraceRecord");
		TraceRecord traceRecord = new TraceRecord();
		traceRecord.setTimestamp(LocalDateTime.now());
		traceRecord.setMessage(message);
		traceRecordRepository.save(traceRecord);
	}
}
