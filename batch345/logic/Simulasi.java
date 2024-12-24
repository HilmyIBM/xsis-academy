import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Simulasi {

    public static void splitText(String text) {
        String[] words = text.split("(?=[A-Z])");
        System.out.println("Banyaknya Kata = " + words.length);
    }

    public static void printInvoice(int start, int end) {
        DateTimeFormatter idDateFormatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        String currentDate = LocalDate.now().format(idDateFormatter);
        for (int i = start; i <= end; i++) {
            System.out.println("XA-" + currentDate + "-" + String.format("%05d", i));
        }
    }

    public static void sisaBuah(String[] isiKeranjang, int pilihanKeranjang) {
        int[] nilaiIsiKeranjang = new int[3];
        int sisa = 0;
        for (int i = 0; i < isiKeranjang.length; i++) {
            if (isiKeranjang[i].toLowerCase().equals("kosong") || i == pilihanKeranjang) {
                nilaiIsiKeranjang[i] = 0;
            } else {
                nilaiIsiKeranjang[i] = Integer.parseInt(isiKeranjang[i]);
            }
            sisa += nilaiIsiKeranjang[i];
        }
        System.out.println("\nSisa Buah = " + sisa);
    }

    public static void hitungBaju(Scanner sc) {
        String[] kategori = { "Laki Dewasa", "Wanita Dewasa", "Anak-anak", "Bayi" };
        int[] multiply = { 1, 2, 3, 5 };
        int[] jumlahOrang = new int[4];
        int[] jumlahBaju = new int[4];
        boolean isEnough = false;
        while (!isEnough) {
            System.out.println("\n1. Laki Dewasa");
            System.out.println("2. Wanita Dewasa");
            System.out.println("3. Anak-anak");
            System.out.println("4. Bayi");
            System.out.print("\nPilih Kategori : ");
            int chooseCategory = sc.nextInt() - 1;
            sc.nextLine();

            System.out.print("\njumlah baju untuk " + kategori[chooseCategory] + " : ");
            jumlahOrang[chooseCategory] += sc.nextInt();
            jumlahBaju[chooseCategory] = (jumlahOrang[chooseCategory] * multiply[chooseCategory]);
            sc.nextLine();
            boolean isWantToInput = true;
            while (isWantToInput) {
                System.out.print("\nIngin input lagi? (y/n) : ");
                String response = sc.nextLine().toLowerCase();

                if (response.equals("y") || response.equals("n")) {
                    isWantToInput = false;
                    if (response.equals("n")) {
                        isEnough = true;
                        System.out.println("");
                    }
                } else {
                    System.out.println("Masukan anda salah!");
                }
            }
            int totalBaju = 0;
            for (int i = 0; i < jumlahBaju.length; i++) {
                System.out.println(
                        "Jumlah baju untuk " + jumlahOrang[i] + " " + kategori[i] + " = " + jumlahBaju[i]);
                totalBaju += jumlahBaju[i];
            }
            if (totalBaju % 2 != 0 && totalBaju > 10) {
                totalBaju += jumlahOrang[1];
            }

            System.out.println("\nTotal baju = " + totalBaju);
        }
    }

    public static void finalGrade(String nilaiString) {
        String[] arrStringsNilai = nilaiString.split(",");
        int[] nilai = new int[arrStringsNilai.length];

        for (int i = 0; i < arrStringsNilai.length; i++) {
            nilai[i] = Integer.parseInt(arrStringsNilai[i]);
            int kelipatan = 0;
            while (kelipatan < nilai[i]) {
                kelipatan += 5;
            }
            if (nilai[i] > 35) {
                if (kelipatan - nilai[i] < 3) {
                    nilai[i] = kelipatan;
                }
            }
            System.out.println(nilai[i]);
        }
    }

    public static void checkPanagram(String text) {
        text = text.toLowerCase();
        boolean isPanagram = true;

        for (char i = 'a'; i <= 'z'; i++) {
            if (!text.contains(String.valueOf(i))) {
                isPanagram = false;
                break;
            }
        }
        System.out.println((isPanagram ? "\nKalimat ini adalah panagram" : "\nKalimat ini bukan panagram"));
    }

    public static int fib2(int n) {
        if (n <= 1)
            return n;
        int a = 0, b = 1, c;
        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return b;
    }

    public static void deretBilangan(int n) {
        ArrayList<String> arrFib = new ArrayList<>();
        ArrayList<String> arrOdd = new ArrayList<>();
        ArrayList<String> arrEven = new ArrayList<>();

        int sumFib = 0;
        int sumOdd = 0;
        int sumEven = 0;

        for (int i = 0; i < n; i++) {
            arrFib.add(String.valueOf(fib2(i + 1)));
            sumFib += Integer.parseInt(arrFib.get(i));
            arrOdd.add(String.valueOf(1 + i * 2));
            sumOdd += Integer.parseInt(arrOdd.get(i));
            arrEven.add(String.valueOf(2 + i * 2));
            sumEven += Integer.parseInt(arrEven.get(i));
        }

        double avgFib = (double) sumFib / n;
        double avgOdd = (double) sumOdd / n;
        double avgEven = (double) sumEven / n;

        System.out.println("Fibonacci : " + String.join(",", arrFib) + " | sum : " + sumFib + " | avg : " + avgFib);
        System.out.println(
                "Output : Genap : " + String.join(",", arrEven) + " | sum : " + sumEven + " | avg : " + avgOdd);
        System.out.println(
                "Output : Ganjil : " + String.join(",", arrOdd) + " | sum : " + sumOdd + " | avg : " + avgEven);
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
            System.out.println("\n1. Split Text");
            System.out.println("2. Print invoice");
            System.out.println("3. Hitung Sisa buah");
            System.out.println("4. Hitung harga sewa warnet");
            System.out.println("5. Validasi Umur untuk Konser");
            System.out.println("6. Beautiful Days");
            System.out.println("7. Maksimal Es Loli");
            System.out.println("0. Keluar");
            System.out.print("\nPilih menu: ");
            int choose_menu = sc.nextInt();
            sc.nextLine();

            switch (choose_menu) {
                case 1:
                    System.out.print("\nMasukkan Kalimat tanpa spasi : ");
                    String text = sc.nextLine();
                    splitText(text);
                    break;
                case 2:
                    System.out.print("\nStart = ");
                    int start = sc.nextInt();
                    System.out.print("End = ");
                    int end = sc.nextInt();
                    sc.nextLine();
                    printInvoice(start, end);
                    break;
                case 3:
                    String[] isiKeranjang = new String[3];
                    System.out.println("");
                    for (int i = 0; i < isiKeranjang.length; i++) {
                        System.out.print("Keranjang " + (i + 1) + " = ");
                        isiKeranjang[i] = sc.nextLine();
                    }
                    System.out.print("Keranjang ke- berapa yang akan dibawa ke pasar = ");
                    int pilihanKeranjang = sc.nextInt() - 1;
                    sc.nextLine();
                    sisaBuah(isiKeranjang, pilihanKeranjang);
                    break;
                case 4:
                    hitungBaju(sc);
                    break;
                case 5:
                    System.out.print("\nMasukkan daftar nilai (pisahkan dengan \",\")");
                    String nilaiString = sc.nextLine();
                    finalGrade(nilaiString);
                    break;
                case 6:
                    System.out.print("\nMasukkan teks : ");
                    text = sc.nextLine();
                    checkPanagram(text);
                    break;
                case 7:
                    System.out.print("\nMasukkan maksimal himpunan : ");
                    int n = sc.nextInt();
                    sc.nextLine();
                    deretBilangan(n);
                    break;
                case 0:
                    System.out.println("Terima kasih!");
                    continueMenu = false;
                    break;
                default:
                    break;
            }
            if (choose_menu != 0) {
                continueMenu = askContinue(sc);
            }
        }
    }
}
