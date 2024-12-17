import java.util.InputMismatchException;
import java.util.Scanner;

public class Day03 {

    public static void main(String[] args){
        //exceptionHandling();

        Day03 objectTest = new Day03();
        objectTest.mamaliaObject();
        // objectTest.humanObject();
       
    }

    public static void exceptionHandling(){
        try {
            Scanner input = new Scanner (System.in);
            double z;
            int x, y;
            
            System.out.println("Input X: ");
            x = input.nextInt();

            System.out.println("Input Y: ");
            y = input.nextInt();

            z = division(x, y);
            System.out.println("The result is " + z);
            input.close();

        } catch (ArithmeticException ae){//check error secara spesifik sebelum ke general exception
            // TODO: handle exception
            System.out.println("Arithmatic process failed: " + ae.getMessage());
            System.out.println();
            exceptionHandling();
        }
        catch (InputMismatchException ime){
            // TODO: handle exception
            System.out.println("Input process failed: " + ime.getMessage());
            System.out.println();
            exceptionHandling();
        }
        
        catch (Exception e) {
            // TODO: handle exception
            System.out.println("General process failed: " + e.getMessage()); //pindahin baris alt +  arrow up/down
            System.out.println("Please check your input values!");
            System.out.println();
            exceptionHandling();
        }
    }

    public static double division(int x, int y) throws Exception{
        double result = 0.0;
        try {
            result = x/y;
        } catch (ArithmeticException ae) {
            // TODO: handle exception
            // System.out.println("Arithmatic process failed: " + ae.getMessage());
            // System.out.println("Please check your input values!");
            throw new Exception("Arithmatic process failed: " + ae.getMessage());
        }
        return result;
    }

    public static double division(double x, double y) throws Exception { //satu function bisa banyak atas nama yang sama tapi beda parameter, namanya overload
        double result = 0.0;
        try {
            result = x/y;
        } catch (ArithmeticException ae) {
            // TODO: handle exception
            // System.out.println("Arithmatic process failed: " + ae.getMessage());
            // System.out.println("Please check your input values!");
            throw new Exception("Arithmatic process failed: " + ae.getMessage());
        }
        return result;
    
    }

    public void humanObject(){
        Human jack = new Human("Jack", "Male");

        Human saitama = new Human("Saitama", "Male");

        System.out.println(jack.getName());
        System.out.println(jack.getGender());
        System.out.println(saitama.getName());
        System.out.println(saitama.getGender());

    }

    public void mamaliaObject(){
        Sapi sapiPutih = new Sapi();
        sapiPutih.jumlahKaki = 4;
        sapiPutih.bergerak();
        sapiPutih.bersuara();

        Paus pausBiru = new Paus();
        pausBiru.jumlahKaki = 0;
        pausBiru.bergerak();
        
    }
}
