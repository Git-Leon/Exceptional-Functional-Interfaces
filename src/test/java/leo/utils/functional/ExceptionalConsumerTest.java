package leo.utils.functional;

import org.junit.Test;

/**
 * @author leon on 4/9/18.
 */
public class ExceptionalConsumerTest {
    private static MockExceptionalObject exceptionalObject = new MockExceptionalObject();
    private static ExceptionalConsumer compareMethod = MockExceptionalObject.ExceptionalConsumerFunctions::valueOf;

    @Test
    public void positiveTest() {
        // given
        String errorMessage = "If this method fails, the test should fail.";
        Object arg1 = "value1";

        // when
        ExceptionalConsumer.tryInvoke(compareMethod, arg1, errorMessage);
    }


    @Test(expected = ExceptionalInvocationError.class)
    public void exceptionalInvocationErrorTest() throws ExceptionalInvocationError {
        // given
        String errorMessage = "If this method fails, the test should pass.";
        Object arg1 = null;
        Object arg2 = "value2";

        // when
        // then
        Boolean actual = ExceptionalBiFunction.tryInvoke(compareMethod, arg1, arg2, errorMessage);
    }
}
