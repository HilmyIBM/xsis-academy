package Day05;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class _Day05 {
    public static void main(String[] args) {
        // convertTimeFormat();
        // candle();
    }

    public static void convertTimeFormat(){
        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan WAKTU (ex: 07:05:45PM): ");
        String timeInput = input.nextLine();

        String timeConvert = LocalTime.parse(timeInput, DateTimeFormatter.ofPattern("hh:mm:ssa")).format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        System.out.println(timeConvert);

        input.close();
    }

    public static void theCost(){

    }

    public static void differencial(){

    }

    public static void candle(){

        try {
            Scanner input = new Scanner(System.in);

            System.out.print("Masukan tinggi lilin: ");
            String heightOfCandle = input.nextLine();

            String[] splitHeight = heightOfCandle.split(" ");
            ArrayList<Integer> candle = new ArrayList<>();
            for (String height : splitHeight) {
                candle.add(Integer.parseInt(height.trim()));
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


            input.close();
        } catch (Exception e) {
            // HANDLE EXCEPTION
            System.out.println("Input process FAILED: " + e.getMessage());
            candle();
        }
        
    }

    public static void changePosition(){

    }
}
