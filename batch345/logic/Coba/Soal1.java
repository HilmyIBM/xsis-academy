package Coba;

import java.util.*;

public class Soal1 {

    public static void tambahLoop(String[] n) {
        int result = 0;
        for (int i = 0; i < n.length; i++) {
            result += Integer.parseInt(n[i]);
            if (i == 0) {
                System.out.println(n[i]);
            } else {
                for (int j = 0; j <= i; j++) {
                    System.out.print(n[j]);
                    if (j == i) {
                        System.out.print(" = " + result);
                    } else {
                        System.out.print(" + ");
                    }
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Masukkan deret angka : ");
        String input = sc.nextLine();
        String[] nums = input.split(" ");
        sc.close();
        tambahLoop(nums);
    }
}
