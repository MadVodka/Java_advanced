package ivan.vatlin.services;

import ivan.vatlin.tasks.TransferMoneyTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TransferTaskExecutor {
    private final int numberOfTasks = 1000;
    private final int numberOfThreads = 20;
    private TransferMoneyTask transferMoneyTask = new TransferMoneyTask();
    private ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

    public void execute() {
        for (int i = 0; i < numberOfTasks; i++) {
            executorService.submit(transferMoneyTask);
        }
        executorService.shutdown();
    }

}
