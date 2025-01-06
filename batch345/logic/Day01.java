public class Day01 {
    public static void main(String[] args){
        // Nomor 1
        double r = 28.0;
        System.out.println("Keliling Lingkaran dengan jari-jari " + r + " adalah : " + kelilingLingkaran(r));
        System.out.println("Luas Lingkaran dengan jari-jari " + r + " adalah : " + luasLingkaran(r) + "\n");
        
        //Nomor 2
        double sisi = 12.0;
        System.out.println("Keliling Persegi dengan panjang sisi " + sisi + " adalah : " + kelilingPersegi(sisi));
        System.out.println("Luas Persegi dengan jari-jari " + r + " adalah : " + luasPersegi(sisi));

        //Nomor 3
        int angka1 = 12;
        int angka2 = 2;
        System.out.println(hasilMod(angka1,  angka2));

        //Nomor 4
        int jumlahPuntung = 150;    
        System.out.println(hitungRokok(jumlahPuntung));

    }

    public static double kelilingLingkaran(double r){
        double phi = 22/7.0;
        return 2 * phi * r;
    }

    public static double luasLingkaran(double r){
        double phi = 22/7.0;
        return phi * r * r;
    }

    public static double luasPersegi(double s){
        return s * s;
    }
    
    public static double kelilingPersegi(double s){
        return s * 4;
    }

    public static String hasilMod(int i, int j){
        if (i % j == 0) {
            return "\nangka " + i + " % dengan angka " + j + " adalah 0\n";
        }else{
            return "\nangka " + i + " % dengan angka " + j + "bukan  0 melainkan " + i%j + "\n";
        }
    }
    
    public static String hitungRokok(int puntung){
        int banyakRokok = puntung/8;
        int sisaRokok = puntung % 8;
        int penghasilan = banyakRokok*500;
        return puntung + " Terkumpul\n" + banyakRokok + " Batang Rokok\n" + sisaRokok + " Sisa Puntung\n" + penghasilan + " Rupiah Dihasilkan"; 
    }    
}

// charAt()	Returns the character at the specified index (position)	char
String myStr = "Hello";
char result = myStr.charAt(0);
System.out.println(result);


// compareTo()	Compares two strings lexicographically	int
String myStr1 = "Hello";
String myStr2 = "Hello";
System.out.println(myStr1.compareTo(myStr2)); // Returns 0 because they are equal


// contains()	Checks whether a string contains a sequence of characters	boolean
String myStr = "Hello";
System.out.println(myStr.contains("Hel"));   // true
System.out.println(myStr.contains("e"));     // true
System.out.println(myStr.contains("Hi"));    // false


// contentEquals()	Checks whether a string contains the exact same sequence of characters of the specified CharSequence or StringBuffer	boolean
// copyValueOf()	Returns a String that represents the characters of the character array	String
// endsWith()	Checks whether a string ends with the specified character(s)	boolean
// equals()	Compares two strings. Returns true if the strings are equal, and false if not	boolean
// equalsIgnoreCase()	Compares two strings, ignoring case considerations	boolean
// format()	Returns a formatted string using the specified locale, format string, and arguments	String
// getBytes()	Converts a string into an array of bytes	byte[]
// getChars()	Copies characters from a string to an array of chars	void
// hashCode()	Returns the hash code of a string	int
// indexOf()	Returns the position of the first found occurrence of specified characters in a string	int
// intern()	Returns the canonical representation for the string object	String
// isEmpty()	Checks whether a string is empty or not	boolean
// join()	Joins one or more strings with a specified separator	String
// lastIndexOf()	Returns the position of the last found occurrence of specified characters in a string	int
// length()	Returns the length of a specified string	int
// matches()	Searches a string for a match against a regular expression, and returns the matches	boolean
// offsetByCodePoints()	Returns the index within this String that is offset from the given index by codePointOffset code points	int
// regionMatches()	Tests if two string regions are equal	boolean
// replace()	Searches a string for a specified value, and returns a new string where the specified values are replaced	String
// replaceAll()	Replaces each substring of this string that matches the given regular expression with the given replacement	String
// replaceFirst()	Replaces the first occurrence of a substring that matches the given regular expression with the given replacement	String

// split()	Splits a string into an array of substrings	String[]
String myStr = "Split a string by spaces, and also punctuation.";
String regex = "[,\\.\\s]";
String[] myArray = myStr.split(regex);
for (String s : myArray) {
  System.out.println(s);
}


// startsWith()	Checks whether a string starts with specified characters	boolean
// subSequence()	Returns a new character sequence that is a subsequence of this sequence	CharSequence
// substring()	Returns a new string which is the substring of a specified string	String
// toCharArray()	Converts this string to a new character array	char[]
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