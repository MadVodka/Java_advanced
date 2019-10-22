package ivan.vatlin.tasks;

import ivan.vatlin.dto.Account;
import ivan.vatlin.services.AccountService;
import ivan.vatlin.services.TransferMoneyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadLocalRandom;

public class TransferMoneyTask implements Runnable {
    private Logger logger = LoggerFactory.getLogger(TransferMoneyTask.class);
    private AccountService accountService = new AccountService();
    private TransferMoneyService transferMoneyService = new TransferMoneyService();

    @Override
    public void run() {
        Account senderAccount = createRandomAccount();
        Account recipientAccount = createRandomAccount();

        int compareResult = senderAccount.compareTo(recipientAccount);
        if (compareResult == 0) {
            logger.info("{} Аккаунт {} не может осуществить перевод самому себе",Thread.currentThread().getName(),
                    senderAccount.getId());
        } else if (compareResult > 0) {
            recipientAccount.lockObject();
            senderAccount.lockObject();

            transferMoneyService.doTransfer(senderAccount, recipientAccount, createRandomSumMoneyToSend());

            senderAccount.unlockObject();
            recipientAccount.unlockObject();
        } else {
            senderAccount.lockObject();
            recipientAccount.lockObject();

            transferMoneyService.doTransfer(senderAccount, recipientAccount, createRandomSumMoneyToSend());

            recipientAccount.unlockObject();
            senderAccount.unlockObject();
        }
    }

    private Account createRandomAccount() {
        int accountIndex = ThreadLocalRandom.current().nextInt(accountService.getNumberOfAccounts());
        return accountService.getAll().get(accountIndex);
    }

    private long createRandomSumMoneyToSend() {
        return ThreadLocalRandom.current().nextLong(1000);
    }
}
