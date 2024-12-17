import java.util.Scanner;

public class Day03_PR {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("1. Gaji Karyawan");
            System.out.println("2. Bangku Faktorial");
            System.out.print("Masukkan menu: ");
            switch (Integer.parseInt(sc.nextLine())) {
                case 1:
                    int golongan = 0;
                    System.out.println("1. golongan 1 dengan upah per jam 2000 rupiah");
                    System.out.println("2. golongan 2 dengan upah per jam 3000 rupiah");
                    System.out.println("3. golongan 3 dengan upah per jam 4000 rupiah");
                    System.out.println("4. golongan 4 dengan upah per jam 5000 rupiah");
                    System.out.print("Masukkan golongan: ");
                    int gol = Integer.parseInt(sc.nextLine());
                    System.out.print("Masukan jam kerja: ");
                    int jamKerja = Integer.parseInt(sc.nextLine());
                    if (gol == 1) {
                        golongan = 2000;
                    } else if (gol == 2) {
                        golongan = 3000;
                    } else if (gol == 3) {
                        golongan = 4000;
                    } else if (gol == 4) {
                        golongan = 5000;
                    } else {
                        System.out.println("Golongan tidak ada");
                    }
                    gajiKaryawan(golongan, jamKerja);
                    break;
                case 2:
                    System.out.print("Masukkan jumlah anak: ");
                    int anak = Integer.parseInt(sc.nextLine()), result = 1;
                    for (int i = anak; i >= 1; i--) {
                        result = result * i;
                    }
                    System.out.printf("ada %d cara", result);
                    break;
                default:
                    break;
            }
            sc.close();
        } catch (Exception e) {
            // TODO: handle exception
            System.err.println(e.getMessage());
        }
    }

    static void gajiKaryawan(int golongan, int jamKerja) {
        int lembur = 0;
        int upah = 0;
        if (jamKerja <= 40) {
            lembur = 0;
            for (int i = 1; i <= jamKerja; i++) {
                upah += golongan;
            }
        } else {
            for (int i = 1; i <= 40; i++) {
                upah += golongan;
            }
            for (int i = 1; i <= jamKerja - 40; i++) {
                lembur += golongan;
            }
            lembur *= 1.5;
        }
        System.out.println("Upah " + upah);
        System.out.println("Lembur " + lembur);
        System.out.println("total " + (upah + lembur));
    }
}
