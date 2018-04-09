package gitleon.utils.functional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author leon on 4/9/18.
 */
public class ExceptionalFunctionTest {
    private ExceptionalFunction<Object, Object[]> function;

    @Before
    public void setup() {
        this.function = (name) -> {
            boolean validName = name instanceof String;
            // throws potential exception
            if (!validName) {
                // Should bubble up to an `ExceptionalInvocationError`
                String error = "`%s` is of type `%s`, not `%s`.";
                String actualType = name.getClass().getSimpleName();
                String expectedType =  String.class.getSimpleName();
                String errorMessage = String.format(error, name, actualType, expectedType);
                throw new Throwable(errorMessage);
            }

            Object[] person = new Object[1];
            person[0] = name;
            return person;
        };
    }

    @Test
    public void stringTest() {
        // given
        Object value = "Quick Brown";
        Object[] expected = { value };

        // When
        Object[] actual = ExceptionalFunction.tryInvoke(function, value);

        // Then
        Assert.assertEquals(expected, actual);
    }


    @Test(expected = ExceptionalInvocationError.class)
    public void exceptionalInvocationErrorTest() throws ExceptionalInvocationError {
        // given
        Object value = -1;
        Object[] expected = { value };

        // When
        Object[] actual = ExceptionalFunction.tryInvoke(function, value);

        // Then
        Assert.assertEquals(expected, actual);
    }


}
