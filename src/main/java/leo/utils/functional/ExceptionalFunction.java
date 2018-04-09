package leo.utils.functional;


/**
 * Behaves as a `Function` object whose `apply` method throws a `Throwable`
 *
 * @param <Arg1Type>   the type of the first argument of the function to call
 * @param <Arg2Type>   the type of the second argument of the function to call
 * @param <ReturnType> the return-type of the function to call
 * @author Leon Hunter on 4/6/18.
 */

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
     * Throws a `ExceptionalInvocationError` upon failure
     *
     * @param method         the method to be invoked
     * @param argValue       the first argument of the method
     * @param errorMessage   the error message to be displayed if exception is thrown
     * @param <ArgumentType> the type of the first argument of the method to call
     * @param <ReturnType>   the return-type of the method to call
     * @return the return-value of the method
     */
    static <ArgumentType, ReturnType> ReturnType tryInvoke(
            ExceptionalFunction<ArgumentType, ReturnType> method,
            ArgumentType argValue,
            String errorMessage) {
        try {
            return method.apply(argValue);
        } catch (Throwable t) {
            throw new ExceptionalInvocationError(t, errorMessage);
        }
    }

    /**
     * Invokes and returns the specified method with the respective arguments
     * Throws a `ExceptionalInvocationError` upon failure
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
        return tryInvoke(method, argValue, null);
    }
}