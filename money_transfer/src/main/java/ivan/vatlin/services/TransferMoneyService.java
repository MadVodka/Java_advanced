package ivan.vatlin.services;

import ivan.vatlin.dto.Account;
import ivan.vatlin.exceptions.InsufficientBalanceException;
import ivan.vatlin.exceptions.NegativeMoneyAmountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransferMoneyService {
    private Logger logger = LoggerFactory.getLogger(TransferMoneyService.class);

    public void doTransfer(Account senderAccount, Account recipientAccount, long amountMoney)
            throws NegativeMoneyAmountException, InsufficientBalanceException {
        String transferInfo = Thread.currentThread().getName() + " Аккаунт " + senderAccount.getId() +
                " --> " + amountMoney + " --> Аккаунт " + recipientAccount.getId();
        if (amountMoney < 0) {
            throw new NegativeMoneyAmountException("Сумма перевода не может быть меньше нуля");
        }

        long senderAccountBalance = senderAccount.getBalance();
        long recipientAccountBalance = recipientAccount.getBalance();

        long senderAccountRemainingBalance = senderAccountBalance - amountMoney;
        if (senderAccountRemainingBalance < 0) {
            throw new InsufficientBalanceException(senderAccount + " имеет недостаточно средств для перевода");
        }

        senderAccount.setBalance(senderAccountBalance - amountMoney);
        recipientAccount.setBalance(recipientAccountBalance + amountMoney);
        logger.info("{}: перевод средств прошел успешно", transferInfo);
    }
}
