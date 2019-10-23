package ivan.vatlin.services;

import ivan.vatlin.tasks.TransferMoneyTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TransferTaskExecutor {
    private static Logger logger = LoggerFactory.getLogger(TransferTaskExecutor.class);
    private static AccountService accountService = new AccountService();

    private static final int numberOfTasks = 1000;
    private static final int numberOfThreads = 20;
    private TransferMoneyTask transferMoneyTask = new TransferMoneyTask();
    private ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

    public void execute() {
        for (int i = 0; i < numberOfTasks; i++) {
            executorService.submit(transferMoneyTask);
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(100, TimeUnit.SECONDS);
            if (executorService.isShutdown()) {
                logger.info("Accounts after operations:\n{}", accountService.getAll());
                logger.info("Total sum of accounts: {}", accountService.getTotalMoneySumOfAccounts());
            }
        } catch (InterruptedException e) {
            logger.debug("Ошибка во время ожидания окончания потоков.\n{}", e.getMessage());
        }
    }
}
