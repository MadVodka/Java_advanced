package ivan.vatlin;

import ivan.vatlin.services.AppInitializer;
import ivan.vatlin.services.TransferTaskExecutor;

public class Application {

    public static void main(String[] args) {
        boolean isInitialized = AppInitializer.initialize();
        if (isInitialized) {
            TransferTaskExecutor transferTaskExecutor = new TransferTaskExecutor();
            transferTaskExecutor.execute();
        }

    }
}
