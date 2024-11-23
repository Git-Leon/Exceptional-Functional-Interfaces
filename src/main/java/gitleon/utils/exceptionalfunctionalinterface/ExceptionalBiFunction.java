package gitleon.utils.exceptionalfunctionalinterface;

import java.util.function.Supplier;

/**
 * Behaves as a `BiFunction` object whose `apply` method throws a `Throwable`
 *
 * @param <FirstArgumentType>   the type of the first argument of the function to call
 * @param <SecondArgumentType>   the type of the second argument of the function to call
 * @param <ReturnType> the return-type of the function to call
 * @author Leon Hunter on 4/6/18.
 */
@FunctionalInterface
public interface ExceptionalBiFunction<FirstArgumentType, SecondArgumentType, ReturnType> {
    /**
     * Applies this function to the given arguments.
     * Allows a client to use an exceptional function as `FunctionalInterface`
     *
     * @param arg1 the first function argument
     * @param arg2 the second function argument
     * @return the function result
     */
    ReturnType apply(FirstArgumentType arg1, SecondArgumentType arg2) throws Throwable;


    /**
     * Invokes and returns the specified method with the respective arguments
     *
     * @param method       the method to be invoked
     * @param arg1Value    the first argument of the method
     * @param arg2Value    the last argument of the method
     * @param fallback     method to invoke in case of exception
     * @param <FirstArgumentType>   the type of the first argument of the function to call
     * @param <SecondArgumentType>   the type of the second argument of the function to call
     * @param <ReturnType> the return-type of the function to call
     * @return the return-value of the method
     */
    static <FirstArgumentType, SecondArgumentType, ReturnType> ReturnType tryInvoke(
            final ExceptionalBiFunction<FirstArgumentType, SecondArgumentType, ReturnType> method,
            final FirstArgumentType arg1Value,
            final SecondArgumentType arg2Value,
            final Supplier<ReturnType> fallback) {
        try {
            return method.apply(arg1Value, arg2Value);
        } catch (Throwable firstFailure) {
            firstFailure.printStackTrace();
            try {
                return fallback.get();
            } catch(Throwable fallbackFailure) {
                fallbackFailure.initCause(firstFailure);
                throw new ExceptionalInvocationError(fallbackFailure);
            }
        }
    }


    /**
     * Invokes and returns the specified method with the respective arguments
     *
     * @param method       the method to be invoked
     * @param arg1Value    the first argument of the method
     * @param arg2Value    the last argument of the method
     * @param fallback     method to invoke in case of exception
     * @param <FirstArgumentType>   the type of the first argument of the function to call
     * @param <SecondArgumentType>   the type of the second argument of the function to call
     * @param <ReturnType> the return-type of the function to call
     * @return the return-value of the method
     */
    static <FirstArgumentType, SecondArgumentType, ReturnType> ReturnType tryInvoke(
            final ExceptionalBiFunction<FirstArgumentType, SecondArgumentType, ReturnType> method,
            final FirstArgumentType arg1Value,
            final SecondArgumentType arg2Value,
            final ExceptionalSupplier<ReturnType> fallback) {
        try {
            return method.apply(arg1Value, arg2Value);
        } catch (Throwable firstFailure) {
            firstFailure.printStackTrace();
            try {
                return fallback.get();
            } catch(Throwable fallbackFailure) {
                fallbackFailure.initCause(firstFailure);
                throw new ExceptionalInvocationError(fallbackFailure);
            }
        }
    }



    /**
     * Invokes and returns the specified method with the respective arguments
     *
     * @param method       the method to be invoked
     * @param arg1Value    the first argument of the method
     * @param arg2Value    the last argument of the method
     * @param <FirstArgumentType>   the type of the first argument of the function to call
     * @param <SecondArgumentType>   the type of the second argument of the function to call
     * @param <ReturnType> the return-type of the function to call
     * @return the return-value of the method
     */
    static <FirstArgumentType, SecondArgumentType, ReturnType> ReturnType tryInvoke(
            final ExceptionalBiFunction<FirstArgumentType, SecondArgumentType, ReturnType> method,
            final FirstArgumentType arg1Value,
            final SecondArgumentType arg2Value) {
        final Supplier<ReturnType> returnTypeSupplier = () -> null;
        return tryInvoke(method, arg1Value, arg2Value, returnTypeSupplier);
    }
}
