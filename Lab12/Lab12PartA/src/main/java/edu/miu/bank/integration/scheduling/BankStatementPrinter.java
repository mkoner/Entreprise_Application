package edu.miu.bank.integration.scheduling;

import edu.miu.bank.domain.Account;
import edu.miu.bank.service.AccountService;
import edu.miu.bank.service.dto.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.TimerTask;

@Component
public class BankStatementPrinter extends TimerTask {
    AccountService accountService;
    public BankStatementPrinter(AccountService accountService) {
        this.accountService = accountService;
    }
    @Override
    public void run() {
        accountService.getAllAccounts().forEach(AccountDTO::printStatement);
    }
}
