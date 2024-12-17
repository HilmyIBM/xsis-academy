package Day03;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Day03 {
    public static void main(String[] args) {
        // divByZeroHandling();

        // System.out.println(sum(100));

        // BASIC OOP
        Day03 objectTest = new Day03(); //karena tidak menggunakan static
        // objectTest.humanObject();
        objectTest.mamaliaObject();
        

    }

    // OOP with Human Object
    public void humanObject(){
        // Example: Calling (GETTER)
        Human human1 =  new Human("Jacky", "Male");
        System.out.println("Human name: " + human1.getName());
        
        Human human2 = new Human("Saitama", "Male");
        System.out.println("Human gender: " + human2.getGender());

        Human human3 = new Human("Luffy", "Male");
        System.out.println("Human name: " + human3.getName() + ", Human gender: " + human3.getGender());

        System.out.println("Total Human Population: " + Human.count);
        // Example calling - END

        // Example: Change the value (SETTER)
        human3.setName("Bruce Wayne");
        System.out.println("Human name: " + human3.getName() + ", Human gender: " + human3.getGender());
    }
    // Human Object - END

    // OOP with Mamalia Object
    public void mamaliaObject(){
        Sapi sapiPutih = new Sapi();
        sapiPutih.jumlahKaki = 4;
        sapiPutih.bergerak();
        sapiPutih.bersuara();

        Paus pausBiru = new Paus();
        pausBiru.jumlahKaki = 0;
        pausBiru.bergerak();

        Kelelawar kelelawarHitam = new Kelelawar();
        kelelawarHitam.jumlahKaki = 2;
        kelelawarHitam.bergerak();

        Kuda kudaCokelat = new Kuda();
        kudaCokelat.jumlahKaki = 4;
        kudaCokelat.bergerak(); 
        kudaCokelat.bersuara();
    }
    // Mamalia Object END

    // Procedure: Exception Handling
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
    // Procedure: Exception Handling - END

    // Function: Recurtion
    public static int sum(int k){
        System.out.print(k + ", ");
        if(k > 0){
            return k + sum(k - 1);
        } else {
            return 0;
        }
    }
    // Function: Recurtion - END
}
