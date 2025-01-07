package Day05;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class _Day05 {
    public static void main(String[] args) {
        // convertTimeFormat();
        // theCost();
        // diagonalDifference();
        // candle();
        // changePosition();
    }

    public static void convertTimeFormat(){
        Scanner s = new Scanner(System.in);

        System.out.print("Masukkan WAKTU (ex: 07:05:45PM): ");
        String timeInput = s.nextLine();

        String timeConvert = LocalTime.parse(timeInput, DateTimeFormatter.ofPattern("hh:mm:ssa")).format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        System.out.println(timeConvert);

        s.close();
    }

    public static void theCost(){
        Scanner s = new Scanner(System.in);

        System.out.print("Enter Total Menu: ");
        int totalMenu = s.nextInt();
        s.nextLine();
        ArrayList<Integer> menuPrice = new ArrayList<>();
        for(int i = 0; i<totalMenu; i++){
            if (i == 0){
                System.out.println("Enter Price: ");
            }
            System.out.print("Index " + i + ": ");
            int listPrice = s.nextInt();
            menuPrice.add(listPrice);
        }
        System.out.print("Index of Alergic: ");
        int indexOfAlergic = s.nextInt();
        System.out.print("Money: ");
        int money = s.nextInt();

        int totalPrice = 0;
        for(int i=0; menuPrice.size() > i; i++){
            totalPrice += menuPrice.get(i);
        }

        if (indexOfAlergic !=0 && indexOfAlergic <= menuPrice.size()){
            totalPrice = totalPrice - menuPrice.get(indexOfAlergic);
        }

        int sisa = money - (totalPrice / 2);
        System.out.println("Yang harus dibayar: " + (totalPrice / 2));
        if(sisa != 0){
            System.out.println("Sisa uang: " + sisa);
        }else{
            System.out.println("Uang Pas");
        }
        

        s.close();
    }

    public static void diagonalDifference(){
        int [][] matrix = {
            {11, 2, 4},
            {4, 5, 6},
            {10, 8, -12}
        };

        int d1 = 0;
        int d2 = 0;

        for(int i = 0; i<matrix.length; i++){
            d1 += matrix[i][i];
            d2 += matrix[i][matrix.length-i-1];
        }

        int difference = Math.abs(d1-d2);

        System.out.println("Output = " +difference);
    }

    public static void candle(){

        try {
            Scanner s = new Scanner(System.in);

            System.out.print("Masukan tinggi lilin: ");
            String heightOfCandle = s.nextLine();

            String[] splitHeight = heightOfCandle.split(" ");
            ArrayList<Integer> candle = new ArrayList<>();
            for (String height : splitHeight) {
                candle.add(Integer.parseInt(height));
            }

            int count = 0;
            int max = 0;

            for (int i : candle){
                if (i > max) {
                    max = i;
                    count = 1;
                } else if (i == max) {
                    count++;
                }
            }

            System.out.println("Lilin yang dapat ditiup adalah " + count + " dengan tinggi " + max);


            s.close();
        } catch (Exception e) {
            // HANDLE EXCEPTION
            System.out.println("Input process FAILED: " + e.getMessage());
            candle();
        }
        
    }

    public static void changePosition(){
        Scanner s = new Scanner(System.in);

        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(5);
        arr.add(6);
        arr.add(7);
        arr.add(0);
        arr.add(1);

        System.out.print("Enter rot: ");
        int rot = s.nextInt();

        for(Integer i : arr){
            System.out.print(arr.indexOf(i) != arr.size() - 1 ? i + ", " : i);
        }

        int temp = 0;

        for (int i = 0; i < rot; i++){
            temp = arr.get(0);
            arr.remove(0);
            arr.add(temp);

            System.out.print("\nLangkah ke " + (i + 1) + " = ");
            for(Integer j : arr){
                System.out.print(arr.indexOf(j) != arr.size() - 1 ? j + ", " : j);
            }

        }

        s.close();
    }
}
