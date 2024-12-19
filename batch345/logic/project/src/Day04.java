import java.util.Scanner;

public class Day04 {

    static void totalKata() {
        String kata;
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Input Kata: ");
            kata = sc.nextLine();
        }

        String[] arrKata = kata.split(" ");

        int i = 1;
        for (String k : arrKata)
            System.out.printf("Kata %d = %s", i, k);

        System.out.println("Total Kata adalah " + arrKata.length);
    }

    static void upperUKata() {
        String kata;
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Input Kata: ");
            kata = sc.nextLine();
        }

        kata = kata.replace(" ", "");
        int u = 0;
        int upper = 0;

        for (char k : kata.toCharArray()) {
            if (Character.isUpperCase(k)) upper++;
            if (Character.toLowerCase(k) == 'u') u++;
        }

        System.out.println("Huruf u ada " + u);
        System.out.println("Huruf Kapital ada " + upper);
    }

    static void no3_4(int no) {
        String kata;
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Input Kata: ");
            kata = sc.nextLine();
        }

        String[] arrKata = kata.split(" ");
        StringBuilder res = new StringBuilder();

        for (String k : arrKata) {
            StringBuilder tmp = new StringBuilder();
            for (int i = 0; i < k.length(); i++) {
                if (((i > 0 & i < k.length()-1) & no == 3) |
                        ((i == 0 | i == k.length()-1) & no == 4))
                    tmp.append("*");
                else tmp.append(k.charAt(i));
            }
            res.append(tmp).append(" ");
        }

        System.out.println(res.toString().trim());
    }

    static void no5() {
        String kata;
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Input Kata: ");
            kata = sc.nextLine();
        }

        String[] arrKata = kata.split(" ");
        StringBuilder res = new StringBuilder();

        for (String k : arrKata) {
            res.append(k.substring(k.length() < 3 ? 0 : k.length()-3)).append(" ");
        }

        System.out.println(res.toString().trim());

    }

    public static void main(String[] args) {
        no5();
    }
}
