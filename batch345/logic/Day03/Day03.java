package Day03;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Day03 {
    public static void main(String[] args) {
        // exceptionHandling();

        // System.out.println(recusiveSum(10));

        // Day03 objectTest = new Day03();
        // objectTest.humanObject();
        // objectTest.mamaliaObject();

        formatAngka();
    }

    public void mamaliaObject() {
        Sapi sapiPutih = new Sapi();
        sapiPutih.jumlahKaki = 4;
        sapiPutih.isMenyusui = false;
        System.out.println("Menyusui: " + sapiPutih.isMenyusui);
        sapiPutih.bergerak();
        sapiPutih.bersuara();

        Paus pausBiru = new Paus();
        pausBiru.jumlahKaki = 0;
        System.out.println("Menyusui: " + pausBiru.isMenyusui);
        pausBiru.bergerak();

        Kelelawar kelelawarHitam = new Kelelawar();
        kelelawarHitam.jumlahKaki = 2;
        kelelawarHitam.bergerak();

        Kuda kudaCoklat = new Kuda();
        kudaCoklat.jumlahKaki = 4;
        kudaCoklat.bergerak();
        kudaCoklat.bersuara();
    }

    public void humanObject() {
        // Human jack = new Human("Jacky", "Male");
        // System.out.println("Human Name: " + jack.getName());
        Human human1 = new Human();
        System.out.println("Human Name: " + human1.getName());

        // Human saitama = new Human("Saitama","Male");
        // System.out.println("Human Gender: " + saitama.getGender());
        Human human2 = new Human();
        System.out.println("Human Gender: " + human2.getGender());

        Human human3 = new Human("Luffy",  "Male");
        System.out.println("Human Name: " + human3.getName() + "; Gender: " + human3.getGender());
        // Human human3 = new Human();
        // System.out.println("Human Name: " + human3.getName() + "; Gender: " + human3.getGender());

        System.out.println("Total Human Population: " + Human.count);

        human3.setName("Bruce Wayne");
        System.out.println("Human Name: " + human3.getName() + "; Gender: " + human3.getGender());
    }

    public static void exceptionHandling() {
        try {
            Scanner input = new Scanner(System.in);
            int x, y;
            double z;

            System.out.print("Input X: ");
            x = input.nextInt();

            System.out.print("Input Y: ");
            y = input.nextInt();

            z = division(x, y);

            System.out.println("X / Y = " + z);

            input.close();
        }
        catch (InputMismatchException e) {
            // TODO: handle InputMismatch exception
            System.out.println("Input proces failed: " + e.getMessage());
            System.out.println("Please check you input values!");

            System.err.println();
            exceptionHandling();
        }
        catch (Exception e) {
            // TODO: handle General exception
            System.out.println("General proces failed: " + e.getMessage());

            System.err.println();
            exceptionHandling();
        }
        finally{
            System.out.println("Akhirnya selesai juga.");
        }
    }

    public static double division(int x, int y) throws Exception {
        double result = 0.0;

        try {
            result = x / y;
        }
        catch (ArithmeticException ae) {
            // TODO: handle Arithmetic exception
            throw new Exception("Arithmatic proces failed: " + ae.getMessage());
        }

        return result;
    }

    public static double division(double x, double y) throws Exception {
        double result = 0.0;

        try {
            result = x / y;
        }
        catch (ArithmeticException ae) {
            // TODO: handle Arithmetic exception
            throw new Exception("Arithmatic proces failed: " + ae.getMessage());
        }

        return result;
    }

    public static int division() {
        return 10/0;
    }

    public static int recusiveSum(int k) {
        System.out.print(k + ", ");

        if (k > 0)
            return k + recusiveSum(k-1);
        else
            return 0;
    }

    public static void formatAngka() {
        double pi = 22/7.0;
        long uang = 1235678901;
        String output =
            String.format("%1$s %2$.2f\n%3$s %4$,d", "Nilai pi adalah:", pi, "Uang yang ada sebesar:", uang);
            //output: Nilai pi adalah: 3.1429
        
        System.out.println(output);
    }
}
