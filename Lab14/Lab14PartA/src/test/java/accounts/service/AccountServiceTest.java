package accounts.service;

import accounts.domain.Account;
import accounts.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @Test
    void testGetAccountFound() {
        String accountNumber = "123";
        Account account = new Account(accountNumber, 800, "John");
        Optional<Account> optionalAccount = Optional.of(account);
        AccountResponse expected = AccountAdapter.getAccountResponse(account);
        Mockito.when(accountRepository.findById(accountNumber)).thenReturn(optionalAccount);
        AccountResponse result = accountService.getAccount(accountNumber);
        assertEquals(result.getAccountNumber(), expected.getAccountNumber());
    }

    @Test
    void testGetAccountNotFound() {
        String accountNumber = "123";
        Optional<Account> optionalAccount = Optional.empty();
        Mockito.when(accountRepository.findById(accountNumber)).thenReturn(optionalAccount);
        AccountResponse result = accountService.getAccount(accountNumber);
        assertNull(result);
    }

    @Test
    void testCreateAccount() {
        accountService.createAccount("123", 1000.0, "Anna");
        Mockito.verify(accountRepository, Mockito.times(1))
                .save(new Account("123", 1000.0, "Anna"));
    }
}