package edu.miu.bank.kafka;

public class CreateAccountParam {
    private Long accountNumber;
    private String customerName;
    public CreateAccountParam() {}
    public CreateAccountParam(Long accountNumber, String customerName) {
        this.accountNumber = accountNumber;
        this.customerName = customerName;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
