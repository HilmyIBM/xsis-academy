import java.util.Scanner;

public class Day02 {
    public static void main(String[] args) {
        pilihSoal();
    }

    public static void pilihSoal(){
        System.out.print("Soal No. Berapa yang akan dikerjakan : ");
        Scanner input = new Scanner(System.in);     
        int noSoal = input.nextInt();
        switch (noSoal) {
            case 1:
                System.out.println(gradeNilai());
                break;
            case 2:
                System.out.println(pointPulsa());
                break;
            case 3:
                System.out.println(grabFood());
                break;
            case 4:
                System.out.println(ongkirSopi());
                break;
            case 5:
                System.out.println(generasi());
                break;
            default:
                break;
        }
    }



    // No 1
    public static String gradeNilai(){
        Scanner input = new Scanner(System.in);     
        System.out.print("Masukkan Nilai : ");
        int nilai = input.nextInt();
        if (nilai >= 90 && nilai <= 100) {
            return "Grade: A\n";
        }else if (nilai >= 70 && nilai < 89) {
            return "Grade B\n";
        }else if (nilai >= 50 && nilai < 69) {
            return "Grade C\n";
        }else{
            return "Grade D\n";
        }
    }


    // No 2
    public static String pointPulsa(){
        Scanner input = new Scanner(System.in);     
        System.out.print("Masukkan Jumlah Pulsa : ");
        int nilai = input.nextInt();
        if (nilai >= 100000) {
            return "Pulsa\t:"+ nilai + "\n Point\t: 800";
        }else if (nilai >= 50000) {
            return "Pulsa\t:"+ nilai + "\n Point\t: 400";
        }else if (nilai >= 25000 ) {
            return "Pulsa\t:"+ nilai + "\n Point\t: 200";
        }else if(nilai >= 10000) {
            return "Pulsa\t:"+ nilai + "\n Point\t: 80";
        }else {
            return "Pulsa\t:"+ nilai;
        }
    }

    // No 3
    public static String grabFood(){
        Scanner input = new Scanner(System.in);     
        System.out.println("Belanja : ");
        double belanja = input.nextDouble();
        System.out.println("Jarak : ");
        double jarak = input.nextDouble();
        input.nextLine();
        System.out.println("Masukkan Kode Promo : ");
        String promo = input.nextLine();
        String kodePromo = "JKTOVO";
        System.out.println(promo);

        if (belanja >= 30000) {
            if (promo.equals(kodePromo)) {
                double total = belanja -  belanja/10*4 + jarak*1000;
                return "\nBelanja :\t\t"+ belanja + "\nDiskon 40% :\t\t" + belanja/10*4 + "\nOngkir :\t\t" + jarak*1000 + "\nTotal Belanja:\t\t" + total;
            } else {
                return "\nKode Promo Salah!";
            }
        }else{
            double total = belanja + jarak*1000;
            return "\nBelanja :\t\t"+ belanja + "\nOngkir :\t\t" + jarak*1000 + "Total Belanja :\t\t" + total;
        }
    }

    // No 4
    public static String ongkirSopi(){
        Scanner input = new Scanner(System.in);     
        System.out.println("Belanja : ");
        double belanja = input.nextDouble();
        System.out.println("Ongkos Kirim : ");
        double ongkos = input.nextDouble();

        double diskonOngkir;
        double diskonBelanja;
        double totalBelanja;
        if (belanja >= 100000) {
            diskonOngkir = 20000;
            diskonBelanja = 10000;
            totalBelanja = belanja + ongkos - diskonOngkir - diskonBelanja;
        }else if (belanja >= 50000) {
            diskonOngkir = 10000;
            diskonBelanja = 10000;
            totalBelanja = belanja + ongkos - diskonOngkir - diskonBelanja;
        }else{
            diskonOngkir = 5000;
            diskonBelanja = 5000;
            totalBelanja = belanja + ongkos - diskonOngkir - diskonBelanja;
        }
        if (ongkos < diskonOngkir) {
            diskonOngkir = ongkos;      
        }
        return "\nBelanja : \t\t\t" + belanja + "\nOngkos Kirim : \t\t\t" + ongkos + "\nDiskon Ongkir : \t\t" + diskonOngkir + "\nDiskon Belanja :\t\t"+ diskonBelanja +"\nTotal Belanja :\t\t\t" + totalBelanja;
    }


    // No 5
    public static String generasi(){
        Scanner input = new Scanner(System.in);     
        System.out.print("Masukkan Nama Anda : ");
        String nama = input.nextLine();
        System.out.print("Tahun Berapa Anda Lahir : ");
        int tahun = input.nextInt();

        String namaGenerasi = "";
        if (tahun >= 1944 && tahun <= 1964) {
            namaGenerasi = "Baby boomer";
        }else if (tahun >= 1945 && tahun <= 1979) {
            namaGenerasi = "X";
        }else if (tahun >= 1980 && tahun <= 1994) {
            namaGenerasi = "Y";
        }else if(tahun >= 1995 && tahun <= 2015) {
            namaGenerasi = "z";
        }
        return "\n" + nama + ", berdasarkan tahun lahir anda tergolong Generasi " + namaGenerasi;
    }

}
