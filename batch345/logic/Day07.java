import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
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

    public static void gamblingNumber(int point, int bet, char guess) {
        Random random = new Random();
        int randNum = random.nextInt(10);
        System.out.println(randNum);
        if (randNum == 5) {
            System.out.println("Draw");
        } else {
            char answer = (randNum > 5 ? 'U' : 'D');
            if (answer == guess) {
                System.out.println("You Win");
                point += bet;
            } else {
                System.out.println("You Lose");
                point -= bet;
            }
        }

        System.out.println("Poin saat ini : " + point);
    }

    public static void gasoline(String[] arrJarakStrings, int batasHitung) {
        double totalJarak = 0;
        for (int i = 0; i < batasHitung; i++) {
            String jarakString = arrJarakStrings[i].toUpperCase().replaceAll("\\s+", "");

            String numPart = jarakString.replaceAll("[^0-9.]", "");
            String unitPart = jarakString.replaceAll("[0-9.]", "").toUpperCase();

            double jarak = Double.parseDouble(numPart);
            if (unitPart.equals("KM")) {
                totalJarak += jarak * 1000;
            } else if (unitPart.equals("M")) {
                totalJarak += jarak;
            }
            System.out.println(totalJarak);
        }

        int countGasoline = (int) Math.ceil(totalJarak / 2500);

        String totalJarakOutput = (totalJarak >= 1000) ? ((int) totalJarak / 1000) + "KM" : (int) totalJarak + "M";

        String[] arrJarakStrings2 = Arrays.copyOfRange(arrJarakStrings, 0, batasHitung);

        System.out.println("Jarak Tempuh = " + String.join(" + ", arrJarakStrings2) + " = " + totalJarakOutput);
        System.out.println("Bensin = " + countGasoline + " Liter");
    }

    public static void recipeCake(int n) {
        double terigu = 115 * (n / 15D);
        double gula = 190 * (n / 15D);
        double susu = 100 * (n / 15D);

        System.out.println(String.format("Terigu (gr) \t: %,.8f", terigu));
        System.out.println(String.format("Gula Pasir (gr) : %,.8f", gula));
        System.out.println(String.format("Susu (mL) \t: %,.8f", susu));

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
            System.out.println("2. Gambling Number");
            System.out.println("3. Hitung Gasoline");
            System.out.println("4. Hitung resep kue pukis");
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
                case 2:
                    System.out.print("Masukkan Poin : ");
                    int point = sc.nextInt();
                    System.out.print("Masukkan Taruhanmu : ");
                    int bet = sc.nextInt();
                    System.out.print("Masukkan Tebakanmu (U/D) : ");
                    char guess = sc.next().charAt(0);
                    sc.nextLine();
                    gamblingNumber(point, bet, guess);
                    break;
                case 3:
                    System.out.print("Masukkan banyaknya customer : ");
                    n = sc.nextInt();
                    sc.nextLine();
                    String[] arrJarak = new String[n];
                    for (int i = 0; i < n; i++) {
                        if (i == 0) {
                            System.out.print(
                                    "Masukkan jarak dari Toko ke customer " + (i + 1) + " = ");
                        } else {
                            System.out
                                    .print("Masukkan jarak dari customer " + (i) + " ke customer " + (i + 1) + " = ");
                        }
                        arrJarak[i] = sc.nextLine();
                    }
                    System.out.print("Customer yang dihitung : ");
                    int batasHitung = sc.nextInt();
                    sc.nextLine();
                    gasoline(arrJarak, batasHitung);
                    break;
                case 4:
                    System.out.print("Masukkan banyaknya kue : ");
                    n = sc.nextInt();
                    sc.nextLine();
                    recipeCake(n);
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
