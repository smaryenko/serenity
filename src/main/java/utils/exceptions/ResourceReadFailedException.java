package utils.exceptions;

public class ResourceReadFailedException extends RuntimeException {

    public ResourceReadFailedException(final String messageTemplate, Object... args) {
        super(String.format(messageTemplate, args));
    }
}
