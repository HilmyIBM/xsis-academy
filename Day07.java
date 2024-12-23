import java.util.Random;
import java.util.Scanner;

public class Day07 {
    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
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
                no3();
                break;

            case 4:
                // no4();
                break;

            case 5:
                // no5();
                break;

            case 6:
                // no6();
                break;

            case 7:
                // no7();
                break;

            case 8:
                // no8();
                break;

            case 9:
                // no9();
                break;
            case 10:
                // no10();
                break;
        }
        scan.close();

    }

    public static void no1() {
        Scanner scan = new Scanner(System.in);

        // Input total number of sticks
        System.out.print("Input n: ");
        int inputN = scan.nextInt();
        scan.nextLine();

        // Input stick lengths
        System.out.print("Input potongan kayu: ");
        String inputPotongan = scan.nextLine();
        String potonganArr[] = inputPotongan.split(" ");
        int[] intArr = new int[potonganArr.length];

        for (int i = 0; i < potonganArr.length; i++) {
            intArr[i] = Integer.parseInt(potonganArr[i]);
        }

        // Main logic
        while (true) {
            int shortestStick = Integer.MAX_VALUE;
            int remainingSticks = 0; // slalu reset ke 0 jadi kalo di array 0 smua, break

            // terkecil yang bukan 0
            for (int stick : intArr) {
                if (stick > 0) {
                    shortestStick = Math.min(shortestStick, stick);
                    remainingSticks++;
                }
            }

            if (remainingSticks == 0) {
                break; // Exit when all sticks are reduced to zero
            }

            // Print current state of sticks
            for (int stick : intArr) {
                System.out.print(stick > 0 ? stick + " " : "_ ");
            }

            // Print the current minimum stick and remaining stick count
            System.out.println(shortestStick + " " + remainingSticks);

            // Reduce stick lengths by the shortest stick
            for (int i = 0; i < intArr.length; i++) {
                if (intArr[i] > 0) {
                    intArr[i] -= shortestStick;
                }
            }
        }

        // Print final "SELESAI SELESAI" state
        for (int i = 0; i < intArr.length; i++) {
            System.out.print("_ ");
        }
        System.out.println("SELESAI SELESAI");
    }

    public static void no2() {

        System.out.print("Input point: ");
        Scanner scan = new Scanner(System.in);

        int point = scan.nextInt();
        scan.nextLine();

        taruhanRedo(point);

    }

    public static void taruhanRedo(int point) {
        System.out.println();
        System.out.print("Input Taruhan: ");
        Scanner scan = new Scanner(System.in);

        int taruhan = scan.nextInt();
        scan.nextLine();

        while (taruhan > point) {
            System.out.print("Taruhan tidak boleh lebih dari poin!, masukkan ulang: ");
            taruhan = scan.nextInt();
            scan.nextLine();
        }

        System.out.println();
        System.out.print("Input Tebak (U/D): ");
        String tebakan = scan.nextLine().toUpperCase();
        Random rand = new Random();
        int randomPoint = rand.nextInt(10);
        System.out.println(randomPoint);
        if (randomPoint > 5 && tebakan.equalsIgnoreCase("U") || randomPoint < 5 && tebakan.equalsIgnoreCase("D")) {
            point += taruhan;
            System.out.print("Anda menang!, point anda sekarang " + point + " point.");
        } else if (randomPoint == 5) {
            System.out.print("Anda seri, point anda sekarang " + point + " point.");

        } else if (randomPoint < 5 && tebakan.equalsIgnoreCase("U")
                || randomPoint > 5 && tebakan.equalsIgnoreCase("D")) {
            point -= taruhan;
            System.out.print("Anda kalah!, point anda sekarang " + point + " point.");

        }

        System.out.println("Lanjut? (y,n)");

        String contString = scan.nextLine();

        if (contString.equalsIgnoreCase("y")) {
            taruhanRedo(point);
        } else if (contString.equalsIgnoreCase("n")) {
            System.out.println("Point anda: " + point);
            scan.close();
        }
    }

    public static void no3() {
        System.out.println("Input");
        Scanner scan = new Scanner(System.in);

        String[] arrJarak = new String[4];

        System.out.print("Jarak toko ke customer 1: ");
        arrJarak[0] = scan.nextLine();

        scan.nextLine();

        System.out.print("Jarak customer 1 ke customer 2: ");
        arrJarak[1] = scan.nextLine();

        scan.nextLine();

        System.out.print("Jarak customer 2 ke customer 3 ");
        arrJarak[2] = scan.nextLine();

        scan.nextLine();

        System.out.print("Jarak customer 3 ke customer 4: ");
        arrJarak[3] = scan.nextLine();
        scan.nextLine();
        scan.close();

        double[] arr = new double[arrJarak.length];

        for (int i = 0; i < arrJarak.length; i++) {
            if (arrJarak[i].contains("KM")) {
                arr[i] = Double.parseDouble(arrJarak[i].replace("KM", "").replace(" ", ""));
                arr[i] *= 1000;

            } else if (arrJarak[i].contains("M")) {
                arr[i] = Double.parseDouble(arrJarak[i].replace("M", "").replace(" ", ""));

            }
        }

        double totalJarak = 0.0F;

        for (int j = 0; j < arrJarak.length; j++) {
            totalJarak += arr[j];
        }

        int bensin = (int) Math.round((totalJarak) / 2500.0F);

        System.out.println("Bensin yang dibutuhkan adalah " + bensin + " Liter");

    }

    public static void no4() {
        Scanner scan = new Scanner(System.in);

        System.out.print("Input n: ");
        int n = scan.nextInt();

        // Bahan total untuk 15 kue
        double terigu15 = 115; // gram
        double gula15 = 190; // gram
        double susu15 = 100; // mL

        // Bahan per kue
        double teriguPerKue = terigu15 / 15;
        double gulaPerKue = gula15 / 15;
        double susuPerKue = susu15 / 15;

        // Bahan untuk n kue
        double teriguN = teriguPerKue * n;
        double gulaN = gulaPerKue * n;
        double susuN = susuPerKue * n;

        // Output
        System.out.printf("Untuk membuat %d kue pukis, dibutuhkan:\n", n);
        System.out.printf("Terigu: %.2f gram\n", teriguN);
        System.out.printf("Gula Pasir: %.2f gram\n", gulaN);
        System.out.printf("Susu: %.2f mL\n", susuN);
    }
}
