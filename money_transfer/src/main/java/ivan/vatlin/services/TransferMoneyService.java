package ivan.vatlin.services;

import ivan.vatlin.dto.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransferMoneyService {
    private Logger logger = LoggerFactory.getLogger(TransferMoneyService.class);

    public void doTransfer(Account senderAccount, Account recipientAccount, long amountMoney) {
        String transferInfo = Thread.currentThread().getName() + " Аккаунт " + senderAccount.getId() +
                " --> " + amountMoney + " --> Аккаунт " + recipientAccount.getId();
        if (amountMoney < 0) {
            logger.info("{}: сумма перевода не может быть меньше нуля", transferInfo);
            return;
        }

        long senderAccountBalance = senderAccount.getBalance();
        long recipientAccountBalance = recipientAccount.getBalance();

        long senderAccountRemainingBalance = senderAccountBalance - amountMoney;
        if (senderAccountRemainingBalance < 0) {
            logger.info("{}: {} имеет недостаточно средств для перевода", transferInfo, senderAccount);
            return;
        }

        senderAccount.setBalance(senderAccountBalance - amountMoney);
        recipientAccount.setBalance(recipientAccountBalance + amountMoney);
        logger.info("{}: перевод средств прошел успешно", transferInfo);
    }
}
