package Day05;

import java.util.Scanner;

public class _PRDay05 {
    public static void main(String[] args) {
        // bubbleSort();
        // prima();
        gameOnline();
    }

    public static void bubbleSort(){
        int[] arr = {2, 5, 4, 1, 3};
        int temp = 0;

        for (int i : arr){
            System.out.print(i + ", ");
        }

        for (int i = 0; i < arr.length; i++){
            for(int j = 1; j < (arr.length-i); j++){
                if(arr[j-1] > arr[j]){
                    temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                    System.out.println();
                    for (int k : arr){
                        System.out.print(k + ", ");
                    }
                }
            }
        }

        System.out.println();

        for (int i : arr){
            System.out.print(i + ", ");
        }

    }


    public static void prima(){
        Scanner s = new Scanner(System.in);

        System.out.print("Input Angka: ");
        int angka = s.nextInt();

        int count = 0;
        for(int i = 2; i <= angka; i++){
            boolean isPrime = true;
            for(int j = 2; j < i; j++){
                if (i % j == 0) {
                    isPrime = false;
                    // System.out.println(i);
                }
            }
            if (isPrime) {
                count++;
                if(count <= 100){
                    System.out.print(i + ", ");
                }
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
        for (int i = 0; p > i; i++){
            if (i != 0){
                p -= d;
            }
            System.out.print(p + "+");
            count++;
            total += p;
            System.out.print(total + " ");
        }

        // System.out.print(total);

        in.close();
    }
}
