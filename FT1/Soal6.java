package FT1;

import java.util.Random;
import java.util.Scanner;

public class Soal6 {
    public static void main(String[] args) {
        no6();
    }

    public static void no6() {
        int arr[] = { 3, 5, 3, 5, 8, 5, 3, 5, 3 };

        int totalA = 0;
        int totalB = 0;

        Random rand = new Random();

        Scanner scan = new Scanner(System.in);
        System.out.println();
        System.out.print("input player: ");
        String player = scan.nextLine().toUpperCase();

        for (int i = 0; i < 3; i++) {
            int numA = rand.nextInt(9);
            int numB = rand.nextInt(9);

            totalA += arr[numA];
            totalB += arr[numB];

            System.out.println("Round " + (i + 1) + "=> A = " + (numA + 1) + ", B = " + (numB + 1));
            System.out.println("Nilai A = " + totalA + ", Nilai B = " + totalB);

            System.out.println();
        }

        if (player.equals("A")) {
            if (totalA > totalB) {
                System.out.println("Anda menang, B kalah");
            } else if (totalA < totalB){
                System.out.println("Anda kalah, B menang");

            } else if (totalA == totalB) {
                System.out.println("Seri!");
    
            }
        } else if (player.equals("B")) {
            if (totalA > totalB) {
                System.out.println("A menang, Anda kalah");
            } else if (totalA < totalB){
                System.out.println("A kalah, Anda menang");

            } else if (totalA == totalB) {
                System.out.println("Seri!");
    
            }
        }  else {
            System.out.println("player tidak tersedia");
        }
    }
}
