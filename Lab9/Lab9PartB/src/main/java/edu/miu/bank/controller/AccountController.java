package edu.miu.bank.controller;

import edu.miu.bank.service.AccountService;
import edu.miu.bank.service.dto.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public ResponseEntity<?> getAllAccounts() {
        return new ResponseEntity<Collection<AccountDTO>>(accountService.getAllAccounts(),
                HttpStatus.OK);
    }
    @GetMapping("/{accountNumber}")
    public ResponseEntity<?> getAccount(@PathVariable Long accountNumber) {
        AccountDTO account = accountService.getAccount(accountNumber);
        if (account == null) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<AccountDTO>(account, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody CreateAccountParam param) {
        AccountDTO account = accountService.createAccount(param.getAccountNumber(),
                param.getCustomerName());
        return new ResponseEntity<AccountDTO>(account, HttpStatus.CREATED);
    }
    @PutMapping("/{accountNumber}/deposit")
    public ResponseEntity<?> deposit(Long accountNumber, @RequestBody double amount){
        accountService.deposit(accountNumber, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/{accountNumber}/withdraw")
    public ResponseEntity<?> withdraw(Long accountNumber, @RequestBody double amount){
        accountService.withdraw(accountNumber, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/{accountNumber}/deposit-euros")
    public ResponseEntity<?> depositEuros(Long accountNumber, @RequestBody double amount){
        accountService.depositEuros(accountNumber, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/{accountNumber}/withdraw-euros")
    public ResponseEntity<?> withdrawEuros(Long accountNumber, @RequestBody double amount){
        accountService.withdrawEuros(accountNumber, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestBody TransferParam param){
        accountService.transferFunds(param.getFrom(), param.getTo(), param.getAmount(),
                param.getDescription());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
