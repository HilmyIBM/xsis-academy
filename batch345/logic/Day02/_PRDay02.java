package Day02;

import java.util.Scanner;

public class _PRDay02 {
    public static void main(String[] args) {
        // slipGaji();
        // bmi();
        // mean();
    }

    public static void slipGaji(){
        Scanner scan = new Scanner(System.in);
        int tunjangan, gapok, banyakBulan, pajak=0, bpjs, gajiPerbulan, totalGaji;
        String nama;

        // Intro 
        System.out.println("Program Slip Gaji");
        System.out.println("=================");

        //Input
        System.out.print("Inputkan Nama: ");
        nama = scan.nextLine();
        System.out.print("Tunjangan: ");
        tunjangan = scan.nextInt();
        System.out.print("Gaji Pokok: ");
        gapok = scan.nextInt();
        System.out.print("Banyak Bulan: ");
        banyakBulan = scan.nextInt();

        // Process
        if (gapok+tunjangan > 10000000){
            pajak = (gapok + tunjangan) * 15 / 100;
        } else if (gapok+tunjangan > 5000000 && gapok+tunjangan <= 10000000){
            pajak = (gapok + tunjangan) * 10 / 100;
        } else if (gapok+tunjangan <= 5000000){
            pajak = (gapok + tunjangan) * 5 / 100;
        }

        bpjs = (gapok + tunjangan) * 3 / 100;
        gajiPerbulan = (gapok + tunjangan) - (pajak + bpjs);
        totalGaji = gajiPerbulan * banyakBulan;

        // Output
        System.out.println("===================");
        System.out.println("Karyawan atas nama "+ nama +" slip gaji sebagai berikut:");
        System.out.println(String.format("Pajak:\t\t\t%1$,d", pajak));
        System.out.println(String.format("BPJS:\t\t\t%1$,d", bpjs));
        System.out.println(String.format("Gaji Per Bulan:\t\t%1$,d", gajiPerbulan));
        System.out.println(String.format("Total Gaji:\t\t%1$,d", totalGaji));
    
        scan.close();
    }

    public static void bmi(){
        Scanner scan = new Scanner(System.in);
        double berat, tinggi, bmi, tinggiConvert;
        String tipeBadan;

        // Intro 
        System.out.println("Program Body Mass Index");
        System.out.println("=======================");

        //Input
        System.out.print("Masukkan Berat Badan (kg): ");
        berat = scan.nextDouble();
        System.out.print("Masukkan Tinggi Badan (cm): ");
        tinggi = scan.nextDouble();

        // Process
        tinggiConvert = (tinggi * tinggi) / 10000;
        bmi = berat / tinggiConvert;
        if (bmi >= 25){
            tipeBadan = "Gemuk";
        } else if (bmi >= 18.5 && bmi < 25){
            tipeBadan = "Langsing / Sehat";
        } else {
            tipeBadan = "Kurus";
        }

        // Output
        System.out.println("======================");
        System.out.println("Nilai BMI (Body Mass Index) anda adalah: " + bmi);
        System.out.println("Anda termasuk berbadan " + tipeBadan);

        scan.close();
    }

    public static void mean(){
        Scanner scan = new Scanner(System.in);
        int mtk, fisika, kimia, mean;

        // Intro 
        System.out.println("Hasil Ujian");
        System.out.println("===========");

        //Input
        System.out.print("Masukkan nilai MTK: ");
        mtk = scan.nextInt();
        System.out.print("Masukkan nilai Fisika: ");
        fisika = scan.nextInt();
        System.out.print("Masukkan nilai Kimia: ");
        kimia = scan.nextInt();

        mean = (mtk + fisika + kimia) / 3;

        System.out.println("Nilai Rata - rata: " + mean);

        if (mean >= 50){
            System.out.println("Selamat");
            System.out.println("Kamu Berhasil");
            System.out.println("Kamu Hebat");
        }else {
            System.out.println("Maaf");
            System.out.println("Kamu Gagal");
        }

        scan.close();
    }
}
