import java.util.ArrayList;
import java.util.Scanner;

public class Day05_PR {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Bubble Sort");
        System.out.println("2. Prima");
        System.out.println("3. Halloween Sale");
        System.out.println("4. Print Triangle");
        System.out.println("5. SOS");
        System.out.print("Masukkan menu: ");
        switch (Integer.parseInt(sc.nextLine())) {
            case 1:
                System.out.print("Masukkan angka (pisahkan dengan tanda ','): ");
                String[] inputData = sc.nextLine().split(",");
                int[] data = new int[inputData.length];
                for (int i = 0; i < data.length; i++) {
                    data[i] = Integer.parseInt(inputData[i]);
                }
                bubleSort(data);
                break;
            case 2:
                System.out.print("Masukkan angka yang ingin dicari bilangan prima kebawah: ");
                int number = Integer.parseInt(sc.nextLine());
                findPrimeNumber(number);
                break;
            case 3:
                System.out.print("harga awal: ");
                int harga = Integer.parseInt(sc.nextLine());
                System.out.print("diskon: ");
                int diskon = Integer.parseInt(sc.nextLine());
                System.out.print("batas dollar : ");
                int batasDiskon = Integer.parseInt(sc.nextLine());
                System.out.print("duit gw: ");
                int myMoney = Integer.parseInt(sc.nextLine());
                halloweenSale(harga, diskon, batasDiskon, myMoney);
                break;
            case 4:
                System.out.print("Masukkan angka untuk print segitiga: ");
                int input = Integer.parseInt(sc.nextLine());
                printTriangle(input);
                break;
            case 5:
                System.out.print("Masukkan kata: ");
                String inputString = sc.nextLine();
                invalidSOSCounter(inputString);
                break;
            default:
                break;
        }

        sc.close();
    }

    // Number 1 (Sorting)
    static void bubleSort(int[] angka) {
        int temp = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < angka.length - 1; i++) {
            for (int j = 0; j < angka.length - i - 1; j++) {
                // change > or < to order the ascending or descending
                if (angka[j] > angka[j + 1]) {
                    temp = angka[j];
                    angka[j] = angka[j + 1];
                    angka[j + 1] = temp;
                }
            }
        }
        for (int i = 0; i < angka.length; i++) {
            sb.append(angka[i]);
            // if it's not the last value, it will add the ','
            if (i < angka.length - 1) {
                sb.append(",");
            }
        }
        System.out.println(sb.toString());
    }

    // Number 2 (Prime Number)
    static void findPrimeNumber(int number) {
        ArrayList<Integer> primeNumber = new ArrayList<>();
        if (number <= 1) {
            System.out.println("Bilangan tidak valid");
            return;
        }
        boolean isPrime;
        for (int i = 2; i < number; i++) {
            // menganggap seluruh bilangan itu prima
            isPrime = true;
            // Looping untuk pembagi
            for (int j = 2; j < i; j++) {
                // mengecek bilangan i apakah bisa dibagi dengan bilangan j - jika bisa, maka
                // dia akan set bilangan prima menjadi false
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime && primeNumber.size() < 100) {
                primeNumber.add(i);
            }
        }
        System.out.println(primeNumber);
    }

    // Number 3 (Halloween Sale)
    static void halloweenSale(int hargaAwal, int diskonHarga, int limitDiskon, int uangKu) {
        int gamesBought = 0;

        while (uangKu >= hargaAwal) {
            uangKu -= hargaAwal;
            gamesBought++;

            // Reduce the price for the next game, ensuring it doesn't go below the limit.
            hargaAwal -= diskonHarga;
            if (hargaAwal < limitDiskon) {
                hargaAwal = limitDiskon;
            }
        }

        System.out.println(gamesBought + " video games");
    }

    // Number 4 (Print Triangle)
    static void printTriangle(int number) {
        for (int i = 1; i <= number; i++) {
            for (int j = number; j >= i; j--) {
                System.out.print(" ");
            }
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }

        // Alternative
        // for (int row = 1; row <= number; row++) {
        //     for (int col = 1; col <= number; col++) {
        //         // Print spaces until the position where asterisks start
        //         if (col <= number - row) {
        //             System.out.print(" "); // Space character
        //         } else {
        //             System.out.print("* "); // Asterisk
        //         }
        //     }
        //     System.out.println(); // New line after each row
        // }
    }

    // Number 5 (Split every 3 characters for 'SOS')
    // More update for challenge if it's not SOS after SOS, you must shift 1 character for checking if it's SOS or not
    static void invalidSOSCounter(String input) {
        String regex = "(?<=\\G.{3})";
        int invalidSOS = 0;
        String[] splitWords = input.split(regex);
        for (int i = 0; i < splitWords.length; i++) {
            if (!splitWords[i].equalsIgnoreCase("SOS")) {
                invalidSOS++;
            }
        }
        System.out.println("Total sinyal yang salah: " + invalidSOS);
    }
}
