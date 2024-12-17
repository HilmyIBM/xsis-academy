import java.util.Scanner;

public class Day02 {

    public static String grade(int nilai) {
        if (nilai >= 90) {
            return "Grade A";
        } else if (nilai >= 70) {
            return "Grade B";
        } else if (nilai >= 50) {
            return "Grade C";
        } else {
            return "Grade E";
        }
    }

    public static int pointPulsa(int pulsa) {
        if (pulsa >= 10000 && pulsa < 25000) {
            return 80;
        } else if (pulsa >= 25000 && pulsa < 50000) {
            return 200;
        } else if (pulsa >= 50000 && pulsa < 100000) {
            return 400;
        } else if (pulsa >= 100000) {
            return 800;
        } else {
            return 0;
        }
    }

    public static void diskonGrab(int belanja, int jarak, String kode_promo) {
        if (kode_promo.equals("JKTOVO")) {
            int diskon = (belanja >= 30000) ? belanja * 40 / 100 : 0;
            int ongkir = (jarak > 5) ? 5000 + (jarak - 5) * 1000 : 5000;

            System.out.println("Belanja       : " + belanja);
            System.out.println("Diskon 40%    : " + diskon);
            System.out.println("Ongkir        : " + ongkir);
            System.out.println("Total Belanja : " + (belanja - diskon + ongkir));
        } else {
            System.out.println("Kode promo gagal digunakan!");
        }
    }

    public static void diskonSopi(int belanja, int ongkir, int choose_voucher) {
        int diskonBelanja = 0, diskonOngkir = 0;
        switch (choose_voucher) {
            case 1:
                if (belanja >= 30000) {
                    diskonBelanja = 5000;
                    diskonOngkir = 5000;
                }
                break;
            case 2:
                if (belanja >= 50000) {
                    diskonBelanja = 10000;
                    diskonOngkir = 10000;
                }
                break;
            case 3:
                if (belanja >= 100000) {
                    diskonBelanja = 10000;
                    diskonOngkir = 20000;
                }
                break;
            default:
                System.out.println("Voucher tidak dapat digunakan!");
                return;
        }

        System.out.println("Belanja        : " + belanja);
        System.out.println("Ongkos Kirim   : " + ongkir);
        System.out.println("Diskon Ongkir  : " + diskonOngkir);
        System.out.println("Diskon Belanja : " + diskonBelanja);
        System.out.println("Total Belanja  : " + ((belanja - diskonBelanja) + (ongkir - diskonOngkir)));
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

    public static void cekGaji(String nama, int tunjangan, int gapok, int bulan) {
        int gaji = gapok + tunjangan;
        double persenPajak = 0;

        if (gaji <= 5000000) {
            persenPajak = 0.05;
        } else if (gaji <= 10000000) {
            persenPajak = 0.10;
        } else {
            persenPajak = 0.15;
        }

        double pajak = persenPajak * gaji;
        double bpjs = 0.03 * gaji;
        int gajiBln = (int) (gaji - pajak - bpjs);
        int gajiTotal = gajiBln * bulan;

        System.out.println("Karyawan atas nama " + nama + " slip gaji sebagai berikut:");
        System.out.println("Pajak                       : Rp. " + (int) pajak);
        System.out.println("Bpjs                        : Rp. " + (int) bpjs);
        System.out.println("Gaji/bln                    : Rp. " + gajiBln);
        System.out.println("Total gaji/banyak bulan     : Rp. " + gajiTotal);
    }

    public static void cekBMI(int bb, int tb) {
        double bmi = bb / Math.pow(tb / 100.0, 2);
        System.out.println("Nilai BMI anda adalah " + bmi);

        if (bmi < 18.5) {
            System.out.println("Anda termasuk berbadan terlalu kurus");
        } else if (bmi < 25) {
            System.out.println("Anda termasuk berbadan langsing");
        } else {
            System.out.println("Anda termasuk berbadan gemuk");
        }
    }

    public static void cekAvgNilai(int mtk, int fisika, int kimia) {
        int avg = (mtk + fisika + kimia) / 3;
        System.out.println("Nilai rata-rata : " + avg);

        if (avg < 50) {
            System.out.println("Maaf, kamu gagal");
        } else {
            System.out.println("Selamat, kamu berhasil!");
        }
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
            System.out.println("\n1. Grade Nilai");
            System.out.println("2. Point Pulsa");
            System.out.println("3. Diskon Grab");
            System.out.println("4. Diskon Sopi");
            System.out.println("5. Cek Generasi");
            System.out.println("6. Cek Total Gaji");
            System.out.println("7. Cek BMI");
            System.out.println("8. Cek Rata-rata Nilai");
            System.out.println("9. Keluar");
            System.out.print("\nPilih menu: ");
            int choose_menu = sc.nextInt();
            sc.nextLine();

            switch (choose_menu) {
                case 1:
                    System.out.print("Masukkan nilai: ");
                    int nilai = sc.nextInt();
                    System.out.println("Nilai " + nilai + " = " + grade(nilai));
                    break;
                case 2:
                    System.out.print("Masukkan pulsa: ");
                    int pulsa = sc.nextInt();
                    System.out.println("Pulsa " + pulsa + " = " + pointPulsa(pulsa));
                    break;
                case 3:
                    System.out.print("Masukkan total belanja: ");
                    int belanja = sc.nextInt();
                    System.out.print("Masukkan jarak pengiriman: ");
                    int jarak = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Masukkan kode promo: ");
                    String kode_promo = sc.nextLine();
                    diskonGrab(belanja, jarak, kode_promo);
                    break;
                case 4:
                    System.out.print("Masukkan total belanja: ");
                    belanja = sc.nextInt();
                    System.out.print("Masukkan ongkir: ");
                    int ongkir = sc.nextInt();
                    System.out.println("1. Voucher 30rb");
                    System.out.println("2. Voucher 50rb");
                    System.out.println("3. Voucher 100rb");
                    System.out.print("Pilih voucher: ");
                    int choose_voucher = sc.nextInt();
                    diskonSopi(belanja, ongkir, choose_voucher);
                    break;
                case 5:
                    sc.nextLine(); // consume newline
                    System.out.print("Masukkan nama anda: ");
                    String nama = sc.nextLine();
                    System.out.print("Tahun berapa anda lahir? ");
                    int tahun_lahir = sc.nextInt();
                    cekGenerasi(nama, tahun_lahir);
                    break;
                case 6:
                    sc.nextLine(); // consume newline
                    System.out.print("Masukkan nama anda: ");
                    nama = sc.nextLine();
                    System.out.print("Masukkan tunjangan anda: ");
                    int tunjangan = sc.nextInt();
                    System.out.print("Masukkan gaji pokok anda: ");
                    int gapok = sc.nextInt();
                    System.out.print("Masukkan berapa bulan gaji yang ingin anda lihat: ");
                    int bulan = sc.nextInt();
                    cekGaji(nama, tunjangan, gapok, bulan);
                    break;
                case 7:
                    System.out.print("Masukkan berat badan anda (kg): ");
                    int bb = sc.nextInt();
                    System.out.print("Masukkan tinggi badan anda (cm): ");
                    int tb = sc.nextInt();
                    cekBMI(bb, tb);
                    break;
                case 8:
                    System.out.print("Masukkan nilai MTK: ");
                    int nilaiMtk = sc.nextInt();
                    System.out.print("Masukkan nilai Fisika: ");
                    int nilaiFisika = sc.nextInt();
                    System.out.print("Masukkan nilai Kimia: ");
                    int nilaiKimia = sc.nextInt();
                    cekAvgNilai(nilaiMtk, nilaiFisika, nilaiKimia);
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

        sc.close();
    }
}
