package leo.utils.functional;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author leon on 4/9/18.
 */
public class ExceptionalBiFunctionTest {
    private static ExceptionalBiFunction<Object, Object, Boolean> compareMethod  = (arg1, arg2) -> {
        return arg1.equals(arg2);
    };

    @Test
    public void positiveTest() {
        // given
        String errorMessage = "If this method fails, the test should fail.";
        Object arg1 = "value1";
        Object arg2 = "value2";
        Boolean expected = false;

        // when
        Boolean actual = ExceptionalBiFunction.tryInvoke(compareMethod, arg1, arg2, errorMessage);

        // then
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void negativeTest() {
        // given
        String errorMessage = "If this method fails, the test should fail.";
        Object arg1 = "value1";
        Object arg2 = "value1";
        boolean expected = true;

        // when
        Boolean actual = ExceptionalBiFunction.tryInvoke(compareMethod, arg1, arg2, errorMessage);

        // then
        Assert.assertEquals(expected, actual);
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
