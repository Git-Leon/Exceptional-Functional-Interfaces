package gitleon.utils.functional;

/**
 * Defers exceptions to an `Error` object which is checked during runtime
 * @author Leon Hunter on 4/6/18.
 */
public class ExceptionalInvocationError extends Error {
    private final String errorMessage;

    public ExceptionalInvocationError(Throwable throwable, String errorMessage) {
        super(throwable);
        this.errorMessage = errorMessage + "\n\n" + throwable.getMessage();
    }

    public ExceptionalInvocationError(Throwable throwable) {
        this(throwable, "");
    }

    @Override
    public void printStackTrace() {
        System.err.println(errorMessage);
    }
}