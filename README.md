# Exceptional Functional Interfaces
* Allows client to defer exceptions to an exceptional functional interface.
* The goal of this repository is to host a maven dependency that can import these tools in other java applications.

## Background
* With the advent of [Java 8 Lambdas](https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html) and [Method References](https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html), functional programming in java has become increasingly popular.
* Unfortunately, Java 8 did not account for creating lambda expressions which may throw an `Exception`. This shortcoming has caused a great deal of frustration when developing a codebase which should be expressed functionally rather than with object orientation.
* This repository aims to help rememdy the deficiencies of Java 8 functional programming.

## What can I do with these interfaces that I cannot do with Java 8's built-in `FunctionalInterface`?
* Users of this repository, can express code that explicitly throws `Exception`, without ever explicitly handling it by defering the `try`/`catch` to the respective `FunctionalInterface`

```java
public static void main(String[] args) {
    ExceptionalBiFunction<Object, Object, Object[]> testFunction = (name, age) -> {
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
    ExceptionalBiFunction.tryInvoke(testFunction, "Name", myAge);
}
```
