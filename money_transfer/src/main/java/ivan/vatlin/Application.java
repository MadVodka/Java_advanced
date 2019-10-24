package ivan.vatlin;

import ivan.vatlin.services.AppInitializer;
import ivan.vatlin.services.TransferTaskExecutor;

public class Application {

    public static void main(String[] args) {
        AppInitializer.initialize();
        TransferTaskExecutor transferTaskExecutor = new TransferTaskExecutor();
        transferTaskExecutor.execute();
    }
}
