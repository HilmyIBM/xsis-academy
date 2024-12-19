import java.util.ArrayList;
import java.util.Scanner;

public class Day05AssignmentPR {

  private static Scanner input = new Scanner(System.in);
  public static void main(String[] args) {
    
  }

  public static void SoalPertama() {
    System.out.print("Input >> ");
    String[] s = input.nextLine().trim().split(",");
    ArrayList<Integer> numArrayList = ConvertToInt(s);
    Integer[] numArray = new Integer[numArrayList.size()-1];

    
    
  }

  public static SortCheck()
  public static ArrayList<Integer> ConvertToInt(String[] str){
    ArrayList<Integer> intNum = new ArrayList<Integer>();
    for (int i = 0; i < str.length; i++){
      intNum.add(Integer.parseInt(str[i]));
    }
    return intNum;
  }
}
