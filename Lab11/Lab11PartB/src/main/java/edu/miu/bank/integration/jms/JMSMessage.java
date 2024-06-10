package edu.miu.bank.integration.jms;

import java.time.LocalDateTime;

public class JMSMessage {
    private LocalDateTime timestamp;
    private String message;
    public JMSMessage(String message) {
        timestamp = LocalDateTime.now();
        this.message = message;
    }

    @Override
    public String toString() {
        return "JMSMessage{" +
                "timestamp=" + timestamp +
                ", message='" + message + '\'' +
                '}';
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
