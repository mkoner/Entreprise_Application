package edu.miu.bank.service;

import edu.miu.bank.domain.Account;
import edu.miu.bank.domain.Customer;
import edu.miu.bank.domain.TraceRecord;
import edu.miu.bank.integration.jms.JMSSender;
import edu.miu.bank.integration.logging.Logger;
import edu.miu.bank.repository.AccountRepository;
import edu.miu.bank.repository.TraceRepository;
import edu.miu.bank.service.dto.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Objects;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
	private AccountRepository accountRepository;
	private CurrencyConverter currencyConverter;
	private JMSSender jmsSender;
	private Logger logger;
	@Autowired
	private ApplicationEventPublisher publisher;
	@Autowired
	TraceRepository traceRepository;
	
	public AccountServiceImpl(AccountRepository accountRepository, CurrencyConverter currencyConverter, JMSSender jmsSender, Logger logger) {
		this.accountRepository = accountRepository;
		this.currencyConverter = currencyConverter;
		this.jmsSender = jmsSender;
		this.logger = logger;
	}

	public AccountDTO createAccount(long accountNumber, String customerName) {
		Account account = new Account();
		account.setId(accountNumber);
		Customer customer = new Customer(customerName);
		account.setCustomer(customer);
//		System.out.println("Service, Account: " + account);
		accountRepository.save(account);
		logger.log("createAccount with parameters accountNumber= "+accountNumber+" , customerName= "+customerName);
		return AccountAdapter.getAccountDTOFromAccount(account);
	}

	public void deposit(long accountNumber, double amount) {
		Account account = accountRepository.findById(accountNumber).orElse(null);
		if (account == null) {
			throw new IllegalArgumentException("Account with accountNumber " + accountNumber + " not found.");
		}
//		System.out.println("entries: " + account.getEntryList());
		account.deposit(amount);
		accountRepository.save(account);
		logger.log("deposit with parameters accountNumber= "+accountNumber+" , amount= "+amount);
		if (amount > 10000){
			jmsSender.sendJMSMessage("Deposit of $ "+amount+" to account with accountNumber= "+accountNumber);
		}
		TraceRecord traceRecord = new TraceRecord(accountNumber, "deposit", amount);
		publisher.publishEvent(traceRecord);
		traceRepository.save(traceRecord);
	}

	public AccountDTO getAccount(long accountNumber) {
		Account account = accountRepository.findById(accountNumber).orElse(null);
		return AccountAdapter.getAccountDTOFromAccount(account);
	}

	public Collection<AccountDTO> getAllAccounts() {
		return accountRepository.findAll().stream()
				.map(AccountAdapter::getAccountDTOFromAccount).toList();
	}

	public void withdraw(long accountNumber, double amount) {
		Account account = accountRepository.findById(accountNumber).orElse(null);
		if (Objects.nonNull(account)) {
			account.withdraw(amount);
			accountRepository.save(account);
			logger.log("withdraw accountNumber= "+accountNumber+" , amount= "+amount);
			TraceRecord traceRecord = new TraceRecord(accountNumber, "withdraw", amount);
			publisher.publishEvent(traceRecord);
			traceRepository.save(traceRecord);
		}
	}

	public void depositEuros(long accountNumber, double amount) {
		Account account = accountRepository.findById(accountNumber).orElse(null);
		if (Objects.nonNull(account)) {
			double amountDollars = currencyConverter.euroToDollars(amount);
			account.deposit(amountDollars);
			accountRepository.save(account);
			logger.log("depositEuros with parameters accountNumber= "+accountNumber+" , amount= "+amount);
			if (amountDollars > 10000){
				jmsSender.sendJMSMessage("Deposit of $ "+amount+" to account with accountNumber= "+accountNumber);
			}
			TraceRecord traceRecord = new TraceRecord(accountNumber, "deposit euros", amount);
			publisher.publishEvent(traceRecord);
			traceRepository.save(traceRecord);
		}

	}

	public void withdrawEuros(long accountNumber, double amount) {
		Account account = accountRepository.findById(accountNumber).orElse(null);
		if (Objects.nonNull(account)) {
			double amountDollars = currencyConverter.euroToDollars(amount);
			account.withdraw(amountDollars);
			accountRepository.save(account);
			logger.log("withdrawEuros with parameters accountNumber= "+accountNumber+" , amount= "+amount);
			TraceRecord traceRecord = new TraceRecord(accountNumber, "withdraw euros", amount);
			publisher.publishEvent(traceRecord);
			traceRepository.save(traceRecord);
		}
	}

	public void transferFunds(long fromAccountNumber, long toAccountNumber, double amount, String description) {
		Account fromAccount = accountRepository.findById(fromAccountNumber).orElse(null);
		Account toAccount = accountRepository.findById(toAccountNumber).orElse(null);
		if (Objects.nonNull(fromAccount) && Objects.nonNull(toAccount)) {
			fromAccount.transferFunds(toAccount, amount, description);
			accountRepository.save(fromAccount);
			accountRepository.save(toAccount);
			logger.log("transferFunds with parameters fromAccountNumber= "+fromAccountNumber+" , toAccountNumber= "+toAccountNumber+" , amount= "+amount+" , description= "+description);
			if (amount > 10000){
				jmsSender.sendJMSMessage("TransferFunds of $ "+amount+" from account with accountNumber= "+fromAccount+" to account with accountNumber= "+toAccount);
			}
		}
	}

}
