import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day06Assignment {
  private static Scanner input = new Scanner(System.in);

  public static void main(String[] args) {
    soalKedua();
  }

  public static void soalPertama() {
    System.out.print("Jumlah Langkah : ");
    int numSteps = Integer.parseInt(input.nextLine());
    char[] steps = input.nextLine().toUpperCase().toCharArray();
    int level = 0; // Sea Level

    // for(int i = 0; i < numSteps ){
    //   if(steps[i] ==)
    // }
    
  }

  public static void soalKedua(){
    System.out.print("Original Text : ");
    String s = input.nextLine();
    System.out.print("Rotation : ");
    int rot = Integer.parseInt(input.nextLine());
    String cipheredText = caesarCipher(s, rot);
    System.out.println("Ciphered Text : " + cipheredText);
    
  }

  public static String caesarCipher(String s, int shift){
    char[] sChar = s.toCharArray();
    for (int i = 0; i < sChar.length; i++){
      if (Character.isUpperCase(sChar[i])){
        sChar[i] = (char) ((sChar[i] - 'A' + shift) % 26 + 'A');
      }
      if (Character.isLowerCase(sChar[i])){
        sChar[i] = (char) ((sChar[i] - 'a' + shift) % 26 + 'a');
      }
      if (sChar[i] == ' '){
        sChar[i] = ' ';
      }
    }
    return new String(sChar);
  }



  public static void soalKetiga(){
    
  }

  public static void soalKeempat(){
    System.out.print("Input >> ");
    String s = input.nextLine().trim().replaceAll(" ", "").toLowerCase();


  }

  public static boolean isConsonant(char c){
    char[] vowels = {'a', 'i', 'u', 'e', 'o'};
    for (int i = 0; i < vowels.length; i++){
      if (c != vowels[i]){
        return false;
      }
    }

    return true;
  }

  public static void soalKelima() {

  }


}
