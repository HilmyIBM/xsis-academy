package ft1;

import java.util.Random;
import java.util.Scanner;

public class Soal5 {
    public static void soal5(int n, int m) {
        int[] vote = new int[m + 1];
        Random ran = new Random();
        for (int i = 0; i < n; i++) {
            int pilihan = ran.nextInt(m + 1);
            vote[pilihan]++;
        }
        for (int i = 0; i < vote.length; i++) {
            double persen = vote[i] * 100 / (double) n;
            if (i == vote.length - 1) {
                System.out.println(String.format("Golput: %d (%.2f%%)", vote[i], persen));
            } else {
                System.out.println(String.format("Calon no. urut %d: %d suara (%.2f%%)", (i + 1), vote[i], persen));
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Masukkan n = ");
        int n = sc.nextInt();
        System.out.print("Masukkan m = ");
        int m = sc.nextInt();
        sc.close();
        soal5(n, m);
    }

}
