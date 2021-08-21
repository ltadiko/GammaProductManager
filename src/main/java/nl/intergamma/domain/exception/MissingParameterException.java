package nl.intergamma.domain.exception;

/**
 * This exception is thrown when a flow is not found in the system
 */
public class MissingParameterException extends RuntimeException {
    public MissingParameterException(String message) {
        super(message);
    }
}
