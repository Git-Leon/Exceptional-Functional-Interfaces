package gitleon.utils.exceptionalfunctionalinterface;

import java.util.function.Supplier;

/**
 * @author leon on 4/9/18.
 */
public final class ExceptionalFunctionalInterface {
    public static <FirstArgumentType, SecondArgumentType, ReturnType> ReturnType tryInvoke(
            final ExceptionalBiFunction<FirstArgumentType, SecondArgumentType, ReturnType> biFunction,
            final FirstArgumentType firstArgument,
            final SecondArgumentType secondArgument) {
        return ExceptionalBiFunction.tryInvoke(biFunction, firstArgument, secondArgument);
    }

    public static <ArgumentType, ReturnType> ReturnType tryInvoke(
            final ExceptionalFunction<ArgumentType, ReturnType> function,
            final ArgumentType argument) {
        return ExceptionalFunction.tryInvoke(function, argument);
    }

    public static <ArgumentType> void tryInvoke(
            final ExceptionalConsumer<ArgumentType> consumer,
            final ArgumentType argument) {
        ExceptionalConsumer.tryInvoke(consumer, argument);
    }

    public static <ReturnType> ReturnType tryInvoke(
            final ExceptionalSupplier<ReturnType> supplier) {
        return ExceptionalSupplier.tryInvoke(supplier);
    }

    public static void tryInvoke(final ExceptionalRunnable runnable) {
        ExceptionalRunnable.tryInvoke(runnable);
    }


    public static <FirstArgumentType, SecondArgumentType, ReturnType> ReturnType tryInvoke(
            final ExceptionalBiFunction<FirstArgumentType, SecondArgumentType, ReturnType> biFunction,
            final FirstArgumentType firstArgument,
            final SecondArgumentType secondArgument,
            final ExceptionalSupplier<ReturnType> fallBack) {
        return ExceptionalBiFunction.tryInvoke(biFunction, firstArgument, secondArgument, fallBack);
    }

    public static <ArgumentType, ReturnType> ReturnType tryInvoke(
            final ExceptionalFunction<ArgumentType, ReturnType> function,
            final ArgumentType argument,
            final ExceptionalSupplier<ReturnType> fallBack){
        return ExceptionalFunction.tryInvoke(function, argument, fallBack);
    }

    public static <ArgumentType> void tryInvoke(
            final ExceptionalConsumer<ArgumentType> consumer,
            final ArgumentType argument,
            final ExceptionalRunnable fallBack){
        ExceptionalConsumer.tryInvoke(consumer, argument, fallBack);
    }

    public static <ReturnType> ReturnType tryInvoke(
            final ExceptionalSupplier<ReturnType> supplier,
            final ExceptionalSupplier<ReturnType> fallback) {
        return ExceptionalSupplier.tryInvoke(supplier, fallback);
    }

    public static void tryInvoke(
            final ExceptionalRunnable runnable,
            final ExceptionalRunnable fallback) {
        ExceptionalRunnable.tryInvoke(runnable, fallback);
    }

    public static <FirstArgumentType, SecondArgumentType, ReturnType> ReturnType tryInvoke(
            final ExceptionalBiFunction<FirstArgumentType, SecondArgumentType, ReturnType> biFunction,
            final FirstArgumentType firstArgument,
            final SecondArgumentType secondArgument,
            final Supplier<ReturnType> fallBack) {
        return ExceptionalBiFunction.tryInvoke(biFunction, firstArgument, secondArgument, fallBack);
    }

    public static <ArgumentType, ReturnType> ReturnType tryInvoke(
            final ExceptionalFunction<ArgumentType, ReturnType> function,
            final ArgumentType argument,
            final Supplier<ReturnType> fallBack){
        return ExceptionalFunction.tryInvoke(function, argument, fallBack);
    }

    public static <ArgumentType> void tryInvoke(
            final ExceptionalConsumer<ArgumentType> consumer,
            final ArgumentType argument,
            final Runnable fallBack){
        ExceptionalConsumer.tryInvoke(consumer, argument, fallBack);
    }

    public static <ReturnType> ReturnType tryInvoke(
            final ExceptionalSupplier<ReturnType> supplier,
            final Supplier<ReturnType> fallback) {
        return ExceptionalSupplier.tryInvoke(supplier, fallback);
    }

    public static void tryInvoke(
            final ExceptionalRunnable runnable,
            final Runnable fallback) {
        ExceptionalRunnable.tryInvoke(runnable, fallback);
    }
}
