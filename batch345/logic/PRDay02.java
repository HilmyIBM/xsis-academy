import java.util.Scanner;

public class PRDay02 {
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
                    System.out.print("\nMasukkan Nama : ");
                    input.nextLine();
                    String nama = input.nextLine();
                    System.out.print("Jumlah Tunjangan: ");
                    double tunjangan = input.nextDouble();
                    System.out.print("Jumlah Gaji Pokok : ");
                    double gapok = input.nextDouble();
                    System.out.print("Banyak Bulan : ");
                    double banyakBulan = input.nextDouble();
                    hitungGaji(nama, tunjangan, gapok, banyakBulan);
                    break;
                case 2:
                    System.out.print("\nMasukkan Berat Badan Anda: ");
                    double berat = input.nextDouble();
                    System.out.print("Masukkan Tinggi Badan Anda : ");
                    double tinggi = input.nextDouble();
                    kurusGemuk(berat, tinggi);
                    break;
                default:
                    System.out.println("Nomor tidak ada!");
                    break;
            }
        }
        input.close();
    }

    public static void hitungGaji(String nama, double tunjangan, double gapok, double banyakBulan){ 
        double pajak, bpjs, gajiBulanan;

        System.out.println("Karyawan atas nama "+ nama + " slip gaji sebagai berikut :");
        if ((tunjangan+gapok) > 10000000) {
            pajak = (tunjangan+gapok) * 0.15;
        } else if ((tunjangan+gapok) > 5000000)  {
            pajak = (tunjangan+gapok) * 0.1;
        }else{
            pajak = (tunjangan+gapok) * 0.05;
        }
        bpjs = (tunjangan+gapok) * 0.03;
        gajiBulanan = (tunjangan+gapok)-(pajak + bpjs);

        System.out.println("Pajak :\t\t\tRp." + pajak);
        System.out.println("BPJS :\t\t\tRp." + bpjs);
        System.out.println("Gaji/bln :\t\tRp." + gajiBulanan);
        System.out.println("Total Gaji :\t\tRp." + gajiBulanan*banyakBulan);
    }

    public static void kurusGemuk(double berat, double tinggi){ 
        String badan;
        tinggi /= 100;
        double bmi = berat/tinggi*tinggi;
        if (bmi >= 25) {
            badan = "Gemuk\n";
        } else if (bmi >= 18.5) {
            badan = "Langsing\n";
        }else{
            badan = "Kurus\n";
        }

        System.out.println("\nNilai BMI Anda Adalah " + bmi);
        System.out.println("Anda Termasuk Berbadan " + badan);
    }
}
