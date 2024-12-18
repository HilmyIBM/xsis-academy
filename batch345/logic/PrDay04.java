import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class PrDay04 {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Masukkan kalimat : ");
        String input_polindrom = input.nextLine();
        polindrom(input_polindrom);

        System.out.println("===============");
        System.out.println("Masukkan uang : ");
        int uang = input.nextInt();
        input.nextLine();
        System.out.println("Masukkan harga baju : ");
        String baju = input.nextLine();
        System.out.println("Masukkan harga celana : ");
        String celana = input.nextLine();
        lebaran(uang, baju, celana);
    }

    public static void polindrom(String n) {
        String hasil = new StringBuilder(n).reverse().toString();
        if (n.equals(hasil)) {
            System.out.println("Benar");
        } else {
            System.out.println("Salah");
        }
    }

    public static void lebaran(int uang, String baju, String celana) {
        int[] harga_baju = Arrays.stream(baju.split(",")).mapToInt(Integer::parseInt).toArray();
        int[] harga_celana = Arrays.stream(celana.split(",")).mapToInt(Integer::parseInt).toArray();
        int[] total = new int[harga_baju.length];
        Integer min = 0;
        for (int i = 0; i < harga_baju.length; i++) {
            total[i] = harga_baju[i] + harga_celana[i];
            if (total[i] <= uang && total[i] > min) {
                min = total[i];
            }
        }
        System.out.println(min);
    }

}
