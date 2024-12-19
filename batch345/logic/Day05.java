import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day05 {
    public static void timeConversion(String timeString) {
        String result = LocalTime.parse(timeString, DateTimeFormatter.ofPattern("hh:mm:ssa"))
                .format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        System.out.println(result);
    }

    public static void cekStatusPembayaran(int totalMenu, int indexAlergi, String hargaMenuString, int uang) {
        String[] hargaMenuStrings = hargaMenuString.split(",");
        int[] hargaMenu = new int[totalMenu];
        int totalBelanja = 0;

        for (int i = 0; i < totalMenu; i++) {
            hargaMenu[i] = Integer.parseInt(hargaMenuStrings[i]);
        }

        for (int i = 0; i < totalMenu; i++) {
            if (i == indexAlergi) {
                continue;
            }
            totalBelanja += hargaMenu[i];
        }

        double tagihan = totalBelanja / 2;
        double sisaUang = uang - tagihan;

        System.out.println(String.format("\nElsa harus membayar\t= Rp. %1$,d", (int) tagihan));
        System.out.println(sisaUang == 0 ? "Uang Pas" : String.format("Sisa uang elsa\t\t= Rp. %1$,d", (int) sisaUang));
    }

    public static int diagonalDifference(int[][] matrix, int n) {
        int diagonal1 = 0;
        int diagonal2 = 0;

        for (int i = 0; i < n; i++) {
            diagonal1 += matrix[i][i];
            diagonal2 += matrix[i][n - 1 - i];
        }

        return Math.abs(diagonal1 - diagonal2);
    }

    public static int countHighestCandles(int[] candles) {
        Arrays.sort(candles);
        int highest = candles[candles.length - 1];
        int countHighest = 0;

        for (int candle : candles) {
            if (candle == highest) {
                countHighest++;
            }
        }
        return countHighest;
    }

    public static void rotateArrayList(ArrayList<Integer> numList, int n) {
        for (int i = 1; i <= n; i++) {
            int firstElement = numList.remove(0);
            numList.add(firstElement);

            System.out.print((i) + ": ");
            displayArrayList(numList);
        }
    }

    public static ArrayList<Integer> stringToArrayIntegers(String numString) {
        String[] arrStrings = numString.split(",");
        ArrayList<Integer> arrList = new ArrayList<>();

        for (String arr : arrStrings) {
            arrList.add(Integer.parseInt(arr.trim()));
        }
        return arrList;
    }

    public static void displayArrayList(ArrayList<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            if (i > 0)
                System.out.print(",");
            System.out.print(list.get(i));
        }
        System.out.println();
    }

    public static void bubleSort(int[] arrNum) {
        int size = arrNum.length;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                if (arrNum[j] > arrNum[j + 1]) {
                    int temp = arrNum[j];
                    arrNum[j] = arrNum[j + 1];
                    arrNum[j + 1] = temp;
                }
            }
        }

        for (int i = 0; i < arrNum.length; i++) {
            if (i < arrNum.length - 1) {
                System.out.print(arrNum[i] + ",");
            } else {
                System.out.print(arrNum[i]);
            }
        }

    }

    public static int[] parseInputToArray(String input) {
        String[] parts = input.split(",");
        int[] result = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            result[i] = Integer.parseInt(parts[i].trim());
        }
        return result;
    }

    public static void deretBilanganPrima(int n) {
        int count = 0;
        int num = 2;
        String strPrima = "";
        while (num < n && count < 100) {
            if (isPrime(num)) {
                if (strPrima != "") {
                    strPrima += ",";
                }
                strPrima += num;
                count++;
            }
            num++;
        }

        System.out.println(strPrima);
    }

    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void countVideoGames(int p, int d, int m, int s) {
        int count = 0;
        int totalBayar = 0;

        while (totalBayar < s) {
            if (totalBayar + p > s) {
                break;
            }

            totalBayar += p;

            if (p - d > m) {
                p -= d;
            } else {
                p = m;
            }

            count++;

            if (totalBayar >= s) {
                break;
            }
        }

        System.out.println(count + " Video Game");
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
            System.out.println("\n1. Time conversion");
            System.out.println("2. Cek tagihan makanan");
            System.out.println("3. Diagonal difference");
            System.out.println("4. Hitung lilin tertinggi");
            System.out.println("5. Rotate position of number");
            System.out.println("6. Bubble sort");
            System.out.println("7. Deret Bilangan Prima");
            System.out.println("8. Hitung jumlah video game");
            System.out.println("9. Keluar");
            System.out.print("\nPilih menu: ");
            int choose_menu = sc.nextInt();
            sc.nextLine();

            switch (choose_menu) {
                case 1:
                    System.out.print("\nMasukkan Waktu : ");
                    String timeString = sc.nextLine();
                    timeConversion(timeString);
                    break;

                case 2:
                    System.out.print("\nMasukkan total menu : ");
                    int totalMenu = sc.nextInt();
                    System.out.print("Masukkan index makanan yang alergi : ");
                    int indexAlergi = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Masukkan harga-harga menu : ");
                    String hargaMenuString = sc.nextLine();
                    System.out.print("Masukkan uang Elsa : ");
                    int uang = sc.nextInt();
                    sc.nextLine();
                    cekStatusPembayaran(totalMenu, indexAlergi, hargaMenuString, uang);
                    break;

                case 3:
                    System.out.print("\nMasukkan ukuran matrix n (n x n): ");
                    int n = sc.nextInt();

                    int[][] matrix = new int[n][n];

                    System.out.println("Masukkan elemen matrix : ");

                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < n; j++) {
                            matrix[i][j] = sc.nextInt();
                        }
                    }
                    sc.nextLine();

                    System.out.println("Perbedaan diagonal = " + diagonalDifference(matrix, n));
                    break;

                case 4:
                    System.out.print("\nMasukkan banyaknya lilin: ");
                    n = sc.nextInt();
                    int[] candles = new int[n];
                    System.out.print("Masukkan daftar panjangnya lilin: ");
                    for (int i = 0; i < n; i++) {
                        candles[i] = sc.nextInt();
                    }
                    sc.nextLine();

                    System.out.println("Jumlah lilin tertinggi yang dapat ditiup adalah " + countHighestCandles(candles)
                            + " lilin");
                    break;

                case 5:
                    System.out.print("\nMasukkan array angka (pisahkan dengan \",\") : ");
                    String numString = sc.nextLine();

                    ArrayList<Integer> numList = stringToArrayIntegers(numString);

                    System.out.print("Masukkan berapa kali di rotate : ");
                    n = sc.nextInt();
                    sc.nextLine();

                    System.out.println("\nHasil " + n + " kali rotasi:");
                    rotateArrayList(numList, n);
                    break;

                case 6:
                    System.out.print("\nMasukkan array angka (pisahkan dengan \",\") : ");
                    numString = sc.nextLine();

                    int[] numArr = parseInputToArray(numString);

                    bubleSort(numArr);
                    break;

                case 7:
                    System.out.print("\nMasukkan angka: ");
                    n = sc.nextInt();
                    sc.nextLine();
                    deretBilanganPrima(n);
                    break;

                case 8:
                    int[] arrInput = new int[4];
                    System.out.print("\nMasukkan nilai P, D, M, dan S (ex: 20 3 6 70) : ");

                    for (int i = 0; i < 4; i++) {
                        arrInput[i] = sc.nextInt();
                    }
                    sc.nextLine();

                    int p = arrInput[0];
                    int d = arrInput[1];
                    int m = arrInput[2];
                    int s = arrInput[3];

                    countVideoGames(p, d, m, s);
                    break;

                case 9:

                    break;

                case 10:

                    break;
                case 0:
                    System.out.println("Terima kasih!");
                    continueMenu = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid! Silakan coba lagi.");
                    break;
            }
            if (choose_menu != 0) {
                continueMenu = askContinue(sc);
            }
        }
        sc.close();
    }
}
