package nl.intergamma.domain.exception;

/**
 * This exception is thrown when a flow is not found in the system
 */
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
