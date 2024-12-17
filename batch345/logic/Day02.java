import java.util.Scanner;

public class Day02 {
    public static void main(String[] args) {
        pilihSoal();
    }

    public static void pilihSoal(){
        Scanner input = new Scanner(System.in);     
        int noSoal;

        while (true) {
            System.out.print("Soal No. Berapa yang akan dikerjakan (0 untuk keluar dari program) : ");
            noSoal = input.nextInt();
            
            if(noSoal == 0) {
                System.out.println("Terima Kasih");
                break;
            }

            
            switch (noSoal) {
                case 1:
                    System.out.print("\nMasukkan Nilai : ");
                    int nilai = input.nextInt();
                    System.out.println(gradeNilai(nilai));
                    break;
                case 2:
                    System.out.print("\nMasukkan Jumlah Pulsa : ");
                    int pulsa = input.nextInt();
                    System.out.println(pointPulsa(pulsa));
                    break;
                case 3:
                    System.out.println("Belanja : ");
                    double belanja = input.nextDouble();
                    System.out.println("Jarak : ");
                    double jarak = input.nextDouble();
                    input.nextLine();
                    System.out.println("Masukkan Kode Promo : ");
                    String promo = input.nextLine();
                    System.out.println(grabFood(belanja, jarak, promo));
                    break;
                case 4:
                    System.out.println("Belanja : ");
                    double totalBelanja = input.nextDouble();
                    System.out.println("Ongkos Kirim : ");
                    double ongkos = input.nextDouble();
                    System.out.println(ongkirSopi(totalBelanja, ongkos));
                    break;
                case 5:
                    System.out.print("Masukkan Nama Anda : ");
                    String nama = input.nextLine();
                    System.out.print("Tahun Berapa Anda Lahir : ");
                    int tahun = input.nextInt();
                    System.out.println(generasi(nama, tahun));
                    break;
                default:
                    System.out.println("Nomor tidak ada!");
                    break;
            }
        }
        input.close();
    }

    // No 1
    public static String gradeNilai(int nilai){ 
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
    public static String pointPulsa(int nilai){     
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
    public static String grabFood(double belanja, double jarak, String promo){
        String kodePromo = "JKTOVO";
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
    public static String ongkirSopi(double belanja, double ongkos){
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
    public static String generasi(String nama, int tahun){
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
