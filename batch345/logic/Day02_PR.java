import java.util.Scanner;

public class Day02_PR {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Tunjangan Pajak");
        System.out.println("2. BMI Calculator");
        System.out.println("3. Rata - Rata");
        System.out.print("Masukkan menu yang diinginkan: ");
        switch (Integer.parseInt(sc.nextLine())) {
            case 1:
                try {
                    System.out.print("Nama: ");
                    String name = sc.nextLine();
                    System.out.print("Tunjangan: ");
                    int tunjangan = Integer.parseInt(sc.nextLine());
                    System.out.print("Gapok: ");
                    int gapok = Integer.parseInt(sc.nextLine());
                    System.out.print("BanyakBulan: ");
                    int banyakBulan = Integer.parseInt(sc.nextLine());
                    tunjanganPajak(name, tunjangan, gapok, banyakBulan);
                } catch (Exception e) {
                    // TODO: handle exception
                    System.err.println(e.getMessage());
                    main(args);
                }
                break;
            case 2:
                try {
                    System.out.print("Masukkan berat badan anda (kg): ");
                    float weight = Float.parseFloat(sc.nextLine());
                    System.out.print("Masukkan tinggi badan anda (cm): ");
                    float height = Float.parseFloat(sc.nextLine());
                    BMICalculator(weight, height);
                } catch (Exception e) {
                    // TODO: handle exception
                    System.err.println(e.getMessage());
                    main(args);
                }
                break;
            case 3:
                System.out.print("Masukkan nilai MTK: ");
                int mat = sc.nextInt();
                System.out.print("Masukkan nilai Fisika: ");
                int physics = sc.nextInt();
                System.out.print("Masukkan nilai Kimia: ");
                int chemistry = sc.nextInt();
                rateCalculator(mat,physics,chemistry);
                break;
            default:
                System.exit(0);
                break;
        }
        sc.close();
    }

    static void tunjanganPajak(String nama, int tunjangan, int gapok, int bulan) throws Exception {
        int bpjs, gajiPerBulan, pajak, persentasePajak;

        if ((tunjangan + gapok) >= 10000000) {
            persentasePajak = 15;
        } else if ((tunjangan + gapok) >= 5000000) {
            persentasePajak = 10;
        } else {
            persentasePajak = 5;
        }

        try {
            pajak = (persentasePajak * (tunjangan + gapok)) / 100;
            bpjs = (3 * (tunjangan + gapok)) / 100;
            gajiPerBulan = (tunjangan + gapok) - (pajak + bpjs);
            System.out.printf("Karyawan atas nama %s slip gaji sebagai berikut :\n", nama);
            System.out.println("Pajak: " + pajak);
            System.out.println("Bpjs: " + bpjs);
            System.out.println("Gaji/bln: " + gajiPerBulan);
            System.out.println("Total gaji/banyak bulan: " + gajiPerBulan * bulan);
        } catch (ArithmeticException e) {
            throw new Exception(e.getMessage());
        }
    }

    static void BMICalculator(float weight, float height) {
        float BMI = 0.0f;
        // convert height to meter to calculate the BMI
        float realHeight = height / 100;
        BMI = weight / (realHeight * realHeight);
        if (BMI >= 25) {
            System.out.println("Anda termasuk berbadan gemuk");
        } else if (BMI >= 18.5) {
            System.out.println("Anda termasuk berbadan langsing/sehat");
        } else {
            System.out.println("Anda termasuk berbadan kurus");
        }
    }

    static void rateCalculator (int mat, int fisika, int kimia){
        int result;
        result = (mat + fisika + kimia) / 3;
        if (result >= 50){
            System.out.println("Nilai Rata-Rata: " + result);
            System.out.println("Selamat");
            System.out.println("Kamu Berhasil");
            System.out.println("Kamu Hebat");
        } else {
            System.out.println("Nilai Rata-Rata: " + result);
            System.out.println("Maaf");
            System.out.println("Kamu Gagal");
        }
    }
}
