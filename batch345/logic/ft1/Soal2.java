package ft1;

import java.util.Scanner;

public class Soal2 {
    public static void soal2(String username) {
        boolean isContainNum = true;
        boolean isLengthEnough = true;
        boolean isContain_ = true;
        boolean isContainAlfabet = true;

        if (username.length() < 5 || username.length() > 10) {
            isLengthEnough = false;
        }
        
        if (!username.contains("_")) {
            isContain_ = false;
        }

        int countAlfabet = 0;
        int countNum = 0;

        for (int i = 0; i < username.length(); i++) {
            if (String.valueOf(username.charAt(i)).matches("[A-Za-z]")) {
                countAlfabet++;
            }
            if (String.valueOf(username.charAt(i)).matches("[0-9]")) {
                countNum++;
            }
        }

        if (countAlfabet == 0) {
            isContainAlfabet = false;
        }
        if (countNum == 0) {
            isContainNum = false;
        }

        if (isContainNum && isContainAlfabet && isContain_ && isLengthEnough) {
            System.out.println("valid");
        } else {
            System.out.println("invalid");
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Masukkan username = ");
        String input = sc.nextLine();
        sc.close();
        soal2(input);
    }
}
