import java.util.Arrays;
import java.util.Scanner;

public class Day06 {

    public static void countHill(String note) {
        int count = 0;
        boolean toGunung = false;
        boolean toLembah = false;
        int gunungCount = 0;
        int lembahCount = 0;
        for (int i = 0; i < note.length(); i++) {
            if (note.charAt(i) == 'U') {
                count++;
            }
            if (note.charAt(i) == 'D') {
                count--;
            }

            if (count > 0 && !toGunung) {
                toGunung = true;
            }
            if (count < 0 && !toLembah) {
                toLembah = true;
            }

            if (toLembah && count == 0) {
                lembahCount++;
                toLembah = false;
            }

            if (toGunung && count == 0) {
                gunungCount++;
                toGunung = false;
            }

        }
        System.out.println("Lembah yang telah dilewati adalah sebanyak " + lembahCount);
    }

    public static void caesarCipher(String text, int key) {
        String encryptedText = "";

        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);

            if (Character.isUpperCase(currentChar)) {
                encryptedText += (char) (((currentChar - 'A' + key) % 26) + 'A');
            } else if (Character.isLowerCase(currentChar)) {
                encryptedText += (char) (((currentChar - 'a' + key) % 26) + 'a');
            } else {
                encryptedText += currentChar;
            }
        }

        System.out.println(encryptedText);
    }

    public static void number03(String highString, String text) {
        int[] highs = Arrays.stream(highString.split(",")).mapToInt(Integer::parseInt).toArray();
        int[] valueChar = new int[text.length()];
        int max = 0;

        for (int i = 0; i < text.length(); i++) {
            int indexChar = text.charAt(i) - 'a';
            valueChar[i] = highs[indexChar];
        }

        for (int i = 0; i < valueChar.length; i++) {
            if (valueChar[i] > max) {
                max = valueChar[i];
            }
        }

        System.out.println(max + " x " + text.length() + " = " + (max * text.length()));
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
            System.out.println("\n1. Hitung lembah");
            System.out.println("2. Caesar Cipher");
            System.out.println("3. Caesar Cipher");
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
                    System.out.println("\nMasukkan Teks : ");
                    String text = sc.nextLine();
                    System.out.println("Masukkan Key : ");
                    int key = sc.nextInt();
                    sc.nextLine();
                    caesarCipher(text, key);
                    break;
                case 3:
                    System.out.println("\nMasukkan array panjang : ");
                    String highString = sc.nextLine();
                    System.out.println("Masukkan text : ");
                    text = sc.nextLine();
                    number03(highString, text);
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
            if (choose_menu != 0) {
                continueMenu = askContinue(sc);
            }

        }
        sc.close();
    }
}
