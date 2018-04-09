package leo.utils.functional;

/**
 * @author leon on 4/9/18.
 */
public class A {
public static void main(String[] args) {
    leo.utils.functional.ExceptionalBiFunction<Object, Object, Object[]> testFunction = (name, age) -> {
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

    String myName= "Leon";
    Integer myAge = 24;
    leo.utils.functional.ExceptionalBiFunction.tryInvoke(testFunction, "Name", myAge);
}
}
