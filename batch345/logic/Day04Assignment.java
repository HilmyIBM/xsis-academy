import java.util.Scanner;

public class Day04Assignment {
  public static void main(String[] args) {
    SoalKelima();
  }

  public static void SoalPertama() {
    Scanner input = new Scanner(System.in);
    System.out.print("Input >> ");
    String s = input.nextLine();
    input.close();
    String[] splitString = s.split(" ");
    for (int i = 1; i <= splitString.length; i++){
      System.out.println("kata " + i + " = " + splitString[i-1]);
    }
  }

  public static void SoalKedua() {
    Scanner input = new Scanner(System.in);
    System.out.print("Input >> ");
    char[] s = input.nextLine().toCharArray();
    input.close();
    int uCount = 0;
    int upperCaseCount = 0;
    for (int i = 0; i < s.length; i++){
      if (s[i] == 'u' || s[i] == 'U'){
        uCount++;
      }

      if (Character.isUpperCase(s[i])){
        upperCaseCount++;
      }
    }

    System.out.println("huruf u ada " + uCount);
    System.out.println("huruf kapital ada " + upperCaseCount);
  }

  public static void SoalKetiga(){
    Scanner input = new Scanner(System.in);
    System.out.print("Input >> ");
    String[] separatedStrings = input.nextLine().split(" ");
    String finalString = "";
    input.close(); 
    for (int i = 0; i < separatedStrings.length; i++){
      String transformedString = AddAsteriskInBetween(separatedStrings[i]);
      if (i != separatedStrings.length-1){
        finalString = finalString + transformedString + " ";
      } else {
        finalString = finalString + transformedString;
      }
    }
    System.out.println(finalString);
  }

  public static String AddAsteriskInBetween(String s){
    char[] string = s.toCharArray();
    for (int i = 0; i < string.length; i++){
      if(i != 0 && i != string.length-1){
        string[i] = '*';
      }
    }

    return String.valueOf(string);
  }

  public static void SoalKeempat(){
    Scanner input = new Scanner(System.in);
    System.out.print("Input >> ");
    String[] separatedStrings = input.nextLine().split(" ");
    String finalString = "";
    input.close(); 
    for (int i = 0; i < separatedStrings.length; i++){
      String transformedString = AddAsterisk(separatedStrings[i]);
      if (i != separatedStrings.length-1){
        finalString = finalString + transformedString + " ";
      } else {
        finalString = finalString + transformedString;
      }
    }
    System.out.println(finalString);
  }

  public static String AddAsterisk(String s){
    char[] string = s.toCharArray();
    for (int i = 0; i < string.length; i++){
      if(i == 0 || i == string.length-1){
        string[i] = '*';
      }
    }

    return String.valueOf(string);
  }

  public static void SoalKelima(){
    Scanner input = new Scanner(System.in);
    System.out.print("Input >> ");
    String[] separatedStrings = input.nextLine().split(" ");
    String finalString = "";
    input.close(); 
    for (int i = 0; i < separatedStrings.length; i++){
      String transformedString = LeaveThreeLetters(separatedStrings[i]);
      if (i != separatedStrings.length-1){
        finalString = finalString + transformedString + " ";
      } else {
        finalString = finalString + transformedString;
      }
    }
    System.out.println(finalString);
  }

  public static String LeaveThreeLetters(String s) {
    char[] string = s.toCharArray();
    for (int i = 0; i < string.length; i++){
      if(!(i == string.length-1 || i == string.length-2 || i == string.length-3)){
        string[i] = ' ';
      }
    }

    return String.valueOf(string).trim();
  }
}
