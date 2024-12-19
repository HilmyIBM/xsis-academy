import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Scanner;

public class Day05 {

    static void format24Hours() throws ParseException {
        String date;
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Input Date: ");
            date = sc.nextLine();
        }

        var getFormatDate = new SimpleDateFormat("hh:mm:ssa");
        var formatDate = new SimpleDateFormat("HH:mm:ss");

        System.out.println(formatDate.format(getFormatDate.parse(date)));
    }

    static void makan() {
        String[] hargaMenu;
        int totalMenu;
        int indexAlergi;
        int uangElsa;

        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Total Menu: ");
            totalMenu = sc.nextInt();

            System.out.print("Index Makanan Alergi: ");
            indexAlergi = sc.nextInt();

            if (indexAlergi > totalMenu)
                throw new Error("Indeks alergi tidak boleh lebih dari total menu");

            sc.nextLine();

            System.out.print("Harga Menu: ");
            hargaMenu = sc.nextLine().split(",");

            if (hargaMenu.length < totalMenu || hargaMenu.length > totalMenu)
                throw new Error("List Harga Menu tidak dapat lebih atau kurang dari total menu");

            System.out.print("Uang Elsa: ");
            uangElsa = sc.nextInt();
        }

        int total = Arrays.stream(hargaMenu)
                .mapToInt(Integer::valueOf)
                .sum();

        int totalBisaMakan = total - Integer.parseInt(hargaMenu[indexAlergi]);
        int bayar = totalBisaMakan / 2;
        int sisa = uangElsa - bayar;

        System.out.println("\n=======================\n");
        System.out.println("Elsa Harus Membayar: " + bayar);

        if (sisa < 0) System.out.println("Uang Kurang: " + sisa);
        else if(sisa == 0) System.out.println("Uang Pas");
        else System.out.println("Sisa Uang: " + sisa);
    }

    static void highCandle(int[] arrCandle) {
        int count = 0;
        int max = 0;

        for (int candle : arrCandle) {
            if (candle > max) {
                max = candle;
                count = 1;
            } else if (candle == max) {
                count++;
            }
        }

        System.out.println(count);
    }

    static void diagonalDifference(int[][] arr) {
        int[] diff = {0, 0};
        int[] mov = {0, arr.length-1};

        for (int[] ints : arr) {
            diff[0] += ints[mov[0]];
            diff[1] += ints[mov[1]];

            mov[0]++;
            mov[1]--;
        }

        System.out.println(Math.abs(diff[0] - diff[1]));
    }

    static void rotateArray(int rot, int[] arr) {
        int tmp;
        for (int i = 0; i < rot; i++) {
            tmp = arr[0];

            for (int j = 0; j < arr.length-1; j++) arr[j] = arr[j+1];

            arr[arr.length-1] = tmp;
        }

        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) throws ParseException {
        makan();

        format24Hours();

        int[][] test3 = {
                {11, 2, 4},
                {4, 5, 6},
                {10, 8, -12},
        };
        int[] test4 = {3, 2, 1, 3};
        int[] test5 = {5, 6, 7, 0, 1};

        diagonalDifference(test3);
        highCandle(test4);
        rotateArray(2, test5);
    }
}
