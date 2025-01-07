// Java Cheatsheet
// Author: Generated from "Java Fundamentals" slides

// Basic structure of a Java program
public class JavaCheatsheet {

    public static void main(String[] args) {
        // Printing to the console
        System.out.println("Hello, Java!");

        // Data Types
        int integerExample = 42;
        double doubleExample = 3.14;
        char charExample = 'A';
        boolean booleanExample = true;
        String stringExample = "This is a string";

        // Variables and Operators
        int a = 5, b = 10;
        System.out.println("Addition: " + (a + b)); // Arithmetic operator
        System.out.println("Relational: " + (a < b)); // Relational operator
        System.out.println("Logical: " + (a < b && booleanExample)); // Logical operator
        int max = (a > b) ? a : b; // Ternary operator

        // Control Flow
        if (a < b) {
            System.out.println("a is less than b");
        } else {
            System.out.println("a is not less than b");
        }

        // Switch case
        switch (a) {
            case 1:
                System.out.println("a is 1");
                break;
            case 5:
                System.out.println("a is 5");
                break;
            default:
                System.out.println("a is not 1 or 5");
        }

        // Loops
        for (int i = 0; i < 5; i++) {
            System.out.println("For loop: " + i);
        }

        int i = 0;
        while (i < 3) {
            System.out.println("While loop: " + i);
            i++;
        }

        do {
            System.out.println("Do-while loop: " + i);
            i--;
        } while (i > 0);

        // Arrays
        int[] array = {1, 2, 3, 4, 5};
        for (int element : array) {
            System.out.println("Array element: " + element);
        }

        // Arrays - Additional Examples
        int[] arr = new int[3]; // Declaration
        arr[0] = 10; arr[1] = 20; arr[2] = 30;
        System.out.println("Accessing element: " + arr[1]); // Access
        System.out.println("Length of array: " + arr.length); // Length

        // Sorting an Array
        java.util.Arrays.sort(array);
        for (int element : array) {
            System.out.println("Sorted element: " + element);
        }

        // Strings
        String str = "Java Programming";
        System.out.println("Length: " + str.length());
        System.out.println("Substring: " + str.substring(0, 4));
        System.out.println("Uppercase: " + str.toUpperCase());
        System.out.println("Contains 'Java': " + str.contains("Java"));
        System.out.println("Replace: " + str.replace("Java", "Python"));
        System.out.println("Split: " + java.util.Arrays.toString(str.split(" ")));
        System.out.println("Index of 'P': " + str.indexOf('P'));
        System.out.println("Last index of 'a': " + str.lastIndexOf('a'));

        // Parsing Strings to Numbers
        String numberStr = "123";
        int number = Integer.parseInt(numberStr);
        System.out.println("Parsed integer: " + number);

        // Regex Matching
        String regexExample = "Java123";
        System.out.println("Matches 'Java\\d+': " + regexExample.matches("Java\\d+"));

        // String Formatting
        String formatted = String.format("Name: %s, Age: %d", "John", 25);
        System.out.println("Formatted string: " + formatted);

        // Methods
        System.out.println("Sum: " + add(10, 20)); // Calling a method

        // OOP: Classes and Objects
        Person person = new Person("John", 25);
        person.displayInfo();

        // Exception Handling
        try {
            int result = 10 / 0; // This will throw an exception
        } catch (ArithmeticException e) {
            System.out.println("Caught an exception: " + e.getMessage());
        } finally {
            System.out.println("Finally block executed");
        }

        // Encapsulation Example
        EncapsulatedClass ec = new EncapsulatedClass();
        ec.setName("Encapsulated Name");
        System.out.println("Encapsulated Name: " + ec.getName());

        // Inheritance Example
        Dog dog = new Dog();
        dog.animalSound(); // Polymorphism

        // Abstraction Example
        AbstractAnimal cat = new Cat();
        cat.animalSound();
        cat.sleep();

        // Date/Time
        java.time.LocalDate today = java.time.LocalDate.now();
        System.out.println("Today's Date: " + today);
        java.time.LocalTime now = java.time.LocalTime.now();
        System.out.println("Current Time: " + now);
    }

    // Method Example
    public static int add(int x, int y) {
        return x + y;
    }
}

// OOP: Custom Class
class Person {
    private String name;
    private int age;

    // Constructor
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Method
    public void displayInfo() {
        System.out.println("Name: " + name + ", Age: " + age);
    }
}

// OOP: Encapsulation
class EncapsulatedClass {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

// OOP: Inheritance
class Animal {
    public void animalSound() {
        System.out.println("Animal makes a sound");
    }
}

class Dog extends Animal {
    @Override
    public void animalSound() {
        System.out.println("Dog barks: Woof Woof");
    }
}

// OOP: Abstraction
abstract class AbstractAnimal {
    public abstract void animalSound();

    public void sleep() {
        System.out.println("Zzzzz");
    }
}

class Cat extends AbstractAnimal {
    @Override
    public void animalSound() {
        System.out.println("Cat meows: Meow Meow");
    }
}