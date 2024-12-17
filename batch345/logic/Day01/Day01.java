package Day01;
import java.util.Scanner;

public class Day01 {
    public static void main(String[] args) {
        int pilih;
        Scanner scan = new Scanner(System.in);

        // Intro 
        System.out.println("PILIH PROGRAM");
        System.out.println("=============");
        System.out.println("1. Hitung Luas dan Keliling Lingkaran");
        System.out.println("2. Hitung Luas dan Keliling Persegi");
        System.out.println("3. Menentukan Modulus");
        System.out.println("4. Program Pemulung");

        // Input
        System.out.print("Pilih Program: ");
        pilih = scan.nextInt();

        switch (pilih) {
            case 1:
                Cls();
                Lingkaran();
                break;
            case 2:
                Cls();
                Persegi();
                break;
            case 3:
                Cls();
                Modulus();
                break;
            case 4:
                Cls();
                Pemulung();
                break;
            default:
                break;
        }

        scan.close();
    }

    public static void Lingkaran () {
        Scanner scan = new Scanner(System.in);
        double jari, luas, keliling;

        // Intro 
        System.out.println("Program Hitung Luas dan Keliling Lingkaran");
        System.out.println("==========================================");

        // Input
        System.out.print("Masukkan Panjang Jari-jari : ");
        jari = scan.nextDouble();

        // Rumus
        luas = Math.PI * (jari * jari);    
        keliling = 2 * Math.PI * jari;

        //Output
        System.out.printf("Luas Lingkaran adalah %.0f\n", luas);    
        System.out.printf("Keliling Lingkaran adalah %.0f", keliling);

        scan.close();
    }

    public static void Persegi(){
        Scanner scan = new Scanner(System.in);
        double sisi, luas, keliling;

        // Intro 
        System.out.println("Program Hitung Luas dan Keliling Persegi");
        System.out.println("=======================================");

        // Input
        System.out.print("Masukkan Panjang Sisi : ");
        sisi = scan.nextDouble();

        // Rumus
        luas = sisi * sisi;    
        keliling = 4 * sisi;

        //Output
        System.out.printf("Luas Persegi adalah %.0f\n", luas);    
        System.out.printf("Keliling Persegi adalah %.0f", keliling);

        scan.close();
    }

    public static void Modulus(){
        Scanner scan = new Scanner(System.in);
        int angka, pembagi;

        // Intro 
        System.out.println("Program Menentukan Modulus");
        System.out.println("===========================");

        System.out.print("Masukan angka: ");
        angka = scan.nextInt();
        System.out.print("Masukan pembagi: ");
        pembagi = scan.nextInt();

        if (angka % pembagi == 0){
            System.out.println(angka + " % " + pembagi + " adalah 0");
        } else {
            System.out.println(angka + " % " + pembagi + " bukan 0 melainkan " + angka % pembagi);
        }

        scan.close();
    }

    public static void Pemulung(){
        Scanner scan = new Scanner(System.in);
        
        int puntung, harga_total, sisa_puntung, total_puntung;
        int batang = 8; // 1 batang membutuhkan 8 puntung rokok
        int harga_per_batang = 500;

        // Intro 
        System.out.println("Program Pemulung");
        System.out.println("=================");

        System.out.print("Masukan Jumlah Puntung Rokok: ");
        puntung = scan.nextInt();

        total_puntung = puntung / batang;
        harga_total = total_puntung * harga_per_batang;
        sisa_puntung = puntung % batang;
        System.out.println("Jumlah batang rokok yang dapat dibuat = " + total_puntung);
        System.out.println("Harga Total = " + harga_total);
        System.out.println("Sisa puntung = " + sisa_puntung);

        scan.close();
    }

    public static void Cls(){  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }  
}
