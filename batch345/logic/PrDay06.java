import java.util.Scanner;

public class PrDay06 {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        /*
         * System.out.print("Masukkan Sinyal : ");
         * String sinyal=input.nextLine();
         * PrDay05 no_5 = new PrDay05();
         * no_5.SOS(sinyal);
         * 
         * System.out.println("=======================");
         * System.out.print("Masukkan Kata : ");
         * String no_2=input.nextLine();
         * kata_star(no_2);
         */

        System.out.println("=======================");
        System.out.print("Masukkan Lompatan : ");
        int lompat = input.nextInt();
        input.nextLine();
        System.out.println("Masukkan Rintangan : ");
        String rintangan = input.nextLine();
        game(lompat, rintangan);
    }

    public static void kata_star(String s) {
        char[] hasil = s.toCharArray();
        for (int i = 0; i < hasil.length; i++) {
            System.out.println("***" + hasil[i] + "***");
        }
    }

    public static void game(int lompat, String rintangan) {
        String[] jumlah_rintangan = rintangan.split(" ");
        int max = 0;
        for (int i = 0; i < jumlah_rintangan.length; i++) {
            int rintangan_1 = Integer.parseInt(jumlah_rintangan[i]);
            if (rintangan_1 > max) {
                max = rintangan_1;
            }
        }
        if (max - lompat < 0) {
            System.out.println("0 botol ramuan ajaib");
        } else {
            System.out.println(max - lompat + " botol ramuan ajaib");
        }
    }
}
