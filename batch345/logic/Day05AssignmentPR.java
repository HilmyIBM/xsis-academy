import java.util.Scanner;

public class Day05AssignmentPR {

  private static Scanner input = new Scanner(System.in);
  public static void main(String[] args) {
    soalPertama();
  }

  public static void soalPertama() {
    System.out.print("Input >> ");
    String[] s = input.nextLine().split(",");
    int[] nums = convertToInt(s);
    int[] sortedNums = bubbleSorting(nums);
    System.out.print("Output >> ");
    printArray(sortedNums);
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
