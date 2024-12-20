import java.util.Arrays;
import java.util.Scanner;

public class Day06 {

    public static void countHill(String note) {
        int count = 0;
        int gunungCount = 0;
        int lembahCount = 0;

        for (int i = 0; i < note.length(); i++) {
            if (Character.toLowerCase(note.charAt(i)) == 'u') {
                count++;
            }
            if (Character.toLowerCase(note.charAt(i)) == 'd') {
                count--;
            }

            if (count == 0 && Character.toLowerCase(note.charAt(i)) == 'u') {
                lembahCount++;
            }

            if (count == 0 && Character.toLowerCase(note.charAt(i)) == 'd') {
                gunungCount++;
            }

        }
        System.out.println("Lembah yang telah dilewati adalah sebanyak " + lembahCount);
        System.out.println("Gunung yang telah dilewati adalah sebanyak " + gunungCount);
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

    public static void vowelConsonant(String text) {
        String vowel = "";
        String consonant = "";

        for (int i = 0; i < text.length(); i++) {
            String lowText = text.toLowerCase();

            if (lowText.charAt(i) != ' ') {
                if (isVowel(lowText.charAt(i))) {
                    vowel += lowText.charAt(i);
                } else {
                    consonant += lowText.charAt(i);
                }
            }
        }

        System.out.println("\nVokal : " + vowel);
        System.out.println("Konsonan :" + consonant);
    }

    public static boolean isVowel(char ch) {
        // Make the list of vowels
        String str = "aeiouAEIOU";
        return (str.indexOf(ch) != -1) ? true
                : false;
    }

    public static void checkPassword(String password) {
        if (password.length() < 6) {
            System.out.println("Password Weak & Kurang dari 6 digit");
        }

        boolean hasDigit = false;
        boolean hasLowerCase = false;
        boolean hasUpperCase = false;
        boolean hasSpecialChar = false;

        String specialChars = "!@#$%^&*()-+";

        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            } else if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            } else if (specialChars.indexOf(c) >= 0) {
                hasSpecialChar = true;
            }
        }

        if (!hasDigit) {
            System.out.println("Password Weak & Kurang Angka.");
        }
        if (!hasLowerCase) {
            System.out.println("Password Weak & Kurang Huruf Kecil.");
        }
        if (!hasUpperCase) {
            System.out.println("Password Weak & Kurang Huruf Besar.");
        }
        if (!hasSpecialChar) {
            System.out.println("Password Weak & Kurang Symbol.");
        }

        if (hasDigit && hasLowerCase && hasUpperCase && hasSpecialChar) {
            System.out.println("Password Strong.");
        }
    }

    public static int incorrectSignals(String signal) {
        String sos = "SOS";
        int incorrectCount = 0;

        for (int i = 0; i < signal.length(); i += 3) {
            String part = signal.substring(i, (i + 3 < signal.length() ? i + 3 : signal.length()));

            for (int j = 0; j < part.length(); j++) {
                if (part.charAt(j) != sos.charAt(j)) {
                    incorrectCount++;
                }
            }
        }
        return incorrectCount;

    }

    public static void printPerChar(String text) {
        for (int i = 0; i < text.length(); i++) {
            System.out.println("***" + Character.toLowerCase(text.charAt(i)) + "***");
        }
    }

    public static void magicPotion(int k, String obsString) {
        int[] obstacles = Arrays.stream(obsString.split(",")).mapToInt(Integer::parseInt).toArray();
        int potionCount = 0;

        for (int i = 0; i < obstacles.length; i++) {
            while (k < obstacles[i]) {
                potionCount++;
                k++;
            }
        }

        System.out.println("Karakter perlu meminum " + potionCount + " botol ramuan ajaib");

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
                    System.out.print("\nMasukkan teks : ");
                    text = sc.nextLine();
                    vowelConsonant(text);
                    break;
                case 5:
                    System.out.print("\nMasukkan Password yang ingin diperiksa : ");
                    String password = sc.nextLine();

                    checkPassword(password);
                    break;
                case 6:
                    System.out.print("\nMasukkan sinyal : ");
                    String sinyalString = sc.nextLine();

                    System.out.println("Total sinyal yang salah adalah sebanyak " + incorrectSignals(sinyalString));
                    break;
                case 7:
                    System.out.println("\nMasukkan text : ");
                    text = sc.nextLine();

                    printPerChar(text);
                    break;
                case 8:
                    System.out.print("\nMasukkan maksimal ketinggian yang dapat dilewati : ");
                    int k = sc.nextInt();
                    sc.nextLine();
                    System.out.print("\nMasukkan array tinggi rintangan (pisahkan dengan \",\") : ");
                    String obsString = sc.nextLine();

                    magicPotion(k, obsString);
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
