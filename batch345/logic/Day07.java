import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day07 {

    public static void potongKayu(int n, Integer[] kayu) {
        ArrayList<Integer> kayuArrList = new ArrayList<Integer>(Arrays.asList(kayu));

        System.out.println("");
        while (!kayuArrList.isEmpty()) {
            int kayuPendek = kayuArrList.stream().min(Integer::compare).orElse(0);

            for (int i = 0; i < kayuArrList.size(); i++) {
                kayuArrList.set(i, kayuArrList.get(i) - kayuPendek);
            }
            System.out.println(kayuArrList.size());

            kayuArrList.removeIf(panjang -> panjang == 0);
        }
    }

    public static boolean askContinue(Scanner sc) {
        System.out.print("\nKembali ke menu utama? (y/n): ");
        String response = sc.nextLine().toLowerCase();
        return response.equals("y");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean continueMenu = true;

        while (continueMenu) {
            System.out.println("\n1. Jumlah Stick");
            System.out.println("2. Caesar Cipher");
            System.out.println("3. High Value");
            System.out.println("4. Parse Vowel & Consonant");
            System.out.println("5. Password Validation");
            System.out.println("6. SOS");
            System.out.println("7. Print per character");
            System.out.println("8. Ramuan Ajaib");
            System.out.println("9. Keluar");
            System.out.print("\nPilih menu: ");
            int choose_menu = sc.nextInt();
            sc.nextLine();

            switch (choose_menu) {
                case 1:
                    System.out.print("Masukkan Jumlah Kayu : ");
                    int n = sc.nextInt();
                    Integer[] kayu = new Integer[n];
                    System.out.println("Masukkan daftar panjang kayu : ");
                    for (int i = 0; i < n; i++) {
                        kayu[i] = sc.nextInt();
                    }
                    sc.nextLine();
                    potongKayu(n, kayu);
                    break;
                case 9:
                    System.out.println("Terima kasih!");
                    continueMenu = false;
                    break;
                default:
                    break;
            }
            if (choose_menu != 0) {
                continueMenu = askContinue(sc);
            }

        }
        sc.close();
    }
}
