package ivan.vatlin.services;

import ivan.vatlin.dto.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransferMoneyService {
    private Logger logger = LoggerFactory.getLogger(TransferMoneyService.class);

    public void doTransfer(Account accountFrom, Account accountTo, long amountMoney) {
        String transferInfo = Thread.currentThread().getName() + " Аккаунт" + accountFrom.getId() +
                "-->" + amountMoney + "--> Аккаунт" + accountTo.getId();
        if (amountMoney < 0) {
            logger.info("{}: сумма перевода не может быть меньше нуля", transferInfo);
        }
        long accountFromBalance = accountFrom.getBalance();
        long accountToBalance = accountTo.getBalance();

        long accountFromRemainingBalance = accountFromBalance - amountMoney;
        if (accountFromRemainingBalance < 0) {
            logger.info("{}: аккаунт {} имеет недостаточно средств для перевода", transferInfo, accountFrom);
        }

        accountFrom.setBalance(accountFromBalance - amountMoney);
        accountTo.setBalance(accountToBalance + amountMoney);
        logger.info("{}: перевод денег прошел успешно", transferInfo);
    }
}
