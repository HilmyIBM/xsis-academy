package Test;

import java.util.Scanner;
import java.util.regex.Pattern;

    public class Soal2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
System.out.print("Username : ");
        String username = input.nextLine();
        soal2(username);
        input.close();
    }

    public static void soal2(String username){
        boolean check = Pattern.matches("[^A-Za-z^0-9]", username);
        if (username.length() >= 5 && username.length() < 10 && username.contains("_") && !check ) {
            System.out.println("Valid");
        }else System.out.println("Invalid");
    }
}
