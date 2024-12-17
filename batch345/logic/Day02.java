import java.util.Scanner;

public class Day02 {
    public static String grade(int nilai) {

        if (nilai <= 100 && nilai >= 90) {
            return "Grade A";
        } else if (nilai <= 89 && nilai >= 70) {
            return "Grade B";
        } else if (nilai <= 69 && nilai >= 50) {
            return "Grade C";
        } else {
            return "Grade E";
        }
    }

    public static int pointPulsa(int pulsa) {
        if (pulsa >= 10_000 && pulsa < 25_000) {
            return 80;
        } else if (pulsa >= 25_000 && pulsa < 50_000) {
            return 200;
        } else if (pulsa >= 50_000 && pulsa < 100_000) {
            return 400;
        } else if (pulsa <= 100_000) {
            return 800;
        } else {
            return 0;
        }
    }

    public static void diskonGrab(int belanja, int jarak) {
        int diskon = (belanja >= 30_000 ? belanja * 40 / 100 : 0);
        int ongkir = (jarak > 5 ? 5000 + (jarak - 5) * 1000 : 5000);

        System.out.println("Belanja       :   " + belanja);
        System.out.println("Diskon 40%    :   " + diskon);
        System.out.println("Ongkir        :   " + ongkir);
        System.out.println("Total Belanja :   " + (belanja - diskon + ongkir));
    }

    public static void diskonSopi(int belanja, int ongkir, int choose_voucher) {
        int diskonBelanja = 0, diskonOngkir = 0;
        switch (choose_voucher) {
            case 1:
                if (belanja >= 30_000 && belanja < 50_000) {
                    diskonBelanja = 5000;
                    diskonOngkir = 5000;
                }
                break;
            case 2:
                if (belanja >= 50_000 && belanja < 100_000) {
                    diskonBelanja = 10_000;
                    diskonOngkir = 10_000;
                }
                break;
            case 3:
                if (belanja >= 100_000) {
                    diskonBelanja = 10_000;
                    diskonOngkir = 20_000;
                }
                break;
        }

        System.out.println("Belanja        :   " + belanja);
        System.out.println("Ongkos Kirim   :   " + ongkir);
        System.out.println("Diskon Ongkir  :   " + diskonOngkir);
        System.out.println("Diskon Belanja :   " + diskonBelanja);
        System.out.println("Total Belanja  :   " + ((belanja - diskonBelanja) + (ongkir - diskonOngkir)));
    }

    public static void cekGenerasi(String nama, int tahun_lahir) {
        String generasi = "";
        if (tahun_lahir >= 1944 && tahun_lahir <= 1964) {
            generasi = "Baby Boomer";
        } else if (tahun_lahir >= 1965 && tahun_lahir <= 1979) {
            generasi = "Generasi X";
        } else if (tahun_lahir >= 1980 && tahun_lahir <= 1994) {
            generasi = "Generasi Y";
        } else if (tahun_lahir >= 1995 && tahun_lahir <= 2015) {
            generasi = "Generasi Z";
        } else {
            generasi = "Tidak Diketahui";
        }

        System.out.println(nama + ", berdasarkan tahun lahir anda tergolong " + generasi);
    }

    public static void main(String[] args) {
        int nilai = 49;
        int pulsa = 90_000;
        System.out.println("1.  Grade Nilai");
        System.out.println("2.  Point Pulsa");
        System.out.println("3.  Diskon Grab");
        System.out.println("4.  Diskon Sopi");
        System.out.println("5.  Cek generasi");
        System.out.print("Pilih menu : ");
        Scanner sc = new Scanner(System.in);
        int choose_menu = sc.nextInt();
        sc.nextLine();
        switch (choose_menu) {
            case 1:
                System.out.println("Nilai " + nilai + " = " + grade(nilai));
                break;
            case 2:
                System.out.println("Pulsa " + pulsa + " = " + pointPulsa(pulsa));
                break;
            case 3:
                diskonGrab(30000, 6);
                break;
            case 4:
                System.out.print("Masukkan total belanja : ");
                int belanja = sc.nextInt();
                System.out.print("Masukkan ongkir : ");
                int ongkir = sc.nextInt();
                System.out.println("1.  Min Order 30rb free ongkir 5rb dan potongan harga belanja 5rb");
                System.out.println("2.  Min Order 50rb free ongkir 10rb dan potongan harga belanja 10rb");
                System.out.println("3.  Min Order 100rb free ongkir 20rb dan potongan harga belanja 10rb");
                System.out.print("Pilih voucher : ");
                int choose_voucher = sc.nextInt();
                sc.nextLine();
                diskonSopi(belanja, ongkir, choose_voucher);
                break;
            case 5:
                System.out.print("Masukkan nama anda: ");
                String nama = sc.nextLine();
                System.out.print("Tahun berapa anda lahir? ");
                int tahun_lahir = sc.nextInt();
                cekGenerasi(nama, tahun_lahir);
                break;
        }
        sc.close();
    }
}
