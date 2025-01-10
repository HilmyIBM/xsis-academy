import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Day05PR {
    static int[] bubbleSort(int[] arr) {
        int swaped = 0;
        int tmp;

        do {
            for (int i = 0; i < arr.length-1; i++) {
                if (arr[i] > arr[i+1]) {
                    tmp = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = tmp;
                    swaped++;
                }
                else swaped--;
            }

        } while (!(swaped < 0));

        return arr;
    }

    static void prime(int n) {
        int limit = 100;
        int counter = 0;
        boolean isPrime;
        int tmp;

        for (int i = 2; i < Integer.MAX_VALUE; i++) {
            isPrime = true;
            tmp = 1;

            for (int j = 1; j <= 9; j++) {
                if (i % j == 0 & i != j) ++tmp;

                if (tmp > 2) {
                    isPrime = false;
                    break;
                }
            }

            if (isPrime) {
                System.out.println(i);
                counter++;
            }

            if (counter == limit) break;
        }
    }

    public static void halloweenSale() {
        int p,d,s,m;
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("p: ");
            p = sc.nextInt();

            System.out.print("d: ");
            d = sc.nextInt();

            System.out.print("s: ");
            s = sc.nextInt();

            System.out.print("m: ");
            m = sc.nextInt();
        }

        int totalPrice = 0;
        int n = 0;

        do {
            totalPrice += Math.max(p, m);
            p -= d;

            if (totalPrice > s) break;

            n++;
        } while (totalPrice < s);

        System.out.println(n);
    }

    static void stairCase(int n) {
        for (int i = 0, h = 1, s = n-1; i < n; i++, h++, s--)
            System.out.print(
                    " ".repeat(s) +
                    "#".repeat(h) +
                    "\n");

    }

    static void SOS(String str) {
        var sos = "SOS";
        var count = 0;
        var tmp = 3;

        for (int i = 0; i < str.length(); ) {
            if (!str.substring(i, tmp).equals(sos)) count++;

            i += 3;
            tmp += 3;

        }

        System.out.println(count);
    }

    static int[] radomArray(int size, int maxSize) {
        int[] arr = new int[size];

        Random random = new Random();

        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(maxSize);
        }

        return arr;
    }

    public static void main(String[] args) {

//        prime(7);
//
//        halloweenSale();
//
//        stairCase(6);
//
//        SOS("SOS");

        int[][] test = {
                {4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,4,2,2},
                {2,5,4,1,3}
        };

        var res = bubbleSort(test[1]);

        System.out.println(Arrays.toString(res));

    }
}
