package FT1;

import java.util.Scanner;

public class Soal2 {
    public static void main(String[] args) {
     no2();   
    }

    public static void no2(){
        System.out.print("Input username: ");
        Scanner scan = new Scanner(System.in);
        boolean isValid = true;

        String usernameInput = scan.nextLine();
        String specialChars = "[!@#$%^&*()-+]";

        if (usernameInput.length() < 5 || usernameInput.length() > 10){
            isValid = false;
        }

        if (usernameInput.matches("[^A-Za-z0-9_]")){
            isValid = false;
        }

        if(!usernameInput.contains("_")){
            isValid = false;
        }

        if(specialChars.matches(specialChars)){
            isValid = false;
        }

        if (isValid == true){
            System.out.println("Output: valid");
        } else  {
            System.out.println("Output: invalid");

        }

    }
}
