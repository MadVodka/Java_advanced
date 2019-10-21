package ivan.vatlin.services;

import ivan.vatlin.dto.Account;

public class AccountTransferService {
    private AccountService accountService = new AccountService();

    public void transferMoney(long from, long to, long moneyAmount) {

        if (from < to) {
            Account fromAccount = accountService.getById(from);
            fromAccount.lockObject();

            Account toAccount = accountService.getById(to);
            toAccount.lockObject();

            accountService.transferMoney(fromAccount, toAccount, moneyAmount);
            toAccount.unlockObject();
            fromAccount.unlockObject();
        } else if(to<from) {
            Account toAccount = accountService.getById(to);
            toAccount.lockObject();

            Account fromAccount = accountService.getById(from);
            fromAccount.lockObject();

            accountService.transferMoney(fromAccount, toAccount, moneyAmount);
            toAccount.unlockObject();
            fromAccount.unlockObject();
        }
    }
}
