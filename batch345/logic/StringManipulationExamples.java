public class StringManipulationExamples {
  public static void main(String[] args) {
      // Creating Strings
      String str1 = "Hello";
      String str2 = new String("World");
      System.out.println("String 1: " + str1);
      System.out.println("String 2: " + str2);

      // String Concatenation
      String concatenated = str1 + " " + str2;
      System.out.println("Concatenated String: " + concatenated);

      // String Length
      System.out.println("Length of String 1: " + str1.length());

      // Character Access
      System.out.println("Character at index 1 in String 1: " + str1.charAt(1));

      // Substring
      System.out.println("Substring of String 2 from index 1 to 4: " + str2.substring(1, 4));

      // String Comparison
      System.out.println("String 1 equals String 2? " + str1.equals(str2));
      System.out.println("String 1 equals 'Hello' (ignore case)? " + str1.equalsIgnoreCase("hello"));

      // String Starts and Ends
      System.out.println("String 1 starts with 'He'? " + str1.startsWith("He"));
      System.out.println("String 2 ends with 'ld'? " + str2.endsWith("ld"));

      // Index of Characters
      System.out.println("Index of 'l' in String 1: " + str1.indexOf('l'));
      System.out.println("Last index of 'l' in String 1: " + str1.lastIndexOf('l'));

      // String Replacement
      System.out.println("String 1 after replacing 'l' with 'p': " + str1.replace('l', 'p'));

      // Case Conversion
      System.out.println("String 1 in uppercase: " + str1.toUpperCase());
      System.out.println("String 1 in lowercase: " + str1.toLowerCase());

      // Trimming
      String str3 = "   Java Programming   ";
      System.out.println("Trimmed String: " + str3.trim());

      // Splitting Strings
      String str4 = "apple,banana,cherry";
      String[] fruits = str4.split(",");
      System.out.println("Split String:");
      for (String fruit : fruits) {
          System.out.println(fruit);
      }

      // String Joining
      String joined = String.join("-", fruits);
      System.out.println("Joined String: " + joined);

      // Converting Strings to Numbers
      String numStr = "123";
      int num = Integer.parseInt(numStr);
      System.out.println("Converted String to int: " + num);

      // Converting Numbers to Strings
      int number = 456;
      String numToStr = String.valueOf(number);
      System.out.println("Converted int to String: " + numToStr);

      // Checking if String is Empty or Blank
      String emptyStr = "";
      String blankStr = "   ";
      System.out.println("Is empty string empty? " + emptyStr.isEmpty());
      System.out.println("Is blank string blank? " + blankStr.isBlank());

      // StringBuilder and StringBuffer Usage
      StringBuilder sb = new StringBuilder("Hello");
      sb.append(" World");
      System.out.println("StringBuilder result: " + sb.toString());

      StringBuffer sbf = new StringBuffer("Hello");
      sbf.insert(5, " Java");
      System.out.println("StringBuffer result: " + sbf.toString());

      // Reversing a String
      System.out.println("Reversed StringBuilder: " + sb.reverse().toString());

      // Palindrome Check
      String palindrome = "madam";
      System.out.println("Is 'madam' a palindrome? " + isPalindrome(palindrome));
  }

  // Utility Method for Palindrome Check
  public static boolean isPalindrome(String str) {
      int start = 0;
      int end = str.length() - 1;
      while (start < end) {
          if (str.charAt(start) != str.charAt(end)) {
              return false;
          }
          start++;
          end--;
      }
      return true;
  }
}
