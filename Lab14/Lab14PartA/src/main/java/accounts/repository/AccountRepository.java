package accounts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import accounts.domain.Account;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, String>{
   Account findByAccountHolder(String accountHolder);
   List<Account> findAccountByBalanceLessThan(double amount);
}
