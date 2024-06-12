package accounts.repository;

import accounts.domain.Account;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class AccountRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void testFindByAccountHolder(){
        Account account1 = new Account("123", 500, "Elly");
        entityManager.persist(account1);
        entityManager.flush();
        Account accountFound = accountRepository.findByAccountHolder(account1.getAccountHolder());
        assertEquals(account1, accountFound);
    }

    @Test
    public void testFindAccountByBalanceLessThan(){
        Account account1 = new Account("123", 40, "Elly");
        Account account2 = new Account("124", 600, "Bile");
        Account account3 = new Account("125", 490, "Alexa");
        entityManager.persist(account1);
        entityManager.persist(account2);
        entityManager.persist(account3);
        entityManager.flush();
        List<Account> result = accountRepository.findAccountByBalanceLessThan(500);
        List<Account> expectedResult = List.of(account1, account3);
        assertEquals(result, expectedResult);

    }


}