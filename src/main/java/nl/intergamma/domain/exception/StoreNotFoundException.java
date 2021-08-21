package nl.intergamma.domain.exception;

/**
 * This exception is thrown when a flow is not found in the system
 */
public class StoreNotFoundException extends RuntimeException {
    public StoreNotFoundException(String message) {
        super(message);
    }
}
