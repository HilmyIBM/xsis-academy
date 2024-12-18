import java.util.Scanner;

public class Day04 {

    public static void splitText(String text) {
        String regex = "[\\s]";
        String[] wordArr = text.split(regex);
        for (int i = 0; i < wordArr.length; i++) {
            System.out.println("Kata " + i + " = " + wordArr[i]);
        }
        System.out.println("Total kata adalah " + wordArr.length);
    }

    public static void wordCheck(String text) {
        int countUpperCase = 0;
        int countCharU = 0;
        char[] charArr = text.toCharArray();
        for (char c : charArr) {
            if (Character.isUpperCase(c)) {
                countUpperCase++;
            }
            if (Character.toLowerCase(c) == 'u') {
                countCharU++;
            }
        }

        System.out.println("Huruf u ada " + countCharU);
        System.out.println("Huruf kapital ada " + countUpperCase);
    }

    // public static void convertToStarIn(String text) {
    // String[] wordArr = text.split(" ");

    // String[] hasilArray = new String[wordArr.length];

    // for (int i = 0; i < wordArr.length; i++) {
    // String word = wordArr[i];
    // String hasilKata = "";
    // for (int j = 0; j < word.length(); j++) {
    // if (j != 0 && j != word.length() - 1) {
    // hasilKata += "*";
    // } else {
    // hasilKata += word.charAt(j);
    // }
    // }
    // hasilArray[i] = hasilKata;
    // }

    // String hasil = String.join(" ", hasilArray);

    // System.out.println(hasil);
    // }

    public static void convertToStarIn(String text) {
        String[] wordArr = text.split(" ");

        String hasil = "";

        for (String word : wordArr) {
            for (int i = 0; i < word.length(); i++) {
                if (i != 0 && i != word.length() - 1) {
                    hasil += "*";
                } else {
                    hasil += word.charAt(i);
                }
            }
            hasil += " ";
        }

        hasil = hasil.trim();

        System.out.println(hasil);
    }

    public static void convertToStarOut(String text) {
        String[] wordArr = text.split(" ");

        String hasil = "";

        for (String word : wordArr) {
            for (int i = 0; i < word.length(); i++) {
                if (i != 0 && i != word.length() - 1) {
                    hasil += word.charAt(i);
                } else {
                    hasil += "*";
                }
            }
            hasil += " ";
        }

        hasil = hasil.trim();

        System.out.println(hasil);
    }

    public static void printLastChar(String text) {
        String[] wordArr = text.split(" ");

        String hasil = "";

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
            System.out.println("\n1. Split Text");
            System.out.println("2. Character Check");
            System.out.println("3. Convert inside word to *");
            System.out.println("4. Convert outside word to *");
            System.out.println("5. Fibonacci 2");
            System.out.println("6. Fibonacci 3");
            System.out.println("7. Upah Mingguan");
            System.out.println("8. Faktorial");
            System.out.println("9. Keluar");
            System.out.print("\nPilih menu: ");
            int choose_menu = sc.nextInt();
            sc.nextLine();

            switch (choose_menu) {
                case 1:
                    System.out.print("\nMasukkan Kata : ");
                    String text = sc.nextLine();
                    splitText(text);
                    break;
                case 2:
                    System.out.print("\nMasukkan Kata : ");
                    String text2 = sc.nextLine();
                    wordCheck(text2);
                    break;

                case 3:
                    System.out.print("\nMasukkan Kata :");
                    String text3 = sc.nextLine();
                    convertToStarIn(text3);
                    break;
                case 4:
                    System.out.print("\nMasukkan Kata :");
                    String text4 = sc.nextLine();
                    convertToStarOut(text4);
                    break;
                case 9:
                    System.out.println("Terima kasih!");
                    continueMenu = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid! Silakan coba lagi.");
                    break;
            }
            if (choose_menu != 9) {
                continueMenu = askContinue(sc);
            }
        }
        sc.close();
    }
}
