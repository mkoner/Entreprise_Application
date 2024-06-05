package edu.miu.bank.service;

import edu.miu.bank.domain.Account;
import edu.miu.bank.domain.AccountEntry;
import edu.miu.bank.domain.Customer;
import edu.miu.bank.service.dto.AccountDTO;
import edu.miu.bank.service.dto.AccountEntryDTO;
import edu.miu.bank.service.dto.CustomerDTO;

public class AccountAdapter {
    public static Account getAccountFromDTO(AccountDTO accountDTO) {
        Account account = new Account();
        account.setAccountNumber((accountDTO.getAccountNumber()));
        account.setCustomer(getCustomerFromDTO(accountDTO.getCustomer()));
        account.setEntryList(accountDTO.getAccountEntries().stream().map(AccountAdapter::getAccountEntryFromDTO).toList());
        return account;
    }

    public static AccountDTO getAccountDTOFromAccount(Account account) {
        return new AccountDTO(account.getAccountNumber(),
                getCustomerDTOFromCustomer(account.getCustomer()),
                account.getEntryList().stream().map(AccountAdapter::getAccountEntryDTOFromAccountEntry).toList());
    }

    private static AccountEntryDTO getAccountEntryDTOFromAccountEntry(AccountEntry accountEntry) {
        return new AccountEntryDTO(accountEntry.getId(), accountEntry.getDate(),
                accountEntry.getAmount(), accountEntry.getDescription(),
                accountEntry.getFromAccountNumber(), accountEntry.getFromPersonName()
                );
    }

    private static CustomerDTO getCustomerDTOFromCustomer(Customer customer) {
        return new CustomerDTO(customer.getId(), customer.getName());
    }

    private static AccountEntry getAccountEntryFromDTO(AccountEntryDTO accountEntryDTO) {
        AccountEntry accountEntry = new AccountEntry(accountEntryDTO.getDate(), accountEntryDTO.getAmount(),
                accountEntryDTO.getDescription(), accountEntryDTO.getFromAccountNumber(), accountEntryDTO.getFromPersonName());
        accountEntry.setId(accountEntry.getId());
        return accountEntry;
    }

    private static Customer getCustomerFromDTO(CustomerDTO customerDTO) {
        Customer customer= new Customer(customerDTO.getName());
        customer.setId(customerDTO.getId());
        return customer;
    }
}
