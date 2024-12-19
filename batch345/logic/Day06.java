import java.util.Scanner;

public class Day06 {

    public static void countHill(String note) {

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean continueMenu = true;

        while (continueMenu) {
            System.out.println("\n1. Hitung melewati lembah");
            System.out.println("2. Cek tagihan makanan");
            System.out.println("3. Diagonal difference");
            System.out.println("4. Hitung lilin tertinggi");
            System.out.println("5. Rotate position of number");
            System.out.println("6. Bubble sort");
            System.out.println("7. Deret Bilangan Prima");
            System.out.println("8. Hitung jumlah video game");
            System.out.println("9. Keluar");
            System.out.print("\nPilih menu: ");
            int choose_menu = sc.nextInt();
            sc.nextLine();

            switch (choose_menu) {
                case 1:
                    System.out.print("\nMasukkan Catatan : ");
                    String note = sc.nextLine();

                    countHill(note);
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 7:

                    break;
                case 8:

                    break;

                default:
                    break;
            }

        }
    }
}
