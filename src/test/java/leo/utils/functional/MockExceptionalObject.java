package leo.utils.functional;

/**
 * @author leon on 4/9/18.
 */
public final class MockExceptionalObject {
    enum ExceptionalBiFunctions {
        TEST_METHOD1((arg1, arg2) -> { return null; }),

        TEST_METHOD2((arg1, arg2)-> {
            return null;
        });

        ExceptionalBiFunctions(ExceptionalBiFunction function) {

        }
    }



    enum ExceptionalConsumerFunctions{
        TEST_METHOD1((arg1) -> {});
        ExceptionalConsumerFunctions(ExceptionalConsumer function) {

        }
    }





    enum ExceptionalFunctionFunctions{
        TEST_METHOD1((arg1) -> { return null; }),
        TEST_METHOD2((arg1) -> { return null; });
        ExceptionalFunctionFunctions(ExceptionalFunction function) {

        }
    }







    enum ExceptionalRunnableFunctions{
        TEST_METHOD1(() -> {}),
        TEST_METHOD2(() -> {} );
        ExceptionalRunnableFunctions(ExceptionalRunnable function) {

        }
    }








    enum ExceptionalSupplierFunctions{
        TEST_METHOD1(() -> {return null;}),
        TEST_METHOD2(() -> {return null;});
        ExceptionalSupplierFunctions(ExceptionalSupplier function) {

        }
    }
}
