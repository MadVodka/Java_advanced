package ivan.vatlin.tasks;

import ivan.vatlin.dto.Account;
import ivan.vatlin.exceptions.InsufficientBalanceException;
import ivan.vatlin.exceptions.NegativeMoneyAmountException;
import ivan.vatlin.services.AccountService;
import ivan.vatlin.services.TransferMoneyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadLocalRandom;

import static ivan.vatlin.dataholder.InitialAppData.MAX_ACCOUNT_BALANCE;

public class TransferMoneyTask implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(TransferMoneyTask.class);
    private AccountService accountService = new AccountService();
    private TransferMoneyService transferMoneyService = new TransferMoneyService();

    @Override
    public void run() {
        Account senderAccount = getRandomAccount();
        Account recipientAccount = getRandomAccount();

        int compareResult = senderAccount.compareTo(recipientAccount);
        if (compareResult == 0) {
            logger.warn("{} Аккаунт {} не может осуществить перевод самому себе", Thread.currentThread().getName(),
                    senderAccount.getId());
        } else if (compareResult > 0) {
            recipientAccount.lockObject();
            senderAccount.lockObject();

            try {
                transferMoneyService.doTransfer(senderAccount, recipientAccount, createRandomSumMoneyToSend());
            } catch (NegativeMoneyAmountException | InsufficientBalanceException e) {
                logger.warn(e.getMessage());
            } finally {
                senderAccount.unlockObject();
                recipientAccount.unlockObject();
            }
        } else {
            senderAccount.lockObject();
            recipientAccount.lockObject();

            try {
                transferMoneyService.doTransfer(senderAccount, recipientAccount, createRandomSumMoneyToSend());
            } catch (NegativeMoneyAmountException | InsufficientBalanceException e) {
                logger.warn(e.getMessage());
            } finally {
                recipientAccount.unlockObject();
                senderAccount.unlockObject();
            }
        }
    }

    private Account getRandomAccount() {
        int accountIndex = ThreadLocalRandom.current().nextInt(accountService.getNumberOfAccounts());
        return accountService.getAll().get(accountIndex);
    }

    private long createRandomSumMoneyToSend() {
        return ThreadLocalRandom.current().nextLong(MAX_ACCOUNT_BALANCE);
    }
}
