import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day07 {
    public static Scanner sc = new Scanner(System.in);
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
            System.out.println("1. Menghitung Lembah");
            System.out.println("2. Password Encryption");
            System.out.println("3. Mencari Element TInggi");
            System.out.println("4. AIUEO");
            System.out.println("5. Password Check");
            System.out.println("6. SOS");
            System.out.println("7. * Adenia *");
            System.out.println("8. Obstacle");
            System.out.println("9. Exit");
            System.out.print("Input: ");
            menu = scanInt();
            switch (menu) {
                case 1:
                    woood();
                    pause();
                    break;
                case 2:
                    // encrypt();
                    pause();
                    break;
                case 3:
                    // elementTinggi();
                    pause();
                    break;
                case 4:
                    // aiueo();
                     pause();
                    break;
                case 5:
                    // password();
                    pause();
                    break;
                case 6:
                    // sos();
                    pause();
                    break;  
                case 7:
                    // adenia();
                    pause();
                    break;
                case 8:
                    // obstacle();
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
    public static void woood(){
        System.out.print("Total Kayu: ");
        int total = scanInt();
        int[] kayu = new int[total+1];
        System.out.print("Kayu: ");
        for(int i = 0;i<total;i++){
            kayu[i] = sc.nextInt();
        }
        sc.nextLine();
        Arrays.sort(kayu);
        for(int i = 1;i<=total;){
            int pengurang = kayu[i];
            int jagajagaindex = i;
            System.out.println("total: " + (total-i+1));
            for(int j =jagajagaindex;j<=total;j++){
                kayu[j] -= pengurang;
                if(kayu[j] == 0){
                    i++;
                }
            }
        }
        //2 2 4 4 5 8 -> 0 0 2 2 3 6 -> 0 0 0 0 1 4 -> 0 0 0 0 0 3 -> 0 0 0 0 0
    }
    public static void guessNumber(){
        System.out.println("Point: ");
        int poin = scanInt();
        while (true) {
            
        }
    }
}
