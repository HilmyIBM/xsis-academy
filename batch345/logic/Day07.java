import java.util.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day07 {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        /*
         * System.out.print("Masukkan jumlah kayu : ");
         * int jumlah_kayu = input.nextInt();
         * input.nextLine();
         * potong_kayu(jumlah_kayu);
         */

        /*
         * System.out.println("=======================");
         * System.out.print("Masukkan point : ");
         * int point = input.nextInt();
         * input.nextLine();
         * System.out.print("Masukkan taruhan : ");
         * int taruhan = input.nextInt();
         * input.nextLine();
         * System.out.print("Masukkan tebakan : ");
         * String tebakan = input.nextLine();
         * taruhan(point, taruhan, tebakan);
         */

        System.out.println("=======================");
        ojol();

        System.out.println("=======================");
        System.out.print("Masukkan jumlah kue : ");
        int n = input.nextInt();
        input.nextLine();
        kue(n);
    }

    public static void potong_kayu(int jumlah_kayu) {
        ArrayList<Integer> list = new ArrayList<>();
        int k = 0;
        int temp = 0;
        for (int i = 0; i < jumlah_kayu; i++) {
            list.add(input.nextInt());
        }
        int min = Collections.min(list);
        System.out.println(list.size());
        while (k <= list.size()) {
            for (int j = 0; j < list.size(); j++) {
                temp = list.get(j) - min;
                list.set(j, temp);
            }
            list.removeAll(Collections.singleton(0));
            min = Collections.min(list);
            k++;
            System.out.println(list.size());
        }
    }

    public static void taruhan(int point, int taruhan, String tebakan) {
        String again;
        Random rand = new Random();
        int hasil = rand.nextInt(10);
        if (hasil > 5 && tebakan.equalsIgnoreCase("U")) {
            System.out.println("Hasil Random : " + hasil);
            System.out.println("You Win");
            point += taruhan;
            System.out.println("Point saat ini: " + point);
        } else if (hasil > 5 && tebakan.equalsIgnoreCase("D")) {
            System.out.println("Hasil Random : " + hasil);
            System.out.println("You Lose");
            point -= taruhan;
            System.out.println("Point saat ini: " + point);
        } else if (hasil < 5 && tebakan.equalsIgnoreCase("D")) {
            System.out.println("Hasil Random : " + hasil);
            System.out.println("You Win");
            point += taruhan;
            System.out.println("Point saat ini: " + point);
        } else if (hasil < 5 && tebakan.equalsIgnoreCase("U")) {
            System.out.println("Hasil Random : " + hasil);
            System.out.println("You Lose");
            point -= taruhan;
            System.out.println("Point saat ini: " + point);
        }
        System.out.println("Apakah anda ingin bermain lagi? (Y/N)");
        again = input.nextLine();
        if (again.equalsIgnoreCase("Y") && point != 0) {
            System.out.print("Masukkan taruhan : ");
            int taruhan1 = input.nextInt();
            input.nextLine();
            System.out.print("Masukkan tebakan : ");
            String tebakan1 = input.nextLine();
            if (taruhan1 > point) {
                System.out.println("Point tidak mencukupi");
                System.exit(0);
            } else {
                taruhan(point, taruhan1, tebakan1);
            }
        } else if (again.equalsIgnoreCase("N") || point == 0) {
            System.out.println("GAME OVER");
            System.exit(0);
        }
    }

    public static void ojol() {

    }

    public static void kue(int n) {
        double terigu = 115.0 / 15;
        double gula = 190.0 / 15;
        double susu = 100.0 / 15;

        double total_terigu = terigu * n;
        double total_gula = gula * n;
        double total_susu = susu * n;

        System.out.println("Terigu yang dibutuhkan : " + total_terigu);
        System.out.println("Gula yang dibutuhkan : " + total_gula);
        System.out.println("Susu yang dibutuhkan : " + total_susu);
    }
}
