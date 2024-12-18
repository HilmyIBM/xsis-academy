import java.util.Scanner;

public class Day04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Pilihan menu");
        System.out.println("1. Counter Kata");
        System.out.println("2. Counter Capitalize and Alphabhet Counter");
        System.out.println("3. Middle Word Censor");
        System.out.println("4. First and Last Word Censor");
        System.out.println("5. Trim to 3 last words");
        System.out.print("Pilih menu: ");
        switch (Integer.parseInt(sc.nextLine())) {
            case 1:
                try {
                    System.out.print("Masukkan kalimat: ");
                    String words = sc.nextLine();
                    WordCounter(words);
                } catch (Exception e) {
                    // TODO: handle exception
                    System.err.println(e.getMessage());
                }
                break;
            case 2:
                try {
                    System.out.print("Masukkan kalimat: ");
                    capitalizedAndAlphabetCounter(sc.nextLine());
                } catch (Exception e) {
                    // TODO: handle exception
                    System.err.println(e.getMessage());
                }
                break;
            case 3:
                System.out.print("Masukkan kalimat: ");
                try {
                    String input = sc.nextLine();
                    middleCensor(input);
                } catch (Exception e) {
                    // TODO: handle exception
                    System.err.println();
                    main(args);
                }
                break;
            case 4:
                System.out.print("Masukkan kalimat: ");
                try {
                    frontBackCensor(sc.nextLine());
                } catch (Exception e) {
                    // TODO: handle exception
                    System.err.println(e.getMessage());
                }
                break;
            case 5:
                System.out.print("Masukkan kalimat: ");
                try {
                    threeLastWord(sc.nextLine());
                } catch (Exception e) {
                    // TODO: handle exception
                    System.err.println(e.getMessage());
                }
                break;
            default:
                System.exit(0);
                break;
        }
        sc.close();
    }

    static void WordCounter(String words) {
        String[] allWords = words.split(" ");
        for (int i = 0; i < allWords.length; i++) {
            System.out.printf("kata %d = %s\n", i + 1, allWords[i]);
        }
        System.out.println("Total kata adalah " + allWords.length);
    }

    static void capitalizedAndAlphabetCounter(String input) {
        int counterCapitalize = 0;
        int counterU = 0;
        char[] isCapitalized = input.toCharArray();
        for (int i = 0; i < isCapitalized.length; i++) {
            // not using ascii number
            if (isCapitalized[i] >= 'A' && isCapitalized[i] <= 'Z') {
                counterCapitalize++;
            }
            // using ascii number
            if ((int) isCapitalized[i] == 85 || (int) isCapitalized[i] == 117) {
                counterU++;
            }
        }
        System.out.println("huruf u ada " + counterU);
        System.out.println("huruf kapital ada " + counterCapitalize);
    }

    static void middleCensor(String input) {
        String[] words = input.split(" ");
        StringBuilder result = new StringBuilder();
        for (String data : words) {
            int panjangKata = data.length();
            if (panjangKata <= 2) {
                result.append(data).append(" ");
            } else {
                String middle = "*".repeat(panjangKata - 2); // tidak menginklude masing masing kata depan dan belakang
                data = data.charAt(0) + middle + data.charAt(panjangKata - 1); // menggabungkan kata (front and back
                                                                               // still default)
                result.append(data).append(" ");
            }
        }
        System.out.println(result);
    }

    static void frontBackCensor(String input) {
        String[] words = input.split(" ");
        StringBuilder result = new StringBuilder();
        for (String data : words) {
            int panjangKata = data.length();
            if (panjangKata <= 2) {
                result.append("*".repeat(2)).append(" ");
            } else {
                data = "*" + data.substring(1, panjangKata - 1) + "*"; // front and back will be replace with asteriks
                result.append(data).append(" ");
            }
        }
        System.out.println(result);
    }

    static void threeLastWord(String input) {
        String[] words = input.split(" ");
        StringBuilder result = new StringBuilder();
        for (String data : words) {
            int panjangKata = data.length();
            data = data.substring(panjangKata - 3, panjangKata); // last index will automatically -1
            result.append(data).append(" ");
        }
        System.out.println(result);
    }
}
