package ft1;

import java.util.Random;
import java.util.Scanner;

public class Soal6 {
    public static void soal6(char choose) {
        int[] bobot = { 3, 5, 3, 5, 8, 5, 3, 5, 3 };
        Random ran = new Random();
        int nilaiA = 0;
        int nilaiB = 0;
        for (int i = 0; i < 3; i++) {
            int a = ran.nextInt(bobot.length);
            int b = ran.nextInt(bobot.length);
            System.out.println("Round " + i+1 + " => A=" + a + ", B=" + b);
            System.out.println("Bobot A=" + bobot[a] + ", B=" + bobot[b]);
            if (bobot[a] > bobot[b]) {
                nilaiA++;
            } else if (bobot[a] < bobot[b]) {
                nilaiB++;
            }
            // nilaiA += bobot[a];
            // nilaiB += bobot[b];
            System.out.println("Skor sementara A=" + nilaiA + ", B=" + nilaiB);
        }
        if (choose == 'A') {
            if (nilaiA > nilaiB) {
                System.out.print("Anda menang, nilai A menang");
            } else if (nilaiA == nilaiB) {
                System.out.println("Nilai Seri");
            } else {
                System.out.println("Anda kalah, nilai B menang");
            }
        } else if (choose == 'B') {
            if (nilaiA < nilaiB) {
                System.out.print("Anda menang, nilai B menang");
            } else if (nilaiA == nilaiB) {
                System.out.println("Nilai Seri");
            } else {
                System.out.println("Anda kalah, nilai A menang");
            }
        } else {
            System.out.println("Pilihan yang kamu cari tidak ditemukan!");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Masukkan pilihan = ");
        char choose = sc.next().charAt(0);
        sc.close();
        soal6(choose);
    }
}
