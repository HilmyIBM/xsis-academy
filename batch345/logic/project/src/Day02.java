import javax.naming.OperationNotSupportedException;
import java.util.*;

public class Day02 {

    static char Grade(int n) {
        if (n >= 90 & n <= 100) return 'A';
        if (n >= 70 & n < 90) return 'B';
        if (n >= 50 & n < 70) return 'C';
        return 'E';
    }

    static int PointPulsa(int pulsa) {
        if (pulsa >= 10_000 & pulsa < 25_000) return 80;
        if (pulsa >= 25_000 & pulsa < 50_000) return 200;
        if (pulsa >= 50_000 & pulsa < 100_000) return 400;
        if (pulsa >= 100_000) return 800;

        return 0;
    }

    static void GrabFood() {
        float diskon = 0.4F;
        int maksDiskon = 30_000;
        int minBelanja = 30_000;
        int ongkirPerKm = 1000;

        int totalDiskon = 0;

        try(Scanner sc = new Scanner(System.in)) {
            System.out.print("Belanja: ");
            int belanja = sc.nextInt();

            System.out.print("Jarak: ");
            int jarak = sc.nextInt();

            sc.nextLine();

            System.out.print("Kode Promo: ");
            String kodePromo = sc.nextLine();

            System.out.println("\n------------------------\n");

            System.out.println("Belanjaan:\t\t\t" + belanja);

            if (belanja >= minBelanja & kodePromo.equalsIgnoreCase("jktovo")) {
                totalDiskon = (int) (belanja * diskon);
                totalDiskon = totalDiskon > maksDiskon ? 30_000 : totalDiskon;
                System.out.println("Diskon (40%):\t\t" + totalDiskon);
            }

            int totalOnkir = jarak * ongkirPerKm;
            System.out.println("Ongkir:\t\t\t\t" + totalOnkir);

            int totalBelanja = (belanja - totalDiskon) + totalOnkir;
            System.out.println("Total Belanja:\t\t" + totalBelanja);
        }
    }

    static void SopiFreeOngkir() throws OperationNotSupportedException {
        int[][] vouchs = {
                {30_000, 5_000, 5_000},
                {50_000, 10_000, 10_000},
                {100_000, 20_000, 10_000}
        };

        ArrayList<ArrayList<Integer>> usableVouchers = new ArrayList<>();

        int minBelanja = 30_000;
        int totalBelanja, diskonOnkir = 0, potonganHarga = 0;
        int belanja, ongkir, voucer = 0;

        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Belanja:\t\t");
            belanja = sc.nextInt();

            System.out.print("Onkos Kirim:\t");
            ongkir = sc.nextInt();

            if (belanja >= minBelanja) {
                System.out.println("\nVoucher yang dapat digunakan adalah: ");

                for (int i = 0; i < vouchs.length; i++) {
                    if (belanja >= vouchs[i][0]) {
                        int finalI = i;
                        usableVouchers.add(new ArrayList<>() {{
                            add(vouchs[finalI][1]);
                            add(vouchs[finalI][2]);
                        }});

                        System.out.printf("%d. Min Order Rp. %d, Free Ongkir %d dan Potongan Harga Sebanyak %d\n", i+1, vouchs[i][0], vouchs[i][1], vouchs[i][2]);
                    }
                }

                System.out.println();
                System.out.print("Pilih No Voucher:\t");
                voucer = sc.nextInt();

                if (voucer > usableVouchers.size()) throw new OperationNotSupportedException("Voucher yang dimasukan tidak ada");
            }
        }

        for (int i = 0; i < usableVouchers.size() & belanja >= minBelanja; i++) {
            if (voucer != i+1) continue;

            diskonOnkir = usableVouchers.get(i).get(0);
            potonganHarga = usableVouchers.get(i).get(1);
        }

        totalBelanja = (belanja - potonganHarga) + (ongkir - diskonOnkir);

        System.out.println("---------------------");
        System.out.println("Belanja: \t\t" + belanja);
        System.out.println("Ongkos kirim: \t" + ongkir);
        System.out.println("Diskon Ongkir: \t" + diskonOnkir);
        System.out.println("Diskon Belanja: " + potonganHarga);
        System.out.println("Total Belanja: \t" + totalBelanja);
    }

    static void Gen() {
        Object[][] gens = {
                {1995, 2015, "Generasi Z"},
                {1980, 1994, "Generasi Y (Milenials)"},
                {1965, 1979, "Generasi X"},
                {1944, 1964, "Generasi Boomer"},
        };

        String nama;
        int age;
        boolean f = false;

        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Masukan Nama Anda: ");
            nama = sc.nextLine();

            System.out.print("Tahun Berapa Anda Lahir: ");
            age = sc.nextInt();
        }

        for (Object[] g : gens) {
            if (age >= (int) g[0]) {
                System.out.println(nama + ", Beradasarkan tahun lahir anda tergolong " + g[2]);
                f = true;
                break;
            }
        }

        if (!f) System.out.println(nama + ", Beradasarkan tahun lahir anda tergolong Generasi (???)");
    }

    public static void main(String[] args) throws OperationNotSupportedException {
        // No 1.
//        int nilai = 10;
//        System.out.println("Grade yang didapat dengan nilai " + nilai + " " + Grade(nilai));
//
//        // No 2.
//        int pulsa = 100_000;
//        System.out.println("Point Pulsa Yang didapat adalah " + PointPulsa(pulsa));
//
//        // No 3.
//        // Read Input
//        GrabFood();
//
//        // No 4.
//        // Read Input
//        SopiFreeOngkir();

        // No 5.
        // Read Input
        Gen();
    }
}
