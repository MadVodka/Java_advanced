package ivan.vatlin.entity;

import ivan.vatlin.annotations.Secured;

public class Item {

    @Secured(level = 3)
    public String getName() {
        return "Some name";
    }

    public boolean isValid() {
        return true;
    }

    @Secured(level = 2, name = "Custom")
    private void doSelfAnalyze() {
        System.out.println("Doing some analyze");
    }

    private int someOperation() {
        return 1;
    }
}
