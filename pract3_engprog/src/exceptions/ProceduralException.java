package exceptions;

/**
 * Exception to handle procedural errors in state transitions.
 */
public class ProceduralException extends Exception {

    // Default constructor
    public ProceduralException() {
        super();
    }

    // Constructor with a custom message
    public ProceduralException(String message) {
        super(message);
    }

    // Constructor with a custom message and a cause
    public ProceduralException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor with a cause
    public ProceduralException(Throwable cause) {
        super(cause);
    }
}