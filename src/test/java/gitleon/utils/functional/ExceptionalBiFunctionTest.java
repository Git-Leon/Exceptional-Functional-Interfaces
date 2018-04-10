package gitleon.utils.functional;

import gitleon.utils.exceptionalfunctionalinterface.ExceptionalBiFunction;
import gitleon.utils.exceptionalfunctionalinterface.ExceptionalInvocationError;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author leon on 4/9/18.
 */
public class ExceptionalBiFunctionTest {
    gitleon.utils.exceptionalfunctionalinterface.ExceptionalBiFunction<Object, Object, Object[]> method;

    @Before
    public void setup() {
        this.method = (name, age) -> {
            Object[] person = new Object[2];
            boolean validName = name instanceof String;
            boolean validAge = age instanceof Integer;
            boolean validPerson = validName && validAge;

            // throws potential exception
            if(!validPerson) {
                // Should bubble up to an `ExceptionalInvocationError`
                throw new Throwable();
            }

            person[0] = name;
            person[1] = age;
            return person;
        };
    }

    @Test
    public void positiveTest() {
        // given
        String errorMessage = "The method invocation should succeed";
        String arg1 = "value1";
        Integer arg2 = 5;
        Object[] expected = {arg1, arg2};

        // when
        Object[] actual = gitleon.utils.exceptionalfunctionalinterface.ExceptionalBiFunction.tryInvoke(method, arg1, arg2, errorMessage);

        // then
        Assert.assertEquals(expected, actual);
    }



    @Test(expected = gitleon.utils.exceptionalfunctionalinterface.ExceptionalInvocationError.class)
    public void exceptionalInvocationErrorTest() throws ExceptionalInvocationError {
        // given
        String errorMessage = "The method invocation should fail due to invalid parameters.";
        Integer arg1 = 5;
        String arg2 = "value1";
        Object[] expected = {arg1, arg2};

        // when
        Object[] actual = ExceptionalBiFunction.tryInvoke(method, arg1, arg2, errorMessage);

        // then
        Assert.assertEquals(expected, actual);
    }
}
