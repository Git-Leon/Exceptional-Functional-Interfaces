package gitleon.utils.exceptionalfunctionalinterface;


import java.util.function.Supplier;

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
     *
     * @param method       the method to be invoked
     * @param fallback     method to invoke in case of exception
     * @param <ReturnType> the return-type of the method to call
     * @return the return-value of the method
     */
    static <ReturnType> ReturnType tryInvoke(
            final ExceptionalSupplier<ReturnType> method,
            final Supplier<ReturnType> fallback) {
        try {
            return method.get();
        } catch (Throwable firstFailure) {
            firstFailure.printStackTrace();
            try {
                return fallback.get();
            } catch (Throwable fallbackFailure) {
                fallbackFailure.initCause(firstFailure);
                throw new ExceptionalInvocationError(fallbackFailure);
            }
        }
    }

    /**
     * Invokes and returns the specified method
     *
     * @param method       the method to be invoked
     * @param fallback     method to invoke in case of exception
     * @param <ReturnType> the return-type of the method to call
     * @return the return-value of the method
     */
    static <ReturnType> ReturnType tryInvoke(
            final ExceptionalSupplier<ReturnType> method,
            final ExceptionalSupplier<ReturnType> fallback) {
        try {
            return method.get();
        } catch (Throwable firstFailure) {
            firstFailure.printStackTrace();
            try {
                return fallback.get();
            } catch (Throwable fallbackFailure) {
                fallbackFailure.initCause(firstFailure);
                throw new ExceptionalInvocationError(fallbackFailure);
            }
        }
    }

    /**
     * Invokes and returns the specified method
     *
     * @param method       the method to be invoked
     * @param <ReturnType> the return-type of the method to call
     * @return the return-value of the method
     */
    static <ReturnType> ReturnType tryInvoke(final ExceptionalSupplier<ReturnType> method) {
        final Supplier<ReturnType> returnTypeSupplier = () -> null;
        return tryInvoke(method, returnTypeSupplier);
    }
}

