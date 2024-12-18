import java.util.Scanner;

public class Day03 {
  public static void main(String[] args) {
    Day03 objectTest = new Day03();
    objectTest.mamaliaObject();
  }

  public void mamaliaObject() {
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

    Kuda kudaCoklat = new Kuda();
    kudaCoklat.jumlahKaki = 4;
    kudaCoklat.bergerak();
    kudaCoklat.bersuara();  
  }

  public static void divByZeroHandling() {
    try {
      Scanner input = new Scanner(System.in);
      int x=10, y=10, z;
      System.out.print("Input X : ");
      x = Integer.parseInt(input.nextLine());
      System.out.print("Input Y : ");
      y = Integer.parseInt(input.nextLine());

      z = (int) division(x, y);
      System.out.println("Result : " + z);

    } catch (Exception e) {
      System.out.println("Alert: " + e.getMessage());
      divByZeroHandling();
    }
  }

  public static double division(int x, int y) throws Exception {
    double result = 0.0;
  
    try {
      result = x/y;
    } catch (ArithmeticException ae) {
      // TODO: handle exception
      throw new Exception("Arithmetic proccess failed: " + ae.getMessage());
    }

    return result;
  }

  public static int division(double x, double y) throws Exception {
    int result = 0;
  
    try {
/*       result = x/y;
 */    } catch (ArithmeticException ae) {
      // TODO: handle exception
      throw new Exception("Arithmetic proccess failed: " + ae.getMessage());
    }

    return result;
  }
}
