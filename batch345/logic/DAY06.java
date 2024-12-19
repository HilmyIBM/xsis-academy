import java.util.Scanner;

public class DAY06 {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Masukkan langkah : ");
        String langkah = input.nextLine();
        lembah(langkah);
    }

    public static void lembah(String n) {
        int jumlah = n.length();
        int gunung = 0;
        int lembah = 0;

        for (char langkah : n.toCharArray()) {
            if (langkah == 'U') {
                gunung++;
            } else if (langkah == 'D') {
                lembah++;
            }
        }
        System.out.println("Jumlah karakter : " + jumlah);
        System.out.println("Jumlah lembah : " + (lembah - gunung));
    }
}
