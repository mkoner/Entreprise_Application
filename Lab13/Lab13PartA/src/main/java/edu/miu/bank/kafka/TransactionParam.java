package edu.miu.bank.kafka;

public class TransactionParam {
    private Long from;
    private Long to;
    private double amount;
    private String description;
    private TransactionType type;
    public TransactionParam() {}
    public TransactionParam(Long from, Long to, double amount, String description, TransactionType type) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.description = description;
        this.type = type;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Long getFrom() {
        return from;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public Long getTo() {
        return to;
    }

    public void setTo(Long to) {
        this.to = to;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
