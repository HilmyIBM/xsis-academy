package Day05;

import java.util.ArrayList;
import java.util.Scanner;

public class Day05PR {
    static Scanner scanIn = new Scanner(System.in);

    public static void main(String[] args) {
        
        //#2
        // primeNumbers();

        //#4
        // stairCase();

        //#4b
        pyramid();
    }

    public static void bubbleSort() {

    }

    public static void primeNumbers() {
        System.out.print("Input a number: ");
        int input = scanIn.nextInt();
        ArrayList<Integer> primeNumbers = new ArrayList<>();

        for (int i=0; i<=input; i++) {
            if (isPrime(i)) {
                if (primeNumbers.size() < 100)
                    primeNumbers.add(i);
                else
                    break;
            }
        }

        System.out.println("Prime Numbers: \n" + primeNumbers);
    }

    public static void halloweenSale() {

    }

    public static void stairCase() {
        System.out.print("Tinggi tangga: ");
        int tinggi = scanIn.nextInt();

        for (int i=1; i <= tinggi; i++){
            System.out.println(" ".repeat(tinggi-i) + "#".repeat(i));
        }
    }

    public static void pyramid() {
        System.out.print("Tinggi Piramida: ");
        int tinggi = scanIn.nextInt();

        for (int i=1; i <= tinggi; i++){
            System.out.println("-".repeat(tinggi-i) + "#".repeat(i) + "#".repeat(i-1));
        }
    }

    public static void sos() {

    }

    private static boolean isPrime(int number) {
        if (number < 1) 
            return false;
        
        int counter = 1;

        for (int i = 2; i <= number; i++ ){
            if (number % i == 0){
                counter++;
            }
        }

        return (counter==2) ? true : false;
    }
}
