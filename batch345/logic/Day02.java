import java.util.Scanner;

public class Day02 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan Nilai : ");
        int nilai = input.nextInt();
        grade(nilai);
        
        System.out.println("=========================");
        System.out.println("Masukkan Nilai Pulsa : ");
        int nilai_pulsa = input.nextInt();
        pulsa(nilai_pulsa);
        
        System.out.println("=========================");
        System.out.print("Masukkan total Belanja : ");
        int belanja = input.nextInt();
        System.out.print("Masukkan Jarak : ");
        int jarak = input.nextInt();
        System.out.print("Masukkan Promo : ");
        String promo = input.next();
        grab(belanja, jarak, promo);

        System.out.println("=========================");
        System.out.print("Masukkan Total Belanja : ");
        int belanja_sopi = input.nextInt();
        System.out.print("Masukkan Ongkir : ");
        int ongkir_sopi = input.nextInt();

        if(belanja_sopi >= 30000){
            System.out.println("Anda dapat menggunakan voucher 1");
        }if(belanja_sopi >= 50000){
            System.out.println("Anda Dapat Menggunakan voucher 2");
        }if(belanja_sopi >= 100000){
            System.out.println("Anda dapat menggunakan voucher 3");
        }
        System.out.print("Masukkan Voucher : ");
        int voucher = input.nextInt();
        sopi(belanja_sopi, ongkir_sopi, voucher);

        System.out.println("=========================");
        int tahun;
        String nama;
        System.out.print("Masukkan tahun : ");
        tahun=input.nextInt();
        input.nextLine();
        System.out.println("Masukkan Nama : ");
        nama=input.next();
        generasi(nama, tahun);
    }

    public static void grade(int n) {
        if (n >= 90 && n <= 100) {
            System.out.println("Mendapatkan grade A");
        } else if (n >= 70 && n <= 89) {
            System.out.println("Mendapatkan grade B");
        } else if (n >= 50 && n <= 69) {
            System.out.println("Mendapatkan Grade C ");
        } else if (n < 50) {
            System.out.println("Mendapatkan Grade E");
        }
    }

    public static void pulsa(int p) {
        if (p >= 10000 && p < 25000) {
            System.out.println("Pulsa : " + p);
            System.out.println("Point : 80");
        } else if (p >= 25000 && p < 50000) {
            System.out.println("Pulsa : " + p);
            System.out.println("Point : 200");
        }else if(p>= 50000 && p < 100000){
            System.out.println("Pulsa : "+p);
            System.out.println("Point : "+ 400);
        }else if(p>=100000){
            System.out.println("Pulsa : "+p);
            System.out.println("Point : "+ 800);
        }else{
            System.out.println("Pulsa : "+ p);
            System.out.println("Point : 0");
        }
    }

    public static void grab(int b, int j, String p) {
        double diskon = 0.4;
        double jumlah_diskon = diskon * b;
        if (jumlah_diskon >= 30000){
            jumlah_diskon=30000;
        }
        int jumlah_ongkir = j * 1000;
        if (b < 30000) {
            System.out.println("Belanja : " + b);
            System.out.println("Diskon : 0");
            System.out.println("Ongkir : " + jumlah_ongkir);
            System.out.println("Total Belanja : " + (b + jumlah_ongkir));
        } else if (b >= 30000 && p.equalsIgnoreCase("JKTOVO")) {
            System.out.println("Belanja : " + b);
            System.out.println("Diskon : " + jumlah_diskon);
            System.out.println("Ongkir : " + jumlah_ongkir);
            System.out.println("Total Belanja : " + ((b - jumlah_diskon) + jumlah_ongkir));
        } else {
            System.out.println("Belanja : " + b);
            System.out.println("Diskon : 0");
            System.out.println("Ongkir : " + jumlah_ongkir);
            System.out.println("Total Belanja : " + (b + jumlah_ongkir));
        }
    }

    public static void sopi(int belanja, int ongkir, int voucher) {
        switch (voucher) {
            case 1:
                if (belanja >= 30000) {
                    int diskon_belanja = belanja - 5000;
                    int diskon_ongkir = ongkir - 5000;
                    System.out.println("Belanja :" + belanja);
                    System.out.println("Ongkir :" + ongkir);
                    System.out.println("Diskon Belanja : 5000");
                    System.out.println("Diskon Ongkir : 5000");
                    System.out.println("Total Belanja :" + (diskon_belanja + diskon_ongkir));
                } else {
                    System.out.println("Voucher Tidak dapat digunakan");
                }
                break;
            case 2:
                if (belanja >= 50000) {
                    int diskon_belanja = belanja - 10000;
                    int diskon_ongkir = ongkir - 10000;
                    System.out.println("Belanja :" + belanja);
                    System.out.println("Ongkir :" + ongkir);
                    System.out.println("Diskon Belanja : 10000");
                    System.out.println("Diskon Ongkir : 10000");
                    System.out.println("Total Belanja :" + (diskon_belanja + diskon_ongkir));
                } else {
                    System.out.println("Voucher Tidak dapat digunakan");
                }
                break;
            case 3:
                if (belanja >= 100000) {
                    int diskon_belanja = belanja - 10000;
                    int diskon_ongkir = ongkir - 20000;
                    System.out.println("Belanja :" + belanja);
                    System.out.println("Ongkir :" + ongkir);
                    System.out.println("Diskon Belanja : 10000");
                    System.out.println("Diskon Ongkir : 20000");
                    System.out.println("Total Belanja :" + (diskon_belanja + diskon_ongkir));
                } else {
                    System.out.println("Voucher Tidak dapat digunakan");
                }
                break;
            default:
                System.out.println("Belanja : " + belanja);
                System.out.println("Ongkir : " + ongkir);
                System.out.println("Total Belanja : " + (belanja + ongkir));
                break;
        }
    }

    public static void generasi(String nama, int lahir){
        if(lahir >= 1944 && lahir <= 1964){
            System.out.println(nama + "," + "berdasarkan tahun lahir anda tergolong generasi Baby Boomer");
        }else if(lahir >= 1965 && lahir <= 1979){
            System.out.println(nama + "," + "berdasarkan tahun lahir anda tergolong generasi X");            
        }else if(lahir >= 1980 && lahir <= 1994){
            System.out.println(nama + "," + "berdasarkan tahun lahir anda tergolong generasi Y");
        }else if(lahir >= 1995 && lahir <= 2015){
            System.out.println(nama + "," + "berdasarkan tahun lahir anda tergolong generasi Z");
        }
    }
}
