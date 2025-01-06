package Coba;

import java.util.Random;
import java.util.Scanner;

public class Soal6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        game(input);

        sc.close();
    }

    /*
     * Terdapat 9 kotak dengan bobot sesuai dengan angka yang tertera pada
     * masing-masing kotak tersebut, kita akan bermain dengan 2 player(A & B) untuk
     * menentukan siapa yang akan mendapatkan total angka terbanyak dalam 3x putaran
     * permainan. Pengguna harus memilih player mana yang akan menang dalam 3
     * putaran tersebut. Cara bermainnya yaitu dengan mengacak angka dari 1-9.
     * 
     * Input: pilihan player
     * Output: player yang menang dan kalah
     * Urutan angka kotak:
     * 1 2 3
     * 4 5 6
     * 7 8 9
     * 
     * Example:
     * player pilihan: A
     * output:
     * Round 1 => A = 3, B = 5
     * Nilai A = 3, B = 8
     * 
     * Round 2 => A = 1, B = 9
     * Nilai A = 6, B = 11
     * 
     * Round 3 => A = 4, B = 4
     * Nilai A = 11, B = 16
     * 
     * Anda kalah, B menang
     * 
     * | 3 | 5 | 3 |
     * ------------------
     * | 5 | 8 | 5 |
     * ------------------
     * | 3 | 5 | 3 |
     */
    public static void game(String player) {
        int playerAScore = 0;
        int playerBScore = 0;
        int min = 1;
        int max = 9;
        int indexXA = 0;
        int indexYA = 0;
        int indexXB = 0;
        int indexYB = 0;

        int[][] dadu = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        int[][] bobot = { { 3, 5, 8 }, { 5, 8, 5 }, { 3, 5, 3 } };

        for (int i = 0; i < 3; i++) {
            Random rand = new Random();
            // random number between 1 and 9
            int randomA = rand.nextInt(max - min + 1) + min;
            int randomB = rand.nextInt(max - min + 1) + min;

            for (int j = 0; j < dadu.length; j++) {
                for (int k = 0; k < j; k++) {
                    if (randomA == dadu[j][k]) {
                        indexXA = i;
                        indexYA = j;
                    }
                }
            }

            for (int j = 0; j < dadu.length; j++) {
                for (int k = 0; k < j; k++) {
                    if (randomB == dadu[j][k]) {
                        indexXB = i;
                        indexYB = j;
                    }
                }
            }

            // get nilai bobot
            int nilaiBobotPlayerA = bobot[indexYA][indexXA];
            int nilaiBobotPlayerB = bobot[indexYB][indexXB];

            playerAScore += nilaiBobotPlayerA;
            playerBScore += nilaiBobotPlayerB;

            System.out.println("Round " + (i + 1) + " => " + "A" + " = " + playerAScore + ", B = " + playerBScore);
            System.out.println("Nilai A = " + nilaiBobotPlayerA + ", B = " + nilaiBobotPlayerB);
        }

        // winner is
        if (player.equals("A")) {
            if (playerAScore > playerBScore) {
                System.out.println("Anda menang, A menang");
            } else if (playerAScore == playerBScore) {
                System.out.println("Seri");
            } else {
                System.out.println("Anda kalah, B menang");
            }
        } else if (player.equals("B")) {
            if (playerBScore > playerAScore) {
                System.out.println("Anda menang, B menang");
            } else if (playerBScore == playerAScore) {
                System.out.println("Seri");
            } else {
                System.out.println("Anda kalah, A menang");
            }
        }

    }

}
