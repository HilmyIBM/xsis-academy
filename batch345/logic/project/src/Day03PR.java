import java.util.Scanner;

public class Day03PR {

    static void upahKaryawan() {
        int[] golongan = {2000, 3000, 4000, 5000};
        int gol, jamKerja, upah, waktuLembur = 0, upahLembur = 0;
        int minWaktuLembur = 40;

        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Golongan: ");
            gol = sc.nextInt();

            if (gol > golongan.length) throw new NoSuchMethodError();

            System.out.print("Jam Kerja: ");
            jamKerja = sc.nextInt();
        }

        if (jamKerja > minWaktuLembur) {
            waktuLembur = jamKerja - minWaktuLembur;
            upahLembur = (int) (golongan[gol-1] * 1.5) * waktuLembur;
        }

        upah = (jamKerja - waktuLembur) * golongan[gol-1];
        int total = upah + upahLembur;
        System.out.printf("Upah:\t\t\t%d\nLembur:\t\t\t%d\nTotal:\t\t\t%d", upah, upahLembur, total);

    }

    static void factor(int n) {
        int res = 1;
        StringBuilder resStr = new StringBuilder();

        System.out.printf("%d! = ", n);
        for (int i = n; i > 0 ; i--) {
            res *= i;
            resStr.append(i).append(" x ");
        }

        System.out.println(resStr.substring(0, resStr.length()-2) + "= " + res);
    }

    public static void main(String[] args) {
        // No. 1
        upahKaryawan();

        // No. 2
        factor(3);
    }
}
