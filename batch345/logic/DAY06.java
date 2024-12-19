import java.util.Scanner;

public class DAY06 {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Masukkan langkah : ");
        int jumlah_langkah = input.nextInt();
        input.nextLine();
        System.out.println("Masukkan pola :");
        String langkah = input.nextLine();
        lembah(jumlah_langkah, langkah);
    }

    public static void lembah(int l, String n) {
        int Jumlah = n.length();
        int gunung = 0;
        int lembah = 0;

        for (int i = 0; i < l; i++) {
            if (n.charAt(i) == 'U') {
                gunung++;
                if (gunung == 0) {
                    lembah++;
                }
            } else {
                gunung--;
            }
        }

        System.out.println("Jumlah karakter : " + Jumlah);
        System.out.println("Jumlah lembah : " + lembah);
    }
}
