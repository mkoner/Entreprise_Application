package accounts.repository;

import accounts.domain.Account;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class AccountRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void findByAccountHolder(){
        Account account1 = new Account("123", 500, "Elly");
        entityManager.persist(account1);
        entityManager.flush();
        Account accountFound = accountRepository.findByAccountHolder(account1.getAccountHolder());
        assertEquals(account1, accountFound);
    }


}