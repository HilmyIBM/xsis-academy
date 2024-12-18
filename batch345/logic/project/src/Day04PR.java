import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.Scanner;

public class Day04PR {

    static void no_1() {
        String kata;
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Input Kata: ");
            kata = sc.nextLine().toLowerCase();
        }

        String rev = new StringBuilder(kata)
                .reverse()
                .toString();

        System.out.println(kata.equals(rev));
    }

    static void belanjaLebaran() {
        int uang;
        String[] tmpBaju, tmpCelana;

        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Uang: ");
            uang = sc.nextInt();

            sc.nextLine();

            System.out.print("Harga Baju: ");
            tmpBaju = sc.nextLine().split(",");

            System.out.print("Harga Celana: ");
            tmpCelana = sc.nextLine().split(",");
        }

        if (tmpBaju.length != tmpCelana.length) throw new Error();

        ArrayList<Integer> total = new ArrayList<>();

        for (int i = 0; i < tmpBaju.length; i++)
            total.add(Integer.parseInt(tmpBaju[i]) + Integer.parseInt(tmpCelana[i]));

        total.sort(Collections.reverseOrder());
        Optional<Integer> result = total.stream()
                .filter(n -> n < uang)
                .findFirst();

        System.out.println(result.isPresent() ? result.get() : "Engga Jadi Beli Baju Lebaran");
    }

    public static void main(String[] args) {

    }

}
