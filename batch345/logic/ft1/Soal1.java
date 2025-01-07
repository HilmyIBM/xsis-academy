package ft1;

import java.util.Scanner;

public class Soal1 {
    // Buatlah format penjumlahan seperti contoh di bawah ini,

    // Input: n (deret angka)
    // Output: penjumlahan seperti contoh
    // Constraint: angka bisa berupa bilangan bulat positif, bilangan bulat negatif,
    // ataupun desimal

    // Example 1:
    // n = 4 1 3
    // output:
    // 4
    // 4 + 1 = 5
    // 4 + 1 + 3 = 8

    // Example 2:
    // n = 2 2 3 0 8
    // output:
    // 2
    // 2 + 2 = 4
    // 2 + 2 + 3 = 7
    // 2 + 2 + 3 + 0 = 7
    // 2 + 2 + 3 + 0 + 8 = 15

    public static void soal1(String n) {
        int num = Integer.parseInt(n);
        for (int i = 1; i <= num; i++) {
            for (int j = 0; j < num - i; j++) {
                System.out.print("  ");
            }
            for (int j = 0; j < i; j++) {
                System.out.print(" *");
            }
            System.out.println();
        }

        // String[] strN = n.split(" ");
        // int[] numN = new int[strN.length];

        // for (int i = 0; i < numN.length; i++) {
        // numN[i] = Integer.parseInt(strN[i]);
        // }

        // for (int i = 0; i < numN.length; i++) {
        // int sum = 0;
        // if (i == 0) {
        // System.out.print(numN[i]);
        // } else {
        // for (int j = 0; j <= i; j++) {
        // sum += numN[j];
        // if (j == 0) {
        // System.out.print(numN[j]);
        // } else {
        // System.out.print(" + " + numN[j]);
        // if (j == i) {
        // System.out.print(" = " + sum);
        // }
        // }

        // }
        // }
        // System.out.println();
        // }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Masukkan deret nilai n = ");
        String input = sc.nextLine();
        sc.close();
        soal1(input);
    }
}