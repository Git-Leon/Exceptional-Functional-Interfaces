package gitleon.utils.functional;


/**
 * Behaves as a `Consumer` object whose `accept` method throws a `Throwable`
 *
 * @param <ArgumentType> the type of the argument of the method to be called
 * @author Leon Hunter on 4/6/18.
 */
@FunctionalInterface
public interface ExceptionalConsumer<ArgumentType> {
    /**
     * Applies this function to the given arguments.
     * Allows a client to use an exceptional function as `FunctionalInterface`
     *
     * @param methodArgument the argument of the method to be called
     * @throws Throwable any type of `Throwable` object
     */
    void accept(ArgumentType methodArgument) throws Throwable;


    /**
     * Invokes the specified method with the respective argument
     * Throws a `ExceptionalInvocationError` upon failure
     *
     * @param method       method to be invoked
     * @param arg          argument of the method to be invoked
     * @param errorMessage message to display upon invocation failure
     * @param <ArgType>    type of argument of the method to be called
     */
    static <ArgType> void tryInvoke(
            ExceptionalConsumer<ArgType> method,
            ArgType arg,
            String errorMessage) {
        try {
            method.accept(arg);
        } catch (Throwable throwable) {
            throw new ExceptionalInvocationError(throwable, errorMessage);
        }
    }


    /**
     * Invokes the specified method with the respective argument
     * Throws a `ExceptionalInvocationError` upon failure
     *
     * @param method       method to be invoked
     * @param arg          argument of the method to be invoked
     * @param <ArgType>    type of argument of the method to be called
     */
    static <ArgType> void tryInvoke(
            ExceptionalConsumer<ArgType> method,
            ArgType arg) {
        tryInvoke(method, arg, null);
    }

}
