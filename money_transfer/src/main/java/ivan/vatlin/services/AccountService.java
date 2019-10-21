package ivan.vatlin.services;

import ivan.vatlin.dto.Account;
import ivan.vatlin.repo.InitialAccountRepository;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

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

    public long getTotalMoneySum() {
        return initialAccountRepository.getAll().stream()
                .mapToLong(Account::getBalance)
                .sum();
    }

    public void transferMoney(Account accountFrom, Account accountTo, long amountMoney) {
        long accountFromBalance = accountFrom.getBalance();
        long accountToBalance = accountTo.getBalance();
        accountFrom.setBalance(accountFromBalance - amountMoney);
        accountTo.setBalance(accountToBalance + amountMoney);
    }
}
