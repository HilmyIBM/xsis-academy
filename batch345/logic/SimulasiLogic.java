import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class SimulasiLogic {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("1. Menghitung Huruf Kapital");
        System.out.println("2. Printing Invoice Penjualan");
        System.out.println("3. Menghitung Sisa Buah");
        System.out.println("4. Hitung baju pengungsi");
        System.out.println("5. Penilaian Dosen");
        System.out.println("6. Pangram");
        System.out.println("7. Fibonacci");
        System.out.println("8. Recursive Digits");
        System.out.println("9. Point Pulsa");
        System.out.println("10. Mencari Pasangan Array");
        System.out.print("Masukkan Nomor Menu: ");
        switch (Integer.parseInt(sc.nextLine())) {
            case 1:
                System.out.print("Masukkan Kalimat: ");
                String kalimat = sc.nextLine();
                findCapitalized(kalimat);
                break;
            case 2:
                System.out.print("Masukkan angka start: ");
                int invoiceAwal = sc.nextInt();
                sc.nextLine();
                System.out.print("Masukkan angka terakhir: ");
                int invoiceTerakhir = sc.nextInt();
                sc.nextLine();
                printInvoicePenjualan(invoiceAwal, invoiceTerakhir);
                break;
            case 3:
                System.out.println("Inputan harus berupa angka atau 'Kosong'");
                System.out.print("Masukkan Nilai Keranjang 1: ");
                String keranjang1 = sc.nextLine();
                System.out.print("Masukkan Nilai Keranjang 2: ");
                String keranjang2 = sc.nextLine();

                System.out.print("Masukkan Nilai Keranjang 3: ");
                String keranjang3 = sc.nextLine();

                System.out.print("Keranjang yang dibawa: ");
                String keranjangYangDibawa = sc.nextLine();
                buahDapur(keranjang1, keranjang2, keranjang3, keranjangYangDibawa);
                break;
            case 4:
                System.out.print("Masukkan jumlah laki - laki dewasa: ");
                int lakiLaki = Integer.parseInt(sc.nextLine());
                System.out.print("Masukkan jumlah wanita dewasa: ");
                int wanita = Integer.parseInt(sc.nextLine());
                System.out.print("Masukkan jumlah anak anak: ");
                int anakAnak = Integer.parseInt(sc.nextLine());
                System.out.print("Masukkan jumlah bayi: ");
                int bayi = Integer.parseInt(sc.nextLine());
                hitungBajuPengungsian(lakiLaki, wanita, anakAnak, bayi);
                break;
            case 5:
                System.out.print("Masukkan nilai: ");
                String inputNilai = sc.nextLine();
                nilaiDosen(inputNilai);
                break;
            case 6:
                System.out.print("Masukkan kalimat pangram: ");
                String kalimatPangram = sc.nextLine();
                if (pangram(kalimatPangram)) {
                    System.out.println("Kalimat tersebut adalah pangram");
                } else {
                    System.out.println("Kalimat ini bukan pangram");
                }
                main(args);
                break;
            case 7:
                fibonacci(10);
                break;
            case 8:
                reverseDigit(153, 3);
                break;
            case 9:
                bonusPoint(20000);
                break;
            case 10:
                System.out.print("Masukkan target angka: ");
                int targetAngka = Integer.parseInt(sc.nextLine());
                System.out.print("Masukkan array: ");
                String[] inputAngkaString = sc.nextLine().split(" ");
                int[] arrayParse = new int[inputAngkaString.length];

                for (int i = 0; i < arrayParse.length; i++) {
                    arrayParse[i] = Integer.parseInt(inputAngkaString[i]);
                }

                mencariPasanganArray(arrayParse, targetAngka);
                break;
            default:
                main(args);
                break;
        }
        sc.close();
    }

    // Nomor 1 (Find Capitalized)
    static void findCapitalized(String input) {
        String dataTrim = input.trim();
        int countCapitalized = 0;

        for (int i = 0; i < dataTrim.length(); i++) {
            if (dataTrim.charAt(i) >= 'A' && dataTrim.charAt(i) <= 'Z') {
                countCapitalized++;
            }
        }
        System.out.println(countCapitalized);
    }

    // Nomor 2, template printing
    static void printInvoicePenjualan(int start, int end) {
        if (start > end) {
            System.err.println("Invoice baru tidak bisa lebih dari invoice terakhir");
            return;
        }

        String templatePrinting = "XA-07082022-";
        String invoice = "";
        for (int i = start; i <= end; i++) {
            // %05d format 5 angka, jika tidak ada angka didepan, maka akan direplace dengan
            // angka '0'
            invoice = templatePrinting + String.format("%05d", i);
            System.out.println(invoice);
        }
    }

    // Nomor 3 (Buah keranjang)
    static void buahDapur(String keranjang1, String keranjang2, String keranjang3, String keranjangYangDibawah) {
        int basket1 = (keranjang1.equalsIgnoreCase("Kosong") ? 0 : Integer.parseInt(keranjang1));
        int basket2 = (keranjang2.equalsIgnoreCase("Kosong") ? 0 : Integer.parseInt(keranjang2));
        int basket3 = (keranjang3.equalsIgnoreCase("Kosong") ? 0 : Integer.parseInt(keranjang3));

        int sisaBuah = 0;

        if (keranjangYangDibawah.equalsIgnoreCase("Keranjang 1 dibawa ke pasar")) {
            sisaBuah = basket2 + basket3;
        } else if (keranjangYangDibawah.equalsIgnoreCase("Keranjang 2 dibawa ke pasar")) {
            sisaBuah = basket1 + basket3;
        } else if (keranjangYangDibawah.equalsIgnoreCase("Keranjang 3 dibawa ke pasar")) {
            sisaBuah = basket1 + basket2;
        } else {
            System.out.println("Nama keranjang tidak valid");
            return;
        }
        System.out.println("Sisa buah: " + sisaBuah);
    }

    // Nomor 4 (Hitung baju pengungsi)
    static void hitungBajuPengungsian(int lakiLaki, int wanita, int anakAnak, int bayi) {
        int totalBaju = 0;
        int bajuLaki = 1 * lakiLaki;
        int bajuPerempuan = 2 * wanita;
        int bajuAnak = 3 * anakAnak;
        int bajuBayi = 5 * bayi;
        boolean trueOrFalseInputan = true;

        while (trueOrFalseInputan) {
            System.out.print("Ingin input lagi? (y/n) ");
            String mauInput = sc.nextLine();
            if (mauInput.equalsIgnoreCase("y")) {
                trueOrFalseInputan = true;
                System.out.println("Menu");
                System.out.println("1. Laki Dewasa");
                System.out.println("2. Wanita Dewasa");
                System.out.println("3. Anak-anak");
                System.out.println("4. Bayi");
                System.out.print("Input baju untuk nomor: ");

                int menuInputan = Integer.parseInt(sc.nextLine());

                if (menuInputan == 1) {
                    System.out.print("Tambahan laki laki: ");
                    lakiLaki += Integer.parseInt(sc.nextLine());
                } else if (menuInputan == 2) {
                    System.out.print("Tambahan Perempuan: ");
                    wanita += Integer.parseInt(sc.nextLine());
                } else if (menuInputan == 3) {
                    System.out.print("Tambahan anak anak: ");
                    anakAnak += Integer.parseInt(sc.nextLine());
                } else if (menuInputan == 4) {
                    System.out.print("Tambahan bayi: ");
                    bayi += Integer.parseInt(sc.nextLine());
                } else {
                    System.out.println("Nomor tidak valid");
                    continue;
                }
            } else if (mauInput.equalsIgnoreCase("n")) {
                trueOrFalseInputan = false;
            } else {
                trueOrFalseInputan = false;
            }

        }

        // recalculate
        bajuLaki = 1 * lakiLaki;
        bajuPerempuan = 2 * wanita;
        bajuAnak = 3 * anakAnak;
        bajuBayi = 5 * bayi;

        totalBaju = bajuLaki + bajuPerempuan + bajuAnak + bajuBayi;
        if (totalBaju % 2 != 0 && totalBaju > 10) {
            bajuPerempuan = 1 * wanita;
        }
        totalBaju = bajuLaki + bajuPerempuan + bajuAnak + bajuBayi;

        System.out.println("Output: " + totalBaju);
    }

    // Nomot 5 (Pemberian nilai dosen)
    static void nilaiDosen(String input) {
        String[] angkaString = input.split(",");
        int[] nilaiRaw = new int[angkaString.length];
        // Memasukkan nilai
        for (int i = 0; i < nilaiRaw.length; i++) {
            nilaiRaw[i] = Integer.parseInt(angkaString[i]);
        }

        // pembulatan nilai
        for (int i = 0; i < nilaiRaw.length; i++) {
            int nilaiFinal = pembulatanNilai(nilaiRaw[i]);
            System.out.println(nilaiFinal);
        }
    }

    static int pembulatanNilai(int nilai) {
        if (nilai < 35) {
            return nilai; // Tidak dibulatkan jika nilai kurang dari 35
        } else {
            // Menentukan kelipatan 5 terdekat ke bawah
            int kelipatan5Next = ((nilai / 5) + 1) * 5;

            // Jika selisih antara nilai dan kelipatan 5 berikutnya kurang dari 3,
            // dibulatkan ke kelipatan 5 berikutnya
            if (kelipatan5Next - nilai < 3) {
                return kelipatan5Next;
            } else {
                return nilai;
            }
        }
    }

    // Nomor 6 (Pangram)
    static boolean pangram(String kalimat) {
        HashSet<Character> checkCharacter = new HashSet<>();
        // need to lowerCase because a and A is different (and make sure the a and A is
        // same)
        String kata = kalimat.toLowerCase();
        for (char c : kata.toCharArray()) {
            if ((c >= 'a' && c <= 'z')) {
                checkCharacter.add(c);
            }
        }
        return checkCharacter.size() == 26;
    }

    // Nomot 7 (Fibonacci dan mencari ganjil genap)
    static void fibonacci(int number) {
        int a = 1, b = 1, c, totalGanjil = 0, totalGenap = 0, totalFibonacci = 0;
        ArrayList<Integer> ganjil = new ArrayList<>();
        ArrayList<Integer> genap = new ArrayList<>();
        ArrayList<Integer> fib = new ArrayList<>();

        // System.out.print(a + ", " + b + ", ");
        ganjil.add(a);
        fib.add(a);
        fib.add(b);
        for (int i = 2; i < number; i++) {
            c = a + b;
            if (c % 2 == 0) {
                genap.add(c);
            } else {
                ganjil.add(c);
            }
            // // Printing
            // if (i < number - 1) {
            // System.out.print(c + ", ");
            // } else {
            // System.out.print(c);
            // }
            fib.add(c);
            a = b;
            b = c;
        }
        System.out.println();

        // calculate total ganjil,genap,fibonacci
        for (int i = 0; i < ganjil.size(); i++) {
            totalGanjil += ganjil.get(i);
        }

        for (int i = 0; i < genap.size(); i++) {
            totalGenap += genap.get(i);
        }

        for (int i = 0; i < fib.size(); i++) {
            totalFibonacci += fib.get(i);
        }
        System.out.printf("Total Fibonacci: %d dengan rata-rata: %d\n", totalFibonacci,
                totalFibonacci / fib.size());
        System.out.printf("Total Ganjil: %d dengan rata-rata: %d\n", totalGanjil, totalGanjil / ganjil.size());
        System.out.printf("Total Genap: %d dengan rata-rata: %d\n", totalGenap, totalGenap / genap.size());
    }

    // Nomor 8 (Recursive Digit)
    static void reverseDigit(int number, int k) {
        float result = 0;
        for (int i = 0; i < k; i++) {
            result = sumOfDigits(number);
        }
        System.out.println((int) Math.floor(result));
    }

    static float sumOfDigits(float number) {
        float result = 0;
        while (number > 0) {
            result += number % 10;
            number = number / 10;
        }
        return result;
    }

    // Nomor 9 (Bonus Point Pulsa)
    static void bonusPoint(int pulsa) {
        int point = 0;
        if (pulsa < 0) {
            System.out.println("Pulsa tidak mencukupi");
            main(null);
            return;
        }
        if (pulsa <= 10000) {
            point += 0;
        }
        if (pulsa <= 30000) {
            point = (pulsa - 10000) / 1000;
        }
        if (pulsa > 30000) {
            point = ((pulsa - 20000) / 1000) * 2;
        }

        System.out.println(point);
    }

    static void mencariPasanganArray(int[] angka, int target) {
        int counter = 0;

        for (int i = 0; i < angka.length; i++) {
            for (int j = 0; j < angka.length; j++) {
                if (Math.abs(angka[i] - angka[j]) == target) {
                    counter++;
                }
            }
        }

        // karena ada hasil yang duplikat
        System.out.println(counter / 2);
    }

    
}
