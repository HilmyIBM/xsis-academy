package Coba;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Soal5 {
    /*
     * Sebuah negara dengan total penduduk n akan melakukan pemilihan calon anggota
     * legislatif. Sistem pemilu di negara tersebut menetapkan jumlah calon anggota
     * legislatif sebanyak m. Tampilkan perolehan suara dari masing-masing calon
     * anggota legislatif!
     * 
     * Input: n, m
     * Output: perolehan suara
     * Constraints:
     * - diurutkan berdasarkan suara terbanyak
     * - total suara dari seluruh calon anggota legislatif tidak boleh > dari n
     * (total penduduk)
     * - gunakan fungsi random sebanyak n untuk mendapatkan suara masing-masing
     * calon
     * - persentase sampai 2 digit dibelakang koma
     * 
     * Example 1:
     * n = 1000
     * m = 3
     * output:
     * Calon no. urut 1: 420 suara (42,00 %)
     * Calon no. urut 2: 122 suara (12,20 %)
     * Calon no. urut 3: 80 suara (8,00 %)
     * Golput: 378 (37,80 %)
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        perolehanSuara(n, m);
        sc.close();
    }

    public static void perolehanSuara(int n, int m) {
        int[] suara = new int[m + 1];
        Random rand = new Random();
        int min = 1;
        int remainingVotes = n;

        for (int i = 0; i < m + 1; i++) {
            int randomVote = rand.nextInt(remainingVotes - (m - i) + 1) + min;
            suara[i] = randomVote;
            remainingVotes -= suara[i];
        }

        if (remainingVotes != 0) {
            suara[m] = suara[m] + remainingVotes;
        }

        int suaraTerbanyak = suara[0];
        for (int i = 1; i < m; i++) {
            if (suara[i] > suaraTerbanyak) {
                suaraTerbanyak = suara[i];
            }
        }

        for (int i = 0; i < m; i++) {

            double persentase = (suara[i] * 100 / n);
            System.out.printf("Calon no. %d : %d suara (%.2f%%)\n", i + 1, suara[i], persentase);

        }

        System.out.println("Golput: " + suara[m]);

    }

}
