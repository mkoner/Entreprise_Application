package edu.miu.bank.service.dto;

import edu.miu.bank.domain.AccountEntry;

import java.util.Collection;

public class AccountDTO {
    private long accountNumber;
    private CustomerDTO customer;
    Collection<AccountEntryDTO> accountEntries;
    public AccountDTO(long accountNumber, CustomerDTO customer, Collection<AccountEntryDTO> accountEntries) {
        this.accountNumber = accountNumber;
        this.customer = customer;
        this.accountEntries = accountEntries;
    }

    public double getBalance() {
        double balance=0;
        for (AccountEntryDTO entry : accountEntries) {
            balance+=entry.getAmount();
        }
        return balance;
    }
    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public Collection<AccountEntryDTO> getAccountEntries() {
        return accountEntries;
    }

    public void setAccountEntries(Collection<AccountEntryDTO> accountEntries) {
        this.accountEntries = accountEntries;
    }

    @Override
    public String toString() {
        return "AccountDTO{" +
                "accountNumber=" + accountNumber +
                ", customer=" + customer +
                ", accountEntries=" + accountEntries +
                '}';
    }

    public void printStatement(){
        System.out.println("Statement for Account: " + getAccountNumber());
        System.out.println("Account Holder: " + customer.getName());
        System.out.println("-Date-------------------------"
                + "-Description------------------"
                + "-Amount-------------");
        for (AccountEntryDTO entry : getAccountEntries()) {
            System.out.printf("%30s%30s%20.2f\n", entry.getDate()
                    .toString(), entry.getDescription(), entry.getAmount());
        }
        System.out.println("----------------------------------------"
                + "----------------------------------------");
        System.out.printf("%30s%30s%20.2f\n\n", "", "Current Balance:",
                getBalance());
    }
}
