public class Day03 {
    public static void main(String[] args) {
        pohonFaktor(12);
        squarePrinting(5);
        kelipatanNumberPrinting(7);
        minusFifteenFactor(7);
        fibonacci(7);
        fibonacci3(7);
    }

    // 1. pohon faktor
    static void pohonFaktor(int number) {
        int divisor = 0;
        int hasil = 0;
        while (number > 1) {
            divisor = findDivisor(number);
            hasil = number / divisor;
            System.out.printf("%d / %d = %d\n", number, divisor, hasil);
            number = hasil;
        }
    }

    private static int findDivisor(int number) {
        for (int i = 2; i <= number; i++) {
            if (number % i == 0) {
                return i;
            }
        }
        return number;
    }

    // 2. looping (square looping)
    static void squarePrinting(int number) {
        for (int i = 1; i <= number; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        for (int i = 1; i <= number - 2; i++) {
            System.out.print(" *");
            for (int j = 1; j <= number - 2; j++) {
                System.out.print("  ");
            }
            System.out.println("*");
        }

        for (int i = number; i >= 1; i--) {
            System.out.print(" " + i);
        }
        System.out.println();
    }

    // 3. Kelipatan 3
    static void kelipatanNumberPrinting(int number) {
        for (int i = 1; i <= number; i++) {
            if (i % 2 == 0) {
                System.out.print(" * ");
            } else {
                System.out.print((int) Math.pow(3, i));
            }
        }
        System.out.println();
    }

    // 4. Kelipatan 15
    static void minusFifteenFactor(int number) {
        int num = 5;
        for (int i = 1; i <= number; i++) {
            int value = (i % 2 == 0) ? num : -num;
            System.out.print(value + " ");
            num += 5;
        }
    }

    // 5. first fibonacci
    static void fibonacci(int number) {
        System.out.println();
        int a = 1, b = 1;
        System.out.print(a + " " + b + " ");
        for (int i = 2; i < number; i++) {
            int c = a + b;
            System.out.print(c + " ");
            a = b;
            b = c;
        }
        System.out.println();
    }

    static void fibonacci3(int number) {
        int a = 1, b = 1, c = 1;
        System.out.print(a + " " + b + " " + c + " ");
        for (int i = 3; i < number; i++) {
            int d = a + b + c;
            System.out.print(d + " ");
            a = b;
            b = c;
            c = d;
        }
        System.out.println();
    }
}
