package leo.utils.functional;


/**
 * Behaves as a `Runnable` object whose `run` method throws a `Throwable`
 * @author Leon Hunter on 4/6/18.
 */
@FunctionalInterface
public interface ExceptionalRunnable {
    /**
     * When an object implementing interface `Runnable` is used
     * to create a thread, starting the thread causes the object's
     * `run` method to be called in that separately executing thread.
     *
     * The general contract of the method `run` is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    void run() throws Throwable;


    /**
     * Invokes the specified method with the respective argument
     * Throws a `ExceptionalInvocationError` upon failure
     *
     * @param method       method to be invoked
     * @param errorMessage message to display upon invocation failure
     */
    static void tryInvoke(ExceptionalRunnable method, String errorMessage) {
        try {
            method.run();
        } catch (Throwable throwable) {
            throw new ExceptionalInvocationError(throwable, errorMessage);
        }
    }
}
