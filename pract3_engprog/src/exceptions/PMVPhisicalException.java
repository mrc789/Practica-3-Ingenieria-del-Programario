package exceptions;

/**
 * Exception thrown when a physical issue occurs with the vehicle.
 */
public class PMVPhisicalException extends Exception {
    public PMVPhisicalException(String message) {
        super(message);
    }

    public PMVPhisicalException(String message, Throwable cause) {
        super(message, cause);
    }
}
