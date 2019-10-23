package ivan.vatlin.services;

import ivan.vatlin.dto.Account;
import ivan.vatlin.repo.AccountRepository;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

public class AccountService {
    private AccountRepository accountRepository = AccountRepository.getInstance();

    public boolean addAccounts(List<Account> accounts) {
        return accountRepository.addAccounts(accounts);
    }

    public List<Account> getAll() {
        return accountRepository.getAll();
    }

    public Account getById(long id) {
        try {
            return accountRepository.getById(id);
        } catch (AccountNotFoundException e) {
            return null;
        }
    }

    public int getNumberOfAccounts() {
        return getAll().size();
    }

    public long getTotalMoneySumOfAccounts() {
        return accountRepository.getAll().stream()
                .mapToLong(Account::getBalance)
                .sum();
    }

}
