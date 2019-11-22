package utils.exceptions;

/**
 * To be thrown when something fails in the core framework (Web driver setup etc.)
 */
public class ObjectMappingException extends RuntimeException {

    public ObjectMappingException(final String message) {
        super(message);
    }

    public ObjectMappingException(final Throwable cause) {
        super(cause);
    }

    public ObjectMappingException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ObjectMappingException(final String message, final Object... args) {
        super(String.format(message, args));
    }

    public ObjectMappingException(final String message, final Throwable cause, final Object... args) {
        super(String.format(message, args), cause);
    }

}