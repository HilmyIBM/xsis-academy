package Coba;

import java.util.Scanner;

public class Soal2 {
    public static void checkUsername(String username) {
        if (username.length() < 5 || username.length() > 10) {
            System.out.println("invalid");
            return;
        }
        if (!username.contains("_")) {
            System.out.println("invalid");
            return;
        }
        for (int i = 0; i < username.length(); i++) {
            if (!String.valueOf(username.charAt(i)).matches("[A-Za-z0-9_]")) {
                System.out.println("invalid");
                return;
            }
        }
        System.out.println("valid");

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Masukkan username : ");
        String input = sc.nextLine();
        sc.close();
        checkUsername(input);
    }
}
