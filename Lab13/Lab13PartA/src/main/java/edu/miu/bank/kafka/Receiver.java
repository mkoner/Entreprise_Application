package edu.miu.bank.kafka;

import edu.miu.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class Receiver {
    @Autowired
    AccountService accountService;
    @KafkaListener(topics = {"new-account"})
    public void newAccountReceiver(@Payload CreateAccountParam param) {
        System.out.println("new account received: " + param);
        accountService.createAccount(param.getAccountNumber(), param.getCustomerName());
    }
    @KafkaListener(topics = {"new-transaction"})
    public void newTransactionReceiver(@Payload TransactionParam param) {
        System.out.println("new transaction received: " + param);
        switch (param.getType()) {
            case DEPOSIT -> accountService.deposit(param.getTo(), param.getAmount());
            case WITHDRAW -> accountService.withdraw(param.getTo(), param.getAmount());
            case TRANSFER -> accountService.transferFunds(param.getFrom(), param.getTo(),
                    param.getAmount(), param.getDescription());
        }
    }

}
