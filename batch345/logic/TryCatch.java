import java.util.InputMismatchException;
import java.util.Scanner;

public class TryCatch {
    public static void main(String[] args) {
        dividedByZero();
        recursiveSum(5);
    }

    public static void dividedByZero() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Please input the x value: ");
            int x = sc.nextInt(), y;
            double z;
            System.out.print("Please input the y value: ");
            y = sc.nextInt();
            z = division(x, y);
            System.out.println("Final value of z was: " + z);
            sc.close();
        } catch (ArithmeticException e) {
            System.err.println("Error divider" + e.getMessage());
            dividedByZero();
        } catch (InputMismatchException e) {
            System.err.println("Invalid Input: " + e.getMessage());
            dividedByZero();
        } catch (Exception e) {
            // TODO: handle general exception
            System.err.printf("General Process Error %s\n", e);
            System.err.println("Please check your input value!");
            dividedByZero();
        } finally {
            System.out.println("Ini adalah akhir dari program");
        }
    }

    // throws Exception melepar error ke parent (dari parent akan masuk ke general)
    public static double division(int x, int y) throws Exception {
        double result = 0.0;
        try {
            result = x / y;
        } catch (ArithmeticException ae) {
            throw new Exception("Aritmatic process failed: " + ae.getMessage());
        }
        return result;

    }

    public static double division (double x, double y) throws Exception {
        double result = 0.0;
        try {
            result = x / y;
        } catch (ArithmeticException ae) {
            throw new Exception("Aritmatic process failed: " + ae.getMessage());
        }
        return result;
    }

    public static int recursiveSum(int k){
        System.out.println(k);
        if(k > 0){
            return k + recursiveSum(k-1);
        } else {
            return 0;
        }
    }
}
