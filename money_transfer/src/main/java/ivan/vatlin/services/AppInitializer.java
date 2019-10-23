package ivan.vatlin.services;

import ivan.vatlin.dto.Account;
import ivan.vatlin.generators.AccountsCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static ivan.vatlin.dataholder.InitialAppData.MAX_ACCOUNT_BALANCE;
import static ivan.vatlin.dataholder.InitialAppData.QUANTITY_ACCOUNT;

public class AppInitializer {
    private static Logger logger = LoggerFactory.getLogger(AppInitializer.class);

    private AppInitializer() {
    }

    public static boolean initialize() {
        AccountsCreator accountsCreator = new AccountsCreator(QUANTITY_ACCOUNT, MAX_ACCOUNT_BALANCE);
        List<Account> accounts = accountsCreator.create();
        AccountService accountService = new AccountService();
        AccountFilesService accountFilesService = new AccountFilesService();

        try {
            accountFilesService.writeAccountsToFiles(accounts);
            List<Account> accountsReadFromFiles = accountFilesService.readAccountsFromFiles();
            accountService.addAccounts(accountsReadFromFiles);
            logger.info("Initialized accounts:\n{}", accountService.getAll());
            logger.info("Total sum of accounts: {}", accountService.getTotalMoneySumOfAccounts());
        } catch (FileNotFoundException e) {
            logger.warn("Файл или путь к файлу не найден.\n{}", e.getMessage());
            return false;
        } catch (IOException e) {
            logger.warn("Ошибка при чтении/записи файлов.\n{}", e.getMessage());
            return false;
        } catch (ClassNotFoundException e) {
            logger.warn("Ошибка при десериализации аккаунтов.\n{}", e.getMessage());
            return false;
        }
        return true;
    }
}
