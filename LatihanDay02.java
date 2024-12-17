import java.util.Scanner;

public class LatihanDay02 {
    static int num;

    public static void main(String[] args) {
        System.out.println("Enter exercise number:\n");
        Scanner scan = new Scanner(System.in);
        num = scan.nextInt();
        switch (num) {
            case 1:
                System.out.println("Grade dari nilai tersebut adalah: " + no1());
                break;

            case 2:
                no2();
                break;

            case 3:
                no3();
                break;

            case 4:
                no4();
                break;

            case 5:
                no5();
                break;

            case 6:
                no6();
            default:
                break;

            case 7:
                no7();
                break;

            case 8:
                no8();
                break;
        }
        scan.close();

    }

    public static String no1() {
        System.out.println("masukkan nilai:");
        Scanner scan = new Scanner(System.in);
        int nilai = scan.nextInt();
        scan.close();

        if (nilai > 89) {
            return "A";
        } else if (nilai > 69) {
            return "B";
        } else {
            return "C";
        }
    }

    public static void no2() {
        System.out.println("Masukkan nominal pulsa:\n");
        Scanner scan = new Scanner(System.in);
        int nominal = scan.nextInt();
        scan.close();
        int point;
        if (nominal >= 100000) {
            point = 800;
            System.out.println("Pulsa: " + nominal);
            System.out.println("Point: " + point + "\n");
        } else if (nominal >= 10000) {
            point = 400;
            System.out.println("Pulsa: " + nominal);
            System.out.println("Point: " + point + "\n");
        } else if (nominal >= 25000) {
            point = 200;
            System.out.println("Pulsa: " + nominal);
            System.out.println("Point: " + point + "\n");
        } else if (nominal >= 10000) {
            point = 80;
            System.out.println("Pulsa: " + nominal);
            System.out.println("Point: " + point + "\n");
        } else {
            System.out.println("Pulsa: " + nominal);
            System.out.println("Point: 0\n");
        }
    }

    public static void no3() {
        int nomBelanja;
        int diskon = 0;
        int ongkir = 0;
        // int totalBelanja = nomBelanja - diskon + ongkir;
        System.out.println("Masukkan nominal belanja: Rp.");
        Scanner scan = new Scanner(System.in);
        int nominal = scan.nextInt();
        nomBelanja = nominal;

        System.out.println("Masukkan jarak: ");
        int jarak = scan.nextInt();

        System.out.println("Masukkan kode promo: ");
        String kodePromo = scan.next();
        scan.close();

        if (kodePromo.contains("JKTOVO")) {
            if (nominal >= 30000) {
                diskon = nominal / 10 * 4;
                if (diskon > 30000) {
                    diskon = 30000;
                }

            } else {

            }
        } else {
            diskon = 0;

        }
        if (jarak <= 5) {
            ongkir = 5000;
        } else {
            ongkir = ((jarak - 5) * 1000) + 5000;
        }

        int totalBelanja = nomBelanja - diskon + ongkir;
        System.out.println("Belanja: " + nomBelanja);
        System.out.println("Diskon 40%: " + diskon);
        System.out.println("Ongkir: " + ongkir);
        System.out.println("Total Belanja: " + totalBelanja);
        System.out.println(kodePromo);
    }

    public static void no4() {
        int nomBelanja;
        int potonganBelanja = 0;
        int potonganOngkir = 0;
        int ongkir = 0;
        int ongkirTerpotong = 0;
        String availVoucher = ""; // int totalBelanja = nomBelanja - diskon + ongkir;
        System.out.println("Masukkan nominal belanja: Rp.");
        Scanner scan = new Scanner(System.in);
        int nominal = scan.nextInt();

        if (nominal > 100000) {
            availVoucher = "1, 2, 3 redeemable";
        } else if (nominal > 50000) {
            availVoucher = "1, 2 redeemable";
        } else if (nominal > 30000) {
            availVoucher = "1 redeemable";
        } else {
            availVoucher = "-";
        }
        nomBelanja = nominal;

        System.out.println("Masukkan ongkos kirim: ");
        ongkir = scan.nextInt();

        System.out.println("Pilih voucher: " + availVoucher);
        int voucher = scan.nextInt();

        scan.close();

        if (voucher == 1 && nomBelanja >= 30000) {
            potonganBelanja = 5000;
            potonganOngkir = 5000;
        }
        if (voucher == 2 && nomBelanja >= 50000) {
            potonganBelanja = 10000;
            potonganOngkir = 10000;
        }
        if (voucher == 3 && nomBelanja >= 100000) {
            potonganBelanja = 10000;
            potonganOngkir = 20000;
        }

        if (ongkir < potonganOngkir) {
            ongkirTerpotong = 0;
        } else {
            ongkirTerpotong = ongkir - potonganOngkir;
        }

        System.out.println("Belanja: " + nomBelanja);
        System.out.println("Ongkos Kirim: " + ongkir);
        System.out.println("Diskon Ongkir: " + potonganOngkir);
        System.out.println("Diskon Belanja: " + potonganBelanja);
        System.out.println("Total Belanja: " + (nomBelanja + ongkirTerpotong - potonganBelanja));
    }

    public static void no5() {
        System.out.println("Masukkan nama anda: ");
        Scanner scan = new Scanner(System.in);
        String nama = scan.next();
        System.out.println("Tahun berapa anda lahir? ");
        int tahunLahir = scan.nextInt();
        String generasi = "";
        scan.close();

        if (tahunLahir >= 1944 && tahunLahir <= 1964) {
            generasi = "Baby Boomer";
        } else if (tahunLahir >= 1965 && tahunLahir <= 1979) {
            generasi = "Generasi X";
        } else if (tahunLahir >= 1980 && tahunLahir <= 1994) {
            generasi = "Generasi Y (Milenials)";
        } else if (tahunLahir >= 1995 && tahunLahir <= 2015) {
            generasi = "Generasi Z";
        }

        System.out.println(nama + ", berdasarkan tahun lahir anda tergolong " + generasi);
    }

    public static void no6() {
        int persentasePajak = 0;
        // int pajak = 0;
        // int bpjs = 0;
        int gajiBulanan = 0;
        int totalGaji = 0;
        System.out.println("Nama: ");
        Scanner scan = new Scanner(System.in);
        String name = scan.next();

        System.out.println("Tunjangan: ");
        int tunjangan = scan.nextInt();

        System.out.println("Gapok: ");
        int gapok = scan.nextInt();

        System.out.println("Bulan: ");
        int banyakBulan = scan.nextInt();

        scan.close();

        if (gapok > 10000000) {
            persentasePajak = 15;
        } else if (gapok > 5000000) {
            persentasePajak = 10;
        } else {
            persentasePajak = 5;
        }

        int pajak = (int) (persentasePajak / 100.0 * (gapok + tunjangan));
        int bpjs = (int) (3 / 100.0 * (gapok + tunjangan));
        gajiBulanan = gapok + tunjangan - pajak - bpjs;
        totalGaji = gajiBulanan * banyakBulan;

        System.out.println("Karyawan atas nama " + name + " slip gaji sebagai berikut: ");
        System.out.println("Pajak: " + pajak);
        System.out.println("BPJS: " + bpjs);
        System.out.println("Gaji/Bulan: " + gajiBulanan);
        System.out.println("Total Gaji/Banyak Bulan: " + totalGaji);
    }

    public static void no7() {
        System.out.println("Masukkan berat badan anda (kg): ");
        Scanner scan = new Scanner(System.in);
        int weight = scan.nextInt();

        System.out.println("Masukkan Tinggi badan anda (cm): ");
        int height = scan.nextInt();

        double bmi = (double) (weight / (height * height / 10000.0));
        String msg = "";

        if (bmi > 18.5) {
            msg = "Anda terlalu kurus.";
        } else if (bmi < 25 && bmi >= 18.5) {
            msg = "Anda termasuk berbadan langsing.";
        } else {
            msg = "Anda terlalu gemuk.";
        }
        
        System.out.println(String.format("%1$s %2$.4f", "Nilai BMI anda adalah ", bmi));
        System.out.println(msg);

    }

    public static void no8() {
        System.out.println("Masukkan nilai MTK: ");
        Scanner scan = new Scanner(System.in);
        int nilaiMtk = scan.nextInt();

        System.out.println("Masukkan nilai Fisika: ");
        int nilaiFisika = scan.nextInt();

        System.out.println("Masukkan nilai Kimia: ");
        int nilaiKimia = scan.nextInt();
        scan.close();

        double rataRata = ((nilaiFisika+nilaiKimia+nilaiMtk)/3.0);

        if (rataRata >= 50.0){
            System.out.println("Nilai Rata-Rata: " + rataRata);
            System.out.println("Selamat");
            System.out.println("Kamu Berhasil");
            System.out.println("Kamu Hebat");
        } else {
            System.out.println("Nilai Rata-Rata: " + rataRata);
            System.out.println("Maaf");
            System.out.println("Kamu Gagal");
        }
    }
}
