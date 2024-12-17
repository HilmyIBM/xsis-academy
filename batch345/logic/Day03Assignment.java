import java.util.Scanner;

public class Day03Assignment {
  public static void main(String[] args) {
    SoalPertama();
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
    
  }
}
