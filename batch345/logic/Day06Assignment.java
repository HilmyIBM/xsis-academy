import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day06Assignment {
  private static Scanner input = new Scanner(System.in);

  public static void main(String[] args) {
    soalKetiga();
  }

    public static void soalPertama() {
      System.out.print("Jumlah Langkah : ");
      int numSteps = Integer.parseInt(input.nextLine());
      System.out.print("Input Langkah (U/D) : ");
      char[] steps = input.nextLine().toUpperCase().toCharArray();
      int level = 0; // Sea Level
      int keluarLembah = 0;
      
      for (int i = 0; i < numSteps; i++) {
        if(steps[i] == 'U'){
          level++;
          if(level == 0){
            keluarLembah++;
          }
        }else if (steps[i] == 'D'){
          level--;
        }
      }

      System.out.println("Keluar lembah " + keluarLembah + " kali");
      
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
    System.out.print("Input Panjang >> ");
    String[] tinggi = input.nextLine().split(" ");
    int[] intTinggi = convertToInt(tinggi);
    System.out.print("Input Text >> ");
    char[] charText = input.nextLine().toLowerCase().toCharArray();
    int maxValue = 0;
    for (int i = 0; i < charText.length; i++){
      int intChar = charText[i] - 'a';
      if (i == 0){
        maxValue = intTinggi[intChar];
      } else {
        if(intTinggi[intChar] > maxValue){
          maxValue = intTinggi[intChar];
        }
      }
    }
    System.out.println(charText.length * maxValue);

  }

  public static int[] convertToInt(String[] str){
    int[] intArray = new int[str.length];
    for(int i = 0; i < str.length; i++) {
      intArray[i] = Integer.parseInt(str[i]);
    }
    return intArray;
  }

  public static void soalKeempat(){
    System.out.print("Input >> ");
    String s = input.nextLine().trim().replaceAll(" ", "").toLowerCase();
    String sortedVowels = extractSortLetter(s, true);
    String sortedConsonant = extractSortLetter(s, false);
    System.out.println(sortedConsonant);
    System.err.println(sortedVowels);

  }


  public static String extractSortLetter(String s, boolean isVowel){
    String letterFound = "";
    String vowelSet = "aeiou";
    
    if(isVowel){
      for (char c : s.toCharArray() ){
        if (vowelSet.indexOf(c) != -1) {
          letterFound += c;
        }
      }
    }else{
      for (char c : s.toCharArray() ){
        if (vowelSet.indexOf(c) == -1 && Character.isLetter(c)) {
          letterFound += c;
        }
      }
    }
    
    return sortString(letterFound);
  }

  private static String sortString(String input) {
    char[] charArray = input.toCharArray();
    Arrays.sort(charArray);
    return new String(charArray);
  }

  public static void soalKelima() {
    
  }


}
