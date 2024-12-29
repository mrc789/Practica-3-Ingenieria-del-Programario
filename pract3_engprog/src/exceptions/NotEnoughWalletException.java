package exceptions;

/**
 * Exception thrown when the wallet does not have enough funds.
 */
public class NotEnoughWalletException extends Exception {
    public NotEnoughWalletException(String message) {
        super(message);
    }
}