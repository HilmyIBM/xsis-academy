import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AlgobashDay05 {
    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        int num = 0;
        System.out.println("Enter exercise number:\n");
        Scanner scan = new Scanner(System.in);
        num = scan.nextInt();

        switch (num) {
            case 1:
                no1();
                break;

            case 2:
                no2();
                break;

            case 3:
                no3();
                break;

            case 4:
                no4();
                break;

            case 5:
                no5();
                break;

        }
        scan.close();

    }


    //list combination stored in reverse order
    public static void no1(){
        solution1(List.of(2, 4, 3), List.of(5, 6, 4));
    }


    //reduced fraction to simplest form
    public static void no2(){
        solution2("3/18");
    }


    //array intersection returns no duplicates
    public static void no3(){
        solution3(List.of(1, 2, 2, 1), List.of(2, 2));
    }


    //most frequent alphabet, if equal show smallest letter (ascending)
    public static void no4(){
        solution4("ayo hello lebron");
    }

    
    //matrix diagonal difference
    public static void no5(){
        solution5(List.of(
            List.of(3, 1, 2),
            List.of(5, 6, 4),
            List.of(7, 9, 8)
        ));
    }



    public static List<Integer> solution1(List<Integer> l1, List<Integer> l2) {
    // Your code starts here.

    List<Integer> ans = new ArrayList<Integer> ();
    int size = (l1.size() > l2.size()) ? l1.size() : l2.size();
    int carryover = 0;
    for (int i = 0 ; i < size; i++){
      if (i >= l1.size() && i <= l2.size()){

        if (l2.get(i) + carryover >= 10){
          ans.add((l2.get(i)+ carryover)%10);
          carryover = 1;
        } else {
          ans.add(l2.get(i) + carryover);
          carryover = 0;
          
        }
        
      } else if (i <= l1.size() && i >= l2.size()){
        if (l1.get(i) + carryover >= 10){
          ans.add((l1.get(i)+ carryover)%10);
          carryover = 1;
        } else {
          ans.add(l1.get(i) + carryover);
          carryover = 0;
          
        }


      } else {
        if (l1.get(i) + l2.get(i) + carryover >= 10){
          ans.add((l1.get(i) + l2.get(i)+ carryover)%10);
          carryover = 1;
        } else {
          ans.add(l1.get(i) + l2.get(i) + carryover);
          carryover = 0;
          
        }

      }


      }
        if (carryover !=0){
          ans.add(carryover);
        }
    System.out.println(ans);
    return ans;
  }



  public static String solution2(String fraction) {
    // Your code starts here.
    String inputString[] = fraction.split("/");
    Integer intArr[] = new Integer [inputString.length];
    String ans = "";
    
    for (int i = 0; i < intArr.length; i++){
    intArr[i] = Integer.parseInt(inputString[i]);
    }

    int minNum = (intArr[0] > intArr[1]) ? intArr[1] : intArr[0];
    int divisor = 1;

    if (intArr[0]%intArr[1] == 0){
      ans = ""+(intArr[0]/intArr[1]);
    } else {
      for (int i = 2; i < minNum; i++){
        if (intArr[0]%i == 0 && intArr[1]%i == 0){
          divisor = i;
        }
      }
      ans = (intArr[0]/divisor) + "/" + (intArr[1]/divisor);
    }
    System.out.println(ans);
    return ans;
  }


  public static List<Integer> solution3(List<Integer> nums1, List<Integer> nums2) {
    // Your code starts here.

    List<Integer> ans = new ArrayList<Integer>();

    for (int i = 0; i < nums1.size(); i++){
      for (int j = 0; j < nums2.size(); j++){
        if (nums1.get(i) == nums2.get(j)){
          if (!ans.contains(nums1.get(i))){
          ans.add(nums1.get(i));
          }
        }
      }
    }

    System.out.println(ans);
    return ans;
  }

  public static String solution4(String Input) {
    // Your code starts here.
    Input = Input.toLowerCase().replaceAll("[^A-Za-z]", "");
    Map<String, Integer> freqMap = new HashMap<>();

    int maxCount = 0;
    String maxAlphabet = "";

    String[] inputArr = Input.split("");

    for (int i = 0; i < inputArr.length; i++){
      freqMap.put(inputArr[i], freqMap.getOrDefault(inputArr[i], 0)+1) ;
    }

    for (Map.Entry<String, Integer> entry : freqMap.entrySet()) {
      String key = entry.getKey();
      int frequency = entry.getValue();

      if (frequency > maxCount || (frequency == maxCount && key.compareTo(maxAlphabet) < 0)) {
        maxCount = frequency;
        maxAlphabet = key;
      }
    }

    System.out.println(maxAlphabet);
    return maxAlphabet;
  }


  public static int solution5(List<List<Integer>> a) {
    // Your code starts here.
    int n = a.size(); // The matrix is n x n
    int primaryDiagonalSum = 0;
    int secondaryDiagonalSum = 0;

    for (int i = 0; i < n; i++) {
      // Add the primary diagonal element
      primaryDiagonalSum += a.get(i).get(i);
      
      // Add the secondary diagonal element
      secondaryDiagonalSum += a.get(i).get(n - i - 1);
    }

    System.out.println(Math.abs(primaryDiagonalSum - secondaryDiagonalSum));
    // Return the absolute difference
    return Math.abs(primaryDiagonalSum - secondaryDiagonalSum);
  }
}
