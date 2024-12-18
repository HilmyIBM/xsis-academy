import java.util.Scanner;

public class LatihanDay03 {

    public static void main(String[] args) {
        int num = 0;
        System.out.println("Enter exercise number:\n");
        Scanner scan = new Scanner(System.in);
        num = scan.nextInt();
        switch (num) {
            case 1:
                no1();
                break;

            case 2:
                no2();
                break;

            case 3:
                // no3();
                break;

            case 4:
                // no4();
                break;

            case 5:
                // no5();
                break;

            case 6:
                // no6();
            default:
                break;

            case 7:
                // no7();
                break;

            case 8:
                // no8();
                break;
        }
        scan.close();

    }

    public static void no1() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Input angka: ");
        int angka = scan.nextInt();
        scan.close();
        while (angka != 1) {
            if (angka % 2 == 0) {
                System.out.println(angka + "/2 = " + (angka / 2));
                angka = angka / 2;
            } else if (angka % 3 == 0) {
                System.out.println(angka + "/3 = " + (angka / 3));
                angka = angka / 3;
            } else if (angka % 5 == 0) {
                System.out.println(angka + "/5 = " + (angka / 5));
                angka = angka / 5;
            } else if (angka % 7 == 0) {
                System.out.println(angka + "/7 = " + (angka / 7));
                angka = angka / 7;
            } else if (angka % 11 == 0) {
                System.out.println(angka + "/11 = " + (angka / 11));
                angka = angka / 11;
            }

        }
    }

    public static void no2() {
        System.out.println("Input n: ");
        String ans = "";
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        System.out.println();
        scan.close();

        for (int i = 1; i <= n; i++) {
            System.out.print(i + " ");
        }
        System.out.println();  // Move to the next line
        
        // Print the middle rows: *     *
        for (int i = 1; i <= n - 2; i++) {
            System.out.print("* ");
            for (int j = 1; j <= n - 2; j++) {
                System.out.print("  ");  // Print spaces
            }
            System.out.println("*");
        }

        // Print the last row: n n-1 n-2 ... 1
        for (int i = n; i >= 1; i--) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

}