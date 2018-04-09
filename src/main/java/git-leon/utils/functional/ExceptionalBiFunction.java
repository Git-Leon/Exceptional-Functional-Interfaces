package leo.utils.functional;

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
     * Throws a `ExceptionalInvocationError` upon failure
     *
     * @param method       the method to be invoked
     * @param arg1Value    the first argument of the method
     * @param arg2Value    the last argument of the method
     * @param errorMessage the error message to be displayed if exception is thrown
     * @param <FirstArgumentType>   the type of the first argument of the function to call
     * @param <SecondArgumentType>   the type of the second argument of the function to call
     * @param <ReturnType> the return-type of the function to call
     * @return the return-value of the method
     */
    static <FirstArgumentType, SecondArgumentType, ReturnType> ReturnType tryInvoke(
            ExceptionalBiFunction<FirstArgumentType, SecondArgumentType, ReturnType> method,
            FirstArgumentType arg1Value,
            SecondArgumentType arg2Value,
            String errorMessage) {
        try {
            return method.apply(arg1Value, arg2Value);
        } catch (Throwable t) {
            throw new leo.utils.functional.ExceptionalInvocationError(t, errorMessage);
        }
    }



    /**
     * Invokes and returns the specified method with the respective arguments
     * Throws a `ExceptionalInvocationError` upon failure
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
            ExceptionalBiFunction<FirstArgumentType, SecondArgumentType, ReturnType> method,
            FirstArgumentType arg1Value,
            SecondArgumentType arg2Value) {
        return tryInvoke(method, arg1Value, arg2Value, null);
    }
}
