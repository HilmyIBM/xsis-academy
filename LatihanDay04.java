import java.util.Scanner;

public class LatihanDay04 {
    public static void main(String[] args) {
        menu();
    }

    public static void menu(){
        int num = 0;
        System.out.println("Enter exercise number:\n");
        Scanner scan = new Scanner(System.in);
        num = scan.nextInt();
        switch (num) {
            case 1:
                no1();
                break;

            case 2:
                no2();
                break;

            case 3:
                no3();
                break;

            case 4:
                no4();
                break;

            case 5:
                no5();
                break;

            case 6:
                // no6();
            default:
                break;

            case 7:
                // no7();
                break;

            case 0:
                // no8();
                break;
        }
        scan.close();

    }

    public static void no1() {
        String input = "Aku bisa belajar c#";
        System.out.println("Input: "+input);
        String[] parts = input.split("\\s+");
        int wordCount = 1;

        for (String word : parts) {
            System.out.println("Kata " + wordCount + " = " + word);
            wordCount++;
        }

        System.out.println("Total kata adalah " + (wordCount - 1));
        System.out.println();
        menu();

    }

    public static void no2() {
        String input = "aku puNya BUKU";
        System.out.println("Input: "+input);
        int len = input.length();
        int uCount = 0;
        int kapitalCount = 0;
        String inputUppercase = input.toUpperCase();

        for (int i = 0; i < len; i++) {
            char inputString = input.charAt(i);
            char inputUppercaseString = inputUppercase.charAt(i);
            if (inputString == 'U' || inputString == 'u') {
                uCount++;
            }
            if (inputString == inputUppercaseString && inputString != ' ') {
                kapitalCount++;
            }
        }

        System.out.println("terdapat huruf U sebanyak " + uCount + " kali.");
        System.out.println("Terdapat huruf kapital sebanyak " + kapitalCount + " kali.");
        System.out.println();
        menu();
    }

    public static void no3() {
        String input = "Aku Sayang Kamu";
        System.out.println("Input: "+input);
        System.out.println();
        // int len = input.length();
        String[] parts = input.split("\\s+");
        int partsCount = parts.length;

        for (int n = 0; n < partsCount; n++) {

            int len = parts[n].length();
            for (int i = 0; i < len; i++) {
                char inputString = parts[n].charAt(i);

                if (i == 0) {
                    System.out.print(inputString);
                } else if (i == len - 1) {
                    System.out.print(inputString + " ");
                    ;
                } else {
                    System.out.print("*");
                }
                // System.out.println(partsCount);
            }

        }
        System.out.println();
        System.out.println();
        menu();

    }

    public static void no4() {
        String input = "Aku Mau Makan";
        System.out.println("Input: "+input);
        System.out.println();
        // int len = input.length();
        String[] parts = input.split("\\s+");
        int partsCount = parts.length;

        for (int n = 0; n < partsCount; n++) {

            int len = parts[n].length();
            for (int i = 0; i < len; i++) {
                char inputString = parts[n].charAt(i);

                if (i == 0) {
                    System.out.print("*");
                } else if (i == len - 1) {
                    System.out.print("*" + " ");
                    ;
                } else {
                    System.out.print(inputString);
                }
                // System.out.println(partsCount);
            }

        }
        System.out.println();
        System.out.println();
        menu();
    }

    public static void no5() {
        String input = "saya pasti bisa";
        System.out.println("Input: "+input);
        System.out.println();
        // int len = input.length();
        String[] parts = input.split("\\s+");
        int partsCount = parts.length;

        for (int n = 0; n < partsCount; n++) {

            int len = parts[n].length();
            for (int i = 0; i < len; i++) {
                char inputString = parts[n].charAt(i);

                if (i == len-2 || i == len-3) {
                    System.out.print(inputString);
                    ;
                } else if (i == len-1) {
                    System.out.print(inputString + " ");
                } 
                // System.out.println(partsCount);
            }

        }
        System.out.println();
        System.out.println();
        menu();

    }

}
