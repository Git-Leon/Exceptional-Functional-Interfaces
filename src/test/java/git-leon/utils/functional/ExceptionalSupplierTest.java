package leo.utils.functional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author leon on 4/9/18.
 */
public class ExceptionalSupplierTest {
    private leo.utils.functional.ExceptionalSupplier function;
    private Integer value;

    @Before
    public void setup() {
        this.value = 0;
        this.function = () -> {
            value++;
            boolean condition = value % 2 == 0;
            // throws potential exception
            if (condition) {
                // Should bubble up to an `ExceptionalInvocationError`
                throw new Throwable();
            }
            return new Object();
        };
    }


    @Test
    public void positiveTest() {
        Integer expected = 1;
        leo.utils.functional.ExceptionalSupplier.tryInvoke(function);
        Assert.assertEquals(expected, value);
    }

    @Test(expected = leo.utils.functional.ExceptionalInvocationError.class)
    public void exceptionalInvocationErrorTest() throws leo.utils.functional.ExceptionalInvocationError {
        for(int i=0; i<6; i++) {
            leo.utils.functional.ExceptionalSupplier.tryInvoke(function);
        }
    }
}
