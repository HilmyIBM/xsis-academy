import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Day05 {
    private static Scanner sc = new Scanner(System.in);

    public static int scanInt(){
    int temp;
    while (true) {
        try {
            temp = sc.nextInt();
            sc.nextLine();
            return temp;
        } catch (Exception e) {
            sc.nextLine();
            // TODO: handle exception
        }
    }
    
    // return -1;
   }
   public static float scanFloat(){
    float temp;
    while (true) {
        try {
            temp = sc.nextFloat();
            sc.nextLine();
            return temp;
        } catch (Exception e) {
            // TODO: handle exception
        }
        sc.nextLine();
    }
   }
   public static String scanStr(){
    String temp;
    while (true) {
        try {
            temp = sc.nextLine();
            return temp;
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
   }
   public static void pause(){
    System.out.print("Tekan Enter untuk melanjutkan...");
    sc.nextLine();
    System.out.printf("\n\n\n\n\n\n");
   }
    public static void main(String[] args) {
        int menu = 0;
        while (true) {
            System.out.println("1. Fungsi Pohon Faktor");
            System.out.println("2. Fungsi Membuat Persegi");
            System.out.println("3. Fungsi Power");
            System.out.println("4. Fungsi Positif Negatif");
            System.out.println("5. Fungsi Fibonaci 2");
            System.out.println("6. Fungsi Fibonaci 3");
            System.out.println("7. Fungsi Upah Golongan");
            System.out.println("8. Fungsi Peluang");
            System.out.println("9. Exit");
            System.out.print("Input: ");
            menu = scanInt();
            switch (menu) {
                case 1:
                    timeConvert();
                    pause();
                    break;
                case 2:
                    // fungsifance();
                    pause();
                    break;
                case 3:
                    // fungsiPower();
                    pause();
                    break;
                case 4:
                    // negatifPositif();
                     pause();
                    break;
                case 5:
                    // fibonaci(5);
                    pause();
                    break;
                case 6:
                    // fibonaci(3);
                    pause();
                    break;  
                case 7:
                    // fungsiGajiMingguan();
                    pause();
                    break;
                case 8:
                    // fungsiPeluang();
                    pause();
                    break;
                case 9:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Inputan Salah");
                    break;
            }
        }
    }

    public static void timeConvert(){
        System.out.print("Tanggal dengan format 12 jam: ");
        String timeBeforeConvert = scanStr();
        String result = LocalTime.parse(timeBeforeConvert, DateTimeFormatter.ofPattern("hh:mm:ssa")).format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.println("Tanggal dengan format 24 jam: " + result);
    }
}
