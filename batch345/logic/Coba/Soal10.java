package Coba;

import java.util.Scanner;

public class Soal10 {
    /*
     * Input: string kalimat
     * Output: banyaknya group konsonal-vokal
     * 
     * Example 1:
     * string s = "Apa kabar kalian semua ?";
     * output = 7
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        banyakKonsonalSetelahVokal(s);

        sc.close();
    }

    public static void banyakKonsonalSetelahVokal(String s) {
        int count = 0;
        String s1 = s.toLowerCase().replaceAll("[^a-z]", "");

        String vowels = "aiueo";

        for (int i = 0; i < s1.length() - 1; i++) {
            char currentChar = s1.charAt(i);
            char nextChar = s1.charAt(i + 1);

            if (!vowels.contains(String.valueOf(currentChar))
                    && vowels.contains(String.valueOf(nextChar))) {
                System.out.printf("%s -> %s\n", currentChar, nextChar);
                count++;
            }
        }
        System.out.println(count);
    }
}
