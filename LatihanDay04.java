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
                no6();
            default:
                break;

            case 7:
                // no7();
                break;

            case 0:
                // exit
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

    public static void no6(){
        System.out.print("Input word: ");

        Scanner scan = new Scanner(System.in);

        String input = scan.next();

        int len = input.length();

        int yesCount = 0;

        boolean isPalindrome = false;

        for (int i = 0; i < len-1; i++){
            
            if (input.charAt(i) == input.charAt(len-1-i)){
                // System.out.println("Yes");
                isPalindrome = true;
                yesCount++;
            } else {
                // System.out.println("No");
                isPalindrome = false;
            }

            // System.out.println(len);
        }
        if (isPalindrome == true && yesCount <= len-1 ){
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

        System.out.println();
        
        menu();


    }

    public static void no7(){
        System.out.print("Masukkan input uang Andi: ");
        Scanner scan = new Scanner(System.in);
        int input = scan.nextInt();
        int hargaBaju[] = {35, 40, 50, 20};
        int hargaCelana[] = {40, 30, 45, 10};
        int hargaTotal[] = {};
        int totalMax = 0;

        for (int i = 0; i < hargaBaju.length-1; i++){
            hargaTotal[i] = hargaBaju[i] + hargaCelana [i];
            
        }
    }

}
