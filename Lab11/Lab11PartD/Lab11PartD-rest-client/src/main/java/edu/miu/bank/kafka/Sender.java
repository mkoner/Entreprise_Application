package edu.miu.bank.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Sender {
    @Autowired
    KafkaTemplate<String, CreateAccountParam> createAccountKafkaTemplate;
    @Autowired
    KafkaTemplate<String, TransactionParam> transactionKafkaTemplate;
    public void send(String topic, CreateAccountParam createAccountParam) {
        createAccountKafkaTemplate.send(topic, createAccountParam);
        System.out.println("Message sent");
    }
    public void send(String topic, TransactionParam transactionParam) {
        transactionKafkaTemplate.send(topic, transactionParam);
        System.out.println("Message sent");
    }

}
