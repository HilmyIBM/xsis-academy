import java.util.ArrayList;
import java.util.Arrays;

public class Catatan {
    public static void main(String[] args) {
        // catatan String

        // charAt()	Returns the character at the specified index (position)	char
        
        // compareTo()	Compares two strings lexicographically	int
        String myStr1 = "Hello";
        String myStr2 = "Hello";
        System.out.println(myStr1.compareTo(myStr2)); // Returns 0 because they are equal
        // compareToIgnoreCase()	Compares two strings lexicographically, ignoring case differences	int

        // contains()	Checks whether a string contains a sequence of characters	boolean
        String myStr = "Hello";
        System.out.println(myStr.contains("Hel"));   // true
        System.out.println(myStr.contains("e"));     // true
        System.out.println(myStr.contains("Hi"));    // false
        
        // indexOf()	Returns the position of the first found occurrence of specified characters in a string	int
        String myStr = "Hello planet earth, you are a great planet.";
        System.out.println(myStr.indexOf("planet"));
        
        // join()	Joins one or more strings with a specified separator	String
        String fruits = String.join(" ", "Orange", "Apple", "Mango");
        System.out.println(fruits);
        
        // replace()	Searches a string for a specified value, and returns a new string where the specified values are replaced	String
        String myStr = "Hello";
        System.out.println(myStr.replace('l', 'p'));

        
        String myStr = "I love cats. Cats are very easy to love. Cats are very popular.";
        String regex = "(?i)cat";
        System.out.println(myStr.replaceAll(regex, "dog"));

        // split()	Splits a string into an array of substrings	String[]
        String myStr = "Split a string by spaces, and also punctuation.";
        String regex = "[,\\.\\s]";
        String[] myArray = myStr.split(regex);
        for (String s : myArray) {
            System.out.println(s);
        }

        // substring()	Returns a new string which is the substring of a specified string	String
        String myStr = "Hello, World!";
        System.out.println(myStr.substring(7, 12));

        // toCharArray()	Converts this string to a new character array	char[]
        String myStr = "Hello";
        char[] myArray = myStr.toCharArray();
        System.out.println(myArray[0]);

        // toLowerCase()	Converts a string to lower case letters	String
        // toString()	Returns the value of a String object	String
        String myStr = "Hello, World!";
        System.out.println(myStr.toString());

        // toUpperCase()	Converts a string to upper case letters	String
        // trim()	Removes whitespace from both ends of a string	String
        String myStr = "       Hello World!       ";
        System.out.println(myStr);
        System.out.println(myStr.trim());
        // valueOf()	Returns the string representation of the specified value	String
        char[] myArray = {'a', 'b', 'c'};
        System.out.println(String.valueOf(myArray));
        System.out.println(String.valueOf('A'));
        System.out.println(String.valueOf(true));
        System.out.println(String.valueOf(4.5f));
        System.out.println(String.valueOf(5.2));
        System.out.println(String.valueOf(12));
        System.out.println(String.valueOf(1400L));


        // Math Method

        // abs(x)	Returns the absolute value of x	double|float|int|long
        System.out.println(Math.abs(-4.7));
        System.out.println(Math.abs(4.7));
        System.out.println(Math.abs(3)); 

        // acos(x)	Returns the arccosine of x, in radians	double
        // addExact(x, y)	Returns the sum of x and y	int|long
        // asin(x)	Returns the arcsine of x, in radians	double
        // atan(x)	Returns the arctangent of x as a numeric value between -PI/2 and PI/2 radians	double
        // atan2(y,x)	Returns the angle theta from the conversion of rectangular coordinates (x, y) to polar coordinates (r, theta).	double
        // cbrt(x)	Returns the cube root of x	double
        // ceil(x)	Returns the value of x rounded up to its nearest integer	double
        // copySign(x, y)	Returns the first floating point x with the sign of the second floating point y	double|float
        // cos(x)	Returns the cosine of x (x is in radians)	double
        // cosh(x)	Returns the hyperbolic cosine of a double value	double
        // decrementExact(x)	Returns x-1	int|long
        // exp(x)	Returns the value of Ex	double
        // expm1(x)	Returns ex -1	double
        // floor(x)	Returns the value of x rounded down to its nearest integer	double
        // floorDiv(x, y)	Returns the division between x and y rounded down	int|long
        // floorMod(x, y)	Returns the remainder of a division between x and y where the result of the division was rounded down	int|long
        // getExponent(x)	Returns the unbiased exponent used in x	int
        // hypot(x, y)	Returns sqrt(x2 +y2) without intermediate overflow or underflow	double
        // IEEEremainder(x, y)	Computes the remainder operation on x and y as prescribed by the IEEE 754 standard	double
        // incrementExact(x)	Returns x+1	int|double
        // log(x)	Returns the natural logarithm (base E) of x	double
        // log10(x)	Returns the base 10 logarithm of x	double
        // log1p(x)	Returns the natural logarithm (base E) of the sum of x and 1	double
        // max(x, y)	Returns the number with the highest value	double|float|int|long
        // min(x, y)	Returns the number with the lowest value	double|float|int|long
        // multiplyExact(x, y)	Returns the result of x multiplied with y	int|long
        // negateExact(x)	Returns the negation of x	int|long
        // nextAfter(x, y)	Returns the floating point number adjacent to x in the direction of y	double|float
        // nextDown(x)	Returns the floating point value adjacent to x in the negative direction	double|float
        // nextUp(x)	Returns the floating point value adjacent to x in the direction of positive infinity	double|float
        // pow(x, y)	Returns the value of x to the power of y	double
        // random()	Returns a random number between 0 and 1	double
        // rint(x)	Returns the double value that is closest to x and equal to a mathematical integer	double
        // round(x)	Returns the value of x rounded to its nearest integer	long|int
        // scalb(x, y)	Returns x multiplied by 2 to the power of y	double|float
        // signum(x)	Returns the sign of x	double|float
        // sin(x)	Returns the sine of x (x is in radians)	double
        // sinh(x)	Returns the hyperbolic sine of a double value	double
        // sqrt(x)	Returns the square root of x	double
        // subtractExact(x, y)	Returns the result of x minus y	int|long
        // tan(x)	Returns the tangent of an angle	double
        // tanh(x)	Returns the hyperbolic tangent of a double value	double
        // toDegrees(x)	Converts an angle measured in radians to an approx. equivalent angle measured in degrees	double
        // toIntExact(x)	Converts a long value to an int	int
        // toRadians(x)	Converts an angle measured in degrees to an approx. angle measured in radians	double
        // ulp(x)	Returns the size of the unit of least precision (ulp) of x	double|float







        // Array method

        // compare()	Compares two arrays
        String[] cars = {"Volvo", "BMW", "Tesla"};
        String[] cars2 = {"Volvo", "BMW", "Tesla"};

        System.out.println(Arrays.compare(cars, cars2)); 

        // copyOf()	Creates a copy of an array with a new length
        // deepEquals()	Compares two multidimensional arrays to check whether they are deeply equal to each other
        // equals()	Checks if two arays are equal
        // fill()	Fills an array with a specified value
        // mismatch()	Returns the index position of the first mismatch/conflict between two arrays
        
        // sort()	Sorts an array in ascending order
        String[] cars = {"Volvo", "BMW", "Tesla", "Ford", "Fiat", "Mazda", "Audi"};
        Arrays.sort(cars);





        
        //arraylist method

        // add()	Add an item to the list	boolean|void
        // addAll()	Add a collection of items to the list	boolean
        // clear()	Remove all items from the list	void
        // clone()	Create a copy of the ArrayList	Object
        // contains()	Checks whether an item exist in the list	boolean
        // ensureCapacity()	Increase the capacity of the list to be able to fit a specified number of items	void
        // forEach()	Perform an action on every item in the list	void
        // get()	Return the item at a specific position in the list	T
        // indexOf()	Return the position of the first occurrence of an item in the list	int
        // isEmpty()	Checks whether the list is empty	boolean
        // iterator()	Return an Iterator object for the ArrayList	Iterator
        // lastIndexOf()	Return the position of the last occurrence of an item in the list	int
        // listIterator()	Return a ListIterator object for the ArrayList	ListIterator
        // remove()	Remove an item from the list	boolean|T
        // removeAll()	Remove a collection of items from the list	boolean
        // removeIf()	Remove all items from the list which meet a specified condition	boolean
        // replaceAll()	Replace each item in the list with the result of an operation on that item	void
        // retainAll()	Remove all elements from the list which do not belong to a specified collection	boolean
        // set()	Replace an item at a specified position in the list	T
        // size()	Return the number of items in the list	int
        // sort()	Sort the list	void
        // spliterator()	Return a Spliterator object for the ArrayList	Spliterator
        // subList()	Return a sublist which provides access to a range of this list's items	List
        // toArray()	Return an array containing the list's items	Object[]
        // trimToSize()	Reduce the capacity of the list to match the number of items if necessary	Object[]

    }
}
