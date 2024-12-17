package Day01;
import java.util.Scanner;

public class PRDay01 {
    public static void main(String[] args) {
        int pilih;
        Scanner scan = new Scanner(System.in);

        // Intro 
        System.out.println("     PILIH PROGRAM");
        System.out.println("=========================");
        System.out.println("1. Menentukan Grade Nilai");
        System.out.println("2. Menentukan Ganjil Genap");

        // Input
        System.out.print("Pilih Program: ");
        pilih = scan.nextInt();

        switch (pilih) {
            case 1:
                Cls();
                GradeNilai();
                break;
            case 2:
                Cls();
                GanjilGenap();
                break;
            default:
                break;
        }

        scan.close();
    }

    public static void GradeNilai(){
        Scanner scan = new Scanner(System.in);
        int nilai;
        String grade = "";

        // Intro 
        System.out.println("Program Menentukan Grade Nilai");
        System.out.println("===============================");

        // Input
        System.out.print("Masukkan Nilai (0-100): ");
        nilai = scan.nextInt();

        if (nilai > 0 && nilai <= 100) {
            if (nilai >= 80){
                grade = "A";
            } else if (nilai >= 60 && nilai<80){
                grade = "B";
            } else if (nilai<60){
                grade = "C";
            }

            System.out.println("Grade nilai anda adalah: " + grade);

        } else {
            System.out.println("Inputan tidak sesuai");
        }
        
        
        scan.close();
    }

    public static void GanjilGenap(){
        Scanner scan = new Scanner(System.in);
        int angka;

        // Intro 
        System.out.println("Program Menentukan Ganjil Genap");
        System.out.println("================================");

        // Input
        System.out.print("Masukkan Angka: ");
        angka = scan.nextInt();

        if (angka % 2 == 0){
            System.out.println("Angka " + angka + " adalah genap");
        } else {
            System.out.println("Angka " + angka + " adalah ganjil");
        }

        scan.close();
    }

    public static void Cls(){  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    } 
}
