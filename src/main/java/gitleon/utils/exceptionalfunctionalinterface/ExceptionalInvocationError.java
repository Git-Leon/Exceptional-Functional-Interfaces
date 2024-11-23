package gitleon.utils.exceptionalfunctionalinterface;

/**
 * Defers exceptions to an `Error` object which is checked during runtime
 * @author Leon Hunter on 4/6/18.
 */
public class ExceptionalInvocationError extends Error {
    private final String errorMessage;

    public ExceptionalInvocationError(final Throwable throwable, final String errorMessage) {
        super(throwable);
        this.errorMessage = errorMessage + "\n\n" + throwable.getMessage();
    }

    public ExceptionalInvocationError(final Throwable throwable) {
        this(throwable, "");
    }

    @Override
    public void printStackTrace() {
        System.err.println(errorMessage);
    }
}