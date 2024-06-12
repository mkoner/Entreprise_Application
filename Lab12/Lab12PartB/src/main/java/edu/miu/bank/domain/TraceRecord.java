package edu.miu.bank.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "tracerecord")
public class TraceRecord {
    @Id
    @GeneratedValue
    private int id;
    private long accountNumber;
    private String operation;
    private double amount;
    private LocalDateTime dateTime;
    public TraceRecord() {
        this.dateTime = LocalDateTime.now();
    }
    public TraceRecord(long accountNumber, String operation, double amount) {
        this.accountNumber = accountNumber;
        this.operation = operation;
        this.amount = amount;
        this.dateTime = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
