package Day08;

import java.util.Scanner;

public class _PRDay08 {
    public static void main(String[] args) {
        // beautifulDays();
        esLoli();
    }

    public static void beautifulDays(){
        Scanner s = new Scanner(System.in);

        // Input
        System.out.print("Masukan batas bawah (i): ");
        int i = s.nextInt();
        System.out.print("Masukan batas atas (j): ");
        int j = s.nextInt();
        System.out.print("Masukan pembagi (k): ");
        int k = s.nextInt();

        double result = 0;
        int found = 0;

        for (int num = i; num <= j; num++) {
            int number = num;
            int reversed = 0;
            
            // Reverse
            while (number > 0) {
                int remainder = number % 10;
                reversed = (reversed * 10) + remainder;
                number /= 10;
            }

            result = Math.abs(num - reversed) / (double) k;

            if (result == (int) result) {
                found++;
                System.out.print(found == 1 ? num : "," + num);
            }
            
            // System.out.println("Reversed number of " + num + " is: " + reversed);
        }

        s.close();
    }

    public static void esLoli(){
        Scanner s = new Scanner(System.in);

        System.out.print("Input jumlah uang: ");
        int money = s.nextInt();

        int jumlah = money / 1000;
        int bonus = 0;

        if(jumlah >= 5){
            for(int i = 5; i <= jumlah; i+=5){
                bonus++;
            }
        }

        int totalEs = jumlah + bonus;

        System.out.println("Total Es = " + totalEs);
        s.close();
    }

}
