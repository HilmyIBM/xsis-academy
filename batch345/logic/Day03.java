import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class Day03 {

    public static void pohonFaktor(int n) {
        int bagi = 2;
        while (n != 0) {
            if (n % bagi != 0) {
                bagi++;
            } else {
                int nOld = n;
                n = n / bagi;
                System.out.println(nOld + " / " + bagi + " = " + n);
                if (n == 1) {
                    break;
                }
            }
        }
    }

    public static void polaKubus(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 1) {
                    System.out.print(j + 1 + " ");
                } else if (i == n) {
                    System.out.print(n - j + " ");
                } else {
                    if (j == 0 || j == n - 1) {
                        System.out.print("* ");
                    } else {
                        System.out.print("  ");
                    }
                }
            }
            System.out.println("");

        }
    }

    public static void polaSqrBintang(int n) {
        ArrayList<String> lists = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 2 != 0) {
                lists.add(Integer.toString((int) Math.pow(3, i)));
            } else {
                lists.add("*");
            }
        }
        System.out.println(String.join(" ", lists));
    }

    public static void polaPlusMinus(int n) {
        ArrayList<String> lists = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            lists.add(Integer.toString(5 * i * (i % 2 != 0 ? -1 : 1)));
        }
        System.out.println(String.join(" ", lists));
    }

    public static int fib2(int n) {
        if (n <= 1) {
            return n;
        }
        return fib2(n - 1) + fib2(n - 2);
    }

    public static int fib3(int n) {
        if (n <= 2) {
            return 1;
        }
        return fib3(n - 1) + fib3(n - 2) + fib3(n - 3);
    }

    public static void countSalary(int gol, int jamKerja) {
        int upahJam = 0;
        double upahMinggu = 0, upahLembur = 0;
        switch (gol) {
            case 1:
                upahJam = 2000;
                break;
            case 2:
                upahJam = 3000;
                break;
            case 3:
                upahJam = 4000;
                break;
            case 4:
                upahJam = 5000;
                break;
        }
        if (jamKerja <= 40) {
            upahMinggu = jamKerja * upahJam;
        } else {
            upahMinggu = (40 * upahJam);
            upahLembur = ((jamKerja - 40) * (upahJam * 1.5));
        }
        System.out.println("Upah    : " + (int) upahMinggu);
        System.out.println("Lembur  : " + (int) upahLembur);
        System.out.println("Total   : " + (int) (upahMinggu + upahLembur));
    }

    public static BigInteger factorial(int x) {
        BigInteger result = BigInteger.ONE;
        for (int i = x; i >= 1; i--) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    public static boolean askContinue(Scanner sc) {
        System.out.print("\nKembali ke menu utama? (y/n): ");
        String response = sc.nextLine().toLowerCase();
        return response.equals("y");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean continueMenu = true;

        while (continueMenu) {
            System.out.println("\n1. Pohon Faktor");
            System.out.println("2. Pola Kubus");
            System.out.println("3. Pola squared bintang");
            System.out.println("4. Pola plus minus");
            System.out.println("5. Fibonacci 2");
            System.out.println("6. Fibonacci 3");
            System.out.println("7. Upah Mingguan");
            System.out.println("8. Faktorial");
            System.out.println("9. Keluar");
            System.out.print("\nPilih menu: ");
            int choose_menu = sc.nextInt();
            sc.nextLine();

            switch (choose_menu) {
                case 1:
                    System.out.print("\nMasukkan angka: ");
                    int angka1 = sc.nextInt();
                    pohonFaktor(angka1);
                    break;

                case 2:
                    System.out.print("\nMasukkan angka: ");
                    int angka2 = sc.nextInt();
                    polaKubus(angka2);
                    break;
                case 3:
                    System.out.print("\nMasukkan angka: ");
                    int angka3 = sc.nextInt();
                    polaSqrBintang(angka3);
                    break;
                case 4:
                    System.out.print("\nMasukkan angka: ");
                    int angka4 = sc.nextInt();
                    polaPlusMinus(angka4);
                    break;
                case 5:
                    System.out.print("\nMasukkan angka: ");
                    int angka5 = sc.nextInt();
                    for (int i = 1; i <= angka5; i++) {
                        System.out.print(fib2(i) + " ");
                    }
                    break;
                case 6:
                    System.out.print("\nMasukkan angka: ");
                    int angka6 = sc.nextInt();
                    for (int i = 0; i < angka6; i++) {
                        System.out.print(fib3(i) + " ");
                    }
                    break;
                case 7:
                    System.out.print("\nMasukkan golongan: ");
                    int golongan = sc.nextInt();
                    System.out.print("\nMasukkan jam kerja: ");
                    int jamKerja = sc.nextInt();
                    countSalary(golongan, jamKerja);
                    break;
                case 8:
                    System.out.print("\nMasukkan angka: ");
                    int x = sc.nextInt();

                    String[] arrNum = new String[x];
                    for (int i = 0; i < x; i++) {
                        arrNum[i] = Integer.toString(x - i);
                    }
                    BigInteger fact = factorial(x);
                    System.out.println(x + "! = " + String.join(" x ", arrNum) + " = " + fact);
                    System.out.println("Ada " + fact + " cara");
                    break;
                case 9:
                    System.out.println("Terima kasih!");
                    continueMenu = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid! Silakan coba lagi.");
            }
            if (choose_menu != 9) {
                sc.nextLine();
                continueMenu = askContinue(sc);
            }
        }
    }
}
