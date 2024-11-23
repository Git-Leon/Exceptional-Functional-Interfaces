package gitleon.utils.exceptionalfunctionalinterface;


import java.util.function.Supplier;

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
     *
     * @param method       method to be invoked
     * @param arg          argument of the method to be invoked
     * @param fallback     method to invoke in case of exception
     * @param <ArgType>    type of argument of the method to be called
     */
    static <ArgType> void tryInvoke(
            final ExceptionalConsumer<ArgType> method,
            final ArgType arg,
            final ExceptionalRunnable fallback) {
        try {
            method.accept(arg);
        } catch (Throwable firstFailure) {
            firstFailure.printStackTrace();
            try {
                fallback.run();
            } catch (Throwable fallbackFailure) {
                fallbackFailure.initCause(firstFailure);
                throw new ExceptionalInvocationError(fallbackFailure);
            }
        }
    }


    /**
     * Invokes the specified method with the respective argument
     *
     * @param method       method to be invoked
     * @param arg          argument of the method to be invoked
     * @param <ArgType>    type of argument of the method to be called
     */
    static <ArgType> void tryInvoke(
            ExceptionalConsumer<ArgType> method,
            ArgType arg) {
        tryInvoke(method, arg, ()->{});
    }

}
