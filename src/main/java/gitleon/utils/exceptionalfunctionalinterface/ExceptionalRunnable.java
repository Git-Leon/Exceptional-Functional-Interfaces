package gitleon.utils.exceptionalfunctionalinterface;


/**
 * Behaves as a `Runnable` object whose `run` method throws a `Throwable`
 *
 * @author Leon Hunter on 4/6/18.
 */
@FunctionalInterface
public interface ExceptionalRunnable {
    /**
     * When an object implementing interface `Runnable` is used
     * to create a thread, starting the thread causes the object's
     * `run` method to be called in that separately executing thread.
     * <p>
     * The general contract of the method `run` is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    void run() throws Throwable;


    /**
     * Invokes the specified method with the respective argument
     *
     * @param method       method to be invoked
     * @param fallback     method to invoke in case of exception
     */
    static void tryInvoke(final ExceptionalRunnable method, final Runnable fallback) {
        try {
            method.run();
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
     * @param fallback     method to invoke in case of exception
     */
    static void tryInvoke(final ExceptionalRunnable method, final ExceptionalRunnable fallback) {
        try {
            method.run();
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
     */
    static void tryInvoke(final ExceptionalRunnable method) {
        final ExceptionalRunnable runnable = () -> {};
        tryInvoke(method, runnable);
    }
}
