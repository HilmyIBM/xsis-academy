package Day03;

import java.util.Scanner;

public class _PRDay03 {
    public static void main(String[] args) {
        // gaji();
        // faktorial();
    }

    public static void gaji(){
        Scanner scan = new Scanner(System.in);
        int golongan, jamKerja, upahPerJam = 0, totalUpah, jamLembur, upahLembur = 0, totalGaji;

        // Intro 
        System.out.println("Gaji Karyawan");
        System.out.println("=================");

        //Input
        System.out.print("Inputkan Golongan: ");
        golongan = scan.nextInt();
        System.out.print("Inputkan Jam Kerja: ");
        jamKerja = scan.nextInt();

        // Process
        switch (golongan) {
            case 1:
                upahPerJam = 2000;
                break;
            case 2:
                upahPerJam = 3000;
                break;
            case 3:
                upahPerJam = 4000;
                break;
            case 4:
                upahPerJam = 5000;
                break;
            default:
                break;
        }

        
        if(jamKerja > 40){
            jamLembur = jamKerja - 40;
            upahLembur = (upahPerJam * 3 / 2) * jamLembur;
            jamKerja -= jamLembur; 
        }
        
        totalUpah = upahPerJam * jamKerja;

        totalGaji = totalUpah + upahLembur;

        // Output
        System.out.println("===================");
        System.out.println("Upah:\t" + totalUpah);
        System.out.println("Lembur:\t" + upahLembur);
        System.out.println("Total:\t" + totalGaji);

        scan.close();
    }

    public static void faktorial(){
        Scanner scan = new Scanner(System.in);
        int nilai, hasil = 1;

        // Intro 
        System.out.println("Program Faktorial");
        System.out.println("====================");

        //Input
        System.out.print("Inputkan Nilai: ");
        nilai = scan.nextInt();

        System.out.print(nilai + "! = ");
        while(nilai >= 1){
            hasil *= nilai;
            System.out.print(nilai + (nilai > 1 ? "x" : ""));
            nilai--;
        }

        System.out.println(" = " + hasil);
        scan.close();
    }
}
