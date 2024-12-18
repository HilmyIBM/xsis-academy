import java.util.Scanner;

public class Day04 {
    public static void main(String[] args) {
        pilihSoal();    
    }

    public static void pilihSoal(){
        Scanner input = new Scanner(System.in);     
        int noSoal;

        while (true) {
            System.out.print("Soal No. Berapa yang akan dikerjakan\n(0 untuk keluar dari program) : ");
            noSoal = input.nextInt();
            input.nextLine();
            
            if(noSoal == 0) {
                System.out.println("Terima Kasih");
                break;
            }

            switch (noSoal) {
                case 1:
                    System.out.print("Masukkan Kalimat : ");
                    String kalimat = input.nextLine();
                    tampilinPerkata(kalimat);    
                    break;
                case 2:
                    System.out.print("Masukkan Kalimat : ");
                    String cekHuruf = input.nextLine();
                    hitungHurufBesar(cekHuruf);    
                    break;
                default:
                    System.out.println("Nomor tidak ada!");
                    break;
            }
        }
        input.close();
    }
    //No 1 
    // ------------------------------------------------------------
    public static void tampilinPerkata(String kalimat){
        String[] kalimatKeArray = kalimat.split(" ");
        int i = 1;
        for (String kata : kalimatKeArray) {
            System.out.println("\nKata "+ i + " = " + kata +"");
            i++;
        }
    }

    //No 2 
    // ------------------------------------------------------------
    public static void hitungHurufBesar(String kalimat){
        char[] charArray = kalimat.toCharArray();
        int i = 0;
        for (char huruf : charArray) {
            if (Character.isUpperCase(huruf)) {
                i++;                    
            }
        }
        System.out.println("Jumlah Huruf Besar = " + i);

    }


}
