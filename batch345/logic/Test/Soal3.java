package Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;
public class Soal3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("masukkan kalimat : ");
        String kalimat = input.nextLine();
        System.out.print("n : ");
        int n = input.nextInt();
        soal3(kalimat, n);
        input.close();
    }

    public static void soal3(String kalimat, int n){
        String result = "";
        kalimat = kalimat.replaceAll("[^A-Za-z]", "");
        kalimat = kalimat.replaceAll("[\\s]", "");
        System.out.println(kalimat);

    //    if (Pattern.matches("[A-Za-z]", kalimat)) {
            for (int i = 1; i < 1+kalimat.length()/n; i+=n) {
                result += kalimat.substring(i, i+n+1) + " ";
                System.err.println(result);
            }
            String[] arrResult = result.split(" ");
            Arrays.sort(arrResult);
            // }
            for (String string : arrResult) {
                
                System.out.println(string);
            }
       
    }
}
