# Exceptional Functional Interfaces
* Allows client to defer exceptions to an exceptional functional interface.
* The goal of this repository is to host a maven dependency that can import these tools in other java applications.

## Background
* With the advent of [Java 8 Lambdas](https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html) and [Method References](https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html), functional programming in java has become increasingly popular.
* Unfortunately, Java 8 did not account for creating lambda expressions which may throw an `Exception`. This shortcoming has caused a great deal of frustration when developing an exceptional-codebase which should be expressed functionally rather than with object orientation.
* This repository aims to help rememdy the deficiencies of Java 8 functional programming.

## What can I do with these interfaces that I cannot do with Java 8's built-in `FunctionalInterface`?
* Users of this repository, can express code that explicitly throws `Exception`, without ever explicitly handling it by defering the `try`/`catch` to the respective `FunctionalInterface`'s static `tryInvoke` method.

```java
public static void main(String[] args) {
    ExceptionalBiFunction<Object, Object, Object[]> testFunction = (name, age) -> {
        boolean validName = name instanceof String;
        boolean validAge = age instanceof Integer;
        boolean validPerson = validName && validAge;

        // throws potential exception
        if(!validPerson) {
            // This line is impossible to declare within a java.util.BiFunction
            throw new Throwable();
        }
        
        Object[] person = new Object[2];
        person[1] = age;
        person[0] = name;
        return person;
    };

    String myName= "Leon";
    Integer myAge = 24;
    String error = "Failed to call function with arguments `%s` and `%s`";
    String optionalErrorMessage = String.format(error, myName, myAge);
    ExceptionalBiFunction.tryInvoke(testFunction, myName, myAge, optionalErrorMessage);
}
```

## How do I use this in my project?
### Maven
* Ensure your `pom.xml` configures these 3 aspects:
    * java language level is set to 8 or higher
    * specify the use of the `git-leon` repository
    * specify artifact dependency

### Sample `pom.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>mygroupid</groupId>
    <artifactId>myartifact</artifactId>
    <version>1.0-SNAPSHOT</version>


    <!--Set language level to Java 8 -->
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.6.1</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                </configuration>
            </plugin>
        </plugins>
    </build>

    <!-- Specify the use of git-leon repository -->
    <repositories>
        <repository>
            <id>git-leon-exceptional-functional-interface</id>
            <url>https://packagecloud.io/git-leon/utils/maven2</url>
            <releases>
                <enabled>true</enabled>
            </releases>
            <snapshots>
                <enabled>true</enabled>
            </snapshots>
        </repository>
    </repositories>


    <!-- Dependency for artifact -->
    <dependencies>
        <dependency>
          <groupId>com.github.git-leon</groupId>
          <artifactId>exceptional-functional-interfaces</artifactId>
          <version>1.2</version>
        </dependency>
    <dependencies>
</project>
        
```
