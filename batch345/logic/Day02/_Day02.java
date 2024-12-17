package Day02;

import java.util.Scanner;

public class _Day02 {
    public static void main(String[] args) {
        // gradeNilai();
        // pulsa();
        // grabFood();
        // shopee();
        // gen();
    }

    public static void gradeNilai(){
        Scanner scan = new Scanner(System.in);
        int nilai;
        String grade = "";

        // Intro 
        System.out.println("Program Menentukan Grade Nilai");
        System.out.println("===============================");

        // Input
        System.out.print("Masukkan Nilai (0-100): ");
        nilai = scan.nextInt();

        if (nilai >= 90 && nilai < 101){
            grade = "A";
        } else if (nilai >= 70 && nilai < 90){
            grade = "B";
        } else if (nilai >= 50 && nilai < 70){
            grade = "C";
        } else if (nilai < 50 && nilai >= 0){
            grade = "E";
        } else {
            grade = "Tidak Valid";
        }

        System.out.println("Grade nilai anda adalah: " + grade);

        scan.close();
    }

    public static void pulsa(){
        Scanner scan = new Scanner(System.in);
        int pulsa, poin;

        // Intro 
        System.out.println("Program Poin Pulsa");
        System.out.println("===================");

        // Input
        System.out.print("Inputkan Jumlah Pulsa: Rp. ");
        pulsa = scan.nextInt();

        if (pulsa >= 100000){
            poin = 800;
        } else if (pulsa >= 50000 && pulsa < 100000){
            poin = 400;
        } else if (pulsa >= 25000 && pulsa < 50000){
            poin = 200;
        } else if (pulsa >= 10000 && pulsa < 25000){
            poin = 80;
        } else {
            poin = 0;
        }

        System.out.println("PEMBELIAN BERHASIL!");

        if (poin != 0){
            System.out.println("Poin yang didapat: " + poin + " poin");
        }

        scan.close();
    }

    public static void grabFood(){
        Scanner scan = new Scanner(System.in);
        int belanja, jarak, disc, ongkir, total_belanja;
        String kodePromo;

        // Intro 
        System.out.println("Program GrabFood");
        System.out.println("=================");

        //Input
        System.out.print("Inputkan Jumlah Belanja: Rp. ");
        belanja = scan.nextInt();
        System.out.print("Inputkan Jarak (km): ");
        jarak = scan.nextInt();
        System.out.print("Masukkan kode promo: ");
        kodePromo = scan.next();
        scan.nextLine();

        // Process Discount
        if("JKTOVO".equals(kodePromo)){
            disc = belanja * 40 / 100;
            if(disc > 30000){
                disc = 30000;
            }
        } else {
            disc = 0;
        }

        // Process Jarak
        if(jarak > 5){
            ongkir = ((jarak - 5) * 1000) + 5000;
        } else {
            ongkir = 5000;
        }

        // Process Total Belanja
        total_belanja = (belanja - disc) + ongkir;

        //Output
        System.out.println("==================");
        System.out.println("Belanja:\t" + belanja);
        if (disc != 0){
            System.out.println("Diskon 40 %:\t" + disc);
        }
        System.out.println("Ongkir:\t\t" + ongkir);
        System.out.println("TOTAL BELANJA:\t" + total_belanja);

        scan.close();
    }

    public static void shopee(){
        Scanner scan = new Scanner(System.in);
        int belanja, ongkir, disc = 0, total_belanja, pickVoucher = 0, freeOngkir = 0;

        // Intro 
        System.out.println("Program Shopee");
        System.out.println("=================");

        //Input
        System.out.print("Inputkan Jumlah Belanja: Rp. ");
        belanja = scan.nextInt();
        System.out.print("Inputkan Ongkos Kirim: ");
        ongkir = scan.nextInt();

        if(belanja>=100000){
            System.out.println("Silahkan Pilih Voucher");
            System.out.println("1. Min Order 30rb free ongkir 5rb dan potongan harga belanja 5rb");
            System.out.println("2. Min Order 50rb free ongkir 10rb dan potongan harga belanja 10rb");
            System.out.println("3. Min Order 100rb free ongkir 20rb dan potongan harga belanja 10rb");
            System.out.print("Pilih Voucher: ");
            pickVoucher = scan.nextInt();
        } else if (belanja >= 50000) {
            System.out.println("Silahkan Pilih Voucher");
            System.out.println("1. Min Order 30rb free ongkir 5rb dan potongan harga belanja 5rb");
            System.out.println("2. Min Order 50rb free ongkir 10rb dan potongan harga belanja 10rb");
            System.out.print("Pilih Voucher: ");
            pickVoucher = scan.nextInt();
        } else if (belanja >= 30000){
            System.out.println("Selamat Bisa Menggunakan Voucher");
            System.out.println("1. Min Order 30rb free ongkir 5rb dan potongan harga belanja 5rb");
            System.out.print("Pilih Voucher: ");
            pickVoucher = scan.nextInt();    
        }

        switch (pickVoucher) {
            case 1:
                freeOngkir = 5000;
                disc = 5000;
                break;
            case 2:
                freeOngkir = 5000;
                disc = 5000;
                break;
            case 3:
                freeOngkir = 5000;
                disc = 5000;
                break;
            default:
                break;
        }

        // Output
        total_belanja = belanja + ongkir - (freeOngkir + disc);

        System.out.println("Belanja:\t" + belanja);
        System.out.println("Ongkos Kirim:\t" + ongkir);
        if (freeOngkir != 0 && disc != 0) {
            System.out.println("Diskon Ongkir:\t" + freeOngkir);
            System.out.println("Diskon Belanja:\t" + disc);
        }
        System.out.println("Total Belanja:\t" + total_belanja);
        scan.close();
    }

    public static void gen(){
        Scanner scan = new Scanner(System.in);
        int tahun;
        String nama = "", gen="";

        // Intro 
        System.out.println("Program Menentukan Generasi");
        System.out.println("============================");

        // Input
        System.out.print("Masukkan Nama: ");
        nama = scan.nextLine();
        System.out.print("Masukkan tahun: ");
        tahun = scan.nextInt();

        // Process
        if(tahun >= 1944 && tahun < 1965){
            gen = "Baby Boomers";
        } else if (tahun >= 1965 && tahun < 1980){
            gen = "Generasi X";
        } else if (tahun >= 1980 && tahun < 1995){
            gen = " Generasi Y (Millenial)";
        } else if (tahun >= 1995 && tahun < 2016){
            gen = "Generasi Z";
        } else {
            gen = "Belum ditentukan";
        }

        // Output
        System.out.println("====================");
        System.out.println(nama + ", berdasarkan tahun lahir anda tergolong " + gen);
    }
}
