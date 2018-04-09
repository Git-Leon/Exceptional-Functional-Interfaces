package leo.utils.functional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author leon on 4/9/18.
 */
public class ExceptionalConsumerTest {
    private ExceptionalBiFunction<Object, Object, Object[]> testFunction;

    @Before
    public void setup() {
        this.testFunction = (name, age) -> {
            Object[] person = new Object[2];
            boolean validName = name instanceof String;
            boolean validAge = age instanceof Integer;
            boolean validPerson = validName && validAge;

            // throws potential exception
            if(!validPerson) {
                // Should bubble up to an `ExceptionalInvocationError`
                throw new Throwable();
            }

            person[1] = age;
            person[0] = name;
            return person;
        };
    }

    @Test
    public void positiveTest() {
        // given
        String errorMessage = "The method invocation should work.";
        String arg1 = "testName";
        Integer arg2 = -1;
        Object[] expected = {arg1, arg2};

        // when
        Object[] actual = ExceptionalBiFunction.tryInvoke(testFunction, arg1, arg2, errorMessage);

        // Then
        boolean outcome = Arrays.equals(expected, actual);
        Assert.assertTrue(outcome);
    }


    @Test(expected = ExceptionalInvocationError.class)
    public void exceptionalInvocationErrorTest() throws ExceptionalInvocationError {
        // given
        String errorMessage = "The method invocation should fail due to invalid parameters.";
        Integer arg1 = -1;
        String arg2 = "testName";
        Object[] expected = {arg1, arg2};

        // when
        Object[] actual = ExceptionalBiFunction.tryInvoke(testFunction, arg1, arg2, errorMessage);

        // Then
        boolean outcome = Arrays.equals(expected, actual);
        Assert.assertTrue(outcome);
    }
}
