package gitleon.utils.functional;

import gitleon.utils.exceptionalfunctionalinterface.ExceptionalInvocationError;
import gitleon.utils.exceptionalfunctionalinterface.ExceptionalSupplier;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author leon on 4/9/18.
 */
public class ExceptionalSupplierTest {
    private ExceptionalSupplier function;
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
        ExceptionalSupplier.tryInvoke(function);
        Assert.assertEquals(expected, value);
    }

    @Test
    public void exceptionalInvocationErrorTest() {
        for(int i=0; i<6; i++) {
            ExceptionalSupplier.tryInvoke(function);
        }
    }
}