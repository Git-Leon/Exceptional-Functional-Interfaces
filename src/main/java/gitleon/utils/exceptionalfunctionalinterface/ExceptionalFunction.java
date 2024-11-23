package gitleon.utils.exceptionalfunctionalinterface;


/**
 * Behaves as a `Function` object whose `apply` method throws a `Throwable`
 *
 * @param <Arg1Type>   the type of the first argument of the function to call
 * @param <Arg2Type>   the type of the second argument of the function to call
 * @param <ReturnType> the return-type of the function to call
 * @author Leon Hunter on 4/6/18.
 */

import java.util.function.Supplier;

/**
 * @param <ArgumentType>
 * @param <ReturnType>
 */
@FunctionalInterface
public interface ExceptionalFunction<ArgumentType, ReturnType> {

    /**
     * @param argumentValue the value of the method-argument
     * @return the method result
     */
    ReturnType apply(ArgumentType argumentValue) throws Throwable;

    /**
     * Invokes and returns the specified method with the respective arguments
     *
     * @param method         the method to be invoked
     * @param argValue       the first argument of the method
     * @param fallback     method to invoke in case of exception
     * @param <ArgumentType> the type of the first argument of the method to call
     * @param <ReturnType>   the return-type of the method to call
     * @return the return-value of the method
     */
    static <ArgumentType, ReturnType> ReturnType tryInvoke(
            final ExceptionalFunction<ArgumentType, ReturnType> method,
            final ArgumentType argValue,
            final ExceptionalSupplier<ReturnType> fallback) {
        try {
            return method.apply(argValue);
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
     * @param method         the method to be invoked
     * @param argValue       the first argument of the method
     * @param <ArgumentType> the type of the first argument of the method to call
     * @param <ReturnType>   the return-type of the method to call
     * @return the return-value of the method
     */
    static <ArgumentType, ReturnType> ReturnType tryInvoke(
            ExceptionalFunction<ArgumentType, ReturnType> method,
            ArgumentType argValue) {
        return tryInvoke(method, argValue, () -> null);
    }
}