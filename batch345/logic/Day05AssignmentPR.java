import java.util.ArrayList;
import java.util.Scanner;

public class Day05AssignmentPR {

  private static Scanner input = new Scanner(System.in);
  public static void main(String[] args) {
    SoalKelima();
  }

  public static void soalPertama() {
    System.out.print("Input >> ");
    String[] s = input.nextLine().split(",");
    int[] nums = convertToInt(s);
    int[] sortedNums = bubbleSorting(nums);
    System.out.print("Output >> ");
    printArray(sortedNums);
  }

  public static void soalKedua(){
    System.out.print("Input >> ");
    int num = Integer.parseInt(input.nextLine());
    printPrimesBelow(num);
  }

    public static void soalKetiga(){
      int p, d, m, s, numGame;
      System.out.print("p = ");
      p = Integer.parseInt(input.nextLine());
      System.out.print("d = ");
      d = Integer.parseInt(input.nextLine());
      System.out.print("m = ");
      m = Integer.parseInt(input.nextLine());
      System.out.print("s = ");
      s = Integer.parseInt(input.nextLine());

      ArrayList<Integer> prices = priceStacking(p, d, m ,s);
      numGame = prices.size(); 
      System.out.println(numGame + " video game");
    }

    public static ArrayList<Integer> priceStacking(int p, int d, int m, int s){
      ArrayList<Integer> prices = new ArrayList<>();
      int totalSpent = 0;
      do{
        totalSpent += p;
        prices.add(p);
        if(p - d > m){
          p -= d;
        }else {
          p = m;
        }
      } while (totalSpent + p <= s);

      return prices; 
    }

  public static void soalKeempat(){
    System.out.print("Input >> ");
    int num = Integer.parseInt(input.nextLine());
    for (int i=1; i<=num; i++){
      for (int j=1; j<=num; j++){
        if(j >= num-i+1){
          System.out.print("#");
        }else{
          System.out.print(" ");
        }
      }
      System.out.println();
    }
  }

  public static void SoalKelima(){
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

  public static void printPrimesBelow(int num){
    for (int i = 2; i < num; i++){
      if (isPrime(i)){
        if (i == 2){
          System.out.print(i);
        } else {
          System.out.print(","+i);

        }
      }
    }
    System.out.println();
  }

  public static boolean isPrime(int num){
    if (num <= 1) {
      return false;
    } 
    double numSqrt = Math.sqrt(num);
    for (int i = 2; i <= numSqrt; i++) {
      if (num % i == 0) {
        return false;
      }
    };

    return true;
  }

  public static int[] convertToInt(String[] str){
    int[] intArray = new int[str.length];
    for(int i = 0; i < str.length; i++) {
      intArray[i] = Integer.parseInt(str[i]);
    }
    return intArray;
  }

  public static int[] bubbleSorting(int[] nums){
    int n = nums.length; 

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n-1-i; j++){
        if(nums[j] > nums[j+1]){
          int temp = nums[j];
          nums[j] = nums[j+1];
          nums[j+1] = temp;
        }
      }
    }
    return nums;
  }

  public static void printArray(int[] array){
    for(int i : array) {
      System.out.print(i + ",");
    }
    System.out.println();
  }
}
