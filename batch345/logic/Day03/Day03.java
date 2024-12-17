package Day03;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Day03 {
    public static void main(String[] args) {
        // divByZeroHandling();

        // System.out.println(sum(100));

        // Basic OOP
        Human jack =  new Human("Jacky", "Male");
        System.out.println("Human name: " + jack.getName());
        
        Human saitama = new Human("Saitama", "Male");
        System.out.println("Human gender: " + saitama.getGender());

        Human human3 = new Human("Luffy", "Male");
        System.out.println("Human name: " + human3.getName() + ", Human gender: " + human3.getGender());

        System.out.println("Total Human Population: " + Human.count);
    }

    public static void divByZeroHandling(){
        try {
            Scanner input = new Scanner(System.in);
            int x, y;
            double z;

            System.out.print("Input X: ");
            x = input.nextInt();
            System.out.print("Input Y: ");
            y = input.nextInt();

            z= division(x, y);
            System.out.println("X / Y = " + z);

            input.close();
        } catch (ArithmeticException ae) {
            // HANDLE EXCEPTION
            System.out.println("Arithmetic process FAILED: " + ae.getMessage());
            System.err.println();
            divByZeroHandling(); // Fungsi Rekursi
        } catch (InputMismatchException ae) {
            // HANDLE EXCEPTION
            System.out.println("Input process FAILED: " + ae.getMessage());
            System.err.println();
            divByZeroHandling(); // Fungsi Rekursi
        } catch (Exception e) {
            // HANDLE EXCEPTION
            System.out.println("ALERT: " + e.getMessage());
            System.out.println("Please check your input values!");
            System.err.println();
            divByZeroHandling(); // Fungsi Rekursi
        }
    }

    public static double division(int x, int y) throws Exception {
        double result = 0.0;
        try {
            result = x / y;
        } catch (ArithmeticException ae) {
            // HANDLE EXCEPTION
            // proses tidak lanjut dan throw akan mengembalikan ke fungsi yang memanggilnya
            throw new Exception("Arithmetic process FAILED: " + ae.getMessage());
        }

        return result;
    }

    public static int sum(int k){
        System.out.print(k + ", ");
        if(k > 0){
            return k + sum(k - 1);
        } else {
            return 0;
        }
    }
}
