package FT1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Soal7 {
    public static void main(String[] args) {
        no7();
    }

    public static void no7(){
        Scanner scan = new Scanner(System.in);

        System.out.print("string: ");
        String input = scan.nextLine();

        input = input.replaceAll("[,\"'@/&]", "");
        //System.out.println(input);

        List<String> arrList = new ArrayList<>();
        String inputArr[] = input.split(" ");
        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < inputArr.length; i++){
            if (!arrList.contains(inputArr[i])){

                arrList.add(inputArr[i]);
                ans.append(inputArr[i] + " ");
            }
        }
        System.out.print("output: " + ans);

        // for (int j = 0; j < arrList.size(); j++){
        //     System.out.print(arrList.get(j)+ " ");
        // }
    }
}
