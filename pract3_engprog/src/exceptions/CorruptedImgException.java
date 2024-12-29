package exceptions;

/**
 * Exception thrown when a corrupted image is encountered.
 */
public class CorruptedImgException extends Exception {
    public CorruptedImgException(String message) {
        super(message);
    }

    public CorruptedImgException(String message, Throwable cause) {
        super(message, cause);
    }
}
