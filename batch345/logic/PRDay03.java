import java.util.Scanner;

public class PRDay03 {
    public static void main(String[] args) {
        pilihSoal();        
    }
    public static void pilihSoal(){
        Scanner input = new Scanner(System.in);     
        int noSoal;

        while (true) {
            System.out.print("Soal No. Berapa yang akan dikerjakan (0 untuk keluar dari program) : ");
            noSoal = input.nextInt();
            input.nextLine();
            
            if(noSoal == 0) {
                System.out.println("Terima Kasih");
                break;
            }

            switch (noSoal) {
                case 1:
                System.out.print("Golongan : ");    
                int golongan = input.nextInt();
                System.out.print("Jam Kerja : ");    
                double jamKerja = input.nextDouble();
                input.nextLine();
                hitungGaji(jamKerja, golongan);
                break;
                case 2:
                System.out.print("Masukkan angka: ");
                int banyakOrang = input.nextInt();
                input.nextLine();
                hitungCaraDuduk(banyakOrang);
                break;
                default:
                System.out.println("Nomor tidak ada!");
                break;
            }
        }
        input.close();
    }

    public static void hitungGaji(double jamKerja, int golongan){
        double lembur, totalGaji = 0;
        switch (golongan) {
            case 1:
                totalGaji = jamKerja * 2000;
                break;
            case 2:
                totalGaji = jamKerja * 3000;
                break;
            case 3:
                totalGaji = jamKerja * 4000;
                break;
            case 4:
                totalGaji = jamKerja * 5000;
                break;

            default:
                break;
        }
        
        lembur = (jamKerja > 40) ? (jamKerja -40)* 0.5 : 0;
        System.out.println("Upah :\t\t\t" + totalGaji);
        System.out.println("Lembur :\t\t\t" + lembur);
        System.out.println("Total :\t\t\t" + (totalGaji + lembur));
    }

    public static void hitungCaraDuduk(int banyakOrang){
        int result = 1;
        for (int i = 1; i <= banyakOrang; i++) {
            result *= i;
        }
        System.out.println("Cara mereka Duduk adalah : " + result);
    }
}
