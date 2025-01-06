package Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;
public class Soal3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String test = input.nextLine();
        soal3(test);
        input.close();
    }

    public static void soal3(String test){
        test.toLowerCase();
        char[] testSort = test.toCharArray();
        Arrays.sort(testSort);
        test = new String(testSort);

        int [] hitung = new int[test.length()];
        
        for (int i = 0; i < test.length(); i++) {
            for (int j = 1; j < hitung.length; j++) {
                if (j==i) {
                    j++;
                }
                if (test.charAt(i) == test.charAt(j)) {
                    hitung[i]++;
                }
            }
        }
    }
}
