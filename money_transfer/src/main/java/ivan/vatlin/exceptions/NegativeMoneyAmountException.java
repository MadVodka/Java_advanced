package ivan.vatlin.exceptions;

public class NegativeMoneyAmountException extends Exception{
    public NegativeMoneyAmountException(String message) {
        super(message);
    }
}
