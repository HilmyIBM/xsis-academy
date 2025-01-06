package Coba;

public class Soal4 {
    public static void lembarKe(int n, int x) {
        int page = 0;
        do {
            page++;
            x -= 2;
        } while (x > 0);
        if (page > n) {
            System.out.println("Nomor halaman melebihi halaman buku yang tersedia");
            return;
        }
        System.out.println("lembar ke-" + page);
    }

    public static void main(String[] args) {
        lembarKe(10, 21);
    }
}
