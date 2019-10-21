package ivan.vatlin.repo;

import ivan.vatlin.dto.Account;

import javax.security.auth.login.AccountNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class InitialAccountRepository {
    private static InitialAccountRepository initialAccountRepository;
    private List<Account> accounts = new ArrayList<>();

    private InitialAccountRepository() {
    }

    public static InitialAccountRepository getInstance() {
        if (initialAccountRepository == null) {
            initialAccountRepository = new InitialAccountRepository();
        }
        return initialAccountRepository;
    }

    public boolean addAccount(Account account) {
        return accounts.add(account);
    }

    public boolean addAccounts(List<Account> accounts) {
        return this.accounts.addAll(accounts);
    }

    public List<Account> getAll() {
        return accounts;
    }

    public Account getById(long id) throws AccountNotFoundException {
        return accounts.stream()
                .filter(account -> account.getId() == id)
                .findFirst()
                .orElseThrow(AccountNotFoundException::new);
    }
}
