package ft1;

import java.util.Scanner;

public class Soal4 {
    public static void soal4(int n, int x) {
        int page = 0;
        do {
            page++;
            x -= 2;
        } while (x > 0);

        if (page > n) {
            System.out.println("halaman yang anda masukkan melebihi halaman yang dipunya buku!");
        } else {
            System.out.println("lembar ke-" + page);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Masukkan n = ");
        int n = sc.nextInt();
        System.out.print("Masukkan x = ");
        int x = sc.nextInt();
        sc.close();
        soal4(n, x);
    }
}
