import java.util.ArrayList;
import java.util.Scanner;

public class Day08AssignmentPR {
  private static Scanner input = new Scanner(System.in);
  public static void main(String[] args) {
    soalKedua();
  }

  public static void soalPertama(){
    System.out.print("i = ");
    int startDay = Integer.parseInt(input.nextLine());
    System.out.print("j = ");
    int j = Integer.parseInt(input.nextLine());
    System.out.print("k = ");
    int k = Integer.parseInt(input.nextLine());

    ArrayList<Integer> niceDays = new ArrayList<>();

    for (int i = startDay;i <= j; i++){
      double result = (double) Math.abs(i-reverseInteger(i)) / k;
      if (result == Math.floor(result)){
        niceDays.add(i);
      }
    }

    for(int i = 0; i<niceDays.size();i++){
      if (i == niceDays.size()-1 ){
        System.out.println(niceDays.get(i));
      } else {
        System.out.print(niceDays.get(i) + ", ");
      }
    }

  }
  
  public static int reverseInteger(int number) {
    String strNumber = Integer.toString(number);
    String reversedStr;
    if (number < 0) {
        reversedStr = "-" + new StringBuilder(strNumber.substring(1)).reverse();
    } else {
        reversedStr = new StringBuilder(strNumber).reverse().toString();
    }

    return Integer.parseInt(reversedStr);

  }

  public static void soalKedua() {
    System.out.print("Uang Bambang : Rp ");
    int uangBambang = Integer.parseInt(input.nextLine());
    int numAffordable = (int) Math.floor(uangBambang/1000);
    int numBonus = (int) Math.floor(numAffordable/5);
    while (numBonus >= 5) {
      numBonus += Math.floor(numBonus/5);
    }
    int esLoliBambang = numAffordable+numBonus;
    System.out.println("Jumlah maksimal es loli yang bisa dibeli Bambang dengan uang Rp "+ uangBambang + " adalah " + esLoliBambang +  " stik es loli");
  }
}
