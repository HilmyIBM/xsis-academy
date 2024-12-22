import java.util.Scanner;

public class Day06AssignmentPR {
  private static Scanner input = new Scanner(System.in);
  public static void main(String[] args) {
    soalKetiga();
  }

  public static void soalPertama(){
    System.out.print("Input >> ");
    String receivedSignal = input.nextLine();
    String correctSignal = "SOS";

    int falseCount = 0;

    for (int i = 0; i <receivedSignal.length(); i++){
      if(receivedSignal.charAt(i) != correctSignal.charAt(i % 3)){
        falseCount++;
      }
    }

    System.out.println("Total Sinyal Salah : " + falseCount);
  }

  public static void soalKedua() {
    System.out.print("Input >> ");
    String s = input.nextLine().toLowerCase();
    for(char c : s.toCharArray()){
      System.out.println("***"+c+"***");
    }
  }

  public static void soalKetiga(){
    System.out.print("Input ketinggian maksimal lompat >> ");
    int k = Integer.parseInt(input.nextLine());
    System.out.print("Input rintangan >> ");
    int[] obstacle = convertToInt(input.nextLine().split(" "));
    int maxValue = k;
    for (int i = 0; i < obstacle.length; i++){
      if (obstacle[i] > k && obstacle[i] > maxValue){
        maxValue = obstacle[i];
      }
    }
    
    int numPotion = maxValue - k;
    System.out.println(numPotion + " botol ajaib dibutuhkan");
  }

  public static int[] convertToInt(String[] str){
    int[] intArray = new int[str.length];
    for(int i = 0; i < str.length; i++) {
      intArray[i] = Integer.parseInt(str[i]);
    }
    return intArray;
  }
}
