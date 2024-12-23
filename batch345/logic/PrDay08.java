import java.util.*;

public class PrDay08 {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        /*
         * System.out.print("Masukkan i : ");
         * int i = input.nextInt();
         * System.out.print("Masukkan j : ");
         * int j = input.nextInt();
         * System.out.print("Masukkan k : ");
         * int k = input.nextInt();
         * lili(i, j, k);
         */

        System.out.println("=======================");
        System.out.print("Masukkan uang : ");
        int uang = input.nextInt();
        es_loli(uang);

    }

    public static void lili(int i, int j, int k) {
        for (int x = i; x <= j; x++) {
            StringBuilder balik_nomor = new StringBuilder();
            balik_nomor.append(x).reverse();
            int hasil = Integer.parseInt(String.valueOf(balik_nomor));
            if (Math.abs(x - hasil) % k == 0) {
                System.out.print(x + " ");
            }
        }
    }

    public static void es_loli(int uang) {
        int harga = 1000;
        int es_krim = uang / harga;
        int total_es_krim = 0;
        System.out.println("Es Krim : " + es_krim);
        total_es_krim = ((es_krim - 1) / 4);
        System.out.println("jumlah Es krim di tukar dengan stick : " + total_es_krim);
        System.out.println("Total Es Krim : " + (total_es_krim + es_krim));
    }

}
