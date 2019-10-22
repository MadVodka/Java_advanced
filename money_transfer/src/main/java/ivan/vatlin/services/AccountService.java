package ivan.vatlin.services;

import ivan.vatlin.dto.Account;
import ivan.vatlin.repo.InitialAccountRepository;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

public class AccountService {
    private InitialAccountRepository initialAccountRepository = InitialAccountRepository.getInstance();

    public boolean addAccounts(List<Account> accounts) {
        return initialAccountRepository.addAccounts(accounts);
    }

    public List<Account> getAll() {
        return initialAccountRepository.getAll();
    }

    public Account getById(long id) {
        try {
            return initialAccountRepository.getById(id);
        } catch (AccountNotFoundException e) {
            return null;
        }
    }

    public int getNumberOfAccounts() {
        return getAll().size();
    }

    public long getTotalMoneySumOfAccounts() {
        return initialAccountRepository.getAll().stream()
                .mapToLong(Account::getBalance)
                .sum();
    }

}
