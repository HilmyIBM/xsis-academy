import java.util.ArrayList;
import java.util.Scanner;

public class Day03Assignment {
  public static void main(String[] args) {
    SoalKelima();
  }

  public static void SoalPertama(){
    Scanner input = new Scanner(System.in);
    System.out.println("Pohon Faktor");
    System.out.print("Input >> ");
    int number = Integer.parseInt(input.nextLine());
    input.close();
    PohonFaktor(number);
    
  }

  public static void PohonFaktor(int x){
    int divider = 0;
    for(int i = 2; i<=x; i++){
      if (x % i == 0) {
        divider = i;
        break;
      }
    }
    int result = x / divider;
    System.out.println(x + " / " + divider + " = " + result);
    if (result != 1) {
      PohonFaktor(result);
    }
  }

  public static void SoalKedua(){
    Scanner input = new Scanner(System.in);
    System.out.print("Input >> ");
    int num = Integer.parseInt(input.nextLine());
    input.close();
    for (int i = 1; i <= num; i++){
      System.out.print(i + "\t");
      if (i == num) {
        System.out.println();
      }
    }
    for (int j = 1; j <= num-2; j++){
      for (int i = 1; i <= num; i++){
        if (i == 1) {
          System.out.print("*\t");
        } else if (i == num){
          System.out.println("*");
        } else {
          System.out.print("\t");
        }
      }
    }
    for (int i = num; i >= 1; i--){
      System.out.print(i + "\t");
      if (i == 1) {
        System.out.println();
      }
    }
  }

  public static void SoalKetiga(){
    Scanner input = new Scanner(System.in);
    System.err.print("Input >> ");
    int num = Integer.parseInt(input.nextLine());
    input.close();
    int result = 3;
    for (int i = 1; i <= num; i++) {
      System.out.print(result + "\t");
      result = result * 3;
    }
    System.out.println();
  }

  public static void SoalKeempat() {
    Scanner input = new Scanner(System.in);
    System.out.print("Input >> ");
    int num = Integer.parseInt(input.nextLine());
    input.close();
    for (int i = 1; i <= num; i++) {
      if (i % 2 == 0) {
        System.out.print(5*i + "\t");
      } else {
        System.out.print(5*i*-1 + "\t");
      }
    }
  }

  public static void SoalKelima() {
    Scanner input = new Scanner(System.in);
    System.out.print("Input >> ");
    int num = Integer.parseInt(input.nextLine());
    
    input.close();
    ArrayList<Integer> resultSequence = new ArrayList<Integer>();
    for (int i = 0; i < num; i++){
      if (i < 2){
        resultSequence.add(1);
      } else {
        resultSequence.add(resultSequence.get(i-1) + resultSequence.get(i-2));
      }
    }
    for (int i = 0; i < resultSequence.size(); i++){
      if (i == 0) {
        System.out.print(resultSequence.get(i));
      } else {
        System.out.print(", " + resultSequence.get(i));
      }
      
    }
  }

  public static void SoalKeenam() {
    Scanner input = new Scanner(System.in);
    System.out.print("Input >> ");
    int num = Integer.parseInt(input.nextLine());
    
    input.close();
    ArrayList<Integer> resultSequence = new ArrayList<Integer>();
    for (int i = 0; i < num; i++){
      if (i < 3){
        resultSequence.add(1);
      } else {
        resultSequence.add(resultSequence.get(i-1) + resultSequence.get(i-2) + resultSequence.get(i-3));
      }
    }
    for (int i = 0; i < resultSequence.size(); i++){
      if (i == 0) {
        System.out.print(resultSequence.get(i));
      } else {
        System.out.print(", " + resultSequence.get(i));
      }
      
    }
  }
}
