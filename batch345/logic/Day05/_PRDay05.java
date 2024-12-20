package Day05;

import java.util.Scanner;

public class _PRDay05 {
    public static void main(String[] args) {
        // bubbleSort();
        // prima();
        // gameOnline();
        starCase();
        // sOs();
    }

    public static void bubbleSort(){
        Scanner s = new Scanner(System.in);

        System.out.print("Masukan jumlah langkah: ");
        int step = s.nextInt();
        
        int[] arr = {2, 5, 4, 1, 3};
        int temp = 0;

        for (int i=0; i < arr.length; i++) {
            System.out.print(i == arr.length - 1 ? arr[i] : arr[i] + ",");
        }

        System.out.println();

        for (int i = 0; i < step; i++){
            for(int j = 1; j < (arr.length-i); j++){
                if(arr[j-1] > arr[j]){
                    temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }
            }
            System.out.print("Langkah " + (i + 1) + ": ");
            for (int k=0; k < arr.length; k++) {
                System.out.print(k == arr.length - 1 ? arr[k] : arr[k] + ",");
            }
            System.out.println();
        }

        s.close();
    }

    public static void prima(){
        Scanner s = new Scanner(System.in);

        System.out.print("Input Angka: ");
        int angka = s.nextInt();

        int count = 0;
        for(int i = 2; i < angka; i++){
            boolean isPrime = true;
            for(int j = 2; j < i; j++){
                if (i % j == 0) {
                    isPrime = false;
                    // System.out.println(i);
                }
            }
            if (isPrime && count <= 100) {
                System.out.print( i == 2 ? i : ", " + i);
                count++;
            }
        }


        s.close();
    }

    public static void gameOnline(){
        Scanner in = new Scanner(System.in);

        System.out.print("Price: ");
        int p = in.nextInt();
        System.out.print("Discount: ");
        int d = in.nextInt();
        System.out.print("Minimum: ");
        int m = in.nextInt();
        System.out.print("Spend: ");
        int s = in.nextInt();

        int total = 0;
        int count = 0;

        while(total + p <= s){
            total += p;
            count++;
            if (p>m) {
                p -= d;
                if (p < m) {
                    p = m;
                }
                // System.out.print(p);
            }
        }

        System.out.println(count + " video game, total spend: " + total);


        // System.out.print(total);

        in.close();
    }

    public static void  starCase(){
        Scanner s = new Scanner(System.in);
        int start = 1;

        System.out.print("Input: ");
        int star = s.nextInt();

        int space = star;

        while(start <= star){
            // System.out.println(" ".repeat(space--) + "*".repeat(start));
            System.out.println(" ".repeat(space-1)+"*".repeat(start-1) + "*" +"*".repeat(start-1) + " ".repeat(space-1));
            start++;
            space--;
        }

        s.close();
    }

    public static void sOs(){
        Scanner s = new Scanner(System.in);

        System.out.print("Input: ");
        String sos = s.nextLine();

        int count = 0;

        for(int i = 0; i < sos.length(); i+=3){
            String words = sos.substring(i, i+3).toUpperCase();
            System.out.println(words);
            if (!"SOS".equals(words)){
                count++;
            }
        }

        System.out.println("Total Sinyal salah: "+ count);

        s.close();
    }
}
