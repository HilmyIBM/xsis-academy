import java.util.ArrayList;
import java.util.LinkedList;

public class Day03 {

    static void pohonFaktor(int n) {
        int res = 0;

        do {
            for (int i = 2; i <= n; i++) {
                if (n % i == 0) {
                    res = n / i;
                    System.out.printf("%d / %d = %d\n", n, i, res);
                    n = res;
                    break;
                }
            }
        } while (res != 1);
    }

    static void printStyle(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0, tmp = 5; j < n; j++) {
                if (i == 0) {
                    System.out.print(j+1 + " ");
                } else if (i == n-1) {
                    System.out.print(tmp + " ");
                    tmp -= 1;
                } else {
                    if (j == 0 | j == n-1) System.out.print("* ");
                    else System.out.print("  ");
                }
            }
            System.out.println();
        }
    }

    static void no3(int n) {
        int res = 3;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                System.out.print(res);
                res *= 9;
            } else System.out.print( " * ");
        }
    }

    static void no4(int n) {
        int res = 5;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) System.out.printf(" %d ", -res);
            else System.out.printf(" %d ", res);

            res += 5;
        }
    }

    static void fibb(int n, int initSize) {
        LinkedList<Integer> l = new LinkedList<>();
        int sum;

        for (int i = 0; i < initSize; i++) {
            l.add(1);
            System.out.printf("%d ", l.getLast());
        }

        for (int i = 0; i < n-initSize; i++) {
            sum = l.stream()
                    .reduce(0, Integer::sum);

            l.add(sum);
            l.removeFirst();

            System.out.printf("%d ", sum);
        }
    }

    public static void main(String[] args) {
        System.out.println("\n=========== No. 1 ============");
        pohonFaktor(12);

        System.out.println("\n=========== No. 2 ============");

        printStyle(5);

        System.out.println("\n\n=========== No. 3 ============");

        no3(7);

        System.out.println("\n\n=========== No. 4 ============");

        no4(7);

        System.out.println("\n\n=========== No. 5 ============");

        // No 5. -> initSize = 2;
        fibb(7, 2);

        System.out.println("\n\n=========== No. 6 ============");

        // No 6. -> initSize = 3;
        fibb(7, 3);
    }
}
