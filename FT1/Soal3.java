package FT1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Soal3 {
    public static void main(String[] args) {
        no3();
    }

    public static void no3(){
        System.out.print("Input: ");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        // scan.nextLine();
        
        System.out.print("Input n: ");
        int n = scan.nextInt();

        input = input.replaceAll("[^A-Za-z]", "");
        String strArr [] = input.split("");
        List <String> ans = new ArrayList<>();
        

        for (int i = 0; i < strArr.length; i+=n){
            
            if (i+n < strArr.length){
                ans.add(input.substring(i, i+n));
                
            } 
            
        }

        for (int j = strArr.length; j >=0; j-=n){
            if (j+-n > 0){
                ans.add(input.substring(j-n, j));
                
            } 

        }
        
        // for (int j = 0; j < strArr.length-1; j++){
        //     if (j >strArr.length){
        //         if (ans.get(j).compareTo(ans.get(j+1)) > 0){
        //             String temp = ans.get(j+1);
        //             ans.set(j+1, ans.get(j));
        //             ans.set(j, temp);
        //         }
        //     }
        // }
        // ans.sort(null);

        ans.sort(String.CASE_INSENSITIVE_ORDER);

       System.out.println(ans);
    }
}
