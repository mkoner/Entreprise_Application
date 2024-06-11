package edu.miu.bank.service;

import edu.miu.bank.domain.Account;
import edu.miu.bank.service.dto.AccountDTO;

import java.util.Collection;

public interface AccountService {

    public AccountDTO createAccount(long accountNumber, String customerName);

    public AccountDTO getAccount(long accountNumber);

    public Collection<AccountDTO> getAllAccounts();

    public void deposit (long accountNumber, double amount);

    public void withdraw (long accountNumber, double amount);

    public void depositEuros (long accountNumber, double amount);

    public void withdrawEuros (long accountNumber, double amount);

    public void transferFunds(long fromAccountNumber, long toAccountNumber, double amount, String description);

}
