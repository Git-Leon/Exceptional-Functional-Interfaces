package leo.utils.functional;


/**
 * Behaves as a `Supplier` object whose `get` method throws a `Throwable`
 *
 * @author Leon Hunter on 4/6/18.
 */
@FunctionalInterface
public interface ExceptionalSupplier<T> {
    /**
     * Gets a result.
     *
     * @return a result
     */
    T get() throws Throwable;

    /**
     * Invokes and returns the specified method
     * Throws a `ExceptionalInvocationError` upon failure
     *
     * @param method       the method to be invoked
     * @param errorMessage the error message to be displayed if exception is thrown
     * @param <ReturnType> the return-type of the method to call
     * @return the return-value of the method
     */
    static <ReturnType> ReturnType tryInvoke(ExceptionalSupplier<ReturnType> method, String errorMessage) {
        try {
            return method.get();
        } catch (Throwable throwable) {
            throw new ExceptionalInvocationError(throwable, errorMessage);
        }
    }

    /**
     * Invokes and returns the specified method
     * Throws a `ExceptionalInvocationError` upon failure
     *
     * @param method       the method to be invoked
     * @param <ReturnType> the return-type of the method to call
     * @return the return-value of the method
     */
    static <ReturnType> ReturnType tryInvoke(ExceptionalSupplier<ReturnType> method) {
        return tryInvoke(method, null);
    }
}

