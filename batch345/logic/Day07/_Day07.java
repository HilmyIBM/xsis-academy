package Day07;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class _Day07 {
    public static void main(String[] args) {
        // potongKayu();
        // randomNumber();
        // ojol();
        // pukis();
    }

    public static void potongKayu(){
        Scanner s = new Scanner(System.in);

        // Input
        System.out.print("Input size: ");
        int size = s.nextInt();
        ArrayList<Integer> arr = new ArrayList<>();
        for(int i = 0; i<size; i++){
            if (i == 0){
                System.out.println("Enter Array: ");
            }
            int array = s.nextInt();
            arr.add(array);
        }
        Collections.sort(arr);

        // Process
        while(!arr.isEmpty()) {
            // System.out.print(arr + " ");
            System.out.println(arr.size());
            int min = arr.get(0);
            // for (int i : arr) {
            //     int newArray = i - min;
            //     System.out.print(newArray);
            // }
            // System.out.println();
            arr.removeIf(x -> x == min);
        }


        s.close();
    }

    public static void randomNumber(){
        Scanner s = new Scanner(System.in);
        
        Random random = new Random();
        String repeat = "";
        
        // Input
        System.out.print("Masukan Point: ");
        int point = s.nextInt();

        do {
            System.out.print("Masukan Taruhan: ");
            int taruhan = s.nextInt();
            // Perulangan Input taruhan jika lebih kecil dari point
            while (taruhan > point) {
                System.out.println("POINT ANDA TIDAK CUKUP");
                System.out.print("Masukan Taruhan: ");
                taruhan = s.nextInt();
            }
            s.nextLine();
            System.out.print("Tebak (U/D): ");
            String tebak = s.nextLine().toUpperCase();

            // Generate random number
            int randomNumber = random.nextInt(10);
            System.out.println(randomNumber);

            // Process
            if (randomNumber == 5) {
                System.out.println("S E R I !");
            }else if (randomNumber > 5 && tebak.equals("U") || randomNumber < 5 && tebak.equals("D")){
                System.out.println("You Win!");
                point += taruhan;
            } else {
                System.out.println("You Lose!");
                point -= taruhan;
            }

            // Output
            System.out.println("Point saat ini: " + point);

            System.out.print("Main lagi? (y/n): ");
            repeat = s.nextLine();

        } while (point != 0 && repeat.equalsIgnoreCase("y"));


        s.close();
    }

    public static void ojol(){
        
    }

    public static void pukis(){
        Scanner s = new Scanner(System.in);

        // Input
        System.out.print("Masukan banyak kue pukis: ");
        double pukis = s.nextDouble();

        double terigu = 115 / 15.0;
        double gula = 190 / 15.0;
        double susu = 100 / 15.0;

        System.out.println("Bahan yang dibutuhkan untuk membuat " + (int) pukis + " kue pukis:");
        System.out.println("Terigu:\t" + terigu*pukis + "gr");
        System.out.println("Gula:\t" + gula*pukis + "gr");
        System.out.println("Susu:\t" + susu*pukis + "mL");

        s.close();
    }
}
