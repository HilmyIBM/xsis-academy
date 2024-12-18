import java.util.Locale;
import java.util.Scanner;

public class Day02PR {

    static void gajiPokok() {
        float pajakPerc;
        int tunjangan, gapok, banyakBulan;
        String nama;

        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Nama: ");
            nama = sc.nextLine();

            System.out.print("Tunjangan: ");
            tunjangan = sc.nextInt();

            System.out.print("Gapok: ");
            gapok = sc.nextInt();

            System.out.print("Banyak Bulan: ");
            banyakBulan = sc.nextInt();
        }

        int gapokTunjangan = gapok + tunjangan;

        if (gapokTunjangan <= 5_000_000) pajakPerc = 0.05F;
        else if (gapokTunjangan <= 10_000_000) pajakPerc = 0.1F;
        else pajakPerc = 0.15F;

        int totalPajak = (int) (pajakPerc * gapokTunjangan);
        int bpjs = (int) (0.03F * gapokTunjangan);
        int gajiPerBulan = gapokTunjangan - (totalPajak + bpjs);
        int totalGaji = gajiPerBulan * banyakBulan;

        Locale localFormat = Locale.forLanguageTag("ID");

        System.out.println("====================================");
        System.out.println("Karyawan atas nama " + nama + " slip gaji sebagai berikut: ");
        System.out.println("Pajak: \t\t\t\t\t\tRp. " + String.format("%,d", totalPajak));
        System.out.println("BPJS: \t\t\t\t\t\tRp. " + String.format("%,d", bpjs));
        System.out.println("Gaji/Bln: \t\t\t\t\tRp. " + String.format("%,d", gajiPerBulan));
        System.out.println("Total Gaji/Banyak Bulan: \tRp. " + String.format("%,d", totalGaji));
    }

    static void BMICalculator() {
        int weight;
        float height;
        String props;

        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Masukan berat badan anda (kg): ");
            weight = sc.nextInt();

            System.out.print("Masukan tinggi badan anda (cm): ");
            height = (sc.nextInt() / 100.0F);
        }

        double res = weight / Math.pow(height, 2.0);

        System.out.println("\nNilai BMI anda adalah " + String.format("%.4f", res));

        if (res <= 18.5) props = "Terlalu Kurus";
        else if (res > 18.5 & res < 25.0) props = "Langsing (Sehat)";
        else props = "gemuk";

        System.out.println("Anda termasuk berbadan " + props);
    }

    static void rataRataNilai() {
        int mtk, fisika, kimia;

        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Masukan Nilai MTK: ");
            mtk = sc.nextInt();

            System.out.print("Masukan Nilai Fisika: ");
            fisika = sc.nextInt();

            System.out.print("Masukan Nilai Kimia: ");
            kimia = sc.nextInt();
        }

        int avarage = (mtk + fisika + kimia) / 3;
        System.out.println("Nilai Rata - Rata: " + avarage);

        if (avarage >= 50) System.out.println("\nSelamat\nKamu Berhasil\nHebat");
        else System.out.println("\nMaaf\nKamu Gagal");
    }

    public static void main(String[] args) {
        // No. 1
        gajiPokok();
        System.exit(0);

        // No. 2
        BMICalculator();

        // No. 3
        rataRataNilai();
    }
}
