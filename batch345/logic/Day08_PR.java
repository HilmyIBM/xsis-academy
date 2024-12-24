import java.util.ArrayList;
import java.util.List;

public class Day08_PR {
    public static void main(String[] args) {
        // cariHariIndah(20, 23, 6);
        hitungTotalEsLoli(24000,1000, 5);
    }

    // Nomor 1 (On Going)
    static void cariHariIndah(int i, int j, int k) {
        List<Integer> hasil = new ArrayList<>();

        // Iterasi untuk setiap hari dari i hingga j
        for (int hari = i; hari <= j; hari++) {
            // Menghitung kebalikan dari angka hari
            int kebalikan = kebalikanAngka(hari);
            // Menghitung selisih antara hari dan kebalikannya
            int perbedaan = Math.abs(hari - kebalikan);

            // Debugging: Tampilkan perhitungan untuk setiap hari
            System.out.println("Hari: " + hari + ", Kebalikan: " + kebalikan + ", Perbedaan: " + perbedaan);

            // Memeriksa apakah hari tersebut habis dibagi k dan perbedaannya habis dibagi k
            if (perbedaan % k == 0) {
                System.out.printf("Hari %d habis dibagi %d\n", perbedaan, k);
                hasil.add(hari);
            } else {
                System.out.printf("Hari %d tidak habis dibagi %d\n", perbedaan, k);
            }
        }

        // Tampilkan hasil
        System.out.println("Hari yang indah: ");
        for (int index = 0; index < hasil.size(); index++) {
            System.out.println(hasil.get(index));
        }
    }

    // Fungsi untuk membalikkan angka
    static int kebalikanAngka(int angka) {
        int kebalikan = 0;
        while (angka > 0) {
            kebalikan = kebalikan * 10 + angka % 10;
            angka /= 10;
        }
        return kebalikan;
    }

    // Nomor 2 (Hitung es loli)
    static void hitungTotalEsLoli(int uang, int hargaEsLoli, int stikPerEsLoliGratis) {
        int esLoliDibeli = uang / hargaEsLoli;
        int stikEsLoli = esLoliDibeli;

        int totalEsLoli = esLoliDibeli;
        while (stikEsLoli >= stikPerEsLoliGratis) {
            int esLoliGratis = stikEsLoli / stikPerEsLoliGratis;
            totalEsLoli += esLoliGratis;
            stikEsLoli = esLoliGratis + (stikEsLoli % stikPerEsLoliGratis);
        }
        System.out.println(totalEsLoli);
        ;
    }
}
