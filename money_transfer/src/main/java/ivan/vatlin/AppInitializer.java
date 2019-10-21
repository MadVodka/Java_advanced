package ivan.vatlin;

import ivan.vatlin.dto.Account;
import ivan.vatlin.generators.AccountsCreator;
import ivan.vatlin.services.AccountFilesService;
import ivan.vatlin.services.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class AppInitializer {
    private static Logger logger = LoggerFactory.getLogger(AppInitializer.class);

    public static void initialize() {
        AccountsCreator accountsCreator = new AccountsCreator(10, 1000);
        List<Account> accounts = accountsCreator.create();
        AccountService accountService = new AccountService();
        AccountFilesService accountFilesService = new AccountFilesService();

        try {
            accountFilesService.writeAccountsToFiles(accounts);
            List<Account> accountsReadFromFiles = accountFilesService.readAccountsFromFiles();
            accountService.addAccounts(accountsReadFromFiles);
            logger.info("Initialized accounts:\n{}", accountService.getAll());
            logger.info("Total sum of accounts: {}", accountService.getTotalMoneySum());
        } catch (IOException e) {
            e.printStackTrace();
            logger.debug(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            logger.debug(e.getMessage());
        }
    }
}
