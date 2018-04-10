package gitleon.utils.exceptionalfunctionalinterface;

/**
 * @author leon on 4/9/18.
 */
public final class ExceptionalFunctionalInterface {
    public static <FirstArgumentType, SecondArgumentType, ReturnType> ReturnType tryInvoke(
            ExceptionalBiFunction<FirstArgumentType, SecondArgumentType, ReturnType> biFunction,
            FirstArgumentType firstArgument,
            SecondArgumentType secondArgument) {
        return ExceptionalBiFunction.tryInvoke(biFunction, firstArgument, secondArgument);
    }

    public static <ArgumentType, ReturnType> ReturnType tryInvoke(
            ExceptionalFunction<ArgumentType, ReturnType> function,
            ArgumentType argument) {
        return ExceptionalFunction.tryInvoke(function, argument);
    }

    public static <ArgumentType> void tryInvoke(
            ExceptionalConsumer<ArgumentType> consumer,
            ArgumentType argument) {
        ExceptionalConsumer.tryInvoke(consumer, argument);
    }

    public static <ReturnType> ReturnType tryInvoke(
            ExceptionalSupplier<ReturnType> supplier) {
        return ExceptionalSupplier.tryInvoke(supplier);
    }

    public static void tryInvoke(ExceptionalRunnable runnable) {
        ExceptionalRunnable.tryInvoke(runnable);
    }
}
